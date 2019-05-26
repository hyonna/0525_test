package com.iu.notice;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.action.Action;
import com.iu.action.ActionFoward;

public class NoticeService implements Action{
	
	private NoticeDAO noticeDAO;
	
	public NoticeService() {
		noticeDAO = new NoticeDAO();
		
	}

	@Override
	public ActionFoward selectList(HttpServletRequest request, HttpServletResponse response) {
		
		ActionFoward actionFoward = new ActionFoward();
		
		String kind = request.getParameter("kind");
		
		if(kind == null) {
			
			kind = "title";
			
		} else if(kind.equals("c")) {
			
			kind = "contents";
			
		} else if(kind.equals("w")) {
			
			kind = "writer";
			
		} else {
			
			kind = "title";
		}
		
		String search = request.getParameter("search");
		
		if(search == null) {
			
			search = "";
			
		}
		
		int curPage = Integer.parseInt(request.getParameter("curPage")); //현재페이지
		
		int perPage = 10; //한 페이지에 글을 몇개씩 보여줄건지
		
		int startRow = (curPage-1)*perPage+1; //한페이지에 보여줄 갯수의 첫숫자
		int lastRow = curPage*perPage; //한 페이지에 보여줄 갯수의 마지막 숫자
		
		int totalCount = 0; //총 글의 갯수
		
		try {
			
			totalCount = noticeDAO.getTotalCount(kind, search);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		int totalPage = totalCount / perPage; //총 페이지 수 = 총 글의 갯수 / 보여질 글 갯수
		
		if(totalCount % perPage != 0) {
			
			totalPage++;
		}
		
		int perBlock = 10; //한 페이지에 나와야 하는 페이지 수
		
		int totalBlock = totalPage / perBlock;//총 블럭 수
		
		if(totalPage % perBlock != 0) {
			
			totalBlock++;
			
		}
		
		int curBlock = totalBlock / curPage; //현재 블럭
		
		if(totalBlock % curPage != 0) {
			
			curBlock++;
			
		}
		
		int startNum = (curPage-1)*perBlock+1;;
		int lastNum = curPage * perBlock;
		
		if(curBlock == totalBlock) {
			
			lastNum = totalPage;
		}
		
		return actionFoward;
		
	}

	@Override
	public ActionFoward select(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		
		return actionFoward;
	}

	@Override
	public ActionFoward delete(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		
		return actionFoward;
	}

	@Override
	public ActionFoward update(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		
		return actionFoward;
	}

	@Override
	public ActionFoward insert(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		
		return actionFoward;
	}	
	
	

}
