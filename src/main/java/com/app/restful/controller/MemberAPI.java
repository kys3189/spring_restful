package com.app.restful.controller;

import com.app.restful.domain.MemberVO;
import com.app.restful.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/members/api/*")
@RequiredArgsConstructor
public class MemberAPI {

    private final MemberService memberService;
//    회원 가입
    @Operation(summary = "회원 가입", description = "회원가입을 위한 API")
    @ApiResponse(responseCode = "200", description = "회원가입 성공")
    @Parameter(
            name = "memberVO",
            description = "회원 정보",
            schema = @Schema(type = "object"),
            required = true
    )
    @PostMapping("write")
    public Map<String, Object> write(@RequestBody MemberVO memberVO) {
        memberService.saveMember(memberVO);
        Optional<MemberVO> member = memberService.getMemberInfo(memberVO.getId());
        Map<String, Object> response = new HashMap<>();
        if (member.isPresent()) {
            response.put("message", "로그인 성공");
            return response;
        }
        response.put("message", "로그인 실패");
        return response;
    }

//    회원 조회
//    url 파라미터 : 모든 컨트롤러에서 사용이 가능하지만 보통 rest에서 사용된다.
    @Operation(summary = "회원 조회", description = "회원 단일 조회를 위한 API")
    @Parameter(
            name = "id",
            description = "회원 아이디",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("member/{id}")
    public MemberVO getMember(@PathVariable Long id) {
        Optional<MemberVO> foundMember = memberService.getMemberInfo(id);
        if (foundMember.isPresent()) {
            return foundMember.get();
        }
//        잘못 전달하면 빈 객체로 전달한다.
//        exception 보다는 null을 보내서 값을 잘못 전달하게 처리한다.
//        그래서 대부분 Optional로 안보낼때가 많지만 상세하게 전달할 때에는 Optional로 전달한다.
        return new MemberVO();
    }

//    회원 정보 수정
    @Operation(summary = "회원 정보 수정", description = "회원 정보 수정을 위한 API")
    @Parameter(
            name = "id",
            description = "회원의 아이디",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @PutMapping("update/{id}")
    public MemberVO update(@PathVariable Long id, @RequestBody MemberVO memberVO) {
        memberService.updateMember(memberVO);
        Optional<MemberVO> member = memberService.getMemberInfo(id);
        if (member.isPresent()) {
            return member.get();
        }
        return new MemberVO();
    }
//    회원 탈퇴
    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴를 위한 API")
    @DeleteMapping("delete")
    public void delete(@RequestBody String id) {
        memberService.deleteMember(Long.parseLong(id));
    }

}
