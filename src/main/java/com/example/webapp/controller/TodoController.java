package com.example.webapp.controller;

import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.webapp.entity.ToDo;
import com.example.webapp.form.ToDoForm;
import com.example.webapp.helper.ToDoHelper;
import com.example.webapp.service.ToDoService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {
    private final ToDoService toDoService;

    // '할일' 목록을 표시합니다.
    @GetMapping
    public String list(Model model) {
        // System.out.println("===========todos==========");
        model.addAttribute("todos", toDoService.findAllToDo());

        System.out.println(model.getAttribute("todos"));
        System.out.println("++++++++++++++++++++++++");

        return "todo/list";
    }

    // 특정 ID의 '할일'에 대한 세부 정보를 표시합니다.
    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Integer id, Model model,
            HttpSession session, RedirectAttributes attributes) {

        ToDo toDo = toDoService.findByIdToDo(id);
        String uId = (String) session.getAttribute("uId");

        if (toDo == null) {
            attributes.addFlashAttribute("errorMessage", "해당 데이터가 존재하지 않습니다.");
            return "redirect:/todos";
        }

        if (!toDo.getUId().equals(uId)) {
            attributes.addFlashAttribute("errorMessage", "작성자만 상세보기를 할 수 있습니다.");
            return "redirect:/todos";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        String createdAtFormatted = toDo.getCreatedAt() != null ? toDo.getCreatedAt().format(formatter) : "N/A";
        String updatedAtFormatted = toDo.getUpdatedAt() != null ? toDo.getUpdatedAt().format(formatter) : "N/A";

        model.addAttribute("todo", toDo);
        model.addAttribute("createdAtFormatted", createdAtFormatted);
        model.addAttribute("updatedAtFormatted", updatedAtFormatted);

        return "todo/detail";
    }

    // === 등록 및 업데이트 처리 추가 ===

    // 새 할일 등록 화면을 표시합니다.
    @GetMapping("/form")
    public String newToDo(@ModelAttribute ToDoForm form, HttpSession session, RedirectAttributes attributes) {

        String uId = (String) session.getAttribute("uId");

        if (uId == null) {
            attributes.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
            return "redirect:/todos/login";
        }

        // 새 할일 등록 화면 설정
        form.setIsNew(true);

        return "todo/form";
    }

    // 새 할일을 등록합니다.
    @PostMapping("/save")
    public String create(@Validated ToDoForm form,
            BindingResult bindingResult,
            HttpSession session,
            RedirectAttributes attributes) {

        // ====== 유효성 검사 =======

        // 입력체크NG : 입력화면 표시
        if (bindingResult.hasErrors()) {
            // 새 할일등록화면설정
            form.setIsNew(true);
            return "todo/form";
        }

        // 세션에서 uId 꺼내서 form에 넣기
        String uId = (String) session.getAttribute("uId");
        System.out.println("세션에서 꺼낸 uId : " + uId);
        form.setUId(uId);

        // 엔티티로 변환
        ToDo ToDo = ToDoHelper.convertToDo(form);
        // 등록 실행
        toDoService.insertToDo(ToDo);

        // 플래시 메시지
        attributes.addFlashAttribute("message", "새 ToDo가 생성되었습니다.");
        // PRG 패턴

        return "redirect:/todos";
    }

    /**
     * 특정 ID의 수정 화면을 표시합니다.
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id,
            Model model,
            HttpSession session,
            RedirectAttributes attributes) {
        // ID에 해당하는 할일을 가져옵니다.
        ToDo target = toDoService.findByIdToDo(id);

        if (target != null) {
            // 대상 데이터가 있는 경우 폼으로 변환
            ToDoForm form = ToDoHelper.convertToDoForm(target);
            // 모델에 저장
            model.addAttribute("toDoForm", form);

            return "todo/form";
        } else {
            // 대상 데이터가 없는 경우 플래시 메시지를 설정
            attributes.addFlashAttribute("errorMessage", "대상 데이터가 없습니다.");
            // 목록 화면으로 리디렉션

            return "redirect:/todos";
        }
    }

    // 할일을 업데이트합니다.
    @PostMapping("/update")
    public String update(@Validated ToDoForm form,
            HttpSession session,
            BindingResult bindingResult,
            RedirectAttributes attributes) {

        String uId = (String) session.getAttribute("uId");
        form.setUId(uId);

        // ====== 유효성 검사 =======

        // 입력체크NG : 입력화면 표시
        if (bindingResult.hasErrors()) {
            // 새 할일등록화면설정
            form.setIsNew(false);

            return "todo/form";
        }

        // 엔티티로 변환
        ToDo ToDo = ToDoHelper.convertToDo(form);
        // 할일 업데이트
        toDoService.updateToDo(ToDo);
        System.out.println("done 값: " + form.getDone());

        // 플래시 메시지
        attributes.addFlashAttribute("message", "ToDo가 업데이트되었습니다.");
        // PRG 패턴

        return "redirect:/todos";
    }

    // 특정 ID의 할일을 삭제합니다.
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes attributes) {
        // 삭제 처리
        toDoService.deleteToDo(id);
        // 플래시 메시지
        attributes.addFlashAttribute("message", "ToDo가 삭제되었습니다.");
        // PRG 패턴

        return "redirect:/todos";
    }

    // 할일 토글
    @PostMapping("/{id}/toggle")
    public String toggleDone(@PathVariable("id") Integer id,
            HttpSession session,
            RedirectAttributes attributes) {

        String uId = (String) session.getAttribute("uId");
        ToDo todo = toDoService.findByIdToDo(id);

        if (!todo.getUId().equals(uId)) {
            attributes.addFlashAttribute("errorMessage", "작성자만 체크할 수 있습니다.");

            return "redirect:/todos";
        }

        Boolean current = Boolean.TRUE.equals(todo.getDone());
        todo.setDone(!current);

        toDoService.updateToDo(todo);

        return "redirect:/todos";
    }
}
