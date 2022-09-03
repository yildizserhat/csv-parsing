package com.yildiz.serhat.csvparsing.controller;

import com.yildiz.serhat.csvparsing.domain.entity.Record;
import com.yildiz.serhat.csvparsing.service.RecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/v1/records")
@Tag(name = "records", description = "Endpoints about records")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @GetMapping()
    @Operation(summary = "Get All Records")
    public ResponseEntity<?> getAllRecords() {
        List<Record> allRecords = recordService.getAllRecords();
        return ResponseEntity.ok(allRecords);
    }

    @PostMapping(value = "/upload", consumes = MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Uploading records from csv file")
    public ResponseEntity<?> uploadRecord(@RequestParam("file") MultipartFile file) {
        recordService.saveRecords(file);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{code}")
    @Operation(summary = "Get Record By code")
    public ResponseEntity<?> getRecordByCode(@PathVariable String code) {
        Optional<Record> recordByCode = recordService.getRecordByCode(code);
        return recordByCode.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping
    @Operation(summary = "Delete All Records")
    public ResponseEntity<?> deleteAllRecords() {
        recordService.deleteAllRecords();
        return ResponseEntity.ok().build();
    }
}
