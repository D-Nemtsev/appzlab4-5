package javaproject.demo.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private Long id;
    private String name;
    private String code;
    private String brand;
    private String description;
    private Integer countOfAvailability;
    private BigDecimal price;
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "product", fetch = EAGER, cascade = CascadeType.ALL)
    private Set<Order> orders;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}