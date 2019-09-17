package com.wkcto.testpath;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

public class Test extends HttpServlet {

	public static void main(String[] args) {
		// 怎么获取类路径下某个文件的绝对路径？
		// 这种方式默认从类的根路径下开始查找资源。
		String absolutePath = Thread.currentThread().getContextClassLoader().getResource("db.properties").getPath();
		// /D:/crm-workspace/apache-commons-fileupload/build/classes/db.properties
		System.out.println(absolutePath);
	}

	public void test1() {
		// 在javaweb项目中怎么获取不在类路径下的文件的绝对路径呢？
		// ServletContext application = this.getServletContext();
		// 这种方式默认从web项目的根路径下作为起点开始。
		// String tmpPath = application.getRealPath("tmp");
		
		String tmpPath = this.getServletContext().getRealPath("tmp");
	}
}
