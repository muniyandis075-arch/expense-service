package uk.lset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.lset.entities.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
}
