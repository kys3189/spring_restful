package com.app.restful.mapper;

import com.app.restful.domain.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MemberMapperTest {

    @Autowired
    private MemberMapper memberMapper;

    //    회원 가입
    @Test
    public void insert() {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("test003@gmail.com");
        memberVO.setMemberPassword("1234");
        memberVO.setMemberName("이순신");

        memberMapper.insert(memberVO);
    }
    //    회원 조회
    @Test
    public void findMemberByIdTest() {
        Long id = 3L;
        MemberVO memberVO = memberMapper.select(id).get();
        log.info("memberVO: {}", memberVO);
    }
    //    회원 정보 수정
    @Test
    public void updateTest() {
        MemberVO memberVO = new MemberVO();
        memberVO.setId(3L);
        memberVO.setMemberEmail("test003@gmail.com");
        memberVO.setMemberPassword("1234");
        memberVO.setMemberName("현무암");
        memberMapper.update(memberVO);
    }
    //    회원 탈퇴
    @Test
    public void deleteTest() {
        Long id = 3L;
        memberMapper.delete(id);
    }

}
