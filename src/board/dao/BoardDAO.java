package board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;

import board.vo.BoardVO;

public class BoardDAO {

	private SqlSession session;
	
	public BoardDAO() {
		session = new MyConfig().getInstance();
	}
	
	public void insert() {
		String title = "현재시각 공구시사십사분... 오전수업중...";
		String writer = "백청현";
		
		BoardVO board = new BoardVO();
		board.setTitle(title);
		board.setWriter(writer);
		
		int cnt = session.insert("board.dao.BoardDAO.insert", board);
		session.commit();
		System.out.printf("총 %d개의 행 삽입!\n", cnt);
	}
	
	public void select() {
		//전체게시글 조회목적
//		List<BoardVO> list = session.selectList("board.dao.BoardDAO.selectAll");
		List<BoardVO> list = session.selectList("board.dao.BoardDAO.selectAll2");
		for(BoardVO board : list) {
			System.out.println(board);
		}
	}
	
	
	public void selectOne() {
		//1. 번호 직접전달
		int boardNo = 41;
		BoardVO result = session.selectOne("board.dao.BoardDAO.selectByNo", boardNo);
		System.out.println("번호로 찾기 >> " + result);
		
		//2. 또는 VO에 Setter로 전달
		BoardVO board = new BoardVO();
		board.setNo(41);
		result = session.selectOne("board.dao.BoardDAO.selectByBoardNo", board);
		System.out.println("VO로 찾기 >> " + result);
		
	}
	
	
	public void selectWhere() {
		
		/*
		//1. 제목이지롱 이면서 글쓴이가 백청현인 게시물 조회
		BoardVO board = new BoardVO();
		board.setTitle("제목이지롱");
		board.setWriter("백청현");
		
		List<BoardVO> list = session.selectList("board.dao.BoardDAO.selectWhere", board);
		for(BoardVO vo : list) {
			System.out.println(vo);
		}
		*/
		
		/*
		//2.글쓴이가 백청현인 게시물 조회
		BoardVO board = new BoardVO();
		board.setWriter("백청현");
		
		List<BoardVO> list = session.selectList("board.dao.BoardDAO.selectWhere", board);
		for(BoardVO vo : list) {
			System.out.println(vo);
		}
		*/
		
		//3. 제목이 xxx인 게실물 조회
		BoardVO board = new BoardVO();
		board.setTitle("현재시각 공구시사십사분... 오전수업중...");
		
		List<BoardVO> list = session.selectList("board.dao.BoardDAO.selectWhere", board);
		for(BoardVO b : list) {
			System.out.println(b);
		}
	}
	
	
	public void selectWhere2() {
		//제목이 제목이지롱, 작성자가 백청현인 게시물 조회
		
		//key가 title과 writer라는 점에 주목할 것! 
		Map<String, String> board = new HashMap<>();
		board.put("title", "현재시각 십칠시삼십오분. 오후일과를 종료합니다.");
		board.put("writer", "백청현");
		
		List<BoardVO> list = session.selectList("board.dao.BoardDAO.selectWhere2", board);
		for(BoardVO b : list) {
			System.out.println(b);
		}
	}
	
	public void selectWhere3() {
		Map<String, String> board = new HashMap<>();
		board.put("no", "41");
		
		Map<String, Object> result = session.selectOne("board.dao.BoardDAO.selectWhere3", board);
		
		Set<String> keys = result.keySet();
		for(String key : keys) {
			System.out.println("key : " + key + ", value : " + result.get(key));
		}
	}
	
	public void selectNos() {
		int[] nos = {2, 3, 5, 20, 41};
		
		List<BoardVO> list = session.selectList("board.dao.BoardDAO.selectNos", nos);
		
		for(BoardVO vo : list) {
			System.out.println(vo);
		}
	}
	
	public void work() {
		
		selectNos();
//		selectWhere3();
//		selectWhere2();
//		selectWhere();
//		selectOne();
//		select();
//		insert();
	}
	
}
