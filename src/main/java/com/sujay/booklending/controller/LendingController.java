package com.sujay.booklending.controller;

import com.sujay.booklending.service.BookService;
import com.sujay.booklending.service.LendingService;
import com.sujay.booklending.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lendings")
public class LendingController {

    @Autowired
    private LendingService lendingService;

    @Autowired
    private BookService bookService;

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String listLendings(Model model) {
        model.addAttribute("lendings", lendingService.getAllLendings());
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("students", studentService.getAllStudents());
        return "lendings";
    }

    @PostMapping("/lend")
    public String lendBook(@RequestParam Long bookId, @RequestParam Long studentId) {
        lendingService.lendBook(bookId, studentId);
        return "redirect:/lendings";
    }

    @GetMapping("/return/{id}")
    public String returnBook(@PathVariable Long id) {
        lendingService.returnBook(id);
        return "redirect:/lendings";
    }
}
