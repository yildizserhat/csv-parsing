package com.yildiz.serhat.csvparsing.repository;

import com.yildiz.serhat.csvparsing.domain.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecordRepository extends JpaRepository<Record, Long> {

    Optional<Record> findRecordByCode(String code);
}
