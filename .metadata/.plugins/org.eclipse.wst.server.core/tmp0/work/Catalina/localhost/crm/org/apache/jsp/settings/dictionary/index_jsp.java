/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.0.M10
 * Generated at: 2018-09-13 08:53:59 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.settings.dictionary;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<base href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.scheme}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(':');
      out.write('/');
      out.write('/');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.serverName}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(':');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.serverPort}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/\">\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<link href=\"jquery/bootstrap_3.3.0/css/bootstrap.min.css\" type=\"text/css\" rel=\"stylesheet\" />\r\n");
      out.write("<script type=\"text/javascript\" src=\"jquery/jquery-1.11.1-min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"jquery/bootstrap_3.3.0/js/bootstrap.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("\t//页面加载完毕\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//导航中所有文本颜色为黑色\r\n");
      out.write("\t\t$(\".liClass > a\").css(\"color\" , \"black\");\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//默认选中导航菜单中的第一个菜单项\r\n");
      out.write("\t\t$(\".liClass:first\").addClass(\"active\");\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//第一个菜单项的文字变成白色\r\n");
      out.write("\t\t$(\".liClass:first > a\").css(\"color\" , \"white\");\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//给所有的菜单项注册鼠标单击事件\r\n");
      out.write("\t\t$(\".liClass\").click(function(){\r\n");
      out.write("\t\t\t//移除所有菜单项的激活状态\r\n");
      out.write("\t\t\t$(\".liClass\").removeClass(\"active\");\r\n");
      out.write("\t\t\t//导航中所有文本颜色为黑色\r\n");
      out.write("\t\t\t$(\".liClass > a\").css(\"color\" , \"black\");\r\n");
      out.write("\t\t\t//当前项目被选中\r\n");
      out.write("\t\t\t$(this).addClass(\"active\");\r\n");
      out.write("\t\t\t//当前项目颜色变成白色\r\n");
      out.write("\t\t\t$(this).children(\"a\").css(\"color\",\"white\");\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//展示市场活动页面\r\n");
      out.write("\t\twindow.open(\"settings/dictionary/type/index.jsp\",\"workareaFrame\");\r\n");
      out.write("\t\t\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("\t<!-- 我的资料 -->\r\n");
      out.write("\t<div class=\"modal fade\" id=\"myInformation\" role=\"dialog\">\r\n");
      out.write("\t\t<div class=\"modal-dialog\" role=\"document\" style=\"width: 30%;\">\r\n");
      out.write("\t\t\t<div class=\"modal-content\">\r\n");
      out.write("\t\t\t\t<div class=\"modal-header\">\r\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\">\r\n");
      out.write("\t\t\t\t\t\t<span aria-hidden=\"true\">×</span>\r\n");
      out.write("\t\t\t\t\t</button>\r\n");
      out.write("\t\t\t\t\t<h4 class=\"modal-title\">My Profile</h4>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"modal-body\">\r\n");
      out.write("\t\t\t\t\t<div style=\"position: relative; left: 40px;\">\r\n");
      out.write("\t\t\t\t\t\t姓名：<b>张三</b><br><br>\r\n");
      out.write("\t\t\t\t\t\t登录帐号：<b>zhangsan</b><br><br>\r\n");
      out.write("\t\t\t\t\t\t组织机构：<b>1005，市场部，二级部门</b><br><br>\r\n");
      out.write("\t\t\t\t\t\t邮箱：<b>zhangsan@bjpowernode.com</b><br><br>\r\n");
      out.write("\t\t\t\t\t\t失效时间：<b>2017-02-14 10:10:10</b><br><br>\r\n");
      out.write("\t\t\t\t\t\t允许访问IP：<b>127.0.0.1,192.168.100.2</b>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"modal-footer\">\r\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭</button>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\t<!-- Modal window for modifying password -->\r\n");
      out.write("\t<div class=\"modal fade\" id=\"editPwdModal\" role=\"dialog\">\r\n");
      out.write("\t\t<div class=\"modal-dialog\" role=\"document\" style=\"width: 70%;\">\r\n");
      out.write("\t\t\t<div class=\"modal-content\">\r\n");
      out.write("\t\t\t\t<div class=\"modal-header\">\r\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\">\r\n");
      out.write("\t\t\t\t\t\t<span aria-hidden=\"true\">×</span>\r\n");
      out.write("\t\t\t\t\t</button>\r\n");
      out.write("\t\t\t\t\t<h4 class=\"modal-title\">修改密码</h4>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"modal-body\">\r\n");
      out.write("\t\t\t\t\t<form class=\"form-horizontal\" role=\"form\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label for=\"oldPwd\" class=\"col-sm-2 control-label\">原密码</label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-sm-10\" style=\"width: 300px;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"oldPwd\" style=\"width: 200%;\">\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label for=\"newPwd\" class=\"col-sm-2 control-label\">新密码</label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-sm-10\" style=\"width: 300px;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"newPwd\" style=\"width: 200%;\">\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t<label for=\"confirmPwd\" class=\"col-sm-2 control-label\">确认密码</label>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-sm-10\" style=\"width: 300px;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"confirmPwd\" style=\"width: 200%;\">\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"modal-footer\">\r\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">取消</button>\r\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\" onclick=\"window.location.href='login.html';\">更新</button>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<!-- modal window for exiting system -->\r\n");
      out.write("\t<div class=\"modal fade\" id=\"exitModal\" role=\"dialog\">\r\n");
      out.write("\t\t<div class=\"modal-dialog\" role=\"document\" style=\"width: 30%;\">\r\n");
      out.write("\t\t\t<div class=\"modal-content\">\r\n");
      out.write("\t\t\t\t<div class=\"modal-header\">\r\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\">\r\n");
      out.write("\t\t\t\t\t\t<span aria-hidden=\"true\">×</span>\r\n");
      out.write("\t\t\t\t\t</button>\r\n");
      out.write("\t\t\t\t\t<h4 class=\"modal-title\">离开</h4>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"modal-body\">\r\n");
      out.write("\t\t\t\t\t<p>您确定要退出系统吗？</p>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"modal-footer\">\r\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">取消</button>\r\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"btn btn-primary\" data-dismiss=\"modal\" onclick=\"window.location.href='login.html';\">确定</button>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<!-- 顶部 -->\r\n");
      out.write("\t<div id=\"top\" style=\"height: 50px; background-color: #3C3C3C; width: 100%;\">\r\n");
      out.write("\t\t<div style=\"position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'\">CRM &nbsp;<span style=\"font-size: 12px;\">&copy;2017&nbsp;动力节点</span></div>\r\n");
      out.write("\t\t<div style=\"position: absolute; top: 15px; right: 15px;\">\r\n");
      out.write("\t\t\t<ul>\r\n");
      out.write("\t\t\t\t<li class=\"dropdown user-dropdown\">\r\n");
      out.write("\t\t\t\t\t<a href=\"javascript:void(0)\" style=\"text-decoration: none; color: white;\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"glyphicon glyphicon-user\"></span> zhangsan <span class=\"caret\"></span>\r\n");
      out.write("\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t<ul class=\"dropdown-menu\">\r\n");
      out.write("\t\t\t\t\t\t<li><a href=\"workbench/index.html\"><span class=\"glyphicon glyphicon-home\"></span> 工作台</a></li>\r\n");
      out.write("\t\t\t\t\t\t<li><a href=\"index.html\"><span class=\"glyphicon glyphicon-wrench\"></span> 系统设置</a></li>\r\n");
      out.write("\t\t\t\t\t\t<li><a href=\"javascript:void(0)\" data-toggle=\"modal\" data-target=\"#myInformation\"><span class=\"glyphicon glyphicon-file\"></span> 我的资料</a></li>\r\n");
      out.write("\t\t\t\t\t\t<li><a href=\"javascript:void(0)\" data-toggle=\"modal\" data-target=\"#editPwdModal\"><span class=\"glyphicon glyphicon-edit\"></span> 修改密码</a></li>\r\n");
      out.write("\t\t\t\t\t\t<li><a href=\"javascript:void(0);\" data-toggle=\"modal\" data-target=\"#exitModal\"><span class=\"glyphicon glyphicon-off\"></span> 退出</a></li>\r\n");
      out.write("\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t</li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<!-- 中间 -->\r\n");
      out.write("\t<div id=\"center\" style=\"position: absolute;top: 50px; bottom: 30px; left: 0px; right: 0px;\">\r\n");
      out.write("\t\r\n");
      out.write("\t\t<!-- 导航 -->\r\n");
      out.write("\t\t<div id=\"navigation\" style=\"left: 0px; width: 18%; position: relative; height: 100%; overflow:auto;\">\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\t<ul id=\"no1\" class=\"nav nav-pills nav-stacked\">\r\n");
      out.write("\t\t\t\t<li class=\"liClass\"><a href=\"settings/dictionary/type/index.jsp\" target=\"workareaFrame\"><span class=\"glyphicon glyphicon-book\"></span> 字典类型</a></li>\r\n");
      out.write("\t\t\t\t<li class=\"liClass\"><a href=\"settings/dictionary/value/index.jsp\" target=\"workareaFrame\"><span class=\"glyphicon glyphicon-list\"></span> 字典值</a></li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<!-- 分割线 -->\r\n");
      out.write("\t\t\t<div id=\"divider1\" style=\"position: absolute; top : 0px; right: 0px; width: 1px; height: 100% ; background-color: #B3B3B3;\"></div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<!-- 工作区 -->\r\n");
      out.write("\t\t<div id=\"workarea\" style=\"position: absolute; top : 0px; left: 18%; width: 82%; height: 100%;\">\r\n");
      out.write("\t\t\t<iframe style=\"border-width: 0px; width: 100%; height: 100%;\" name=\"workareaFrame\"></iframe>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<div id=\"divider2\" style=\"height: 1px; width: 100%; position: absolute;bottom: 30px; background-color: #B3B3B3;\"></div>\r\n");
      out.write("\t\r\n");
      out.write("\t<!-- 底部 -->\r\n");
      out.write("\t<div id=\"down\" style=\"height: 30px; width: 100%; position: absolute;bottom: 0px;\"></div>\r\n");
      out.write("\t\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
