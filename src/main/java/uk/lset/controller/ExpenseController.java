package uk.lset.controller;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uk.lset.entities.Expense;
import uk.lset.requestdto.CreateExpenseDto;
import uk.lset.requestdto.ExpenseResponse;
import uk.lset.requestdto.UpdateExpenseDto;
import uk.lset.service.ExpenseService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/rest")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping(path = "/expense", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ExpenseResponse> addExpense(@Valid @RequestBody CreateExpenseDto createExpenseDto) {
        ExpenseResponse expenseResponse = expenseService.addExpense(createExpenseDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{role_id}")
                .buildAndExpand(expenseResponse.getExpenseId())
                .toUri();

        return ResponseEntity.created(location).body(expenseResponse);
    }


    @GetMapping(path = "/expense", produces = "application/json")
    public ResponseEntity<List<ExpenseResponse>> getExpenses() {
        List<ExpenseResponse> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok().body(expenses);
    }


    @GetMapping(path = "/expense/{id}")
    public ResponseEntity<ExpenseResponse> getExpenseById(@PathVariable int id) {
        ExpenseResponse expense = expenseService.getExpenseById(id);
        return ResponseEntity.ok().body(expense);
    }

    @PatchMapping(path = "/expense/non-null-fields/{id}")
    public ResponseEntity<ExpenseResponse> updateNonNullFields(@PathVariable int id,@Valid @RequestBody UpdateExpenseDto updateExpenseDto) {
        ExpenseResponse expense = expenseService.updateNonNullFieldsExpense(id, updateExpenseDto);
        return ResponseEntity.ok().body(expense);
    }

    @PutMapping(path = "/expense/{id}")
    public ResponseEntity<ExpenseResponse> updateExpense(@PathVariable int id, @Valid @RequestBody UpdateExpenseDto updateExpenseDto) {
        ExpenseResponse expense = expenseService.updateExpense(id, updateExpenseDto);
        return ResponseEntity.ok().body(expense);
    }

    @DeleteMapping(path = "/expense/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable int id) {
        expenseService.deleteExpenseById(id);
        return ResponseEntity.ok("Deleted expense with id " + id);
    }


}
