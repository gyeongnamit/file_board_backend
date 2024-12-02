package com.example.file_board_backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FileBoardBackendApplicationTests {

	@Autowired
	PostRepository repository;

	@Test
	void contextLoads() {
		
		//데이터베이스에 추가할 게시물 정보를 입력 합니다
		Post post  = new Post();
		post.setTitle("공지사항");
		post.setAuthor("운영자");
		post.setContent("테스트 입니다");
		post.setAttachment_url("log.png");
		//게시물에 데이터 추가
		repository.save(post);

	}
}
