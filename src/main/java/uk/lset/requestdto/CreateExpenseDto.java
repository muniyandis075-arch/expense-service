package uk.lset.requestdto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateExpenseDto {

    @NotBlank(message = "Expense name is required")
    @Size(min = 2, max = 255, message = "Name between 2 and 255 characters")
    private String newName;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be > 0")
    private Double newAmount;

    @NotNull(message = "Category is required")
    @Positive(message = "Id must be > 0")
    private Integer newCategoryId;

    @NotNull(message = "Account is required")
    private Integer newAccountTypeId;


    @NotNull(message = "Currency is required")
    private Integer newCurrencyId;



}
