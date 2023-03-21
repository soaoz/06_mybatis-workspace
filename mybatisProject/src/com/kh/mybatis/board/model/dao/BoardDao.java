package com.kh.mybatis.board.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.vo.Board;
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
}
