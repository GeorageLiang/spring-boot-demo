package com.spittr.utils.serializer;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Jackson序列化工具类
 * 
 * @author chufei 2018年1月29日
 * 
 */
public class JacksonSerializerUtil {

	protected static final ObjectMapper JSON_MAPPER;

	static {
		JSON_MAPPER = new ObjectMapper();
		// 配置mapper序列化忽略null值
		JSON_MAPPER.setSerializationInclusion(Include.NON_NULL);
		// 禁用未知属性打断反序列化功能
		JSON_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

	/**
	 * 将对象转换为字符串Json格式
	 * @param obj 对象
	 * @return 返回json字符串，当转换过程抛出异常是返回""字符串
	 */
	public static String toJson(Object obj) {
		try {
			return JSON_MAPPER.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			return "";
		}
	}
	
	/**
	 * 将json字符串转换为对应想要的类型引用
	 * @param json 对象json字符串
	 * @param typeRef 类型引用
	 * @return <T> 返回设置的类型引用对象，当转换过程抛出异常是返回{@code null}
	 */
	public static <T> T readValue(String json, TypeReference<T> typeRef) {
		try {
			return JSON_MAPPER.readValue(json, typeRef);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 将json字符串转换为对应想要的类
	 * @param json 对象json字符串
	 * @param clazz 类
	 * @return <T> 返回设置的类，当转换过程抛出异常是返回{@code null}
	 */
	public static <T> T readValue(String json, Class<T> clazz) {
		try {
			return JSON_MAPPER.readValue(json, clazz);
		} catch (Exception e) {
			return null;
		}
	}
	
}
