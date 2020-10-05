package kr.hkit.exam09.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import kr.hkit.exam09.BoardVO;
import kr.hkit.exam09.Query;

class QueryTest {

	@BeforeAll
	static void start() {
		Query.createTable();
	}
	
	
	@AfterAll
	static void end() {
		Query.dropTable();
	}
	
	@BeforeEach
	void before() {
		Query.boardDelete(0);
		
		BoardVO bv1 = new BoardVO();
		bv1.setBtitle("타이틀1");
		bv1.setBcontent("내용1");
		Query.boardInsert(bv1);
		
		BoardVO bv2 = new BoardVO();
		bv2.setBtitle("타이틀2");
		bv2.setBcontent("내용2");
		Query.boardInsert(bv2);
	}
	
	@Test
	void testA() {
		List<BoardVO> list = Query.getAllBoardList();
		System.out.println("size : " + list.size());
		Assert.assertEquals(2, list.size());
		
		BoardVO vo1 = list.get(0);
		Assert.assertEquals("타이틀1", vo1.getBtitle());
		Assert.assertEquals("내용1", vo1.getBcontent());
		
		BoardVO vo2 = list.get(1);
		Assert.assertEquals("타이틀2", vo2.getBtitle());
		Assert.assertEquals("내용2", vo2.getBcontent());
		
//		for(BoardVO i : list) {
//			System.out.println("제목: " + i.getBtitle());
//			System.out.println("내용: " + i.getBcontent());
//			
//		}
		
	
	}
	
	@Test
	void testB() {
		List<BoardVO> list = Query.getAllBoardList();
		BoardVO vo1 = list.get(0);
		Query.boardDelete(vo1.getBid());
		BoardVO vo1Db = Query.getBoardDetail(vo1.getBid());
		Assert.assertEquals(0, vo1Db.getBid());
		Assert.assertNull(vo1Db.getBtitle());
		Assert.assertNull(vo1Db.getBcontent());
		
		Assert.assertEquals(1, Query.getAllBoardList().size());
		
		BoardVO vo2 = list.get(1);
		Query.boardDelete(vo2.getBid());
		BoardVO vo2Db = Query.getBoardDetail(vo2.getBid());
		Assert.assertEquals(0, vo1Db.getBid());
		Assert.assertNull(vo1Db.getBtitle());
		Assert.assertNull(vo1Db.getBcontent());
		
		Assert.assertEquals(0, Query.getAllBoardList().size());
		
		
	}

}
