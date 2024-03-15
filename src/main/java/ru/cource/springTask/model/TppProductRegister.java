package ru.cource.springTask.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "tpp_product_register")
public class TppProductRegister {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "product_id")
    private Integer productId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type", referencedColumnName = "value")
    private TppRefProductRegisterType type;
    @OneToOne
    @JoinColumn(name = "account")
    private Account account;
    @Column(name = "currency_code")
    private String currencyCode;
    private String state;
    @Column(name = "account_number")
    private String accountNumber;
}
