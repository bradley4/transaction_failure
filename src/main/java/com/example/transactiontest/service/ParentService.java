package com.example.transactiontest.service;

import com.example.transactiontest.domain.Monkey;
import com.example.transactiontest.repository.MonkeyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class ParentService {

    private final MonkeyRepository repository;

    public final ChildService childService;

    @Transactional
    public void changeFromParent(Long id) {
        Monkey monkey = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        try {
            childService.changeFromChild(monkey.getId());
        } catch (Exception e) {
            log.info("error catch");
        }

        monkey.setName("change name from parent");
    }
}
