package se.mutate.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import se.mutate.backend.filter.JwtRequestFilter;
import se.mutate.backend.service.AuthService;
import se.mutate.backend.service.impl.MyUserDetailsService;

import java.util.Arrays;

/**
 * https://www.youtube.com/watch?v=X80nJ5T7YpE
 * https://www.youtube.com/watch?v=rBNOc4ymd1E
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthService x;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //verkar funka för flera (nu hämtas jobb från, dont use h2-like this in a production environment /
        http.csrf().disable().authorizeRequests().
                antMatchers("/authenticate", "/", "/login", "/h2-console/**", "/download", "/register").permitAll().
                //antMatchers("/applications", "/applications/*").permitAll(). //Handlar ju om jobbtweaking
                //antMatchers(HttpMethod.GET, "/recruit/applications").denyAll().
                antMatchers(HttpMethod.POST, "/recruit/**").permitAll().
                antMatchers(HttpMethod.GET, "/recruit/{id:\\d+}").permitAll(). //works good
                anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        http.cors(); //Då funkar getters som vanligt, verkar dock inte ha en påverkan på mina deletes
        //https://stackoverflow.com/questions/36968963/how-to-configure-cors-in-a-spring-boot-spring-security-application
        //http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());

        //pga https://stackoverflow.com/questions/26220083/h2-database-console-spring-boot-load-denied-by-x-frame-options
        //http.headers().frameOptions().sameOrigin();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
