package com.example.transactiontest.service;

import com.example.transactiontest.domain.Monkey;
import com.example.transactiontest.repository.MonkeyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@RequiredArgsConstructor
@Slf4j
@Service
public class ChildService {
    private final MonkeyRepository repository;

    @Transactional
    public void changeFromChild(Long id) {
        Monkey monkey = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        monkey.setNickname("change from child");

        throw new RuntimeException("throw run time exception :(");
    }
}
