package com.spittr.utils;

/**
 * Spittr自定义异常类
 * 
 * @author chufei
 * @date 2017年4月10日
 */
public class SpittrException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String errorCode;
	
	public SpittrException() {
		super();
	}

	public SpittrException(String msg) {
		super(msg);
	}

	public SpittrException(String msg, String errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}
	
	public SpittrException(Throwable e, String errorCode) {
		super(e);
		this.errorCode = errorCode;
	}
	
	public SpittrException(String msg, Throwable e, String errorCode) {
		super(msg, e);
		this.errorCode = errorCode;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
}
