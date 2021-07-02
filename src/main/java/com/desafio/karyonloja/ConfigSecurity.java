package com.desafio.karyonloja;

import com.desafio.karyonloja.security.oauth2.OrganizationSubClaimAdapter;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                    .authorizeRequests()
                        .antMatchers(HttpMethod.GET, "/api/empresa/**", "/api/lojafisica/**")
                            .hasAuthority("SCOPE_authorities")
                        .antMatchers(HttpMethod.POST, "/api/empresa/**","/api/lojafisica/**")
                            .hasAuthority("SCOPE_authorities")
                        .antMatchers(HttpMethod.PUT, "/api/empresa/**","/api/lojafisica/**")
                            .hasAuthority("SCOPE_authorities")
                        .antMatchers(HttpMethod.DELETE, "/api/empresa/**","/api/lojafisica/**")
                            .hasAuthority("SCOPE_authorities")
                        .anyRequest()
                            .authenticated()

                .and()
                    .oauth2ResourceServer()
                    .jwt();
    }

    @Bean
    public JwtDecoder customDecoder(OAuth2ResourceServerProperties properties) {
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(
                properties.getJwt().getJwkSetUri()).build();

        jwtDecoder.setClaimSetConverter(new OrganizationSubClaimAdapter());
        return jwtDecoder;
    }

}
