package com.example.transactiontest.repository;

import com.example.transactiontest.domain.Monkey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonkeyRepository extends JpaRepository<Monkey, Long> {
}
