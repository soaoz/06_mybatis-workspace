package com.kh.mybatis.member.model.service;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.common.template.Template;
import com.kh.mybatis.member.model.dao.MemberDao;
import com.kh.mybatis.member.model.vo.Member;

public class MemberServiceImpl implements MemberService {
	
	private MemberDao mDao = new MemberDao();

	@Override
	public int insertMember(Member m) {

		/*
		 * 옛날에 했던 방식
		 * Connection conn = JDBCTemplate.getConnection();
		 * int result = new MemberDao().inserMember(conn, m);
		 * 
		 * if(result>0){
		 * 		//커밋
		 * }else{
		 * 		//롤백
		 * }
		 */
		
		//이제부터는~ 마이바티스객체를 사용을 할거임 
		// 기존에 커넥션 객체 생성하는걸 이코드로 대신할거임 
		//sql세션활용 (마이바티스에있는거 임포트),Template.java에서 
		SqlSession sqlSession = Template.getSqlSession();
		//xml에 에러있으면 여기서 에러남~!!!  get 타면 xml바로타가지고그럼 디비가 myBatis구나~ 설정읽고, 
		//이때 mybatis-config.xml 문서 읽어들임
		
		//db는 dao에서 실행
		
		//서버재기동없이 쿼리 실행할수있또록 계속 new dao 계속 생성했었음
		//이제 xml에서 읽어들일거임
		//맨위에 전역변수로 dao 쓸건데 ~ dao 없어서 클래스생성하고 씀 
		int result = mDao.insertMember(sqlSession, m);
		
		if(result>0) {
			sqlSession.commit();
			//우리가 만들지않은 commit 잇음 -> 자동제공
			//오토커밋해놨기때문에
		}
		
		//단건만 처리할때는 (오토커밋이꺼져있다면) 사실 롤백 안해도됨 => autocommit 꺼져있기때문에 (리절트에 담긴게없어서), 켜져있으면 해야됨
		//연쇄적으로 처리할때 (게시판인서트,첨부파일인서트치거나할때 ) 보드인서트는쳤는데 어태치에는 에러났을떄 같은떄, 롤백 작서애향함. 
		
		sqlSession.close();
		//구현한적없지만 마이바티스에서 제공하는거, 안쓴느거 알아서 close해줨
		
		return result;
	}

	@Override
	public Member loginMember(Member m) {
		//회원가입
		
		SqlSession sqlSession = Template.getSqlSession();
		
		Member loginUser = mDao.loginMember(sqlSession, m);
		
		sqlSession.close();
		return loginUser;
	}

	@Override
	public int updateMember(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
