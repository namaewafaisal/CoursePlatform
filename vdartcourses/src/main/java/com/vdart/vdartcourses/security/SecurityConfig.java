package com.vdart.vdartcourses.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.vdart.vdartcourses.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig  {

    @Autowired
    private JwtFilter jwtFilter;

    // 
    // /auth/login
    // /api/users/auth/signup
    // /api/users/auth/signup/post
    // /api/users/auth/login
    // /api/subtopics/courseid/{courseId}/add
    // /api/quizzes/quizid/{quizid}/question/add
    // /api/quizzes/courseid/{courseId}/
    // /api/questions/add
    // /api/enrollments/userid/{userId}/courseid/{courseId}/enrollment/add
    // /api/courses/courseid/{id}/subtopic/add
    // /api/courses/add
    // /api/certificates/post
    // /api/users/userid/{id}
    // /api/users/search
    // 
    // 
    // 
    // /api/quizzes/quizid/{id}
    // /api/quizzes/courseid/{courseId}/questions/all
    // /api/quizzes/all
    // /api/questions/quizid/{quizId}
    // 
    // /api/enrollments/userid/{userId}/course/all
    // /api/courses/search/coursetitlekeyword/{keyword}
    // 
    // /api/courses/coursekey/{courseKey}/subtopic/{subtopic}
    // 
    // 
    // 
    // 
    // 
    // 
    


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz
        .requestMatchers( "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html").permitAll()
        .anyRequest().permitAll())
        .csrf(csrf -> csrf.disable())
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) ;
        
        return http.build();
    }
     @Bean
    public UserDetailsService userDetailsService() {
        // UserDetails user = User.withUsername("faisal")
        //     .password(passwordEncoder.encode("password"))
        //     .roles("USER")
        //     .build();
        
        //     UserDetails admin = User.withUsername("admin")
        //     .password(passwordEncoder.encode("password"))
        //     .roles("ADMIN")
        //     .build();
        
        //     return new InMemoryUserDetailsManager(user, admin);  

        return new CustomUserDetailsService();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
}
    @Bean
    public AuthenticationManager authenticationManager(){
        return new ProviderManager(
            authenticationProvider()
        );
    }

}
