package cn.edu.nyist.springboot07_jsp_mybatis.biz;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;

import cn.edu.nyist.springboot07_jsp_mybatis.model.Book;
import cn.edu.nyist.springboot07_jsp_mybatis.model.Type;

public interface BookBiz {
	@Transactional
	int save(Book book);

	@Transactional(readOnly = true)
	List<Type> findAll();

	@Transactional(readOnly = true)
	Book findByid(int id);

	@Transactional
	Type findByTid(int tid);

	@Transactional(readOnly = true)
	PageInfo<Book> findAllBook(int pageNo, String name, int tid);

	@Transactional
	int delBook(int id);
	@Transactional
	int updateBook(Book book);

}
