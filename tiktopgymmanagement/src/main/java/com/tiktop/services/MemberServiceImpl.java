package com.tiktop.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tiktop.dao.AttendanceDao;
import com.tiktop.dao.MemberDao;
import com.tiktop.model.Member;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao memberDao;
	
	@Autowired
	AttendanceDao attendanceDao;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public void save(Member member) {
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		member.setRole("ROLE_MEMBER");
		member.setEnable(true);
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

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Member member = findMemberByLogin(login);
		if (member == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	    String role = member.getRole();
	    grantedAuthorities.add(new SimpleGrantedAuthority(role));
	    return new org.springframework.security.core.userdetails.User(member.getLogin(), member.getPassword(), grantedAuthorities);
	}



	public Member findMemberByLogin(String login) {
		Member member = new Member();
		member.setLogin(login);
		member = memberDao.findMember(member).get(0);
		return member;
	}
	
}
