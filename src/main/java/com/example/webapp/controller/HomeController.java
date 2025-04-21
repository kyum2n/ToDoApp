package com.example.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

import org.json.JSONObject;

@Controller
@RequiredArgsConstructor
public class HomeController {
    @Value("${weather.api.key}")
    private String apiKey;

    @GetMapping("/")
    public String mainPage(Model model, RedirectAttributes attributes) {
        // 서울 기준
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
        return "main";
    }
}
