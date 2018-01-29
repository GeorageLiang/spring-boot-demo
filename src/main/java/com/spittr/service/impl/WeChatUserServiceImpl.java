package com.spittr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spittr.mapper.wx.master.WeChatUserMapper;
import com.spittr.service.WeChatUserService;

/**
 * 微信小程序相关业务service实现
 * @author chufei
 * 2018年1月17日
 */
@Service
public class WeChatUserServiceImpl implements WeChatUserService {

	@Autowired
	private WeChatUserMapper weChatUserMapper;
	
	@Override
	public String getUserName(int id) {
		// TODO Auto-generated method stub
		return weChatUserMapper.getWeChatUserNameByUserId(id);
	}

}
