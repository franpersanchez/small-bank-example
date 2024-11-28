package org.example.smallbankexample.infraestructure.addapters.repository;

import org.example.smallbankexample.domain.models.Wallet;
import org.example.smallbankexample.infraestructure.addapters.entities.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, UUID> {
}
