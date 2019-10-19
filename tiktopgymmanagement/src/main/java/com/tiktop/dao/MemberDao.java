package com.tiktop.dao;

import java.util.List;

import com.tiktop.model.Member;

public interface MemberDao {

	void save(Member member);
	
	 List<Member> findMember(Member member);
	 
	 void delete(Integer id);
	 
	 void update(Member member);
}
