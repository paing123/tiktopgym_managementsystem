package com.tiktop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiktop.dao.MemberDao;
import com.tiktop.model.Member;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao memberDao;

	@Override
	public void save(Member member) {
		memberDao.save(member);
	}

	@Override
	public List<Member> findMember(Member member) {
		return memberDao.findMember(member);
	}
	
	@Override
	public void delete(Integer id) {
		memberDao.delete(id);
	}

	@Override
	public void update(Member member) {
		memberDao.update(member);
	}
}
