package cn.edu.nyist.springboot07_jsp_mybatis.biz;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.nyist.springboot07_jsp_mybatis.model.Admin;

public interface AdminBiz {
	//业务层开事务
    @Transactional(readOnly=true)
	public Admin findAdminByid(String name, String pwd);
}
