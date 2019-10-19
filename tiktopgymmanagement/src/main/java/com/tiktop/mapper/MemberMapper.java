package com.tiktop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tiktop.model.Member;

@Mapper
public interface MemberMapper {
	public void save(@Param("member") Member member);
	
	public List<Member> findMember(@Param("member") Member member);
	
	public void update(@Param("member") Member member);
	
	public void delete(@Param("id") Integer id);
}
