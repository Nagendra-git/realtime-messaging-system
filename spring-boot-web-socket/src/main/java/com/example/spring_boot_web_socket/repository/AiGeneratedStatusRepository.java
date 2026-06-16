package com.example.spring_boot_web_socket.repository;

import com.example.spring_boot_web_socket.model.AiGeneratedStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AiGeneratedStatusRepository extends JpaRepository<AiGeneratedStatus, Integer> {
    Optional<AiGeneratedStatus> findById(Long id);
}
