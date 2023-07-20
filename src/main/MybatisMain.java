package main;

import board.dao.BoardDAO;

public class MybatisMain {

	
	public static void main(String[] args) {
		
		BoardDAO dao = new BoardDAO();
		dao.work();

	}
	
}
