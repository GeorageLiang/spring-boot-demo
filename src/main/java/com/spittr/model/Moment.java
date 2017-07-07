package com.spittr.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 动态实体对象
 * 
 * @param id
 *            动态id
 * @param userId
 *            动态发布人id
 * @param content
 *            动态文本内容
 * @param replyCount
 *            动态回复数
 * @param isDisplay
 *            是否对外显示，0-不显示，1-显示
 * @param isDelete
 *            是否删除，0-不删除，1-删除
 * @param memo
 *            备注信息
 * @param createdTime
 *            创建时间
 * @param updatedTime
 *            最后操作时间
 * @param momentAdditionals
 *            动态附加信息
 * 
 * @author chufei
 * @date 2017年7月4日
 */
public class Moment implements Serializable {

	private static final long serialVersionUID = -2970314427750382628L;

	private long id;

	private long userId;

	private String content;

	private int replyCount;

	private int isDisplay;

	private int isDelete;

	private String memo;

	private Date createdTime;

	private Date updatedTime;

	private List<MomentAdditional> momentAdditionals;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public int getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(int isDisplay) {
		this.isDisplay = isDisplay;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public List<MomentAdditional> getMomentAdditionals() {
		return momentAdditionals;
	}

	public void setMomentAdditionals(List<MomentAdditional> momentAdditionals) {
		this.momentAdditionals = momentAdditionals;
	}

}
