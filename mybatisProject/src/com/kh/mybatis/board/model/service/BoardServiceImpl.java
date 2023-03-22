package com.kh.mybatis.board.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.dao.BoardDao;
import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.board.model.vo.Reply;
import com.kh.mybatis.common.model.vo.PageInfo;
import static com.kh.mybatis.common.template.Template.*;

public class BoardServiceImpl implements BoardService{

	
	BoardDao bDao = new BoardDao();
	@Override
	public int selectListCount() {

	
		SqlSession sqlSession = /*Template.*/getSqlSession();
		int listCount = bDao.selectListCount(sqlSession);
				//전체 리스트 개수

	
		sqlSession.close();
		return listCount;
	}

	@Override
	public ArrayList<Board> selectList(PageInfo pi) {


		SqlSession sqlSession = getSqlSession();
		ArrayList<Board> list = bDao.selectList(sqlSession, pi);

		sqlSession.close();
		return list;



	}

	@Override
	public int increaseCount(int boardNo) {
		
		SqlSession sqlSession = getSqlSession();
		int result = bDao.increaseCount(sqlSession, boardNo);
		
		if(result > 0) {
			sqlSession.commit();
		}
		//단건이면 롤백안함  오토커밋꺼져있기 때문에 
		
		sqlSession.close();
		
		return result;
		//-> 결과가  보드디테일컨트롤러로 날라감
		
	}

	/**
	 *
	 *
	 */
	@Override
	public Board selectBoard(int boardNo) {
		
		SqlSession sqlSession = getSqlSession();
		Board b = bDao.selectBoard(sqlSession,boardNo);
		
		sqlSession.close();
		return b;
		
		
	}

	/**
	 * 게시글 상세조회
	 *
	 */
	@Override
	public ArrayList<Reply> selectReplyList(int boardNo) {

		SqlSession sqlSession = getSqlSession();
		ArrayList<Reply> list = bDao.selectReplyList(sqlSession, boardNo);
		//셀렉문이니까 트랜젝션처리 x
		
		sqlSession.close();
		return list;
	
	
	
	}

	@Override
	public int selectSearchCount(HashMap<String, String> map) {
		SqlSession sqlSession = getSqlSession();
		int searchCount = bDao.selectSearchCount(sqlSession,map);
		sqlSession.close();
		return searchCount;
	}

	@Override
	public ArrayList<Board> selectSearchList(HashMap<String, String> map, PageInfo pi) {
		SqlSession sqlSession = getSqlSession();
		ArrayList<Board> list = bDao.selectSearchList(sqlSession,map,pi);
		sqlSession.close();
		return list;
	}

}































