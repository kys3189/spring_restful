package com.app.restful.mapper;

import com.app.restful.domain.PostDTO;
import com.app.restful.domain.PostVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class PostMapperTests {

    @Autowired
    private PostMapper postMapper;

    @Test
    public void selectAllTest() {
        List<PostDTO> postDTO = postMapper.selectAll();
        postDTO.forEach(postDTO1 -> log.info("{}", postDTO1));
    }

    @Test
    public void selectByIdTest() {
        log.info("{}", postMapper.selectById(1L));
    }

    @Test
    public void insertTest() {
        PostVO postVO = new PostVO();

        postVO.setPostTitle("테스트 제목100");
        postVO.setPostContent("테스트 내용100");
        postVO.setMemberId(2L);

        postMapper.insert(postVO);
    }

    @Test
    public void updateTest() {
        PostVO postVO = new PostVO();

        postVO.setId(51L);
        postVO.setPostTitle("테스트 제목");
        postVO.setPostContent("테스트 내용");

        postMapper.update(postVO);
    }

    @Test
    public void deleteTest() {
        postMapper.delete(51L);
    }

    @Test
    public void deleteAllTest() {
        postMapper.deleteAll(2L);
    }
}
