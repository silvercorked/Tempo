package cist4830.unomaha.tempo.services;

import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.LinkedList;
import cist4830.unomaha.tempo.model.User;
import org.springframework.security.core.GrantedAuthority;

public class CustomUserPrincipal implements UserDetails {
	private User user;

	public User getUser() {
		return this.user;
	}

	public CustomUserPrincipal(User user) {
		this.user = user;
	}

	public boolean isEnabled() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public String getUsername() {
		return this.user.getUsername();
	}

	public String getPassword() {
		return this.user.getPassword();
	}

	public Collection<GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> auths = new LinkedList<GrantedAuthority>();
		auths.add(() -> "user");
		return auths;
	}
}