package uk.lset.mapper;


import org.springframework.stereotype.Component;
import uk.lset.entities.Expense;
import uk.lset.requestdto.ExpenseResponse;
import uk.lset.requestdto.CreateExpenseDto;

@Component
public class ExpenseMapper {

    public Expense toEntity(CreateExpenseDto createExpenseDto) {
        Expense expense = new Expense();
        expense.setName(createExpenseDto.getNewName());
        expense.setAmount(createExpenseDto.getNewAmount());
        expense.setCategoryId(createExpenseDto.getNewCategoryId());
        expense.setAccountTypeId(createExpenseDto.getNewAccountTypeId());
        expense.setCurrencyId(createExpenseDto.getNewCurrencyId());
        return expense;
    }

    public ExpenseResponse toResponse(Expense expense) {
        ExpenseResponse expenseResponse = new ExpenseResponse();
        expenseResponse.setExpenseId(expense.getId());
        expenseResponse.setExpenseName(expense.getName());
        expenseResponse.setExpenseAmount(expense.getAmount());
        expenseResponse.setExpenseCategoryId(expense.getCategoryId());
        expenseResponse.setExpenseAccountTypeId(expense.getAccountTypeId());
        expenseResponse.setExpenseCurrencyId(expense.getCurrencyId());
        expenseResponse.setExpenseCreatedAt(expense.getCreatedAt());
        return expenseResponse;
    }
}
