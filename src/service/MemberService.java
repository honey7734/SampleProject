package service;

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
}
