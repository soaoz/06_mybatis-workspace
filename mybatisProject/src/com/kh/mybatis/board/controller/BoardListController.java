package com.kh.mybatis.board.controller;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class BoardListController
 */
@WebServlet("/list.bo")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	// ---------------- 페이징 처리 -----------------
    	int listCount = new BoardServiceImpl().selectListCount(); // 현재 총 게시글 개수
    	int currentPage = Integer.parseInt(request.getParameter("cpage")); // 현재 페이지 (즉, 사용자가 요청한 페이지)
    	//int pageLimit = 10; // 페이지 하단에 보여질 페이징바의 페이지 최대개수 (몇개 단위씩 배치)  1 2 3
    	//int boardLimit = 10; // 한 페이지 내에 보여질 게시글 최대 개수 (몇개 단위씩)

    	//만들어놓은 Pagination 호출해서 페이징 값을 pi 에 담음  
    	PageInfo pi = Pagination.getPageInfo(listCount, currentPage, 10, 5);

    	//System.out.println(pi);
    	//게시글 리스트 조회 할거임
    	
    	ArrayList<Board> list = new BoardServiceImpl().selectList(pi);
    	
    	request.setAttribute("list",list);
    	request.setAttribute("pi",pi);
    	

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
