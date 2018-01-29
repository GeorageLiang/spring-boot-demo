package com.spittr.mapper.master;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.spittr.model.Moment;

/**
 * 动态相关数据操作
 * 
 * @author chufei
 * @date 2017年7月4日
 */
@Mapper
public interface MomentMapper {

	/**
	 * 添加动态
	 * 
	 * @param params 入参集合，具体如下
	 * <p>userId: 发布人id</p>
	 * <p>content: 发布文本内容</p>
	 * <p>is_display: 是否对外显示,0-不显示,1-显示</p>
	 * <p>createdTime: 发布时间</p>
	 * <p>updatedTime: 最后操作时间</p>
	 * @return 返回动态id
	 */
	@Insert(value = "insert into `moments` (`user_id`, `content`, `is_display`, `created_time`, `updated_time`) "
			+ "values (#{userId}, #{content}, #{isDisplay}, #{createdTime}, #{updatedTime})")
	@SelectKey(before = false, keyProperty = "id", resultType = Long.class, statement = { "SELECT LAST_INSERT_ID()" })
	public void addMoment(Map<String, Object> params);

	/**
	 * 设置动态对外显示状态
	 * 
	 * @param params 入参集合，具体如下
	 * <p>isDisplay: 设置是否对外显示，0-不显示，1-显示</p>
	 * <p>updatedTime: 最后操作时间</p>
	 * <p>id: 动态id</p>
	 * @return 返回操作行数
	 */
	@Update(value = "update `moments` set `is_display` = #{isDisplay}, `updated_time` = #{updatedTime} where `id` = #{id}")
	public int displayMoment(Map<String, Object> params);

	/**
	 * 逻辑删除动态
	 * 
	 * @param id
	 *            动态id
	 * @return 返回操作行数
	 */
	@Update(value = "update `moments` set `is_delete` = 1, `updated_time` = now() where `id` = #{id}")
	public int deleteMoment(long id);

	/**
	 * 根据动态id获取动态信息
	 * 
	 * @param id
	 *            动态id
	 * @return 返回动态对象
	 */
	@Select(value = "select " + "`id` as id, " + "`user_id` as userId, " + "`content` as content, "
			+ "`reply_count` as replyCount, " + "`is_display` as isDisplay, " + "`is_delete` as isDelete, "
			+ "`memo` as memo, " + "`created_time` as createdTime, " + "`updated_time` as updatedTime "
			+ "from `moments` where `id` = #{id}")
	public Moment getMomentById(long id);

}
