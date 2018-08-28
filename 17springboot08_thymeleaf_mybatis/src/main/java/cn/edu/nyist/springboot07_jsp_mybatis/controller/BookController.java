package cn.edu.nyist.springboot07_jsp_mybatis.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.edu.nyist.springboot07_jsp_mybatis.PageConstant;
import cn.edu.nyist.springboot07_jsp_mybatis.biz.BookBiz;
import cn.edu.nyist.springboot07_jsp_mybatis.model.Book;
import cn.edu.nyist.springboot07_jsp_mybatis.model.Type;

@Controller
public class BookController {
	@Autowired
	private BookBiz bookBiz;

	@RequestMapping("/tobookAdd")
	public String tobookAdd(Model model) {
		List<Type> type = bookBiz.findAll();
		model.addAttribute("types", type);
		model.addAttribute("book", new Book());
		return "bookAdd";
	}

	@RequestMapping("/bookAdd")
	public String bookAdd(@Valid @ModelAttribute Book book, BindingResult result,
			@RequestParam("photofile") MultipartFile file, HttpServletRequest request, @RequestParam String vcode,
			HttpSession session) throws Exception {
		String servervcode = (String) session.getAttribute("validateCode");// 接收服务器验证码
		if (!servervcode.equalsIgnoreCase(vcode))// 验证码比较不区分大小写
		{
			result.rejectValue("vcode", "eq.book.vcode", "验证码不正确");
		}
		// 验证是否错误信息
		if (result.hasErrors()) {
			// 验证不通过重新加载类型
			List<Type> type = bookBiz.findAll();
			request.setAttribute("types", type);
			return "bookAdd";
		}
		// 文件名
		String fileName = file.getOriginalFilename();

		// 解决IE下路径问题
		fileName = fileName.lastIndexOf("\\") == -1 ? fileName : fileName.substring(fileName.lastIndexOf("\\") + 1);
		// 文件后缀
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		String newfileName = UUID.randomUUID() + "." + ext;
		String realpath = request.getServletContext().getRealPath("/WEB-INF/upload/" + newfileName);
		if (realpath != null) {
			file.transferTo(new File(realpath));
			book.setPhoto(newfileName);
			System.out.println(newfileName);
		}

		int ret = bookBiz.save(book);
		if (ret > 0) {
			return "main";
		} else {
			return "bookAdd";
		}

	}

	@RequestMapping("/tobookEdit")
	public String tobookEdit(Model model, @RequestParam int id) {
		List<Type> types = bookBiz.findAll();
		Book book = bookBiz.findByid(id);
		// Type type=bookBiz.findByTid(book.getTid());
		// System.out.println(type);
		model.addAttribute("types", types);
		model.addAttribute("book", book);
		// model.addAttribute("id",id);//更新需要id
		return "bookEdit";

	}

	@RequestMapping("/bookEdit")
	public String bookEdit(@Valid @ModelAttribute Book book, BindingResult result,
			@RequestParam("photofile") MultipartFile file, HttpServletRequest request, Model model,
			@RequestParam String vcode, HttpSession session) throws IllegalStateException, IOException {
		String servervcode = (String) session.getAttribute("validateCode");// 接收服务器验证码
		System.out.println(servervcode);
		if (!servervcode.equalsIgnoreCase(vcode))// 验证码比较不区分大小写
		{
			result.rejectValue("vcode", "eq.book.vcode", "验证码不正确");
			System.out.println("11113333333333333333qqqqqq");
		}
		// 验证是否错误信息
		if (result.hasErrors()) {
			// 验证不通过重新加载类型
			List<Type> type = bookBiz.findAll();
			request.setAttribute("types", type);
			System.out.println("111111111111111111111111qqqqqqqqqqqqqqqqq");
			return "bookEdit";
		}
		// 文件名
		String fileName = file.getOriginalFilename();
		if (!fileName.equals("")) {
			// 解决IE下路径问题
			fileName = fileName.lastIndexOf("\\") == -1 ? fileName : fileName.substring(fileName.lastIndexOf("\\") + 1);
			// 文件后缀
			String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
			String newfileName = UUID.randomUUID() + "." + ext;
			String realpath = request.getServletContext().getRealPath("/WEB-INF/upload/" + newfileName);
			if (realpath != null) {
				file.transferTo(new File(realpath));
				book.setPhoto(newfileName);
			}
		} else {

			book.setPhoto(null);
		}
		int ret = bookBiz.updateBook(book);
		if (ret > 0) {
			return "main";
		} else {
			List<Type> type = bookBiz.findAll();
			request.setAttribute("types", type);
			System.out.println("2222222222222222222222222222qqqq");
			return "bookEdit";

		}

	}

	@RequestMapping("/tobooklist")
	public String list(Model model, @RequestParam(defaultValue = "1", required = false) int pageNo,
			@RequestParam(defaultValue = "", required = false) String name,
			@RequestParam(defaultValue = "-1", required = false) int tid) {
		List<Type> types = bookBiz.findAll();// 返回type类型
		model.addAttribute("types", types);
		PageHelper.startPage(pageNo, PageConstant.PAGE_SZIE);

		model.addAttribute(name);
		model.addAttribute(tid);
		PageInfo<Book> pageInfo = bookBiz.findAllBook(pageNo, name, tid);// 返回对象封装到pageinfo

		model.addAttribute("pageInfo", pageInfo);
		return "bookList";
	}

	@RequestMapping("/bookdel")
	public String del(@RequestParam int id) {
		int ret = bookBiz.delBook(id);
		if (ret > 0) {
			return "main";
		} else {

			return "redirect:/tobooklist";
		}
	}

}
