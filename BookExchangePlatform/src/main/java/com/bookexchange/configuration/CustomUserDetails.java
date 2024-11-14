package com.bookexchange.configuration;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	private String fullname;
	private Collection<? extends GrantedAuthority> authorities;
	
	public CustomUserDetails(String email, String password, Collection<? extends GrantedAuthority> authorities, String fullname) {
		super();
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		this.fullname = fullname;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	public String getFullname() {
		return fullname;
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
		return true;
	}
}
