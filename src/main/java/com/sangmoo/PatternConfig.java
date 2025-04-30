package com.sangmoo;

public class PatternConfig {
    public static final String NAME_PATTERN = "(?<=\\b이름[:\\s]*)([가-힣]{1,2})";
    public static final String JUMIN_PATTERN = "(\\d{6})-(\\d{7})";
    public static final String PHONE_PATTERN = "(\\d{3})-(\\d{3,4})-(\\d{4})";
    public static final String EMAIL_PATTERN = "[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}";
}
