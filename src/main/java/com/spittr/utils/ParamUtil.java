package com.spittr.utils;

import com.spittr.utils.constant.CodeConstant;
import com.theft.code.utils.string.StringUtil;

/**
 * 传入参数工具类
 * 
 * @author chufei
 * @date 2017年4月10日
 */
public class ParamUtil {

	/**
	 * 字符串参数处理
	 * 
	 * @param param
	 *            传入参数名
	 * @param value
	 *            传入参数值
	 * @param isRequire
	 *            是否必填
	 * @param defaultValue
	 *            选填参数为空时默认值
	 * @param nullCode
	 *            必填参数为空时错误响应码
	 * @param minLength
	 *            传入参数最小长度
	 * @param maxLength
	 *            传入参数最大长度
	 * @return
	 * @throws SpittrException
	 */
	public static String toString(String param, String value, boolean isRequire, String defaultValue, String nullCode,
			int minLength, int maxLength) throws SpittrException {
		if (StringUtil.strIsNull(value)) {
			if (isRequire) {
				throw new SpittrException("param[" + param + "] can not null", nullCode);
			} else {
				return defaultValue;
			}
		} else {
			int len = value.length();
			if (len >= minLength && len <= maxLength) {
				return value;
			} else {
				throw new SpittrException(
						"param[" + param + "] length must great than " + minLength + " and less than " + maxLength,
						CodeConstant.PARAM_OUT_OF_RANGE);
			}
		}
	}

	/**
	 * 整型数参数处理
	 * 
	 * @param param
	 *            传入参数名
	 * @param value
	 *            传入参数值
	 * @param isRequire
	 *            是否必填
	 * @param defaultValue
	 *            选填参数为空时默认值
	 * @param nullCode
	 *            必填参数为空时错误响应码
	 * @param min
	 *            传入参数最小值
	 * @param max
	 *            传入参数最大值
	 * @return
	 * @throws SpittrException
	 */
	public static int toInt(String param, Integer value, boolean isRequire, int defaultValue, String nullCode, int min,
			int max) throws SpittrException {
		if (null != value) {
			if (value >= min && value <= max) {
				return value;
			} else {
				throw new SpittrException(
						"param[" + param + "] length must great than " + min + " and less than " + max,
						CodeConstant.PARAM_OUT_OF_RANGE);
			}
		} else {
			if (isRequire) {
				throw new SpittrException("param[" + param + "] can not null", nullCode);
			} else {
				return defaultValue;
			}
		}
	}

}
