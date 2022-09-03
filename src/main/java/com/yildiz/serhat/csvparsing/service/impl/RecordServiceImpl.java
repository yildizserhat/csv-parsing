package com.yildiz.serhat.csvparsing.service.impl;

import com.yildiz.serhat.csvparsing.domain.entity.Record;
import com.yildiz.serhat.csvparsing.domain.model.CsvRecordModel;
import com.yildiz.serhat.csvparsing.helper.CsvHelper;
import com.yildiz.serhat.csvparsing.repository.RecordRepository;
import com.yildiz.serhat.csvparsing.service.RecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.yildiz.serhat.csvparsing.domain.entity.Record.buildRecord;


@Service
@Slf4j
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

    private final RecordRepository recordRepository;

    @Override
    public void saveRecords(MultipartFile file) {
        validateFile(file);
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            List<CsvRecordModel> csvRecordModels = CsvHelper.convertFromFileToRecord(reader);
            List<Record> records = createRecords(csvRecordModels);
            recordRepository.saveAll(records);
            log.info("{} record(s) saved", records.size());
        } catch (Exception ex) {
            log.error("Exception occurred: {}", ex.getMessage());
        }
    }

    @Override
    public Optional<Record> getRecordByCode(String code) {
        log.info("Record is received by code: {}", code);
        return recordRepository.findRecordByCode(code);
    }

    @Override
    public List<Record> getAllRecords() {
        return recordRepository.findAll();
    }

    @Override
    public void deleteAllRecords() {
        recordRepository.deleteAll();
        log.info("All Records are deleted.");
    }

    private void validateFile(MultipartFile file) {
        CsvHelper.isCSVFormat(file.getContentType());
    }

    private List<Record> createRecords(List<CsvRecordModel> csvRecordModels) {
        List<Record> records = new ArrayList<>();
        csvRecordModels.forEach(csvRecordModel ->
                {
                    Record record = buildRecord(csvRecordModel);
                    records.add(record);
                }
        );
        return records;
    }
}
