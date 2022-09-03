package com.yildiz.serhat.csvparsing.domain.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CsvRecordModel {
    @CsvBindByName
    private String source;
    @CsvBindByName
    private String codeListCode;
    @CsvBindByName
    private String code;
    @CsvBindByName
    private String displayValue;
    @CsvBindByName
    private String longDescription;
    @CsvBindByName
    private String fromDate;
    @CsvBindByName
    private String toDate;
    @CsvBindByName
    private int sortingPriority;
}
