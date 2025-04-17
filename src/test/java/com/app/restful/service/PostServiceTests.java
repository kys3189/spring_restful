package com.app.restful.service;

import com.app.restful.domain.PostDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class PostServiceTests {

    @Autowired
    private PostService postService;

    @Test
    public void testPostService() {
        List<PostDTO> posts = postService.getPosts();
        posts.forEach(post -> {
            log.info("Post ID: {}", post);
        });
    }

}
