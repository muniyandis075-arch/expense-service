package uk.lset.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.lset.entities.Expense;
import uk.lset.exceptionHandler.ItemNotFoundException;
import uk.lset.mapper.ExpenseMapper;
import uk.lset.repository.ExpenseRepository;
import uk.lset.requestdto.ExpenseResponse;
import uk.lset.requestdto.CreateExpenseDto;
import uk.lset.requestdto.UpdateExpenseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository, ExpenseMapper expenseMapper) {
        this.expenseRepository = expenseRepository;
        this.expenseMapper = expenseMapper;
    }

    @Transactional
    public ExpenseResponse addExpense(CreateExpenseDto createExpenseDto) {
        Expense expense = expenseMapper.toEntity(createExpenseDto);
        Expense savedExpense = expenseRepository.save(expense);
        return expenseMapper.toResponse(savedExpense);
    }

    @Transactional(readOnly = true)
    public List<ExpenseResponse> getAllExpenses() {
        return expenseRepository.findAll()
                .stream().map(expenseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ExpenseResponse getExpenseById(int expenseId) {
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(()
                ->new ItemNotFoundException("Expense with id " + expenseId + " not found"));
        return expenseMapper.toResponse(expense);
    }

    /*
    - Partially update an existing Account
    - Only non-null fields in the request will be updated
 */
    @Transactional
    public ExpenseResponse updateNonNullFieldsExpense(int expenseId, UpdateExpenseDto updateExpenseDto) {
        if (updateExpenseDto.getName() == null &&
            updateExpenseDto.getAmount() ==  null &&
            updateExpenseDto.getCategoryId() == null &&
            updateExpenseDto.getAccountTypeId() == null &&
            updateExpenseDto.getCurrencyId() == null
            ) {
            throw new RuntimeException("Expense with id: " + expenseId + " has no updates ! For update at least one field must be provided");
        }
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(() -> new ItemNotFoundException("Expense with id " + expenseId + " not found"));
        if(updateExpenseDto.getName() != null) {
            expense.setName(updateExpenseDto.getName());
        }
        if(updateExpenseDto.getAmount() != null) {
            expense.setAmount(updateExpenseDto.getAmount());
        }
        if(updateExpenseDto.getCategoryId() != null) {
            expense.setCategoryId(updateExpenseDto.getCategoryId());
        }
        if(updateExpenseDto.getAccountTypeId() != null) {
            expense.setAccountTypeId(updateExpenseDto.getAccountTypeId());
        }
        if(updateExpenseDto.getCurrencyId() != null) {
            expense.setCurrencyId(updateExpenseDto.getCurrencyId());
        }
        expenseRepository.save(expense);
        return expenseMapper.toResponse(expense);
    }


    /*
        -Fully updates the account entity by replacing its fields with the values from the provided updateFieldAccount
        -All updatable fields are expected to pe present in the request body.
        -Null field it will overwrite the existing value with null in the database
     */

    @Transactional
    public ExpenseResponse updateExpense(int expenseId, UpdateExpenseDto updateExpenseDto) {
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(()
                -> new ItemNotFoundException("Expense with id " + expenseId + " not found"));
        expense.setName(updateExpenseDto.getName());
        expense.setAmount(updateExpenseDto.getAmount());
        expense.setCategoryId(updateExpenseDto.getCategoryId());
        expense.setAccountTypeId(updateExpenseDto.getAccountTypeId());
        expense.setCurrencyId(updateExpenseDto.getCurrencyId());
        expenseRepository.save(expense);
        return expenseMapper.toResponse(expense);
    }


    @Transactional
    public void deleteExpenseById(int expenseId) {
        if(!expenseRepository.existsById(expenseId)) {
            throw new ItemNotFoundException("Expense with id " + expenseId + " not found");
        }
        expenseRepository.deleteById(expenseId);
    }


}
