package cn.edu.nyist.springboot07_jsp_mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.edu.nyist.springboot07_jsp_mybatis.model.Book;
import cn.edu.nyist.springboot07_jsp_mybatis.model.Type;

@Mapper
public interface BookMapper {
	@Insert("insert into t_book values(default,#{tid},#{name},#{descri},#{photo},#{ pubdate},#{price},#{author})")
	int save(Book book);

	@Select("select *  from t_type")
	List<Type> findAll();

	@Select("select  *  from  t_book where id=#{id}")
	Book findBookByid(int id);

	@Select("select  *  from  t_type where id=#{tid}")
	Type findTypeByid(int tid);
	@SelectProvider(type=BookProver.class,method="FindAllProveter")
	List<Book> findAllb(@Param(value = "name") String name,@Param(value = "tid") int tid);
    @Delete("delete from t_book where id=#{id}")
	int delByid(int id);
   @UpdateProvider(type=BookProver.class,method="updatebook")
int update(Book book);

}
