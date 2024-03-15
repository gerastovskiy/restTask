package ru.cource.springTask.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tpp_ref_product_class")
public class TppRefProductClass {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "internal_id")
    private int internalId;
    private String value;
    @Column(name = "gbi_code")
    private String gbiCode;
    @Column(name = "gbi_name")
    private String gbiName;
    @Column(name = "product_row_code")
    private String productRowCode;
    @Column(name = "product_row_name")
    private String productRowName;
    @Column(name = "subclass_code")
    private String subclassCode;
    @Column(name = "subclass_name")
    private String subclassName;
}
