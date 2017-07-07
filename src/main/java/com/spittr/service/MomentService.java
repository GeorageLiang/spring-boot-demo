package com.spittr.service;

import com.spittr.model.Moment;

/**
 * 用户动态相关业务操作
 * 
 * @author chufei
 * @date 2017年7月3日
 */
public interface MomentService {

	/**
	 * 发布动态
	 * 
	 * @param userId
	 *            发布人id
	 * @param content
	 *            发布文本内容
	 * @param isDisplay
	 *            是否对外显示,0-不显示,1-显示
	 * @param additionalType
	 *            附加信息类型
	 * @param additionalIndex
	 *            附加信息排序索引组
	 * @param additionalUrl
	 *            附加信息地址组
	 * @return 返回动态id
	 */
	public long addMoment(long userId, String content, int isDisplay, int additionalType, int[] additionalIndex,
			String[] additionalUrl);

	/**
	 * 设置动态是否私密，如果之前是显示状态，则设置为不显示状态；反之亦然
	 * 
	 * @param momentId
	 *            动态id
	 * @return 返回设置结果，0表示设置为不显示，1表示为设置显示，返回-1表示设置失败
	 */
	public int displayMoment(long momentId);

	/**
	 * 根据动态id获取动态信息
	 * 
	 * @param momentId
	 *            动态id
	 * @return 返回动态信息对象
	 */
	public Moment getMomentByMomentId(long momentId);

}
