package com.example.junitProject.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest	// DB와 관련된 컴포넌트만 메모리에 로딩
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository bookRepository;
	
	@BeforeEach
	public void 데이터준비() {
		String title = "junit5";
		String author = "겟인데어";
		Book book = Book.builder()
				.title(title)
				.author(author)
				.build();
		bookRepository.save(book);
	}
	
	// 1. 책 등록
	@Test
	public void 책등록_test() {
		// given(데이터 준비)
		String title = "junit5";
		String author = "메타코딩";
		Book book = Book.builder()
				.title(title)
				.author(author)
				.build();
		
		// when(테스트 실행)
		Book bookPS = bookRepository.save(book);
		
		// then(검증)
		assertEquals(title, bookPS.getTitle());
		assertEquals(author, bookPS.getAuthor());
	} // 트랜잭션 종료 (저장된 데이터 초기화함)
	
	// 2. 책 목록보기
	@Test
	public void 책목록_test() {
		// given
		String title = "junit5";
		String author = "겟인데어";
		
		// when 
		List<Book> books = bookRepository.findAll();
		
		// then 
		assertEquals(title, books.get(0).getTitle());
		assertEquals(author, books.get(0).getAuthor());
	} // 트랜잭션 종료 (저장된 데이터 초기화함)
	
	// 3. 책 한건 보기
	@Test
	public void 책한건보기_test() {
		// given
		String title = "junit5";
		String author = "겟인데어";
		
		// when 
		Book bookPS = bookRepository.findById(1L).get();
		
		// then 
		assertEquals(title, bookPS.getTitle());
		assertEquals(author, bookPS.getAuthor());
	} // 트랜잭션 종료 (저장된 데이터 초기화함)

}
