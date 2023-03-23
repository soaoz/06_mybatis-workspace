package com.kh.mybatis.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.board.model.service.BoardServiceImpl;
import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.model.vo.PageInfo;
import com.kh.mybatis.common.template.Pagination;

/**
 * Servlet implementation class BoardSearchController
 */
@WebServlet("/search.bo")
public class BoardSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardSearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String condition = request.getParameter("condition"); // "writer" | "title" |  "content"
		String keyword = request.getParameter("keyword"); //"사용자가 입력한 키워드 값"
		
		//담아서 보내고 싶은데 담을 만한 vo가 없다..
		// 1) 나만의 vo 객체를 만드는 방법..
		// 2) 해시맵을 이용하는 방법  *** 
		
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("condition", condition); //컨디션이라는 키로 컨디션을 담음
		map.put("keyword", keyword); //키워드라는 키로 키워드값을 담았다
		
		
		BoardServiceImpl bService = new BoardServiceImpl();
		//검색한 게시글의 총 개수를 이용해 페이징 처리를 해야함
		//검색 게시글의 총 개수 구하는 서비스
		int serarchCount = bService.selectSearchCount(map); //vo 가 없으면 해쉬맵을 이용해 일회성으로 사용할 수 있다
		//인터페이스가서새로운 메소드 추가 
		//serarchCount: 현재 검색결과에 맞는 게시글의 총 개수가 담겨잇음 
		int currentPage = Integer.parseInt(request.getParameter("cpage"));//몇페이지를 보여줄건지 
		
		PageInfo pi = Pagination.getPageInfo(serarchCount, currentPage, 10, 5);//pagelimit:10페이지로 boardLimit:5개씩
		//System.out.println(pi);
		
		//검색결과를 사용자에게 보여주기
		
		ArrayList<Board> list = bService.selectSearchList(map, pi);
		
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		
		request.setAttribute("condition", condition);
		request.setAttribute("keyword", keyword);
		//이걸 가지고 가야지 url에서 날라기지안흠...? 
		
		request.getRequestDispatcher("WEB-INF/views/board/boardListview.jsp").forward(request, response);
		
		
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
