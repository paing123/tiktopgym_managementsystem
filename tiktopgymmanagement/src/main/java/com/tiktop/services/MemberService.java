package com.tiktop.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.tiktop.model.Member;

public interface MemberService extends UserDetailsService{

	void save(Member member);

	List<Member> findMember(Member member);
	
	Member findMemberByLogin(String login);

	void delete(Integer id);

	void update(Member member);
}
