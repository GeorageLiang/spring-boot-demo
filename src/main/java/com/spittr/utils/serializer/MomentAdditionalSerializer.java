package com.spittr.utils.serializer;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.spittr.model.MomentAdditional;

/**
 * 动态附加信息实体对象Json序列化和反序列化
 * @author chufei
 * 2018年1月18日
 */
public class MomentAdditionalSerializer extends AbstractSerializer<MomentAdditional> {

	@Override
	public JsonElement serialize(MomentAdditional momentAdditional, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", momentAdditional.getId());
		jsonObject.addProperty("momentId", momentAdditional.getMomentId());
		jsonObject.addProperty("userId", momentAdditional.getUserId());
		jsonObject.addProperty("type", momentAdditional.getType());
		jsonObject.addProperty("index", momentAdditional.getIndex());
		jsonObject.addProperty("url", momentAdditional.getUrl());
		jsonObject.addProperty("createdTime", momentAdditional.getCreatedTime().getTime());
		jsonObject.addProperty("updatedTime", momentAdditional.getUpdatedTime().getTime());
		return jsonObject;
	}

	@Override
	public MomentAdditional deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject jsonObject = json.getAsJsonObject();
		MomentAdditional momentAdditional = new MomentAdditional();
		momentAdditional.setId(jsonObject.get("id").getAsLong());
		momentAdditional.setMomentId(jsonObject.get("momentId").getAsLong());
		momentAdditional.setUserId(jsonObject.get("userId").getAsLong());
		momentAdditional.setType(jsonObject.get("type").getAsInt());
		momentAdditional.setIndex(jsonObject.get("index").getAsInt());
		momentAdditional.setUrl(jsonObject.get("url").getAsString());
		momentAdditional.setCreatedTime(new Date(jsonObject.get("createdTime").getAsLong()));
		momentAdditional.setUpdatedTime(new Date(jsonObject.get("updatedTime").getAsLong()));
		return momentAdditional;
	}

}
