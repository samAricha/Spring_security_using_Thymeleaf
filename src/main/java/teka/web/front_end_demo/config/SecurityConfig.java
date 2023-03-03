package teka.web.front_end_demo.config;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private CustomUserDetailsService userDetailsService;
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        return http
//                .csrf().disable()
//                .authorizeRequests(auth ->{
//                    auth.requestMatchers(HttpMethod.GET,"/").permitAll();
//                    auth.requestMatchers("/user").hasRole("USER");
//                    auth.requestMatchers("/admin").hasRole("ADMIN");
//                    auth.requestMatchers("/persons").authenticated();
//                    auth.requestMatchers(HttpMethod.POST).authenticated();
//                })
//                .httpBasic(Customizer.withDefaults())
//                .build();

        http
                .authorizeHttpRequests()
                .anyRequest().permitAll()
                .and()
                .csrf().disable();

        return http.build();

    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}

