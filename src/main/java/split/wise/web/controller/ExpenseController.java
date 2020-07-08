package split.wise.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import split.wise.web.model.Comment;
import split.wise.web.service.*;

import split.wise.web.model.Category;
import split.wise.web.model.Expense;
import split.wise.web.service.CategoryService;
import split.wise.web.service.ExpenseService;

import java.util.List;


@CrossOrigin
@RestController
public class ExpenseController {

    @Autowired
    ExpenseService service;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UsersService usersService;

    @Autowired
    CommentService commentService;

    @Autowired
    StackOverflowResolverService jsonService;
    @PostMapping("/category")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        return new ResponseEntity<>(jsonService.resolveCategory(categoryService.save(category)), HttpStatus.OK);
    }

    @PostMapping("/expense")
    public ResponseEntity<?> addExpense(@RequestBody Expense expense) {
        return new ResponseEntity<>(jsonService.resolveExpense(service.saveNewExpense(expense)), HttpStatus.OK);
    }

    @GetMapping("expense/{id}")
    public @ResponseBody
    ResponseEntity<?> getExpenseById(@PathVariable Long id) {
        return new ResponseEntity<>(jsonService.resolveExpense(service.get(id)), HttpStatus.OK);
    }

    @GetMapping("expense/")
    public @ResponseBody
    ResponseEntity<?> getUserExpenses() {
        List<Expense> expenseList = service.getAllUserInvolvedExpenses(usersService.get(1L));
        return new ResponseEntity<>(jsonService.resolveExpenseList(expenseList), HttpStatus.OK);
    }

    @DeleteMapping("expense/{id}")
    public @ResponseBody
    ResponseEntity<?> deleteExpenses(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>("successfully deleted", HttpStatus.OK);
    }

    @PutMapping("expense/{id}/undelete")
    public ResponseEntity<?> undeleteExpense(@PathVariable Long id) {
        service.undelete(id);
        return new ResponseEntity<>(jsonService.resolveExpense(service.get(id)), HttpStatus.OK);
    }

    @PostMapping("expense/{id}/comment")
    public ResponseEntity<?> addComment(@PathVariable Long id, @RequestBody Comment comment) {
        comment.setExpense(service.get(id));
        return new ResponseEntity<>(jsonService.resolveComment(commentService.save(comment)), HttpStatus.OK);
    }

    @PutMapping("expense/{id}")
    public ResponseEntity<?> editComment(@RequestBody Expense expense) {
        return new ResponseEntity<>(jsonService.resolveExpense(service.edit(expense)), HttpStatus.OK);
    }

}
