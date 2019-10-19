package com.tiktop.services;

import java.util.List;

import com.tiktop.model.Member;

public interface MemberService {

	void save(Member member);

	List<Member> findMember(Member member);

	void delete(Integer id);

	void update(Member member);
}
