package com.servasoft.crm.utils;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * MyBatis工具类
 * 
 * @author Administrator
 *
 */
public class SqlSessionUtil {

	// 工具类的构造方法一般都是私有化的，因为工具类的使用不需要实例化。
	private SqlSessionUtil() {

	}

	private static SqlSessionFactory factory;
	private static ThreadLocal<SqlSession> local = new ThreadLocal<>();

	// 类加载的时候初始化SqlSessionFactory对象，只初始化一次。
	static {
		try {
			factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取当前线程中的SqlSession对象。
	 * 
	 * @return
	 */
	public static SqlSession getCurrentSqlSession() {
		SqlSession sqlSession = local.get();
		if (sqlSession == null) {
			sqlSession = factory.openSession();
			local.set(sqlSession); // 向t1线程上绑定sqlSession对象
		}
		return sqlSession;
	}

	/**
	 * 释放资源
	 * 
	 * @param sqlSession
	 */
	public static void close(SqlSession sqlSession) {
		if (sqlSession != null) {
			sqlSession.close();
			// 非常重要
			local.remove(); // Tomcat服务器自带“线程池”，用过的线程t1，下一次可能还会使用线程t1
							// 解除SqlSession对象与线程t1的绑定关系。
		}
	}

	/**
	 * 回滚事务
	 * 
	 * @param sqlSession
	 */
	public static void rollback(SqlSession sqlSession) {
		if (sqlSession != null) {
			sqlSession.rollback();
		}
	}
}
