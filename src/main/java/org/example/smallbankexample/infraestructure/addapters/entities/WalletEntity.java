package org.example.smallbankexample.infraestructure.addapters.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.smallbankexample.domain.models.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "wallets")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WalletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal balance;

    @ElementCollection
    @MapKeyColumn(name = "transaction_id")
    @Column(name = "transaction_timestamp")
    @CollectionTable(name = "wallet_transactions", joinColumns = @JoinColumn(name = "wallet_id"))
    private Map<String, LocalDateTime> transactions;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}

