<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="resources/icon/iconfont.css" rel="stylesheet" type="text/css" />
<link href="resources/bs/<spring:theme code='bstheme'/>/bootstrap.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="container">
		<div class="row card" style="border-color: red;">


			<!-- 顶部 -->
			<div class="row">
				<div class="col-md-12">
					<%@include file="common/top.jsp"%>
				</div>
			</div>

		</div>



		<div class="row card" style="border-color: red;">
			<div class="col-md-12">
				<div class="card-header" style="border-color: black;">


					<form class="form-inline" id="bookListSearchFrm">
						<!-- 默认看第一页 ,简化点击分页链接时候带表单参数问题-->
						<input name="pageNo" type="hidden" value="1"> <label class="sr-only" for="inlineFormInputName2">名字 </label> <input type="text" class="form-control mb-2 mr-sm-2" id="inlineFormInputName2"
							placeholder="书名" name="name" value="${param.name}"> <label class="sr-only" for="inlineFormInputName2">类型 </label> <select name="tid" class="form-control mb-2 mr-sm-2" id="tid_sel">
							<option value="-1">--请选择--</option>
							<c:forEach items="${types}" var="type">
								<option value="${type.id}">${type.name }</option>
							</c:forEach>
						</select>
						<button type="submit" class="btn btn-primary mb-2">Submit</button>
					</form>

				</div>
				<div class="card-body">
					<form></form>

					<table class="table table-bordered table-hover row mx-0">
						<thead class="col-md-12">
							<tr class="row">
								<th class="col-md-1 col-1 text-center">id</th>
								<th class="col-md-1 col-1 text-center">name</th>
								<th class="col-md-2 col-2 text-center">descri</th>
								<th class="col-md-1 col-1 text-center">tid</th>
								<th class="col-md-3 col-3 text-center">photo</th>
								<th class="col-md-1 col-1text-center">price</th>
								<th class="col-md-1 col-1 text-center">author</th>
								<th class="col-md-1 col-1 text-center">pubDate</th>

								<th class="col-md-1 col-1 text-center">操作</th>
							</tr>
						</thead>
						<tbody class="col-md-12">
							<c:choose>
								<c:when test="${empty pageInfo.list}">
									<tr class="row">
										<td colspan="9">无数据</td>
									</tr>
								</c:when>
								<c:otherwise>

									<c:forEach items="${pageInfo.list}" var="book">
										<tr class="row">
											<td class="col-md-1 col-1 text-center">${book.id}</td>

											<td class="col-md-1 col-1 text-center">${book.name}</td>
											<td class="col-md-2 col-2 text-center">${book.descri}</td>
											<td class="col-md-1 col-1 text-center">${book.tid}</td>

											<td class="col-md-3 col-3 text-center"><img alt="" src="/upload/${book.photo }" style="max-width: 20%"></td>
											<td class="col-md-1 col-1 text-center">${book.price}</td>
											<td class="col-md-1 col-1 text-center">${book.author}</td>
											<td class="col-md-1 col-1 text-center"><fmt:formatDate value="${book.pubDate}" pattern="yyyy-mm-dd" /></td>

											 <td class="col-md-1 col-1 text-center">
											 <a href="bookDel?id=${book.id}" title="删除" style="text-decoration: none;" onclick="delConfirm(event)">
											 <img src="resources/icon/shanchu.png" class="icon-trash-can"/>
											</a>&nbsp;&nbsp; <a href="toBookEdit?id= ${book.id } " title="编辑" style="text-decoration: none;"> <span class="icon-clipboard-edit"> <img src="resources/icon/xiugai.png" />
												</span>
											</a></td> 
											<%-- <td>
											<a href="bookDel?id= ${book.id}" title="删除" onclick="delConfirm(event)">
										 <img src="resources/icon/shanchu.png" />
											</a>
											</td> --%>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>

					<!-- 删除确认框 -->
					
					<div class="modal fade" id="delDlg" tabindex="-1" role="dialog">
						<div class="modal-dialog modal-dialog-centered" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLongTitle">删除确认</h5>
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">你确认要删除?</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary btn_ok">确定</button>
								</div>
							</div>
						</div>
					</div>





				</div>
				<div class="card-footer text-muted" style="border-color: black;">





					<nav aria-label="...">
						<ul class="pagination">
							<c:choose>
								<c:when test="${pageInfo.isFirstPage}">
									<li class="disabled" class="page-item"><a href="javascript:void(0)" class="page-link">&laquo;</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a href="bookList?pageNo=${pageInfo.prePage}" class="page-link">&laquo;</a></li>
								</c:otherwise>
							</c:choose>
							<c:forEach items="${pageInfo.navigatepageNums}" var="pi">
								<li class="page-item <c:if test="${pi==pageInfo.pageNum }">active</c:if>"><a href="bookList?pageNo=${pi}" class="page-link">${pi }</a></li>
							</c:forEach>
							<c:choose>
								<c:when test="${pageInfo.isLastPage }">
									<li class="disabled"><a href="javascript:void(0)" class="page-link">&raquo;</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a href="bookList?pageNo=${pageInfo.nextPage}" class="page-link">&raquo;</a></li>

								</c:otherwise>

							</c:choose>
						</ul>
					</nav>

				</div>

			</div>
		</div>









		<!--下面 top -->
		<div class="row card" style="border-color: red;">
			<div class="col-md-12">
				<%@include file="common/under.jsp"%>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="resources/bower_components/jquery/dist/jquery.slim.js"></script>
	<script type="text/javascript" src="resources/bower_components/bootstrap/dist/js/bootstrap.js"></script>
	<script type="text/javascript" src="resources/bower_components/uri-util/dist/uri-util.min.js"></script>
	<script type="text/javascript" src="resources/js/js.js"></script>
	<script type="text/javascript">
		$(function() {
			//修正主题选中值
			$("#themeSel").val("<spring:theme code='bstheme'/>");
			//让tid回填
			$("#tid_sel").val("${empty param.tid?-1:param.tid}");
			//让超级链接追加表单参数，最简单方式阻止超级链接默认行为，为表单添加隐藏字段a['class="page-link"']")
			
			$('a[class="page-link"][href^="bookList?pageNo="]').click(function(evt){
			evt.preventDefault();
			//修改表单中pageNo的值
			$('#bookListSearchFrm input[name="pageNo"]').val($(this).text());
			$("#bookListSearchFrm").submit();
			});

			//对模态对话框配置
			$("#delDlg").modal({
			backdrop: 'static',//否则你点击对话框外的部分就消失
			show:false//默认是显示的
			});
			
		});
		function delConfirm(event){
			//取消超级链接的默认行为
			event.preventDefault();
			//显示模态对话框：点击的是图标，因而我们要找他的父亲a
			var url=$(event.target).parent("a").attr("href");
			$('#delDlg').modal('show');
			//给确认框绑定一次性事件
			$("#delDlg button.btn_ok ").one("click",function(){
			window.location.href=url;
			});
			}
	</script>
</body>
</html>