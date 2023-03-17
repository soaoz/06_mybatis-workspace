package com.kh.mybatis.common.template;

import org.apache.ibatis.session.SqlSession;

public class Template {

	
	/*
	 * 기존 JDBC
	 * public static Connection getConnection(){
	 * 		// driver.properties 파일 읽어들여서
	 *		//해당 DB와 접속된 Connection 객체 생성해서 반환했음
	 * }
	 * 
	 * public static void close(JDBC용 객체 conn,pstmt, rset){
	 * 		//전달받은 JDBC 용 객체를 반납시키는 구문 (안함,마이바티스가해줄거임)
	 * }
	 * 
	 * public static void commit | rollback (Conn){
	 * 		// 트랜젝션 처리 (안할거임, 마바가 해줄거)
	 * } 
	 * 
	 */
	
	//마이바티스 ibatis, mybatis.. 
	public static SqlSession getSqlSession() {
		
	}
	
	
	
	
	
	
	
}
