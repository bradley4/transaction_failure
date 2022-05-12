package com.example.transactiontest;

import com.example.transactiontest.domain.Monkey;
import com.example.transactiontest.repository.MonkeyRepository;
import com.example.transactiontest.service.ParentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TransactionTestApplicationTests {

    @Autowired
    private ParentService parentService;

    @Autowired
    private MonkeyRepository repository;

    private Long savedId;

    @BeforeEach
    void setUp() {
        Monkey monkey = new Monkey();
        monkey.setName("init name");
        monkey.setNickname("init nick name");
        Monkey saved = repository.save(monkey);
        savedId = saved.getId();
    }

    @DisplayName("해당 트렌젝션이 작동안하는 원인을 파악하고 해결책을 제시하세요.")
    @Test
    @Commit
    void transaction_test() {
        parentService.changeFromParent(savedId);

        Monkey monkey = repository.findById(savedId)
                .orElseThrow(EntityNotFoundException::new);

        assertThat(monkey.getName()).isEqualTo("change name from parent");
        assertThat(monkey.getNickname()).isEqualTo("init nick name");
    }

}
