package com.iu.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

import com.iu.util.DBConnector;

public class NoticeDAO {
	
	
	public static void main(String[] args) {

		NoticeDAO noticeDAO = new NoticeDAO();
		Random random = new Random();

		for (int i = 0; i < 100; i++) {

			NoticeDTO noticeDTO = new NoticeDTO();
			noticeDTO.setTitle(""+i);
			noticeDTO.setContents("");
			noticeDTO.setWriter("");
			
			try {
				noticeDAO.insert(noticeDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	
	public int insert(NoticeDTO noticeDTO) throws Exception {
		
		Connection con = DBConnector.getConnect();
		
		String sql = "insert into notice values(notice_seq.nextval, ?, ?, ?, sysdate, 0)";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, noticeDTO.getTitle());
		st.setString(2, noticeDTO.getContents());
		st.setString(3, noticeDTO.getWriter());
		
		int result = st.executeUpdate();
		
		DBConnector.getConnect();
		
		return result;
		
		
	}
	
	public int getTotalCount(String kind, String search) throws Exception {
		
		Connection con = DBConnector.getConnect();
		
		String sql = "select count(num) from notice where " + kind + " like ?";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, "%"+search+"%");

		ResultSet rs = st.executeQuery();
		
		rs.next();
		
		int result = rs.getInt(1);
		
		DBConnector.disConnect(con, st, rs);
		
		return result;
		
	}
	
	public ArrayList<NoticeDTO> selectList(String kind, String search, int startRow, int lastRow) throws Exception {
		
		Connection con = DBConnector.getConnect();
		
		String sql = "select * from "
					+ "(select rownum R, P.* from "
					+ "(select * from notice where " + kind + " like ?) N) "
					+ "where R between ? and ?";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, "%"+search+"%");
		st.setInt(2, startRow);
		st.setInt(3, lastRow);
		
		ResultSet rs = st.executeQuery();
		
		ArrayList<NoticeDTO> ar = new ArrayList<NoticeDTO>();
		
		while(rs.next()) {
			
			NoticeDTO noticeDTO = new NoticeDTO();
			
			noticeDTO.setNum(rs.getInt("num"));
			noticeDTO.setTitle(rs.getString("title"));
			noticeDTO.setContents(rs.getString("contents"));
			noticeDTO.setWriter(rs.getString("writer"));
			noticeDTO.setReg_date(rs.getDate("reg_date"));
			noticeDTO.setHit(rs.getInt("hit"));
			
			ar.add(noticeDTO);
			
			
		}
				
		DBConnector.disConnect(con, st, rs);
		
		return ar;
		
	}

}
