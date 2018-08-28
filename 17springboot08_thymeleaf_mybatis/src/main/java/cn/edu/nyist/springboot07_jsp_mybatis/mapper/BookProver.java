package cn.edu.nyist.springboot07_jsp_mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import cn.edu.nyist.springboot07_jsp_mybatis.model.Book;

public class BookProver {
	public String FindAllProveter(@Param(value = "name") final String name,@Param(value = "tid") final int tid) {
		// 匿名内部类，不知道类名，不能调用构造函数，只能放在匿名内部类中
		return new SQL() {
			// 放在非静态代码块一次调用
			{
				SELECT("*");
				FROM("t_book");
			
				if (tid != -1) {
					// WHERE("tid="+tid);
					WHERE("tid=#{tid}");
				}
				
				if (!name.equals("") || name != null) {
					// 拼接字符串存在sql注入
					// WHERE("name like '%"+name+"%'");
					WHERE("name like concat('%',#{name},'%')");

				}

			}
		}.toString();

	}
	public String updatebook(final Book book) {
		// 匿名内部类，不知道类名，不能调用构造函数，只能放在匿名内部类中
		return new SQL() {
			// 放在非静态代码块一次调用
			{
				UPDATE("t_book");
			
				SET("tid=#{tid} ,name= #{name},descri=#{descri},pubdate=#{ pubdate},author=#{author},price=#{price}");
				if(book.getPhoto()!=null)
				{
					SET("photo=#{photo}");
				}
				WHERE("id=#{id}");
			}
		}.toString();

	}
}
