package cist4830.unomaha.tempo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/", "/home").permitAll()
        .antMatchers("/admin", "/h2_console/**").permitAll();
    http.csrf().disable();
    http.headers().frameOptions().disable();
  }
}