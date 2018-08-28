package cn.edu.nyist.springboot07_jsp_mybatis.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.edu.nyist.springboot07_jsp_mybatis.PageConstant;
import cn.edu.nyist.springboot07_jsp_mybatis.biz.BookBiz;
import cn.edu.nyist.springboot07_jsp_mybatis.mapper.BookMapper;
import cn.edu.nyist.springboot07_jsp_mybatis.model.Book;
import cn.edu.nyist.springboot07_jsp_mybatis.model.Type;

@Service
public class BookBizImpl implements BookBiz {
	@Autowired
	private BookMapper bookMapper;

	@Override
	public int save(Book book) {

		return bookMapper.save(book);
	}

	@Override
	public List<Type> findAll() {

		return bookMapper.findAll();
	}

	@Override
	public Book findByid(int id) {

		return bookMapper.findBookByid(id);
	}

	@Override
	public Type findByTid(int tid) {

		return bookMapper.findTypeByid(tid);
	}

	@Override
	public PageInfo<Book> findAllBook(int pageNo, String name, int tid) {
		PageHelper.offsetPage((pageNo - 1) * PageConstant.PAGE_SZIE + 1 - 1, PageConstant.PAGE_SZIE, true);
		List<Book> ls = bookMapper.findAllb(name, tid);
		PageInfo<Book> pageInfo = new PageInfo<Book>(ls);
		System.out.println("=============="+pageInfo);
		return pageInfo;
	}

	@Override
	public int delBook(int id) {
	
		return bookMapper.delByid(id);
	}

	@Override
	public int updateBook(Book book) {
		
		return bookMapper.update(book);
	}

}
