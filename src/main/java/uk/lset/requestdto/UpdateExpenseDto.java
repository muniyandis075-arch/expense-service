package uk.lset.requestdto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateExpenseDto {

    @Size(min = 2, max = 255, message = "Name between 2 and 255 characters")
    private String name;

    @Positive(message = "Amount must be > 0")
    private Double amount;

    @Positive(message = "Id must be > 0")
    private Integer categoryId;

    @Positive(message = "Id must be > 0")
    private Integer accountTypeId;

    @Positive(message = "Id must be > 0")
    private Integer currencyId;


}
