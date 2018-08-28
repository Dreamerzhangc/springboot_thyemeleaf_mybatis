package cn.edu.nyist.springboot07_jsp_mybatis.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.nyist.springboot07_jsp_mybatis.biz.AdminBiz;
import cn.edu.nyist.springboot07_jsp_mybatis.mapper.AdminMapper;
import cn.edu.nyist.springboot07_jsp_mybatis.model.Admin;

@Service
public class AdminBIzImpl implements AdminBiz {
	@Autowired
	private AdminMapper adminMapper;

	@Override
	public Admin findAdminByid(String name, String pwd) {
	
		return adminMapper.find(name, pwd);
	}



}
