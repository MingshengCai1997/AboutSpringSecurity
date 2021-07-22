package com.sheng.security01.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Configuration
public class SecurityConfigTest extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailServiceaaa")
    UserDetailsService aaaaa;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

        // 这是原先自己定义的死的用户名以及密码
//        BCryptPasswordEncoder bcry = new BCryptPasswordEncoder();
//        String password = bcry.encode("1234");
//        auth.inMemoryAuthentication().withUser("configUser").password(password).roles("admin");

        // 通过数据库查询出用户名以及密码
        /**
         * 选择一个userDetailService以及选择一个PasswordEncoder。
         */
        auth.userDetailsService(aaaaa).passwordEncoder(passwordaa());
    }

    @Bean
    PasswordEncoder passwordaa() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 通过配置这个方法，实现对自定义登录认证进行设置
     * @param http
     * @throws Exception
     *
     * 一些补充：
     *
     * 除了路径选择，我们还通过authenticated()和permitAll()来定义该如何保护路径。authenticated()要求在执行该请求时，
     * 必须已经登录了应用。如果用户没有认证的话，Spring Security的Filter将会捕获该请求，并将用户重定向到应用的登录页面。
     * 同时，permitAll()方法允许请求没有任何的安全限制。
     *
     * 除了authenticated()方法和permitAll()方法外,还有一些其他方法用来定义该如何保护请求.
     *
     * access(String) 如果给定的SpEL表达式计算结果为true，就允许访问
     * anonymous() 允许匿名用户访问
     * authenticated() 允许认证的用户进行访问
     * denyAll() 无条件拒绝所有访问
     * fullyAuthenticated() 如果用户是完整认证的话（不是通过Remember-me功能认证的），就允许访问
     * hasAuthority(String) 如果用户具备给定权限的话就允许访问
     * hasAnyAuthority(String…)如果用户具备给定权限中的某一个的话，就允许访问
     * hasRole(String) 如果用户具备给定角色(用户组)的话,就允许访问/
     * hasAnyRole(String…) 如果用户具有给定角色(用户组)中的一个的话,允许访问.
     * hasIpAddress(String 如果请求来自给定ip地址的话,就允许访问.
     * not() 对其他访问结果求反.
     * permitAll() 无条件允许访问
     * rememberMe() 如果用户是通过Remember-me功能认证的，就允许访问
     * 通过上面的方法,我们可以修改 configure 方法,要求用户不仅需要认证,还需要具备相应的权限
     *
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        // 配置没有权限访问跳转到自己的页面
        http.exceptionHandling().accessDeniedPage("/unauth.html");

        http.formLogin()                    // 自定义自己编写的登录页面
            .loginPage("/login.html")       // 登录页面设置
            .loginProcessingUrl("/user/login")  // 登录访问路径，提交表单之后跳转的地址,可以看作一个中转站，这个步骤就是验证user的一个过程，是框架内帮助我们做的
            .defaultSuccessUrl("/test/index").permitAll()   // 登录成功之后跳转到这个路径
            .and().authorizeRequests()
                .antMatchers("/", "/test/hello", "/user/login").permitAll()     // 【设置哪些路劲可以直接访问，不需要认证】
                // 当前登录的用户，只有具有了admins权限才可以访问这个路径
                // 【方法一：hasAuthority】
                //.antMatchers("/test/index").hasAuthority("admins")

                // 【方法二：hasAnyAuthority】
//                .antMatchers("/test/index").hasAnyAuthority("admin, roles, aa, bb")

                // 【方法三：hasRole】
//                .antMatchers("/test/index").hasRole("role")

                // 【方法四：hasAnyRole】
                .antMatchers("/test/index").hasAnyRole("ro, role")
                .anyRequest().authenticated()   // 所有的请求都应该登录认证之后可以访问
            .and().csrf().disable();        // 关闭csrf防护
    }
}
