package com.spittr.mapper.master;

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
	 * @param params 入参集合，具体如下
	 * <p>momentId: 动态id</p>
	 * <p>userId: 发布人id</p>
	 * <p>type: 类型，1-图片，2-视频，3-音频</p>
	 * <p>index: 排序索引</p>
	 * <p>url: 附加资源地址</p>
	 * <p>createdTime: 发布时间</p>
	 * <p>updatedTime: 最后操作时间</p>
	 * @return 返回动态附加信息主键id</p>
	 */
	@Insert(value = "insert into `moments_additional` (`moment_id`, `user_id`, `type`, `index`, `url`, `created_time`, `updated_time`) "
			+ "values (#{momentId}, #{userId}, #{type}, #{index}, #{url}, #{createdTime}, #{updatedTime})")
	@SelectKey(before = false, keyProperty = "id", resultType = Long.class, statement = { "SELECT LAST_INSERT_ID()" })
	public void addMomentAdditional(Map<String, Object> params);

	/**
	 * 根据动态id获取动态附加信息
	 * 
	 * @param momentId
	 *            动态id
	 * @return 返回动态附加对象
	 */
	@Select(value = "select " + "`id` as id, " + "`moment_id` as momentId, " + "`user_id` as userId, "
			+ "`type` as `type`, " + "`index` as `index`, " + "`url` as url, " + "`created_time` as createdTime, "
			+ "`updated_time` as updatedTime " + "from `moments_additional` where `moment_id` = #{momentId}")
	public List<MomentAdditional> getAdditionalByMomentId(long momentId);

}
