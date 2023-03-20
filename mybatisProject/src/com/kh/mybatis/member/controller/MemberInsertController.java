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
 * Servlet implementation class MemberInsertController
 */
@WebServlet("/insert.me")
public class MemberInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//회원가입~
		//아이디,비번,이름 입력하면 값을 가지고db로 날라가야함
		
		//인코딩처리
		request.setCharacterEncoding("utf-8");
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String birthday = request.getParameter("birtyday");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String address =request.getParameter("address");
				
		//멤버객체에 넣어서 가져가려고 vo 만들러감
		Member m = new Member(userId, userPwd, userName, email, birthday, gender, phone, address);
		//8개 매개변수 생성자 생성
		
		//원래는 int result = memberSErvice.inser..
		
		//서비스를 만들건데 interface로 만들거임
		//모델, 서비스폴더에 인터페이스 MemberService 생성
		//int 형으로 결과가 날라올거임
		
		int result = new MemberServiceImpl().insertMember(m);
		//부장님이 만든대로 매개변수는 Member m 밖에 안됨~~
		
		if(result >0) { //성공 => 메인페이지
			
			response.sendRedirect(request.getContextPath());
			
		}else { //실패 => 에러페이지 (에러문구 담아서)
			
			request.setAttribute("errorMsg", "회원가입 실패잉~~~");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
			//에러페이지.jsp 만들러감~~~
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
