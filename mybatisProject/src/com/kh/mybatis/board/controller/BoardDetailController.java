package com.kh.mybatis.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.board.model.service.BoardService;
import com.kh.mybatis.board.model.service.BoardServiceImpl;
import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.board.model.vo.Reply;

/**
 * Servlet implementation class BoardDetailController
 */
@WebServlet("/detail.bo")
public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//jsp에서 넘겨받은 넘버 받기
		int boardNo = Integer.parseInt(request.getParameter("bno"));
	
		//BoardService bService = new BoardServiceImpl();//문제점 : 이렇게 하는 곳도 있는데 이렇게하면 컨트롤로 메소드 타면 인터페이스메소드로 간다...불편함! 
		
		BoardServiceImpl bService = new BoardServiceImpl();
		
		
		//해야할일
		//1. 조회수 증가시키는 서비스 update set count = count+1 where board_no =1 and status='Y' => 증가가잘되면 유효한게시글이
		int result = bService.increaseCount(boardNo);
		
		if(result > 0) { //유효한 게시글
			
			//2. 잘됐다는 가정하에 해당 게시글 상세조회 서비스
			Board b = bService.selectBoard(boardNo);
			
			//3. 해당 게시글에 딸린 댓글 리스트 조회 서비스도 => 서비스호출많이하니까 전역변수로 서비스만들기
			//인터페이스에 댓글메소드가 없어서 추가,impl서비스에서 메소드명만 구현
			ArrayList<Reply> list = bService.selectReplyList(boardNo);
			
			request.setAttribute("b", b);
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("WEB-INF/views/board/boardDetailView.jsp").forward(request, response);
			
		}else {
			request.setAttribute("errorMsg", "상세조회 실패!");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
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
