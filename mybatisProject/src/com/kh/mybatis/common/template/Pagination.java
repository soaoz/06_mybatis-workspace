package com.kh.mybatis.common.template;

import com.kh.mybatis.common.model.vo.PageInfo;

public class Pagination {

	
	//바로바로 사용할수있도록 static
	public static PageInfo getPageInfo(int listCount, int currentPage, int pageLimit, int boardLimit) {
		
		//값을 받아서 페이징 처리하기 
		
		
		int maxPage = (int)Math.ceil((double)listCount / boardLimit);
		int startPage = (currentPage-1) / pageLimit * pageLimit + 1;
		int endPage = startPage + pageLimit -1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		return pi;
	}
	
}
