package jp.co.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

/**
 * セキュリティ設定
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	/**
	 * 認証認可設定
	 * 
	 * @param http {@link HttpSecurity}
	 * @return {@link SecurityFilterChain}
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
			.cors(Customizer.withDefaults())
			.authorizeHttpRequests(authorize -> authorize
					.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
					.anyRequest().authenticated())
			.securityContext(securityContext ->securityContext
					.requireExplicitSave(false));
		
		
		return http.build();
	}
}
