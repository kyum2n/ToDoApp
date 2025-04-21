package com.example.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.webapp.entity.User;
import com.example.webapp.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/todos")
@RequiredArgsConstructor
public class JoinController {
    // DI
    private final UserService userService;

    // 회원가입 화면=
    @GetMapping("/join")
    public String getJoinInfo() {
        // System.out.println("========join============");

        return "join";
    }

    // 회원가입 처리 (post 방식)
    @PostMapping("/main")
    public String postInsertUser(@ModelAttribute User users, RedirectAttributes attributes) {
        userService.insertUser(users);
        attributes.addFlashAttribute("message", "회원가입이 완료되었습니다.");
        System.out.println("========joined============");
        System.out.println("users : " + users);

        return "redirect:/todos/login";
    }
}
