package com.example.file_board_backend;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Post")
@Data
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;

    private String title;
    private String content;
    private String author;
    private LocalDateTime created_at;
    private String attachment_url;

    // 객체가 DB에 저장되기 전에 created_at을 자동으로 설정
    @PrePersist
    public void prePersist() {
        if (created_at == null) {
            created_at = LocalDateTime.now();
        }
    }
}
