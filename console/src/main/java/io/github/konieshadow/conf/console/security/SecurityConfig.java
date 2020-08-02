package io.github.konieshadow.conf.console.security;

import com.allanditzel.springframework.security.web.csrf.CsrfTokenResponseHeaderBindingFilter;
import io.github.konieshadow.conf.common.domain.ErrorResultBean;
import io.github.konieshadow.conf.common.domain.OptResultBean;
import io.github.konieshadow.conf.common.util.ResponseUtil;
import io.github.konieshadow.conf.security.CustomAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.header.writers.frameoptions.WhiteListedAllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CustomLogoutHandler customLogoutHandler;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    private CustomLoginHandler customLoginHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin();

        http.authorizeRequests()
                .antMatchers("/api/**").authenticated();

        http.formLogin();

        http.logout()
                .logoutUrl("/api/logout")
                .addLogoutHandler(customLogoutHandler)
                .logoutSuccessHandler(customLogoutHandler);

        http.exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler)
                .authenticationEntryPoint(customAccessDeniedHandler);

        http.csrf()
                .ignoringAntMatchers("/h2-console/**", "/api/login", "/api/logout");

        http.addFilterBefore(new AcceptHeaderLocaleFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(new CsrfTokenResponseHeaderBindingFilter(), CsrfFilter.class);

        http.exceptionHandling();
    }

    private CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(customLoginHandler);
        filter.setAuthenticationFailureHandler(customLoginHandler);
        filter.setAuthenticationManager(authenticationManager());
        filter.setFilterProcessesUrl("/api/login");
        return filter;
    }

    @Component
    public static class CustomLogoutHandler implements LogoutHandler, LogoutSuccessHandler {

        @Override
        public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        }

        @Override
        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            ResponseUtil.optResultResponse(response, new OptResultBean<>());
        }
    }

    @Component
    public static class CustomAccessDeniedHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
            ResponseUtil.errorResponse(response, new ErrorResultBean<>(authException.getMessage()), HttpStatus.UNAUTHORIZED.value());
        }

        @Override
        public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
            ResponseUtil.errorResponse(response, new ErrorResultBean<>(accessDeniedException.getMessage()), HttpStatus.FORBIDDEN.value());
        }
    }

    @Component
    public static class CustomLoginHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
            ResponseUtil.errorResponse(response, new ErrorResultBean<>(exception.getMessage()), HttpStatus.BAD_REQUEST.value());
        }

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            ResponseUtil.optResultResponse(response, new OptResultBean<>());
        }
    }

    private static class AcceptHeaderLocaleFilter implements Filter {

        private AcceptHeaderLocaleResolver localeResolver;

        private AcceptHeaderLocaleFilter() {
            localeResolver = new AcceptHeaderLocaleResolver();
            localeResolver.setDefaultLocale(Locale.US);
        }

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            Locale locale = localeResolver.resolveLocale((HttpServletRequest) servletRequest);
            LocaleContextHolder.setLocale(locale);

            filterChain.doFilter(servletRequest, servletResponse);
        }

        @Override
        public void destroy() {
        }
    }

}
