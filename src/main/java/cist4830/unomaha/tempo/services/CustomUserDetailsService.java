package cist4830.unomaha.tempo.services;

import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import cist4830.unomaha.tempo.model.User;
import cist4830.unomaha.tempo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

@Service
public class CustomUserDetailsService implements UserDetailsService, Serializable {
 
	private static final long serialVersionUID = 998877L;

    @Autowired
    private transient UserRepository userRepository;
 
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserPrincipal(user);
	}
	
	public class CustomUserPrincipal implements UserDetails {
		private User user;

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
}