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
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/", "/users/register").permitAll()
            .antMatchers("/**").hasAnyRole()
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
        auth.jdbcAuthentication().dataSource(jdbcTemplate.getDataSource())
            .passwordEncoder(passwordEncoder)
            .usersByUsernameQuery("SELECT id, username, password FROM user WHERE username = ?");
    }
}