package it.italiandudes.webserver6329.core.security.configuration;

import it.italiandudes.webserver6329.core.logging.WebServer6329Logger;
import it.italiandudes.webserver6329.core.security.filter.UserExistenceFilter;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.filter.UrlHandlerFilter;

import java.sql.DatabaseMetaData;

@SuppressWarnings("SqlDialectInspection")
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Attributes
    @NotNull private final UserExistenceFilter userExistenceFilter;

    // Bean Providers
    @Bean public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean public RequestCache requestCache() {
        return new HttpSessionRequestCache();
    }
    @Bean public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
    @Bean
    public FilterRegistrationBean<UrlHandlerFilter> trailingSlashRedirect() { // Translates 'urls/' to 'urls'
        UrlHandlerFilter filter = UrlHandlerFilter.trailingSlashHandler("/**")
                .redirect(HttpStatus.PERMANENT_REDIRECT).build();
        FilterRegistrationBean<UrlHandlerFilter> registrationBean = new FilterRegistrationBean<>(filter);
        registrationBean.setOrder(0);
        return registrationBean;
    }

    // Constructors
    public SecurityConfig(@NotNull final UserExistenceFilter userExistenceFilter) {
        this.userExistenceFilter = userExistenceFilter;
    }

    // DB Triggers
    @Bean
    public CommandLineRunner executeDBMSSpecificQueries(JdbcTemplate jdbcTemplate) {
        return args -> jdbcTemplate.execute((ConnectionCallback<Object>) (connection) -> {
            DatabaseMetaData meta = connection.getMetaData();
            String dbName = meta.getDatabaseProductName();
            WebServer6329Logger.getLogger().debug("Database name: {}", dbName);

            //noinspection SwitchStatementWithTooFewBranches
            switch (dbName.toLowerCase()) {
                case "sqlite" -> {}
            }
            return null;
        });
    }

    // HTTP Routes
    @Bean
    public SecurityFilterChain filterChain(@NotNull final HttpSecurity http) throws Exception {
        return http
                .addFilterBefore(userExistenceFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/login",
                                "/register",
                                "/web/css/**",
                                "/favicon.ico",
                                "/dnd5e/css/**", // Da rimuovere prima della release
                                "/dnd5e/css_test", // Da rimuovere prima della release
                                "/fallout/css/**", // Da rimuovere prima della release
                                "/fallout/css_test" // Da rimuovere prima della release
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .maximumSessions(1)
                        .sessionRegistry(sessionRegistry())
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/", false)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                )
                .redirectToHttps(Customizer.withDefaults()).build();
    }
}
