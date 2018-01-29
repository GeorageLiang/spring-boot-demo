package com.spittr.redis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.spittr.config.jedis.MomentJedisConfig;
import com.spittr.model.Moment;
import com.spittr.model.MomentAdditional;
import com.spittr.utils.ConverUtil;
import com.spittr.utils.serializer.GsonSerializerHelper;
import com.spittr.utils.serializer.MomentAdditionalSerializer;
import com.theft.code.utils.string.StringUtil;

import redis.clients.jedis.Jedis;

/**
 * 动态相关redis操作
 * 
 * @author chufei
 * @date 2017年7月5日
 */
@Repository
public class MomentRedisClient extends AbstractRedisClient {

	private static final Logger LOG = Logger.getLogger(MomentRedisClient.class);

	private static final String MOMENT_KEY = "moment_%s";

	private Jedis getJedisIntance() {
		return getJedisIntanceByResourceName(MomentJedisConfig.RESOURCE_NAME);
	}
	
	/**
	 * 保存动态信息到redis
	 * 
	 * @param momentId
	 *            动态id
	 * @param userId
	 *            发布人id
	 * @param content
	 *            发布文本内容
	 * @param replyCount
	 *            回复数
	 * @param isDisplay
	 *            是否对外显示
	 * @param isDelete
	 *            是否删除
	 * @param memo
	 *            备注信息
	 * @param momentAdditionals
	 *            附加信息列表
	 * @param createdTime
	 *            创建时间
	 * @param updatedTime
	 *            最后操作时间
	 */
	public void saveMoment(long momentId, long userId, String content, int replyCount, int isDisplay, int isDelete,
			String memo, List<MomentAdditional> momentAdditionals, Date createdTime, Date updatedTime) {
		Jedis jedis = null;
		try {
			jedis = getJedisIntance();
			String key = String.format(MOMENT_KEY, momentId);
			Map<String, String> hash = new HashMap<String, String>(16);
			hash.put("id", String.valueOf(momentId));
			hash.put("userId", String.valueOf(userId));
			hash.put("content", content);
			hash.put("replyCount", String.valueOf(replyCount));
			hash.put("isDisplay", String.valueOf(isDisplay));
			hash.put("isDelete", String.valueOf(isDelete));
			if (!StringUtil.strIsNull(memo)) {
				hash.put("memo", memo);
			}
			if (!momentAdditionals.isEmpty()) {
				Gson gson = GsonSerializerHelper.addSerializer(MomentAdditional.class, new MomentAdditionalSerializer());
				hash.put("momentAdditionals", gson.toJson(momentAdditionals));
			}
			hash.put("createdTime", String.valueOf(createdTime.getTime()));
			hash.put("updatedTime", String.valueOf(updatedTime.getTime()));
			jedis.hmset(key, hash);
			// 缓存动态信息7天
			jedis.expire(key, 7 * 24 * 60 * 60);
		} catch (Exception e) {
			LOG.error("error save moment_" + momentId, e);
		} finally {
			closeJedis(jedis);
		}
	}

	/**
	 * 获取动态信息缓存
	 * 
	 * @param momentId
	 *            动态id
	 * @return 返回动态信息hash
	 */
	public Moment getMomentByMomentId(long momentId) {
		Jedis jedis = null;
		Map<String, String> moment = new HashMap<String, String>(16);
		try {
			jedis = getJedisIntance();
			String key = String.format(MOMENT_KEY, momentId);
			moment = jedis.hgetAll(key);
			return ConverUtil.map2Object(moment, Moment.class);
		} catch (Exception e) {
			LOG.error("error get moment_" + momentId, e);
		} finally {
			closeJedis(jedis);
		}

		return null;
	}

	/**
	 * 获取动态信息某个域值
	 * 
	 * @param momentId
	 *            动态id
	 * @param field
	 *            域
	 * @return 返回域值
	 */
	public String getMomentFieldByMomentId(long momentId, String field) {
		Jedis jedis = null;
		try {
			jedis = getJedisIntance();
			String key = String.format(MOMENT_KEY, momentId);
			return jedis.hget(key, field);
		} catch (Exception e) {
			LOG.error("error get moment field, momentId: " + momentId + ", field: " + field, e);
		} finally {
			closeJedis(jedis);
		}
		return null;
	}

	/**
	 * 设置动态某个域值
	 * 
	 * @param momentId
	 *            动态id
	 * @param field
	 *            设置的域
	 * @param value
	 *            设置的值
	 */
	public void setMomentField(long momentId, String field, String value) {
		Jedis jedis = null;
		try {
			jedis = getJedisIntance();
			String key = String.format(MOMENT_KEY, momentId);
			jedis.hset(key, field, value);
		} catch (Exception e) {
			LOG.error("error set moment field, momentId: " + momentId + ", field: " + field + ", value: " + value, e);
		} finally {
			closeJedis(jedis);
		}
	}

}
