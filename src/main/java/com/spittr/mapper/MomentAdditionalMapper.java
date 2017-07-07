package com.spittr.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.spittr.model.MomentAdditional;

/**
 * 动态附加信息相关数据操作
 * 
 * @author chufei
 * @date 2017年7月5日
 */
@Mapper
public interface MomentAdditionalMapper {

	/**
	 * 添加动态附加信息
	 * 
	 * @param momentId
	 *            动态id
	 * @param userId
	 *            发布人id
	 * @param type
	 *            类型，1-图片，2-视频，3-音频
	 * @param index
	 *            排序索引
	 * @param url
	 *            附加资源地址
	 * @param createdTime
	 *            发布时间
	 * @param updatedTime
	 *            最后操作时间
	 * @return 返回动态附加信息主键id
	 */
	@Insert(value = "insert into `moments_additional` (`moment_id`, `user_id`, `type`, `index`, `url`, `created_time`, `updated_time`) "
			+ "values (#{momentId}, #{userId}, #{type}, #{index}, #{url}, #{createdTime}, #{updatedTime})")
	@SelectKey(before = false, keyProperty = "id", resultType = Long.class, statement = { "SELECT LAST_INSERT_ID()" })
	public void addMomentAdditional(Map<String, Object> params);

	/**
	 * 根据动态id获取动态附加信息
	 * 
	 * @param id
	 *            动态id
	 * @return 返回动态附加对象
	 */
	@Select(value = "select " + "`id` as id, " + "`moment_id` as momentId, " + "`user_id` as userId, "
			+ "`type` as `type`, " + "`index` as `index`, " + "`url` as url, " + "`created_time` as createdTime, "
			+ "`updated_time` as updatedTime " + "from `moments_additional` where `moment_id` = #{momentId}")
	public List<MomentAdditional> getAdditionalByMomentId(long momentId);

}
