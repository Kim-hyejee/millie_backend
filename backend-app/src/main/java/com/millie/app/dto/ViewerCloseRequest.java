package com.millie.app.dto;

import com.millie.app.enums.LengthOption;
import lombok.Data;

@Data
public class ViewerCloseRequest {
    private Long bookId;
    private Integer lastPage;
    private LengthOption lengthOpt;
}
