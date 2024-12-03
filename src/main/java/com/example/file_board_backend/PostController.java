package com.example.file_board_backend;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository; 

    // 전체 게시물 조회 메서드
    @GetMapping("/post/list")
    public List<Post> getAllPosts() {
        // 모든 게시물을 조회하여 반환
        return postRepository.findAll();
    }
}

