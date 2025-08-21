package com.millie.app.enums;

public enum LengthOption {
    SHORT("short"),
    NORMAL("normal"),
    DEEP("deep");
    
    private final String value;
    
    LengthOption(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static LengthOption fromValue(String value) {
        for (LengthOption option : values()) {
            if (option.value.equals(value)) {
                return option;
            }
        }
        throw new IllegalArgumentException("Unknown length option: " + value);
    }
    
    // 데이터베이스 값으로부터 ENUM 생성 (대소문자 구분 없이)
    public static LengthOption fromString(String value) {
        if (value == null) {
            return null;
        }
        
        for (LengthOption option : values()) {
            if (option.value.equalsIgnoreCase(value)) {
                return option;
            }
        }
        
        // 정확한 매칭이 안 되면 null 반환 (예외 대신)
        return null;
    }
}
