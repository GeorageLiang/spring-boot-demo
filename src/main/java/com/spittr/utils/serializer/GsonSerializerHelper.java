package com.spittr.utils.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 序列化帮助类
 * @author chufei
 * 2018年1月26日
 */
public class GsonSerializerHelper {

	public static Gson addSerializer(Class<?> clazz, AbstractSerializer<?> serializer) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		return gsonBuilder.registerTypeAdapter(clazz, serializer).create();
	}
	
}