package us.peaksoft.gadgetarium.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationProvider authenticationProvider;

    private static final String[] AUTH_WHITELIST = {
            "/api/v1/auth/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/public/**").permitAll()
                .requestMatchers("/api/profile/**").permitAll()
                .requestMatchers(AUTH_WHITELIST).permitAll()
                .requestMatchers("/api/public/register")
                .permitAll()
                .requestMatchers("/api/products/**").permitAll()
                .requestMatchers("/api/discounts/**").permitAll()
                .requestMatchers("/api/feedbacks/**").permitAll()
                .requestMatchers("/api/wishlist/**").permitAll()
                .requestMatchers("/api/cart/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider);
        return http.build();
    }

}
