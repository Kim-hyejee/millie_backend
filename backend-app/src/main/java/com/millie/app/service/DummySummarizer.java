package com.millie.app.service;

import com.millie.app.enums.LengthOption;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DummySummarizer {
    
    public String summarize(String text, LengthOption lengthOpt) {
        if (text == null || text.trim().isEmpty()) {
            return "";
        }
        
        // Split by sentence endings (.!?)
        String[] sentences = text.split("[.!?]+");
        List<String> validSentences = Arrays.stream(sentences)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
        
        int maxSentences = switch (lengthOpt) {
            case SHORT -> 3;
            case NORMAL -> 6;
            case DEEP -> 12;
        };
        
        int sentenceCount = Math.min(validSentences.size(), maxSentences);
        
        if (sentenceCount == 0) {
            return "";
        }
        
        return validSentences.subList(0, sentenceCount).stream()
                .collect(Collectors.joining(". ")) + ".";
    }
}
