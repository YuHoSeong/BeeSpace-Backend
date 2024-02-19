package com.creavispace.project.domain.file.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.file.dto.response.UploadFileResponseDto;
import com.creavispace.project.domain.file.service.FileServiceImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
public class FileController {
    
    private final FileServiceImpl s3UploadService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessResponseDto<UploadFileResponseDto>> fileUpload(@RequestParam(value = "file", required = true) MultipartFile file) throws Exception {
        return ResponseEntity.ok().body(s3UploadService.saveFile(file));
    }

    
    
}
