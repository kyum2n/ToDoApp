package com.example.webapp.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.webapp.entity.ToDo;
import com.example.webapp.entity.User;
import com.example.webapp.service.UserService;
import com.example.webapp.service.ToDoService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/todos")
@RequiredArgsConstructor
public class LoginController {
    // 날씨 api 불러오기
    @Value("${weather.api.key}")
    private String apiKey;

    // DI
    private final UserService userService;
    private final ToDoService toDoService;

    // // 로그인 화면
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("todos", toDoService.findAllToDo());
        System.out.println("login======================");
        return "main"; // main.html을 반환
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(@RequestParam(name = "uId") String uId, @RequestParam(name = "uPwd") String uPwd,
            HttpSession session, Model model) {
        User foundUser = userService.findByUId(uId);

        if (foundUser == null) {
            model.addAttribute("msg", "존재하지 않는 ID입니다.");
            model.addAttribute("todos", toDoService.findAllToDo());
            return "main"; // 로그인 실패 시 main.html로 이동
        } else if (!foundUser.getUPwd().equals(uPwd)) {
            model.addAttribute("msg", "비밀번호가 잘못되었습니다.");
            model.addAttribute("todos", toDoService.findAllToDo());
            return "main";
        }

        // 로그인 성공 시 세션에 사용자 정보 저장
        session.setAttribute("uId", foundUser.getUId());
        System.out.println("로그인 성공 시 세션에 저++++++장된 uID : " + foundUser.getUId());
        System.out.println("할 일 등록 직전 세션에서 가져온 uID : " + uId);
        session.setAttribute("username", foundUser.getUName());
        session.setAttribute("loggedInUser", foundUser); // ✨ 이거 한 줄만 추가해줘!

        return "redirect:/todos/welcome";

    }

    // 로그인 성공 화면

    @GetMapping("/welcome")
    public String showWelcomePage(HttpSession session, Model model, RedirectAttributes attributes) {

        String username = (String) session.getAttribute("username");

        if (username == null) {
            return "redirect:/todos/login"; // 로그인 안 했으면 Home 버튼 클릭 시 로그인 페이지로 이동
        }
        model.addAttribute("username", username);

        List<ToDo> toDos = toDoService.findAllToDo();
        System.out.println("====================");
        System.out.println(toDos.get(0).getDone());

        model.addAttribute("todos", toDoService.findAllToDo());
        System.out.println("==================");

        String city = "Seoul";
        String url = String.format(
                "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric&lang=kr",
                city, apiKey);

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        JSONObject json = new JSONObject(response);
        String description = json.getJSONArray("weather").getJSONObject(0).getString("description");
        double temp = json.getJSONObject("main").getDouble("temp");

        if (url == null) {
            attributes.addFlashAttribute("errorMessage", "날씨 정보를 불러올 수 없습니다.");

            return "main";
        }

        model.addAttribute("weather", String.format("오늘 %s의 날씨: %s, %.1f°C", city, description, temp));
        return "welcome";
    }

    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 삭제

        return "redirect:/todos/login";
    }
}
