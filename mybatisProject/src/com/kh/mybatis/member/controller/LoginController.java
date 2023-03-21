package com.kh.mybatis.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		//String userId = request.getParameter("userId");
		//String userPwd = request.getParameter("userPwd");
		
		//귀찮아서 userId, userPwd 두개만 들고가고싶지만 인터페이스에 Member 객체 m을 넘기라고 되어있어서 강제로 멤버객체만들어서 넣어줘야함
		
		Member m = new Member();
		//m.setUserId(userId);
		//m.setUserPwd(userPwd);
		
		//재사용될 가능성있으면 위의 변수선언해서 사용하고~ 아니면 코드를 줄이자 
		m.setUserId(request.getParameter("userId"));
		m.setUserPwd(request.getParameter("userPwd"));
		
		//쿼리를 생각해보면 select * from member where user_id =? and userPwd = ?  => 한 행만 검색됨 => 앞의 변수를 객체로설정~
		Member loginUser = new MemberServiceImpl().loginMember(m);
	
		if(loginUser==null) { //로그인실패
			
			//에러페이지로 , 에러페이지에 el 에러문구로 errorMsg
			request.setAttribute("errorMsg", "로그인실패");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}else { //로그인성공
				//HttpSession session = request.getSession();
				//session.setAttribute("loginUser", loginUser);
			
				request.getSession().setAttribute("loginUser", loginUser);
				response.sendRedirect(request.getContextPath());
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
