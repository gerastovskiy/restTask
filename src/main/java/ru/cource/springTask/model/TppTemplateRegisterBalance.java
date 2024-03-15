package ru.cource.springTask.model;

import jakarta.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(name = "tpp_template_register_balance")
// TODO: таблица есть, но никак не используется, уточнить зачем
public class TppTemplateRegisterBalance {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "register_id")
    private Long registerId;
    private BigInteger amount;
    private String order;
    @Column(name = "last_modify_date")
    private Timestamp lastModifyDate;
}
