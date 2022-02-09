package service;

import java.util.List;
import java.util.Map;

import dao.BoardDao;
import utill.ScanUtil;
import utill.View;

public class BoardService {
	
	private BoardService() {
		
	}
	private static BoardService instance;
	public static BoardService getInstance() {
		if(instance == null) {
			instance = new BoardService();
		}
		return instance;
	}
	
	private BoardDao boardDao = BoardDao.getInstance();
	
	public int boardList() {
		List<Map<String, Object>> boardList = boardDao.selectBoardList();
		
		System.out.println("=======================================");
		System.out.println("번호\t제목\t작성자\t작성일");
		System.out.println("---------------------------------------");
		for(Map<String,Object> board : boardList) {
			System.out.println(board.get("BOARD_NO")
				    + "\t" + board.get("TITTLE")
				    + "\t" + board.get("MEM_NAME")
				    + "\t" + board.get("REG_DATE"));
		}
		System.out.println("=======================================");
		System.out.println("1.조회  2.등록  0.로그아웃");
		
		int intput = ScanUtil.nextInt();
		
		switch (intput) {
		case 1: return View.BOARD_READ;
		case 2: return View.BOARD_INSERT;
		case 0:
			MemberService.loginMember = null;
			return View.HOME;
		}
		return View.BOARD_LIST;
	}
}
