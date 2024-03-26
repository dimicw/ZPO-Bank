package dimi.zpo.bank3.configurations;

import dimi.zpo.bank3.handlers.CustomAuthenticationSuccessHandler;
import dimi.zpo.bank3.handlers.CustomLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.RequestMethod;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/", "/home", "/offers/*/", "/register",
                                "/error/**", "/not-implemented/",
                                "/js/**", "/css/**", "/img/**").permitAll()
                        .requestMatchers(HttpMethod.POST).permitAll() // TODO
                        .requestMatchers("/offers/*/open-account/**").authenticated()
                        .anyRequest().authenticated())
                .formLogin(request -> request
                        .loginPage("/login")
                        .successHandler(new CustomAuthenticationSuccessHandler())
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/home")
                        .logoutSuccessHandler(new CustomLogoutSuccessHandler())
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
