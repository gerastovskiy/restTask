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
@EqualsAndHashCode
@Table(name = "tpp_product")
public class TppProduct {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Column(name = "product_code_id")
    private Long productCodeId;
    @Column(name = "client_id")
    private Long clientId;
    private String type;
    private String number;
    private Integer priority;
    @Column(name = "date_of_conclusion")
    private Timestamp dateOfConclusion;
    @Column(name = "start_date_time")
    private Timestamp startDateTime;
    @Column(name = "end_date_time")
    private Timestamp endDateTime;
    private Long days;
    @Column(name = "penalty_rate")
    private Float penaltyRate;
    private Float nso;
    @Column(name = "threshold_amount")
    private Float thresholdAmount;
    @Column(name = "requisite_type")
    private String requisiteType;
    @Column(name = "interest_rate_type")
    private String interestRateType;
    @Column(name = "tax_rate")
    private Float taxRate;
    @Column(name = "reasone_close")
    private String reasoneClose;
    private String state;
}
