package com.yildiz.serhat.csvparsing.service;

import com.yildiz.serhat.csvparsing.domain.entity.Record;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface RecordService {

    void saveRecords(MultipartFile file);

    Optional<Record> getRecordByCode(String code);

    List<Record> getAllRecords();

    void deleteAllRecords();
}
