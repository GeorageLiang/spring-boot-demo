package com.spittr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spittr.mapper.spittr_wx.master.WeChatUserMapper;
import com.spittr.service.WeChatUserService;

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
