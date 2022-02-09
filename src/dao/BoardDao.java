package dao;

import java.util.List;
import java.util.Map;

import utill.JDBCUtill;

public class BoardDao {
	
	private BoardDao() {
		
	}
	private static BoardDao instance;
	public static BoardDao getInstance() {
		if(instance == null) {
			instance = new BoardDao();
		}
		return instance;
	}
	
	public List<Map<String, Object>> selectBoardList(){
		String sql = "SELECT A.BOARD_NO"
				+ "		   , A.TITTLE"
				+ "		   , B.MEM_NAME"
				+ "		   , TO_CHAR(A.REG_DATE, 'MM/DD') AS REG_DATE"
				+ "		FROM TB_JDBC_BOARD A"
				+ "	    LEFT OUTER JOIN TB_JDBC_MEMBER B ON A.MEM_ID = B.MEM_ID"
				+ "    ORDER BY A.BOARD_NO DESC";
		
		return JDBCUtill.selectList(sql);
		
	}
	
}
