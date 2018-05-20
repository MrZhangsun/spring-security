package site.zhangsun.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <p> Function: </p>
 *
 * @author : zhangsunjiankun 2018/5/18 下午10:13
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    private final UserDetailsService SysUserServiceImpl;

    @Autowired
    public WebSecurityConfig(UserDetailsService SysUserServiceImpl) {
        this.SysUserServiceImpl = SysUserServiceImpl;
    }

    /**
     * 用户认证
     *
     * @param auth 认证管理器
     * @throws Exception Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(SysUserServiceImpl);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    /**
     * 用户授权
     *
     * @param http request
     * @throws Exception Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            // 所有用户均可访问的资源
            .antMatchers("/", "/index", "/css/**", "/js/**", "/images/**", "/webjars/**", "**/favicon.ico").permitAll()
            // 任何尚未匹配的URL只需要验证用户即可访问
            .anyRequest().authenticated()
            .and()
            .formLogin()
            // 指定登录页面,授予所有用户访问登录页面
            .loginPage("/login")
            //设置默认登录成功跳转页面,错误回到login界面
            .defaultSuccessUrl("/home").failureUrl("/error").permitAll()
            .and()
            //开启cookie保存用户数据
            .rememberMe()
            //设置cookie有效期
            .tokenValiditySeconds(60 * 60 * 24 * 7)
            //设置cookie的私钥
            .key("security")
            .and()
            .logout()
            .permitAll();
    }
}
