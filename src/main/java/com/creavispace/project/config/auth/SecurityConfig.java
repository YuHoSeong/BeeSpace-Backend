package com.creavispace.project.config.auth;

import com.creavispace.project.domain.member.Role;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    private final CustomOauth2Service customOauth2Service;

    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(
                        auth -> auth.requestMatchers("/admin/**").hasRole(Role.ADMIN.name())
                                .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/resources", "/signup", "/about")
                                .permitAll().requestMatchers("/member/**").hasRole(Role.MEMBER.name()).anyRequest()
                                .authenticated()).logout(logout -> logout.logoutUrl("/"))
                .oauth2Login(login -> login.userInfoEndpoint(endPoint -> endPoint.userService(customOauth2Service)));
        return httpSecurity.build();
    }

    //    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth.requestMatchers("/admin/**").hasRole(Role.ADMIN.name())
//                .requestMatchers("/","/css/**","/images/**","/js/**","/resources", "/signup","/about").permitAll()
//                .requestMatchers("/member/**").hasRole(Role.MEMBER.name()).anyRequest().authenticated())
//                .logout(logout -> logout.logoutUrl("/"))
//                .oauth2Login(login -> login.userInfoEndpoint(endPoint -> endPoint.userService(customOauth2Service)));
//        return httpSecurity.build();
//    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.googleClientRegistration());
    }

    private ClientRegistration googleClientRegistration() {
        return ClientRegistration.withRegistrationId("google")
                .clientId("google-client-id")
                .clientSecret("google-client-secret")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .scope("openid", "email")
                .authorizationUri("")
                .tokenUri("")
                .userInfoUri("")
                .userNameAttributeName(IdTokenClaimNames.SUB)
                .clientName("Google")
                .redirectUri("http://localhost:8080") // 리디렉션될 URL을 설정합니다.

                .build();
    }

}
