package com.app.restful.controller;

import com.app.restful.domain.PostDTO;
import com.app.restful.domain.PostVO;
import com.app.restful.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts/api/*")
@RequiredArgsConstructor
@Slf4j
public class PostAPI {

    public final PostService postService;

//    게시글 전체 조회
    @Operation(summary = "게시글 전체 조회", description = "게시글 정보를 전체 조회할 수 있는 API")
    @GetMapping("posts")
    public List<PostDTO> getPosts() {
        return postService.getPosts();
    }

//    게시글 단일 조회
    @Operation(summary = "게시글 정보 조회", description = "게시글 1개의 정보를 조회할 수 있는 API")
    @Parameter(
            name = "id",
            description = "게시글 번호",
            schema = @Schema(type = "number"), //스키마 타입
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("post/{id}")
    public PostDTO getPost(@PathVariable Long id) {
        log.info("{}", id);
        Optional<PostDTO> foundPost = postService.getPost(id);
        if (foundPost.isPresent()) {
            return foundPost.get();
        }
        return new PostDTO();
    }
//    게시글 작성
    @Operation(summary = "게시글 작성", description = "게시글을 작성할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "게시글 작성 성공")
    @PostMapping("write")
    public PostDTO write(@RequestBody PostVO postVO) {
        postService.write(postVO);
        Optional<PostDTO> foundPost = postService.getPost(postVO.getId());
        if (foundPost.isPresent()) {
            return foundPost.get();
        }
        return new PostDTO();
    }
//    게시글 수정
//    PutMapping : 모든 컬럼을 수정할 때
//    PatchMapping : 부분 컬럼을 수정할 때
    @Operation(summary = "게시글 수정", description = "게시글 정보를 수정할 수 있는 API")
    @Parameter(
            name = "id",
            description = "게시글 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @PutMapping("post/{id}")
    public PostDTO modify(@PathVariable Long id, @RequestBody PostVO postVO) {
        postVO.setId(id);
        postService.modify(postVO);
        Optional<PostDTO> foundPost = postService.getPost(id);
        if (foundPost.isPresent()) {
            return foundPost.get();
        }
        return new PostDTO();
    }
//    게시글 삭제
    @Operation(summary = "게시글 삭제", description = "게시글을 삭제할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "게시글 삭제 성공")
    @Parameter(
            name = "id",
            description = "게시글 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @DeleteMapping("post/{id}")
    public void delete(@PathVariable Long id) {
        postService.remove(id);
    }
}
