package com.app.restful.service;

import com.app.restful.domain.MemberVO;

import java.util.Optional;

public interface MemberService {
//    회원 가입
    public void saveMember(MemberVO memberVO);
//    회원 조회
    public Optional<MemberVO> getMemberInfo(Long id);
//    회원 정보 수정
    public void updateMember(MemberVO memberVO);
//    회원 탈퇴
    public void deleteMember(Long id);
}
