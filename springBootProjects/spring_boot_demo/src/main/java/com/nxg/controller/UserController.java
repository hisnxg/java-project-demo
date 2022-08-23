package com.nxg.controller;

import com.nxg.domain.User;
import com.nxg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

/**
 * @author nxg
 * date 2022/1/9
 * @apiNote
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/all")
    public String all(Model model){
        List<User> list = userService.selectAll();
        model.addAttribute("users",list);
        model.addAttribute("text","hello thymeleaf----");
        return "users";
    }
}
