package cist4830.unomaha.tempo.services;

import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import cist4830.unomaha.tempo.model.User;
import cist4830.unomaha.tempo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import cist4830.unomaha.tempo.services.CustomUserPrincipal;

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
}