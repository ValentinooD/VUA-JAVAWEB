package valentinood.javaweb.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import valentinood.javaweb.domain.Cart;
import valentinood.javaweb.interceptor.ShopHandlerInterceptor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration implements WebMvcConfigurer {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, RequestContextFilter requestContextFilter) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/css/*.css").permitAll()
                        .requestMatchers("/js/*.js").permitAll()
                        .requestMatchers("/img/*").permitAll()

                        .requestMatchers("/").permitAll()
                        .requestMatchers("/fragment/articles").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/article**").permitAll()
                        .requestMatchers("/cart**").permitAll()
                        .requestMatchers("/cart/**").permitAll()
                        .requestMatchers("/cart/add/**").permitAll()
                        .requestMatchers("/fragment/*").permitAll()

                        .requestMatchers("/user/*").authenticated()
                        .requestMatchers("/user/**").authenticated()
                        .requestMatchers("/payment/**").authenticated()

                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/admin/*").hasRole("ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/admin/fragment/**").hasRole("ADMIN")

                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .defaultSuccessUrl("/", true)
                )
                .logout(LogoutConfigurer::permitAll);

        // for testing
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);

        http.csrf(config -> config.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));

        return http.build();
    }

    private ShopHandlerInterceptor shopHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(shopHandlerInterceptor).addPathPatterns("/**").addPathPatterns("**");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @SessionScope
    public Cart cart() {
        return new Cart();
    }
}
