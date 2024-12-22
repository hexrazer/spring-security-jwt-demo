package in.coder.abhijit.demo.config;

import in.coder.abhijit.demo.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        //boolean result = encoder.matches("admin123", "{bcrypt}$2a$12$pbekQ7tq5LvGDS1pbIXb1e8do0lk0zRdVpjfw.VihHcdUgG3yuzlO");
        // String hash = encoder.encode("admin123");   
        // System.out.println("Generated hash: " + hash);
        // System.out.println("hola Password matches: " + result); // Should print true or false


        return encoder;

    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        // .requestMatchers("/admin/**").hasRole("ADMIN")
                        // .requestMatchers("/user/**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET,"/products/**").hasAnyRole("USER","ADMIN")
                        .requestMatchers(HttpMethod.POST,"/products/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/products/**").hasRole("ADMIN")
                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin()
                .and()
                .httpBasic();

                http.headers().frameOptions().disable();                
        return http.build();
    }
}
