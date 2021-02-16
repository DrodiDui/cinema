package by.kapitonov.cinema.fapi.config;

import by.kapitonov.cinema.fapi.security.JwtAuthenticationEntryPoint;
import by.kapitonov.cinema.fapi.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationEntryPoint authenticationEntryPoint;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(
            JwtAuthenticationEntryPoint authenticationEntryPoint,
            @Qualifier("customUserDetailsService") UserDetailsService userDetailsService) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public JwtAuthenticationFilter authenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/cinemas")
                    .permitAll()
                .antMatchers("/api/cinemas/*")
                    .permitAll()
                .antMatchers("/api/halls/*/**")
                    .permitAll()
                .antMatchers("/api/halls/*/*")
                    .permitAll()
                .antMatchers("/api/film-sessions/*/active")
                    .permitAll()
                .antMatchers("/api/film-sessions/*")
                    .permitAll()
                .antMatchers("/api/tickets/*/all")
                    .permitAll()
                .antMatchers("/api/auth/**")
                    .permitAll()
                .antMatchers("/api/statistics/films/**")
                    .hasRole(Constants.ROLE_OWNER)
                .antMatchers(HttpMethod.GET, "/api/users")
                    .hasRole(Constants.ROLE_ADMIN)
                .antMatchers(HttpMethod.POST, "/api/users")
                    .hasAnyRole(Constants.ROLE_OWNER, Constants.ROLE_ADMIN)
                .antMatchers(HttpMethod.PUT, "/api/users/*/role")
                    .hasAnyRole(Constants.ROLE_ADMIN)
                .antMatchers(HttpMethod.GET, "/api/roles")
                    .hasAnyRole(Constants.ROLE_ADMIN, Constants.ROLE_OWNER)
                .antMatchers(HttpMethod.GET, "/api/statuses")
                    .hasAnyRole(Constants.ROLE_ADMIN, Constants.ROLE_OWNER)
                .antMatchers(HttpMethod.PUT, "/api/users/*/status")
                    .hasAnyRole(Constants.ROLE_ADMIN)
                .antMatchers("/api/cinema-statuses")
                    .hasAnyRole(Constants.ROLE_ADMIN, Constants.ROLE_OWNER, Constants.ROLE_MANAGER)
                .anyRequest()
                    .authenticated();

        http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}