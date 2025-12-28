package com.example.auth.controller;

import com.example.auth.entity.Expense;
import com.example.auth.repository.ExpenseRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {

        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }

        double totalExpense = expenseRepository.getTotalExpense();
        double monthlyExpense = expenseRepository.getMonthlyExpense();
        double dailyAverage = expenseRepository.getDailyAverage();

        List<Expense> recentExpenses =
        expenseRepository.findTop5ByOrderByIdDesc();

        model.addAttribute("totalExpense", totalExpense);
        model.addAttribute("monthlyExpense", monthlyExpense);
        model.addAttribute("recentExpenses", recentExpenses);

        return "dashboard";
    }

    @PostMapping("/add")
    public String addExpense(Expense expense, HttpSession session) {

        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }

        if (expense.getExpenseDate() == null) {
            expense.setExpenseDate(LocalDate.now());
        }

        expenseRepository.save(expense);
        return "redirect:/expenses/dashboard";
    }
    @GetMapping("/delete/{id}")
public String deleteExpense(@PathVariable Long id, HttpSession session) {

    if (session.getAttribute("loggedInUser") == null) {
        return "redirect:/login";
    }

    expenseRepository.deleteById(id);
    return "redirect:/expenses/dashboard";
}

@GetMapping("/edit/{id}")
public String editExpense(@PathVariable Long id, Model model, HttpSession session) {

    if (session.getAttribute("loggedInUser") == null) {
        return "redirect:/login";
    }

    Expense expense = expenseRepository.findById(id).orElse(null);
    model.addAttribute("expense", expense);

    return "edit-expense";
}

@PostMapping("/update")
public String updateExpense(Expense expense, HttpSession session) {

    if (session.getAttribute("loggedInUser") == null) {
        return "redirect:/login";
    }

    expenseRepository.save(expense);
    return "redirect:/expenses/dashboard";
}

}
