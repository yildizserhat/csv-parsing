package com.yildiz.serhat.csvparsing.impl;

import com.yildiz.serhat.csvparsing.domain.entity.Record;
import com.yildiz.serhat.csvparsing.repository.RecordRepository;
import com.yildiz.serhat.csvparsing.service.impl.RecordServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecordServiceImplTest {

    @InjectMocks
    private RecordServiceImpl recordService;

    @Mock
    private RecordRepository recordRepository;


    @Test
    void shouldGetRecordByCode() {
        Record actual = Record.builder().code("code1").build();
        when(recordRepository.findRecordByCode("code1")).thenReturn(Optional.of(actual));

        Optional<Record> expected = recordService.getRecordByCode("code1");

        assertEquals(actual.getCode(), expected.get().getCode());
    }

    @Test
    void shouldSaveRecord() {
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "exercise.csv",
                "text/csv",
                "exercise".getBytes());

        recordService.saveRecords(file);

        verify(recordRepository, atLeastOnce()).saveAll(any());
    }
}