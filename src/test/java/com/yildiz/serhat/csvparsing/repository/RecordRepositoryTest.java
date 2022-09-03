package com.yildiz.serhat.csvparsing.repository;

import com.yildiz.serhat.csvparsing.domain.entity.Record;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RecordRepositoryTest {

    @Autowired
    private RecordRepository recordRepository;

    @BeforeEach
    void setup() {
        recordRepository.deleteAll();
    }

    @Test
    void shouldGetRecordByCode() {
        Record record1 = Record.builder()
                .code("code1")
                .build();

        Record record2 = Record.builder()
                .code("code2")
                .build();

        recordRepository.saveAll(Arrays.asList(record1, record2));

        Optional<Record> recordByCode = recordRepository.findRecordByCode("code1");

        assertEquals("code1", recordByCode.get().getCode());
    }

}