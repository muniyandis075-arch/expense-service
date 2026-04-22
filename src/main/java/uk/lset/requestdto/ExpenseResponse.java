package uk.lset.requestdto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseResponse {

    private int expenseId;
    private String expenseName;
    private double expenseAmount;
    private int expenseCategoryId;
    private int expenseAccountTypeId;
    private int expenseCurrencyId;
    private Instant expenseCreatedAt;

}
