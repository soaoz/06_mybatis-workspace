package com.kh.mybatis.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.member.model.service.MemberServiceImpl;
import com.kh.mybatis.member.model.vo.Member;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login.me")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//아이디 비번 한글 없어서 인코딩 안함
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		//귀찮아서 userId, userPwd 두개만 들고가고싶지만 인터페이스에 Member 객체 m을 넘기라고 되어있어서 강제로 멤버객체만들어서 넣어줘야함
		
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		
		//쿼리를 생각해보면 select * from member where user_id =? and userPwd = ?  => 한 행만 검색됨 => 앞의 변수를 객체로설정~
		Member loginMember = new MemberServiceImpl().loginMember(m);
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
