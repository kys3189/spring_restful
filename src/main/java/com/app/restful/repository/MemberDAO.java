package com.app.restful.repository;

import com.app.restful.domain.MemberVO;
import com.app.restful.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberDAO {

    private final MemberMapper memberMapper;
    
//    회원 가입
    public void save(MemberVO memberVO) {
        memberMapper.insert(memberVO);
    }
//    회원 조회
    public Optional<MemberVO> find(Long id){
        return memberMapper.select(id);
    }
//    회원 정보 수정
    public void update(MemberVO memberVO) {
        memberMapper.update(memberVO);
    }
//    회원 탈퇴
    public void delete(Long id) {
        memberMapper.delete(id);
    }
}
