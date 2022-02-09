package service;

import java.util.HashMap;
import java.util.Map;

import dao.MemberDao;
import utill.ScanUtil;
import utill.View;

public class MemberService {

	//싱글톤 패턴 : 하나의 객체를 돌려쓰게 만들어주는 디자인 패턴
	//생성자를 private로 보호해서 다른 클래스에서 호출하지 못하게 해서 여러개 생성하지 못하게 해줌
	//같은 클래스에서 쓸땐 객체를 보관할 공간을 만들어두고 (instance) 객체가 필요할때마다 메소드(getInstance)를 호출해서 사용한다
	//객체가 없으면 생성해서 리턴 있으면 새로생성하지 않고 있는 객체를 리턴
	private MemberService() {
		
	}
	private static MemberService instance;
	public static MemberService getInstance() {
		if(instance == null) {
			instance = new MemberService();
		}
		return instance;
	}
	
	public static Map<String, Object> loginMember;
	
	private MemberDao memberDao = MemberDao.getInstance();
	
	public int join() {
		System.out.println("=========== 회원가입 ===========");
		System.out.print("아이디>");
		String memId = ScanUtil.nextLine();
		System.out.println("비밀번호>");
		String password = ScanUtil.nextLine();
		System.out.println("이름>");
		String memName = ScanUtil.nextLine();
		
		//아이디 중복확인
		//비밀번호 확인
		//유효성 검사(정규표현식)
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("MEM_ID", memId);
		param.put("PASSWORD", password);
		param.put("MEM_NAME", memName);
		
		int result = memberDao.insertMember(param); //데이터베이스접속 내용을 따로 분리해서 재사용이 용이하게 하기 위함
		
		if(0 < result) {
			System.out.println("회원가입 성공");
		}else {
			System.out.println("회원가입 실패");
		}
		
		return View.HOME;
		
	}

	public int login() {
		System.out.println("=========== 로그인 ===========");
		System.out.println("아이디>");
		String memId = ScanUtil.nextLine();
		System.out.println("비밀번호>");
		String password = ScanUtil.nextLine();
		
		Map<String,Object> member = memberDao.selectMember(memId, password);
		
		if(member == null) {
			System.out.println("아이디 혹은 비밀번호를 잘못 입력하셨습니다.");
		}else {
			System.out.println("로그인 성공");
			loginMember = member;
			return View.BOARD_LIST;
		}
		
		return View.LOGIN;   //또는 홈화면(View.Home)
	}
}
