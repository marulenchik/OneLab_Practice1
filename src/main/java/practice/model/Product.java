package practice.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.*;
import java.util.Set;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal price;

    @ManyToMany(mappedBy = "products")
    private Set<Order> orders;
}

