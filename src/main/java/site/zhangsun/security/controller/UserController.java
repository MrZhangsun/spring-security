package site.zhangsun.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> Function: </p>
 *
 * @author: zhangsunjiankun 2018/4/10 下午9:29
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping(value = "/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/world")
    public String world() {
        return "world";
    }
}
