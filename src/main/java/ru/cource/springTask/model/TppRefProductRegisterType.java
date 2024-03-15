package ru.cource.springTask.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "tpp_ref_product_register_type")
public class TppRefProductRegisterType {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "internal_id")
    private int internalId;
    private String value;
    @Column(name = "register_type_name")
    private String registerTypeName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_class_code", referencedColumnName = "value")
    private TppRefProductClass productClass;
    @Column(name = "register_type_start_date")
    private Timestamp registerTypeStartDate;
    @Column(name = "register_type_end_date")
    private Timestamp registerTypeEndDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_type", referencedColumnName = "value")
    private TppRefAccountType accountType;
}
