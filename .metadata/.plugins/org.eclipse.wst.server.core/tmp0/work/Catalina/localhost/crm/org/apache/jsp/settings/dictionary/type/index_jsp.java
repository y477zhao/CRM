/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.0.M10
 * Generated at: 2018-09-24 06:57:49 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.settings.dictionary.type;

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
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"jquery/jquery-1.11.1-min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"jquery/bootstrap_3.3.0/js/bootstrap.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\tdisplay();\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t// 全选和取消全选\r\n");
      out.write("\t\t$(\"#selectAll\").click(function(){\r\n");
      out.write("\t\t\t// $(\":checkbox\") // 获取当前页面中所有的checkbox复选框对象\r\n");
      out.write("\t\t\t// $(\":checkbox[name='id']\") // 获取当前页面中所有name=\"code\"的checkbox复选框对象\r\n");
      out.write("\t\t\t// jquery支持批量操作（支持多个元素一块操作）\r\n");
      out.write("\t\t\t$(\":checkbox[name='code']\").prop(\"checked\" , this.checked);\r\n");
      out.write("\t\t\t/*\r\n");
      out.write("\t\t\tif(this.checked){\r\n");
      out.write("\t\t\t\t$(\":checkbox[name='id']\").prop(\"checked\" , true);\r\n");
      out.write("\t\t\t} else {\r\n");
      out.write("\t\t\t\t$(\":checkbox[name='id']\").prop(\"checked\" , false);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t*/\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$(\"#deleteBtn\").click(function(){\r\n");
      out.write("\t\t\tif($(\":checkbox[name='items']:checked\").size()==0){\r\n");
      out.write("\t\t\t\talert(\"请选择要删除的数据!\");\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\tif(window.confirm(\"您确定要删除吗?\")){\r\n");
      out.write("\t\t\t\t\tvar dataSent = \"\";\r\n");
      out.write("\t\t\t\t\t$.each( $(\":checkbox[name='items']:checked\"), function(i, n){\r\n");
      out.write("\t\t\t\t\t\t//n是其中一个复选框checkbox对象,并且是dom对象\r\n");
      out.write("\t\t\t\t\t\tdataSent += \"&code=\" + n.value;\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\tdataSent = dataSent.substr(1);\r\n");
      out.write("\t\t\t\t\t$.post(\r\n");
      out.write("\t\t\t\t\t\t\"settings/dictionary/type/delete.do\",\r\n");
      out.write("\t\t\t\t\t\tdataSent,\r\n");
      out.write("\t\t\t\t\t\tfunction(json){\r\n");
      out.write("\t\t\t\t\t\t\tif(json.success){\r\n");
      out.write("\t\t\t\t\t\t\t\t$(\"#selectAll\").prop(\"checked\", false);\r\n");
      out.write("\t\t\t\t\t\t\t\tdisplay();\r\n");
      out.write("\t\t\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\t\t\talert(\"删除失败!\");\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t});\r\n");
      out.write("\tfunction display(){\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\ttype : \"post\",\r\n");
      out.write("\t\t\turl : \"settings/dictionary/type/list.do\",\r\n");
      out.write("\t\t\tcache : false,\r\n");
      out.write("\t\t\tsuccess : function(json){\r\n");
      out.write("\t\t\t\t//alert(json.dataList);\r\n");
      out.write("\t\t\t\t//返回一个json格式的Map\r\n");
      out.write("\t\t\t\tvar html = \"\";\r\n");
      out.write("\t\t\t\t// {\"dataList\" : [{\"id\":\"\",\"name\":\"\",\"owner\":\"\",\"startDate\":\"\",\"endDate\":\"\"},{}]}\r\n");
      out.write("\t\t\t\t$.each(json.dataList, function(i,n){\r\n");
      out.write("\t\t\t\t\t//alert(n.code);\r\n");
      out.write("\t\t\t\t\thtml += '<tr class=\"active\">';\r\n");
      out.write("\t\t\t\t\thtml += '<td><input type=\"checkbox\" name=\"items\" value=\"'+n.code+'\"/></td>';\r\n");
      out.write("                    html += '<td>'+(++i)+'</td>';\r\n");
      out.write("                    html += '<td>'+n.code+'</td>';\r\n");
      out.write("\t\t\t\t\thtml += '<td>'+n.name+'</td>';\r\n");
      out.write("\t\t\t\t\thtml += '<td>'+n.description+'</td>';\r\n");
      out.write("\t\t\t\t\thtml += '</tr>';\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\t$(\"#dicTypeTbody\").html(html);\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("\t<div>\r\n");
      out.write("\t\t<div style=\"position: relative; left: 30px; top: -10px;\">\r\n");
      out.write("\t\t\t<div class=\"page-header\">\r\n");
      out.write("\t\t\t\t<h3>字典类型列表</h3>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"btn-toolbar\" role=\"toolbar\" style=\"background-color: #F7F7F7; height: 50px; position: relative;left: 30px;\">\r\n");
      out.write("\t\t<div class=\"btn-group\" style=\"position: relative; top: 18%;\">\r\n");
      out.write("\t\t  <button type=\"button\" class=\"btn btn-primary\" onclick=\"window.location.href='settings/dictionary/type/save.jsp'\"><span class=\"glyphicon glyphicon-plus\"></span> 创建</button>\r\n");
      out.write("\t\t  <button type=\"button\" class=\"btn btn-default\" onclick=\"window.location.href='settings/dictionary/type/edit.jsp'\"><span class=\"glyphicon glyphicon-edit\"></span> 编辑</button>\r\n");
      out.write("\t\t  <button id=\"deleteBtn\" type=\"button\" class=\"btn btn-danger\"><span class=\"glyphicon glyphicon-minus\"></span> 删除</button>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div style=\"position: relative; left: 30px; top: 20px;\">\r\n");
      out.write("\t\t<table class=\"table table-hover\">\r\n");
      out.write("\t\t\t<thead>\r\n");
      out.write("\t\t\t\t<tr style=\"color: #B3B3B3;\">\r\n");
      out.write("\t\t\t\t\t<td><input type=\"checkbox\" id=\"selectAll\" /></td>\r\n");
      out.write("\t\t\t\t\t<td>序号</td>\r\n");
      out.write("\t\t\t\t\t<td>编码</td>\r\n");
      out.write("\t\t\t\t\t<td>名称</td>\r\n");
      out.write("\t\t\t\t\t<td>描述</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</thead>\r\n");
      out.write("\t\t\t<tbody id=\"dicTypeTbody\">\r\n");
      out.write("\t\t\t\t<!--<tr class=\"active\">\r\n");
      out.write("\t\t\t\t\t<td><input type=\"checkbox\" /></td>\r\n");
      out.write("\t\t\t\t\t<td>1</td>\r\n");
      out.write("\t\t\t\t\t<td>sex</td>\r\n");
      out.write("\t\t\t\t\t<td>性别</td>\r\n");
      out.write("\t\t\t\t\t<td>性别包括男和女</td> \r\n");
      out.write("\t\t\t\t</tr>-->\r\n");
      out.write("\t\t\t</tbody>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</div>\r\n");
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