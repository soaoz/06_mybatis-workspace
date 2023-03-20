package com.kh.mybatis.member.model.service;

import com.kh.mybatis.member.model.vo.Member;

public interface MemberService {

	//인터페이스 : 상수필드(묵시적으로 public static final 이 붙음 ) + 추상메서드(묵시적으로 public abstract가 붙는다) 만 쓸 수 있음! 일반변수는 불가
	
	//설계를 한다고 생각하면됨, 이런~이런 메소드 있으면 되겠다
	//일반적으로 회원가입을 생각하면, insertMember(Member m){} 이런 메소드 필요할거같고~
	//로그인할거면 이런메소드가 public Member loginMember(String userId, String userPwd){} ~~ 혹은 public Member loginMember(Member m) {}
	//회원정보수정 public int updateMember(Memebr m){}~~
	//회원탈퇴하면 public int deleteMember(String userId){}~~~ 
	//이런~~메소드를 만들면 되지않을까.
	
	
	/*public abstract ->안써도기본값 */int insertMember(Member m);
		//이렇게 설계를 하고 준다~ 메소드 이름만 짜서 좀
		
	Member loginMember(Member m);
	
	int updateMember(Member m); //강제성 부여
	
	int deleteMember(String userId);
	
	
}
