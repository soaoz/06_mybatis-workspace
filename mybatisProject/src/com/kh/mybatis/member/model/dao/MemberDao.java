package com.kh.mybatis.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.member.model.vo.Member;

public class MemberDao {

	/*
	 * 원래했던거
	 * int result = 0;
	 * PreparedStatement pstmt = null;
	 * String sql = prop.getProperty("insertMember");
	 * 
	 * try{
	 * 	pstmt = conn.preparedStatement(sql);
	 * 
	 * 	pstmt.setString(1,m.getUserId());
	 * 	pstmt.setString(2,m.getUserPwd());
	 * 
	 * 	result = pstmt.excuteUdpdate();
	 * }catch(xxx){
	 * 
	 * }finally{
	 * 	close(pstmt);
	 * 
	 * }
	 * 
	 * return result;
	 * 
	 * -> 이것들이 마이바티스에서는 한줄로 줄음
	 * 
	 */
	
	//쿼리를 먼저짬 
	
	/*
	 * sqlSession에서 제공하는 메소드를 통해서 sql문 찾아서 실행하고 결과 바로 받음
	 * 
	 * 결과 = sqlSession.sql문 종류에 맞는 메소드("매퍼의 별칭(namespace).쿼리아이디(id)", [미완성된 sql 완성 시킬 객체명])
	 * 
	 * 
	 */
	
	
	public int insertMember(SqlSession sqlSession, Member m) {
		
		//int result = sqlSession.insert("memberMapper.insertMember", m);
		//return result;
		//위의 두줄을 한줄로
		
		return sqlSession.insert("memberMapper.insertMember", m);
		//config에 mapper작성
		
	}
	
	
	public Member loginMember(SqlSession sqlSession, Member m) {
		//sql 디벨롭에서 쿼리짜기 
		
	
		
		//기존
		/*
		 * if(rest.next()){
		 * 
		 * Member m = new Member(rset.getInt("user_no"),
		 * 						rset.getString("user_id") => db에 있는 user_id를 가져와라
		 * }
		 * 
		 * => 이런과정이 없어짐   => resultMap 등쟝!
		 * member-mapper .ㅌxml 에 필기 
		 * 
		 */

	
	
	
	}
}

































