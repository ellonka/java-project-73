package hexlet.code;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityWebAppConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().and().sessionManagement().disable();
        http
                .authorizeRequests()
                .antMatchers("/*").permitAll()
                .and().httpBasic();
        http.headers().frameOptions().disable();
    }
}
