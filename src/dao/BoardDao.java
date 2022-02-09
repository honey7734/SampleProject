package dao;

import java.util.ArrayList;
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

	public static int insertBoard(Map<String, Object> param) {
		//글 번호, 제목, 내용, 아이디, 작성일자
		String sql = "INSERT INTO TB_JDBC_BOARD"
				+ "	       VALUES((SELECT NVL(MAX(BOARD_NO),0)+1 FROM TB_JDBC_BOARD)"
				+ "				  , ?,?,?"
				+ "				  ,SYSDATE)";
		
		
		List<Object> _param = new ArrayList<Object>();
		_param.add(param.get("TITTLE"));
		_param.add(param.get("CONTENT"));
		_param.add(param.get("MEM_ID"));
		
		return JDBCUtill.update(sql, _param);
	}

	public static Map<String, Object> selectBoard(int boardNo) {
		String sql = "SELECT A.BOARD_NO"
				+ "        , A.TITTLE"
				+ "        , A.CONTENT"
				+ "		   , B.MEM_NAME"
				+ "		   , TO_CHAR(A.REG_DATE, 'MM/DD') AS REG_DATE"
				+ "     FROM TB_JDBC_BOARD A"
				+ "     LEFT OUTER JOIN TB_JDBC_MEMBER B ON A.MEM_ID = B.MEM_ID"
				+ "    WHERE A.BOARD_NO = ?";
		
		List<Object> param = new ArrayList<Object>();
		param.add(boardNo);
		
		return JDBCUtill.selectOne(sql, param);
	}

	public static int updateBoard(List<Object> param) {
		String sql = "UPDATE TB_JDBC_BOARD"
				+ "		 SET TITTLE = ?"
				+ "		   , CONTENT = ?"
				+ "	   WHERE BOARD_NO = ?";
		
		return JDBCUtill.update(sql, param);
	}

	public static int deleteBoard(List<Object> param) {
		String sql = "DELETE FROM TB_JDBC_BOARD"
				+ "	   WHERE BOARD_NO = ?";
		
		return JDBCUtill.update(sql, param);
		
	}
	
}
