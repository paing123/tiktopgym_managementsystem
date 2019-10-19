package com.tiktop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tiktop.mapper.MemberMapper;
import com.tiktop.model.Member;

@Repository("memberRepository")
public class MemberDaoImpl implements MemberDao {

	@Autowired
	MemberMapper memberMapper;
	
	public void save(Member member) {
		memberMapper.save(member);
	}
	
	public List<Member> findMember(Member member) {
		List<Member> members = memberMapper.findMember(member);
		return members;
	}
	
	public void delete(Integer id) {
		memberMapper.delete(id);
	}
	
	public void update(Member member) {
		memberMapper.update(member);
	}
}
