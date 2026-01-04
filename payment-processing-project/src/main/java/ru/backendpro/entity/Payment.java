package ru.backendpro.entity;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "payment", indexes = {
    @Index(name = "payment_source_account_id_I", columnList = "source_account_id"),
    @Index(name = "payment_target_account_id_I", columnList = "target_account_id"),
    @Index(name = "payment_status_I", columnList = "status"),
    @Index(name = "payment_payment_date_I", columnList = "payment_date")
})
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_seq")
    @SequenceGenerator(name = "payment_seq", sequenceName = "payment_id_seq", allocationSize = 5)
    private Long id;

    @Column(name = "payment_date", nullable = false)
    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime paymentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_account_id", nullable = false)
    private Account sourceAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_account_id", nullable = false)
    private Account targetAccount;

    @Column(name = "amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "currency_code", nullable = false, length = 3)
    private String currencyCode;

    @Convert(converter = PaymentStatusConverter.class)
    @Column(name = "status", nullable = false)
    private PaymentStatus status;

    @Column(name = "processed_at")
    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime processedAt;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime updatedAt;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PaymentDetail> paymentDetails = new ArrayList<>();
}
