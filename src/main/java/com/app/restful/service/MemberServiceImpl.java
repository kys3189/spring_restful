package com.app.restful.service;

import com.app.restful.domain.MemberVO;
import com.app.restful.domain.PostDTO;
import com.app.restful.repository.MemberDAO;
import com.app.restful.repository.PostDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MemberServiceImpl implements MemberService {

    private final MemberDAO memberDAO;
    private final PostDAO postDAO;

    @Override
    public void saveMember(MemberVO memberVO) {
        memberDAO.save(memberVO);
    }

    @Override
    public Optional<MemberVO> getMemberInfo(Long id) {
        return memberDAO.find(id);
    }

    @Override
    public void updateMember(MemberVO memberVO) {
        memberDAO.update(memberVO);
    }

    @Override
    public void deleteMember(Long id) {
        memberDAO.delete(id);
        postDAO.deleteAll(id);
    }
}
