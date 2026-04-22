package uk.lset.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.Instant;


@Entity
@Table(name = "expense")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column (name = "category_id", nullable = false)
    private int categoryId;

    @Column(name = "account_type_id")
    private int accountTypeId;

    @Column(name = "currency_id")
    private int currencyId;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

//    @Column(name = "created_by")
//    @CreatedBy
//    private String createdBy;
//
//    @LastModifiedDate
//    @Column(name = "last_modified_at")
//    private Instant lastModifiedAt;
//
//    @LastModifiedBy
//    @Column(name = "modified_by")
//    private String modifiedBy;


}
