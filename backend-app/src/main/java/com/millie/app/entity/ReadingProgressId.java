package com.millie.app.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import jakarta.persistence.Column;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ReadingProgressId implements Serializable {
    
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "book_id")
    private Long bookId;
}
