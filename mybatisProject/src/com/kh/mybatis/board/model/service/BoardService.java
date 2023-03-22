package com.kh.mybatis.board.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.board.model.vo.Reply;
import com.kh.mybatis.common.model.vo.PageInfo;

public interface BoardService {

	/* 원래는 이러케 했었음 
	//게시판 리스트 조회
	public int selectListCount() {}
	public ArraList<Board> selectList(PageInfo pi){
	}
	//게시판상세조회
	public int increaseCount(int boardNo) {} 조회수증가
	public Board selectBoard() {} 
	*/
	
	//게시글 리스트 조회
	int selectListCount();
	ArrayList<Board> selectList(PageInfo pi);
	
	//게시글 상세조회
	int increaseCount(int boardNo);
	Board selectBoard(int boardNo);
	
	
	//댓글 조회
	//댓글은 아마도 여러개가 조회가 되니까 -> 반환형 ArrayList
	ArrayList<Reply> selectReplyList(int boardNo);
	//만드니까 impl 클래스에서 에러 -> add메소드 함 
	
	//게시글 검색
	//컨트롤러에서 map에 담음
	int selectSearchCount(HashMap<String, String> map);
	ArrayList<Board> selectSearchList(HashMap<String, String> map, PageInfo pi);
	
	
	
	//파이널할때도 누가 설계해서 준다음에 이건내가할테니까~ 이건너가해~
	//지금 페이지 인포가 없어서 만들거임 -> 여러개 쓰일곳이 많으니까 common/model/vo
}
