package ru.backendpro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;

@Entity
@Table(name = "payment_detail",
    uniqueConstraints = @UniqueConstraint(name = "payment_detail_payment_id_field_id_UNQ", columnNames = {"payment_id", "field_id"}),
    indexes = {
        @Index(name = "payment_detail_field_id_I", columnList = "field_id")
    })
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_detail_seq")
    @SequenceGenerator(name = "payment_detail_seq", sequenceName = "payment_detail_id_seq", allocationSize = 5)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id", nullable = false)
    private PaymentDataField field;

    @Column(name = "field_value", nullable = false, length = 500)
    private String fieldValue;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime updatedAt;
}
