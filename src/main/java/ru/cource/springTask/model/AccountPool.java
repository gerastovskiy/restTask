package ru.cource.springTask.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "account_pool")
public class AccountPool {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "branch_code")
    private String branchCode;
    @Column(name = "currency_code")
    private String currencyCode;
    @Column(name = "mdm_code")
    private String mdmCode;
    @Column(name = "priority_code")
    private String priorityCode;
    @ManyToOne
    @JoinColumn(name = "registry_type_code", referencedColumnName = "value")
    private TppRefProductRegisterType registryType;
    // TODO: подумать над реализацией условий с выборкой (в разных случаях должны быть разные условия)
    // для того, чтобы в данном не выбирать все счета, а только доступные для получения
    @OneToMany(mappedBy = "accountPool", fetch = FetchType.EAGER)
    private List<Account> accountList;
}
