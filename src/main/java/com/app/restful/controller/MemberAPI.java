package com.app.restful.controller;

import com.app.restful.domain.MemberVO;
import com.app.restful.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/api/*")
public class MemberAPI {

    private final MemberService memberService;

    //    url 파라미터 : 모든 컨트롤러에서 사용이 가능하지만 보통 rest에서 사용된다.
    @GetMapping("member/{id}")
    public MemberVO getMember(@PathVariable Long id){

        Optional<MemberVO> foundMember = memberService.getMemberInfo(id);
        if(foundMember.isPresent()){
            return foundMember.get();
        }

//       잘못 전달하면 빈 객체를 전달한다.
//       exception 보다는 null을 보내서 값을 잘못 전달하게 처리한다.
//       그래서 대부분 Optional로 안보낼때가 많지만 상세하게 전달할 때에는 Optional로 전달한다.
        return new MemberVO();
    }

    //    회원 가입
    @Operation(summary = "회원 가입", description = "회원 가입을 할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "회원 가입 완료")
    @PostMapping("join")
    public void join(@RequestBody MemberVO memberVO) {
        memberService.saveMember(memberVO);
    }

    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "회원 수정 완료")
//    회원 정보 수정
    @PutMapping("modify")
    public void modify(@RequestBody MemberVO memberVO) {
        memberService.updateMember(memberVO);
    }

    //    회원 탈퇴
    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴 할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "회원 탈퇴 완료")
    @DeleteMapping("withdraw/{id}")
    public void withdraw(@PathVariable Long id) {
//        세션에 저장된 회원의 id
        memberService.deleteMember(id);
    }






}
