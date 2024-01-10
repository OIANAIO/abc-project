package com.project.ABCDEproject.vo;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member implements UserDetails{
	
	int id;
	String memberid;
	String memberpw;
	String email;
	String phone;
	boolean enabled;
	String create_at;
	String last_login;
	String thumbnail;
	String originalthumbnail;
	int point;
	String rolename;
	
	String teamList;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	@Override
	public String getPassword() {
		return this.memberpw;
	}
	@Override
	public String getUsername() {
		return this.memberid;
	}
	
	public int getUserID() {
		return this.id;
	}
	
	public boolean isAdmin()
	{
		if(rolename.equals("ROLE_ADMIN")) return true;
		else return false;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
}
