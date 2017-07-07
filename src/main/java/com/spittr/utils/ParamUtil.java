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
			int minLength, int maxLength) {
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
	 * 字符串数组参数处理
	 * 
	 * @param param
	 *            传入参数名
	 * @param values
	 *            传入参数值
	 * @param isRequire
	 *            是否必填
	 * @param defaultValues
	 *            选填参数为空时默认值
	 * @param nullCode
	 *            必填参数为空时错误响应码
	 * @param minLength
	 *            传入数字最小长度
	 * @param maxLength
	 *            传入数组最大长度
	 * @return
	 * @throws SpittrException
	 */
	public static String[] toStringArray(String param, String[] values, boolean isRequire, String[] defaultValues,
			String nullCode, int minSize, int maxSize) {
		if (null == values) {
			if (isRequire) {
				throw new SpittrException("param[" + param + "] can not null", nullCode);
			} else {
				return defaultValues;
			}
		} else {
			if (values.length >= minSize && values.length <= maxSize) {
				return values;
			} else {
				throw new SpittrException(
						"param[" + param + "] length must great than " + minSize + " and less than " + maxSize,
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
			int max) {
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

	/**
	 * 整型数组参数处理
	 * 
	 * @param param
	 *            传入参数名
	 * @param values
	 *            传入参数值
	 * @param isRequire
	 *            是否必填
	 * @param defaultValues
	 *            选填参数为空时默认值
	 * @param nullCode
	 *            必填参数为空时错误响应码
	 * @param minSize
	 *            传入数组最小长度
	 * @param maxSize
	 *            传入数组最大参数
	 * @return
	 * @throws SpittrException
	 */
	public static int[] toIntArray(String param, int[] values, boolean isRequire, int[] defaultValues, String nullCode,
			int minSize, int maxSize) {
		if (null != values) {
			if (values.length >= minSize && values.length <= maxSize) {
				return values;
			} else {
				throw new SpittrException(
						"param[" + param + "] length must great than " + minSize + " and less than " + maxSize,
						CodeConstant.PARAM_OUT_OF_RANGE);
			}
		} else {
			if (isRequire) {
				throw new SpittrException("param[" + param + "] can not null", nullCode);
			} else {
				return defaultValues;
			}
		}
	}

	/**
	 * 长整型数参数处理
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
	public static long toLong(String param, Long value, boolean isRequire, long defaultValue, String nullCode, long min,
			long max) {
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
