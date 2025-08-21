package com.millie.app.controller;

import com.millie.app.dto.ReadingProgressResponse;
import com.millie.app.dto.ViewerCloseRequest;
import com.millie.app.service.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/viewer")
@RequiredArgsConstructor
public class ViewerController {
    
    private final ProgressService progressService;
    
    @PostMapping("/close")
    public ResponseEntity<ReadingProgressResponse> closeViewer(@RequestBody ViewerCloseRequest request) {
        try {
            ReadingProgressResponse response = progressService.closeViewerAndSummarize(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
