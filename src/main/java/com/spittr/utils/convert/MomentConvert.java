package com.spittr.utils.convert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.spittr.model.Moment;
import com.spittr.model.MomentAdditional;
import com.theft.code.utils.string.StringUtil;

/**
 * 动态对象模型转换
 * 
 * @author chufei
 * @date 2017年7月6日
 */
public class MomentConvert {

	/**
	 * Map对象转换Moment对象
	 * 
	 * @param map
	 * @return
	 */
	public static Moment map2Moment(Map<String, String> map) {
		Moment moment = new Moment();
		if (map.get("momentId") != null) {
			moment.setId(Long.parseLong(map.get("momentId")));
		}
		if (map.get("userId") != null) {
			moment.setUserId(Long.parseLong(map.get("userId")));
		}
		if (map.get("content") != null) {
			moment.setContent(map.get("content"));
		}
		if (map.get("replyCount") != null) {
			moment.setReplyCount(Integer.parseInt(map.get("replyCount")));
		}
		if (map.get("isDisplay") != null) {
			moment.setIsDisplay(Integer.parseInt(map.get("isDisplay")));
		}
		if (map.get("isDelete") != null) {
			moment.setIsDelete(Integer.parseInt(map.get("isDelete")));
		}
		if (map.get("memo") != null) {
			moment.setMemo(map.get("memo"));
		}
		String momentAdditionals = map.get("momentAdditionals");
		if (!StringUtil.strIsNull("momentAdditionals")) {
			List<MomentAdditional> additionals = new Gson().fromJson(momentAdditionals,
					new TypeToken<ArrayList<MomentAdditional>>(){}.getType());
			moment.setMomentAdditionals(additionals);
		}
		if (map.get("createdTime") != null) {
			moment.setCreatedTime(new Date(Long.parseLong(map.get("createdTime"))));
		}
		if (map.get("updatedTime") != null) {
			moment.setUpdatedTime(new Date(Long.parseLong(map.get("updatedTime"))));
		}
		return moment;
	}

	/**
	 * Moment对象转换Json对象
	 * 
	 * @param moment
	 * @return
	 */
	public static JsonObject moment2Json(Moment moment) {
		JsonObject jsonObject = new JsonObject();
		if (moment != null) {
			jsonObject.addProperty("momentId", moment.getId());
			jsonObject.addProperty("userId", moment.getUserId());
			if (moment.getContent() != null) {
				jsonObject.addProperty("content", moment.getContent());
			}
			jsonObject.addProperty("replyCount", moment.getReplyCount());
			jsonObject.addProperty("isDisplay", moment.getIsDisplay());
			jsonObject.addProperty("isDelete", moment.getIsDelete());
			if (moment.getMemo() != null) {
				jsonObject.addProperty("memo", moment.getMemo());
			}
			List<MomentAdditional> additionals = moment.getMomentAdditionals();
			if (additionals != null && !additionals.isEmpty()) {
				JsonArray jsonArray = new JsonArray();
				for (MomentAdditional momentAdditional : additionals) {
					JsonObject object = new JsonObject();
					object.addProperty("type", momentAdditional.getType());
					object.addProperty("index", momentAdditional.getIndex());
					object.addProperty("url", momentAdditional.getUrl());
					jsonArray.add(object);
				}
				jsonObject.add("momentAdditionals", jsonArray);
			}
			if (moment.getCreatedTime() != null) {
				jsonObject.addProperty("createdTime", moment.getCreatedTime().getTime());
			}
			if (moment.getUpdatedTime() != null) {
				jsonObject.addProperty("updatedTime", moment.getUpdatedTime().getTime());
			}
		}
		return jsonObject;
	}

}
