package com.gsm.global.security.config;

import com.gsm.global.security.handler.JwtAccessDeniedHandle;
import com.gsm.global.security.handler.JwtAuthenticationEntryPoint;
import com.gsm.global.security.jwt.JwtFilter;
import com.gsm.global.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private final JwtAccessDeniedHandle jwtAccessDeniedHandle;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exceptionConfig ->
                        exceptionConfig.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                                .accessDeniedHandler(jwtAccessDeniedHandle)
                )

                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                                .requestMatchers(HttpMethod.POST, "/auth").permitAll()
                                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                                .requestMatchers(HttpMethod.PATCH, "/auth").permitAll()

                                .requestMatchers("/board/**").authenticated()

                                .requestMatchers(HttpMethod.GET,"/notice").authenticated()
                                .requestMatchers(HttpMethod.GET,"/notice/{id}").authenticated()
                                .requestMatchers(HttpMethod.POST,"/notice").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.PATCH,"/notice/{id}").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/notice/{id}").hasAnyAuthority("ROLE_ADMIN")

                                .requestMatchers("/role/**").hasAnyAuthority("ROLE_ADMIN")

                                .requestMatchers(HttpMethod.POST,"/inquiry").authenticated()
                                .requestMatchers(HttpMethod.GET,"/inquiry/{id}").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET,"/inquiry").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.PATCH,"/inquiry/approve/{id}").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.PATCH,"/inquiry/refusal/{id}").hasAnyAuthority("ROLE_ADMIN")


                                .anyRequest().authenticated()
                )

                .addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
}