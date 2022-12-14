package com.example.date_scheduling.post.api;

import com.example.date_scheduling.post.dto.FindAllPostDto;
import com.example.date_scheduling.post.dto.PostDto;
import com.example.date_scheduling.post.entity.Post;
import com.example.date_scheduling.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j  // 로깅을 위해
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@CrossOrigin    // 다른 서버의 요청 허용
public class PostApiController {

    private final PostService service;

    // 게시물 목록 전체 조회 요청
    @GetMapping
    public ResponseEntity<?> posts() {
        log.info("/api/posts GET request!");
        return ResponseEntity.ok().body(service.findAllServ());
    }

    // 게시물 개별 조회 요청
    @GetMapping("/{postId}")
    public ResponseEntity<?> findOne(@PathVariable String postId) {
        log.info("/api/posts/{} GET request!", postId);

        if (postId == null) return ResponseEntity.badRequest().build();

        PostDto dto = service.findOneServ(postId);

        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(dto);
    }

    /**
     * 조회 - Get
     * 등록 - Post
     * 수정 - Put
     * 삭제 - Delete
     */

    // 게시물 목록 등록 요청
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Post newPost) {

        log.info("/api/posts POST request! - {}", newPost);

        try {
            FindAllPostDto dto = service.createServ(newPost);

            if (dto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 게시물 삭제 요청
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> delete (@PathVariable String postId) {

        log. info("/api/posts/{} DELETE request!", postId);

        try {
            FindAllPostDto dtos = service.deleteServ(postId);
            return ResponseEntity.ok().body(dtos);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 게시물 수정 요청
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Post post) {

        log.info("/api/posts PUT request!", post);

        try {
            FindAllPostDto dtos = service.update(post);
            return ResponseEntity.ok().body(dtos);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
