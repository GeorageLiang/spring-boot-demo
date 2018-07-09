package com.spittr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spittr.constant.CodeConstant;
import com.spittr.exception.SpittrException;
import com.spittr.mapper.master.MomentAdditionalMapper;
import com.spittr.mapper.master.MomentMapper;
import com.spittr.model.Moment;
import com.spittr.model.MomentAdditional;
import com.spittr.redis.MomentRedisClient;
import com.spittr.service.MomentService;

/**
 * 用户动态相关业务service实现
 * @author chufei
 * 2018年1月17日
 */
@Service
public class MomentServiceImpl implements MomentService {

	private static final Logger LOG = LoggerFactory.getLogger(MomentServiceImpl.class);

	@Autowired
	private MomentMapper momentMapper;

	@Autowired
	private MomentAdditionalMapper momentAdditionalMapper;

	@Autowired
	private MomentRedisClient momentRedisClient;

	@Override
	public long addMoment(long userId, String content, int isDisplay, int additionalType, int[] additionalIndex,
			String[] additionalUrl) {
		Date now = new Date();
		Map<String, Object> params = new HashMap<String, Object>(16);
		params.put("userId", userId);
		params.put("content", content);
		params.put("isDisplay", isDisplay);
		params.put("createdTime", now);
		params.put("updatedTime", now);
		try {
			momentMapper.addMoment(params);
			long momentId = (long) params.get("id");
			if (momentId > 0) {
				List<MomentAdditional> additionals = new ArrayList<MomentAdditional>();
				// 如果有附加信息
				if (additionalType > 0) {
					params.put("momentId", momentId);
					params.put("type", additionalType);
					for (int i = 0; i < additionalIndex.length; i++) {
						params.put("index", i + 1);
						params.put("url", additionalUrl[i]);
						momentAdditionalMapper.addMomentAdditional(params);
						additionals.add(new MomentAdditional(momentId, userId, additionalType, i + 1, additionalUrl[i],
								now, now));
					}
				}

				// 将动态信息缓存redis
				momentRedisClient.saveMoment(momentId, userId, content, 0, isDisplay, 0, null, additionals, now, now);

				return momentId;
			}
		} catch (Exception e) {
			LOG.error("error execute momentMapper.addMoment, moment-param: " + params.toString(), e);
			throw new SpittrException("error execute momentMapper.addMoment", e, CodeConstant.EXCEPTION_SERVICE);
		}
		return 0;
	}

	@Override
	public int displayMoment(long momentId) {
		// 获取动态信息
		Moment moment = getMomentByMomentId(momentId);
		if (moment == null) {
			throw new SpittrException("there is no moment, can not display, momentId, " + momentId, "2005");
		}

		// 即将设置的显示状态
		int display = moment.getIsDisplay() == 0 ? 1 : 0;

		try {
			Map<String, Object> params = new HashMap<String, Object>(16);
			params.put("id", momentId);
			params.put("isDisplay", display);
			params.put("updatedTime", new Date());
			int row = momentMapper.displayMoment(params);
			if (row > 0) {
				momentRedisClient.setMomentField(momentId, "isDisplay", display + "");

				return display;
			}
		} catch (Exception e) {
			LOG.error("error execute momentMapper.displayMoment, momentId: " + momentId, e);
			throw new SpittrException("error execute momentMapper.displayMoment", e, CodeConstant.EXCEPTION_SERVICE);
		}

		return -1;
	}

	@Override
	public Moment getMomentByMomentId(long momentId) {
		try {
			Moment moment = momentRedisClient.getMomentByMomentId(momentId);
			// 如果redis没有缓存数据
			if (moment == null) {
				moment = momentMapper.getMomentById(momentId);
				if (moment != null) {
					// 根据动态id获取附加信息
					List<MomentAdditional> additionals = momentAdditionalMapper.getAdditionalByMomentId(momentId);
					moment.setMomentAdditionals(additionals);

					// 缓存到redis
					momentRedisClient.saveMoment(momentId, moment.getUserId(), moment.getContent(),
							moment.getReplyCount(), moment.getIsDisplay(), moment.getIsDelete(), moment.getMemo(),
							additionals, moment.getCreatedTime(), moment.getUpdatedTime());

					return moment;
				}

				return null;
			}

			return moment;
		} catch (Exception e) {
			LOG.error("error execute momentMapper.getMomentById, momentId: " + momentId, e);
			throw new SpittrException("error execute momentMapper.getMomentById", e, CodeConstant.EXCEPTION_SERVICE);
		}
	}

}
