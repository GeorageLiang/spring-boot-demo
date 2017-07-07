package com.spittr.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 动态附加信息实体对象
 * 
 * @param id
 *            动态附加信息id
 * @param momentId
 *            动态id
 * @param userId
 *            动态发布人id
 * @param type
 *            类型，1-图片，2-视频，3-音频
 * @param index
 *            排序索引，从1开始递增
 * @param url
 *            附加资源地址
 * @param createdTime
 *            创建时间
 * @param updatedTime
 *            最后操作时间
 * 
 * @author chufei
 * @date 2017年7月4日
 */
public class MomentAdditional implements Serializable {

	private static final long serialVersionUID = 7658903820654747353L;

	private long id;

	private long momentId;

	private long userId;

	private int type;

	private int index;

	private String url;

	private Date createdTime;

	private Date updatedTime;

	public MomentAdditional() {
	}

	public MomentAdditional(long momentId, long userId, int type, int index, String url, Date createdTime,
			Date updatedTime) {
		this.momentId = momentId;
		this.userId = userId;
		this.type = type;
		this.index = index;
		this.url = url;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMomentId() {
		return momentId;
	}

	public void setMomentId(long momentId) {
		this.momentId = momentId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

}
