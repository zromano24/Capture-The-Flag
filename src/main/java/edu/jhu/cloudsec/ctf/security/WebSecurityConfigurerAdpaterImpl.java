package edu.jhu.cloudsec.ctf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
class WebSecurityConfigurerAdapterImpl extends WebSecurityConfigurerAdapter {
    private static final String USER_ROLE = "USER";
    private static final String ADMIN_ROLE = "USER";

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationSuccessHandler successHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("user").password(passwordEncoder.encode("user")).roles(USER_ROLE)
                .and()
                .withUser("admin").password(passwordEncoder.encode("admin")).roles(USER_ROLE, ADMIN_ROLE);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable().authorizeRequests()
                .antMatchers("/", "/login", "/css/**", "js/**").permitAll()
                .antMatchers("/admin").hasAnyRole(ADMIN_ROLE, USER_ROLE)
                .antMatchers("/user").hasAnyRole(ADMIN_ROLE, USER_ROLE)
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(successHandler)
        ;
    }

}
