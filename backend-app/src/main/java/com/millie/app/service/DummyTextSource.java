package com.millie.app.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DummyTextSource {
    
    private static final String PAGE_SEPARATOR = "\n===PAGE===\n";
    
    public List<String> getPages(Long bookId) {
        try {
            ClassPathResource resource = new ClassPathResource("books/" + bookId + ".txt");
            if (!resource.exists()) {
                return List.of();
            }
            
            String content = resource.getContentAsString(StandardCharsets.UTF_8);
            String[] pages = content.split(PAGE_SEPARATOR);
            
            return List.of(pages);
        } catch (IOException e) {
            return List.of();
        }
    }
    
    public String getTextFromPages(Long bookId, int fromPage, int toPage) {
        List<String> pages = getPages(bookId);
        
        if (pages.isEmpty() || fromPage < 1 || toPage > pages.size()) {
            return "";
        }
        
        return pages.subList(fromPage - 1, toPage).stream()
                .collect(Collectors.joining(" "));
    }
}
