package com.spittr.utils.serializer;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

/**
 * Gson序列化抽象类
 * @author chufei
 * 2018年1月29日
 * @param <T> 需要序列化和反序列化的具体对象
 */
public abstract class AbstractSerializer<T> implements JsonSerializer<T>, JsonDeserializer<T> {

}
