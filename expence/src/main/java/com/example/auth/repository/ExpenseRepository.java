package com.example.auth.repository;

import com.example.auth.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findTop5ByOrderByIdDesc();


    @Query("SELECT COALESCE(SUM(e.amount),0) FROM Expense e")
    double getTotalExpense();

    @Query("SELECT COALESCE(SUM(e.amount),0) FROM Expense e WHERE MONTH(e.expenseDate)=MONTH(CURRENT_DATE)")
    double getMonthlyExpense();

    @Query("SELECT COALESCE(AVG(e.amount),0) FROM Expense e")
    double getDailyAverage();
}
