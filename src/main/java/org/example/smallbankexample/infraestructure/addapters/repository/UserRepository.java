package org.example.smallbankexample.infraestructure.addapters.repository;

import org.example.smallbankexample.domain.models.User;
import org.example.smallbankexample.infraestructure.addapters.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
