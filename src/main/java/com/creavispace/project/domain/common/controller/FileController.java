package com.creavispace.project.domain.common.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.creavispace.project.domain.common.service.S3UploadService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
public class FileController {
    
    private final S3UploadService s3UploadService;

    @PostMapping("/upload")
    public ResponseEntity<?> fileUpload(@RequestParam(value = "file", required = true) MultipartFile file) throws Exception {
        return s3UploadService.saveFile(file);
    }

    
    
}
