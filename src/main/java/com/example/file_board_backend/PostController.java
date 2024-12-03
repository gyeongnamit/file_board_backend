package com.example.file_board_backend;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.regions.*;
import software.amazon.awssdk.services.s3.*;
import software.amazon.awssdk.services.s3.model.*;
import java.io.*;
import java.nio.file.*;


@RestController
public class PostController {

    //@Autowired
    private PostRepository postRepository; 

    private final S3Client s3Client;
    private final String bucketName;

    @Autowired
    public PostController(PostRepository postRepository, 
                            
                            @Value("${aws.region}") String region,

                            @Value("${aws.bucket-name}")String bucketName,

                            @Value("${aws.access-key}") String accessKey,

                            @Value("${aws.secret-key}") String secretKey
                            ) {
                                
    
        this.postRepository = postRepository;
        
        this.bucketName = bucketName;

        AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);
        
        this.s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();

        System.out.println("Post Initialize End");
    }

    @PostMapping("/post/create")
    public String createPost(@ModelAttribute Post post, 
                       @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

            String fileUrl = null;

            if (file!=null && !file.isEmpty()){
            
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            
                Path tempFile = Files.createTempFile(null, file.getOriginalFilename());
            
                file.transferTo(tempFile);
        
                PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();
        
                s3Client.putObject(putObjectRequest, tempFile);
        
                fileUrl = String.format("https://%s.s3.ap-northeast-2.amazonaws.com/%s", bucketName, fileName); 

            }
            
            post.setAttachment_url(fileUrl);         
                        
            postRepository.save(post);

            return "post create success";        
    }


    // 전체 게시물 조회 메서드
    @GetMapping("/post/list")
    public List<Post> getAllPosts() {
        // 모든 게시물을 조회하여 반환
        return postRepository.findAll();
    }
}

