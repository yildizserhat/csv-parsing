package com.yildiz.serhat.csvparsing.controller;

import com.yildiz.serhat.csvparsing.repository.RecordRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RecordControllerTest {

    @Autowired
    private RecordRepository recordRepository;
    @Autowired
    private MockMvc mvc;


    @AfterEach
    void tearDown() {
        recordRepository.deleteAll();
    }

    @Test
    void shouldUploadFile() throws Exception {
        String url = "/v1/records/upload";
        MockMultipartFile file = new MockMultipartFile("file", "exercise.csv",
                "text/csv", "Records".getBytes());

        mvc.perform(MockMvcRequestBuilders.multipart(url)
                        .file(file))
                .andExpect(status().isOk());
    }
}