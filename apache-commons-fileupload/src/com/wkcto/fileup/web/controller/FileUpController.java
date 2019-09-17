package com.wkcto.fileup.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(urlPatterns = { "/fileup.do" })
public class FileUpController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 无效了。
		/*
		 * request.setCharacterEncoding("utf-8"); String username =
		 * request.getParameter("username");
		 */

		// 服务器的java代码接收浏览器提交过来的文件，将文件保存到服务器目录中。
		// 创建文件条目工厂对象
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置阀值（设置一次吞吐量）
		factory.setSizeThreshold(DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD); // 10KB
		// 设置临时文件的存储目录
		String tmpPath = this.getServletContext().getRealPath("tmp"); // 在javaweb项目中怎么获取一个文件的绝对路径
		factory.setRepository(new File(tmpPath));
		// 创建核心对象
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			// 解析request请求对象
			List<FileItem> fileItems = upload.parseRequest(request);
			// System.out.println(fileItems.size());
			// 遍历
			for (FileItem fileItem : fileItems) {
				if (fileItem.isFormField()) {
					// 该FileItem只是一个普通表单元素
					String fieldName = fileItem.getFieldName();
					System.out.println(fieldName); // username
					// String fieldValue = fileItem.getString();
					String fieldValue = fileItem.getString("utf-8");
					System.out.println(fieldValue); // zhangsan
				} else {
					// 表示该FileItem是一个文件控件。
					String fileName = fileItem.getName();
					// System.out.println(fileName);
					// 将该文件放到files目录下。
					String path = this.getServletContext().getRealPath("files") + "/" + fileName;
					fileItem.write(new File(path));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
