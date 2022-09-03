package com.yildiz.serhat.csvparsing.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yildiz.serhat.csvparsing.domain.model.CsvRecordModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDate;

import static com.yildiz.serhat.csvparsing.helper.DateHelper.timeFormatter;


@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "record", indexes = @Index(name = "idx_code", columnList = "code", unique = true))
public class Record extends BaseEntity {

    @Id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "source")
    private String source;

    @Column(name = "description")
    private String description;

    @Column(name = "code_list")
    private String codeList;

    @Column(name = "code")
    private String code;

    @Column(name = "display_value")
    private String displayValue;

    @Column(name = "priority")
    private int priority;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    public static Record buildRecord(CsvRecordModel model) {
        return Record.builder()
                .source(model.getSource())
                .description(model.getLongDescription())
                .codeList(model.getCodeListCode())
                .code(model.getCode())
                .displayValue(model.getDisplayValue())
                .priority(model.getSortingPriority())
                .fromDate(StringUtils.isBlank(model.getFromDate()) ? null : LocalDate.parse(model.getFromDate(), timeFormatter))
                .toDate(StringUtils.isBlank(model.getToDate()) ? null : LocalDate.parse(model.getToDate(), timeFormatter))
                .build();
    }
}
