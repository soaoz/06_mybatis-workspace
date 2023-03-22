package com.kh.mybatis.board.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.board.model.vo.Reply;
import com.kh.mybatis.common.model.vo.PageInfo;

public class BoardDao {
	
	public int selectListCount(SqlSession sqlSession) {
		//쿼리수행구문
		//(네임스페이스) 
		return 	sqlSession.selectOne("boardMapper.selectListCount");
	}

	
	//전체게시글조회
	public ArrayList<Board> selectList(SqlSession sqlSession, PageInfo pi){
		//sql 디벨롭으로 
		sqlSession.selectList("boardMapper.selectList");
		//보드매퍼에있는 selctList 쿼리를 실행하겠다~ 이렇게만 하면 전체 리스트가 싹 다 조회된다..
		//내가 1번페이지를 누르면 1~ 1~10개 목록 2번누르면 11~2
		
		//마이바티스에서는 페이징 처리를 위해 RowsBounds 라는 클래스를 제공함. 이걸 이용하면 쉽게 가넝
		
		// 알아야할 단어
		//* offset :  몇 개의 게시글을 건너 뛰고 조회할건지에 대한 값
		
		/*
		 * ex) boardLimit : 5 (한페이지에 5개씩 보여짐)
		 * 
		 * currentPage : (몇번페이질 클릭했냐)
		 * 						 offset(건너뛸숫자)  			limit(조회할 숫자)
		 * currentPage :1	1~5			0 (0개건너띄고 5개)		5
		 * 				2	6~10		5						5
		 * 				3	11~15		10						5
		 * 				...
		 * 
		 * 보드리밋의배수
		 */
		
		int offset = (pi.getCurrentPage()-1)* pi.getBoardLimit();
		int limit = pi.getBoardLimit();
	
	
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		ArrayList<Board> list = (ArrayList)sqlSession.selectList("boardMapper.selectList",null, rowBounds);
		//버전 업 돼서 제네릭 쓰면 안됨
	
		return list;
	}
	
	
	
	/**
	 * 조회수 증가
	 * @param sqlSession
	 * @param boardNo
	 * @return
	 */
	public int increaseCount(SqlSession sqlSession,int boardNo) {
		//쿼리 짜보기 -> board-mapper 메퍼로 
		
		return sqlSession.update("boardMapper.increaseCount", boardNo);
		//완벽한쿼리일경우 매개1 미완성이면 2 (연결할메퍼, 넘길거)
	}
	
	
	/**
	 * 게시글 상세조회 
	 * @param sqlSession
	 * @param boardNo
	 * @return
	 */
	public Board selectBoard(SqlSession sqlSession,int boardNo) {
		//게시글 상세조회
		//sql 디벨롭 쿼리짜기 
		return sqlSession.selectOne("boardMapper.selectBoard", boardNo);
		//결과가 한행이면 selectOne
		//메퍼에 값 넘겨주니까 메퍼에서 parameterYtpe
		
		
	}
	
	
	/**
	 * 게시글 상세조회에서 댓글 목록 조회
	 * @param sqlSession
	 * @param boardNo
	 */
	public ArrayList<Reply> selectReplyList(SqlSession sqlSession, int boardNo) {
		//쿼리짜러 
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectReplyList", boardNo);
		//에러 -> 형변환 -> 제네릭 쓰면 에러남 
		//매개변수 3개짜리는 페이징 처리할때
	}
	
	public int selectSearchCount(SqlSession sqlSession, HashMap<String, String> map) {
		//쿼리..
		return sqlSession.selectOne("boardMapper.selectSearchCount", map);
		//map을 넘김 
	} 
	
	public ArrayList<Board> selectSearchList(SqlSession sqlSession,HashMap<String, String> map, PageInfo pi){
		//페이징을 위한 로우바운즈를 사용할건데 갤 슬라면 offset
		//위에서 복사 
		//쿼리짜기...
		int offset = (pi.getCurrentPage()-1)* pi.getBoardLimit();
		int limit = pi.getBoardLimit();
	
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectSearchList", map, rowBounds);
	}
	
	
	
	
}


























