package com.kh.mybatis.common.template;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

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
	
	//마이바티스 ibatis, mybatis..  이 한가지로 가넝, 마바에서 만들라고 정해준거
	//어려워도 걍 이케 쓰는거구나~~
	public static SqlSession getSqlSession() {
		
		//mybatis-config.xml 파일 읽어들여서 
		//해당 db와 접속된 SqlSession 객체 생성해서 반환하는 작업을 할 것이다~
		SqlSession sqlSession = null; //(커밋,롤백등을 하기위해 필요한 객체)
		
		//SqlSession 생성하기 위해서 => SqlSessionFactory 필요
		// SqlSessionFactory 생성하기 위해서 => SqlSessionFactoryBuilder 필요하다
		
		//xml파일을 읽어들이기위한파일경로
		//슬래쉬 소스폴더의 가장 상위폴더(그냥폴더같은 resources가 소스폴더임, 소스폴더로 만들면 경로를 제시하기가 간단해짐(일반폴더는좀복잡 점클래스,겟리얼패스)
		//슬래쉬 ->소스의 최상위 폴더 -> src , resource 같은거로 인식을 한다 , 소스폴더와 일반폴더의 차이 
		String resource ="/mybatis-config.xml"; //리솔시스에있는 마이바티스
		
		//읽어들일수있는 통로만들기
		//리솔시스객체 => apache.ibats에 있는걸로 import해야함 
		try {
			
			InputStream stream = Resources.getResourceAsStream(resource);
			//sql 세션만들기
			//빌더먼저 만들기(cpnfig -> 어떤환경설정?)
			//autoCommit
			sqlSession = new SqlSessionFactoryBuilder().build(stream).openSession(false);
							//openSession() : 기본값 false 
							//openSession(boolean autocommit) :  자동커밋여부( true : 하겠다,  false면 안하겠다) 
							// => 개발자가 autoCommit을 직접 설정할수있음. 
			//sql세션을 만들려면 빌더를 만들어야하고, 커밋,롤백을 할거면 해야함
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sqlSession;
		//회사에서는...네트워크, 서버팀이 함
	}
	
	
	
	
	
	
	
}
