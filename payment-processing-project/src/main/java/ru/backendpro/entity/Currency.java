package ru.backendpro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CURRENCY",
    uniqueConstraints = {
        @UniqueConstraint(name = "currency_alfa3_UNQ", columnNames = {"alfa3"})
    })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Currency {

    @Id
    @Column(name = "currency_id", nullable = false)
    private Integer currencyId;

    @Column(name = "alfa3", nullable = false, length = 3)
    private String alfa3;

    @Column(name = "description", nullable = false, length = 100)
    private String description;
}
