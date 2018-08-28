package cn.edu.nyist.springboot07_jsp_mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import cn.edu.nyist.springboot07_jsp_mybatis.model.Admin;

@Mapper
public interface AdminMapper {
	 @Select("select * from  t_admin  where name=#{name} and pwd=#{pwd}")
		public Admin find(@Param("name")String name,@Param("pwd")String pwd);

}
