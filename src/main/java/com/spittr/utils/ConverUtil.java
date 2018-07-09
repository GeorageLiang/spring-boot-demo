package com.spittr.utils;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.spittr.utils.serializer.JacksonSerializerUtil;

/**
 * BeanUtils类型转化工具类
 * @author chufei
 * 2018年1月26日
 */
public class ConverUtil {

	/**
	 * 将HashMap转化成bean对象
	 * @param map key-value键值对象
	 * @param beanClass 要转化成的bean对象类型
	 * @return
	 * @throws Exception
	 */
	public static <T> T map2Object(Map<String, String> map, Class<T> beanClass) throws Exception {
		if (map == null || map.isEmpty()) {
			return null;
		}
		T object = beanClass.newInstance();
		ConvertUtils.register(new DateLocaleConverter(), Date.class);
		ConvertUtils.register(new CollectionConverter(), List.class);
		BeanUtils.populate(object, map);
		return (T) object;
	}

}

class CollectionConverter implements Converter {

	@Override
	public <T> T convert(Class<T> type, Object value) {
		if (value == null) {
			return null;
		}
		if (value instanceof String) {
			String json = (String) value;
			TypeReference<T> typeRef = new TypeReference<T>() {
			};
			return JacksonSerializerUtil.readValue(json, typeRef);
		}
		return null;
	}
	
}

class DateLocaleConverter implements Converter {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T convert(Class<T> type, Object value) {
		if (value == null) {
			return null;
		}
		if (value instanceof String) {
			Long timestamp = new Long((String) value);
			return (T) new Date(timestamp);
		}
		return null;
	}
	
}