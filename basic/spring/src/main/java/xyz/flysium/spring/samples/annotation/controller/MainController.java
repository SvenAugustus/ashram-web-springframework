package xyz.flysium.spring.samples.annotation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import xyz.flysium.spring.samples.annotation.entity.User;
import xyz.flysium.spring.samples.annotation.service.MainService;

/**
 * @author Sven Augustus
 * @version 1.0
 */
@Controller
public class MainController {

  @Autowired
  private MainService service;

  public String login(String loginName, String password) {
    User user = service.login(loginName, password);
    if (user == null) {
      return "登录失败";
    } else {
      return "登录成功";
    }
  }

}
