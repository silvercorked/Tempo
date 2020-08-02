package cist4830.unomaha.tempo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import cist4830.unomaha.tempo.services.CustomUserDetailsService;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private transient PasswordEncoder passwordEncoder;

    @Autowired
    private transient JdbcTemplate jdbcTemplate;

    @Autowired
    private transient UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/", "/users/register").permitAll()
            .antMatchers("/**").authenticated()
            .and()
            .formLogin().loginPage("/login")
                .defaultSuccessUrl("/goals")
                .failureUrl("/login?error=true")
                .permitAll()
            .and()
            .logout().logoutSuccessUrl("/").invalidateHttpSession(true).permitAll()
            .and()
            .csrf().disable();
        http.headers().frameOptions().disable();
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder);
        auth.jdbcAuthentication()
            .dataSource(jdbcTemplate.getDataSource());
    }
}