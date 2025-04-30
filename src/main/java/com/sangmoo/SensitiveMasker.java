package com.sangmoo;

public class SensitiveMasker {

    // 문자열 반복 함수 (JDK 8 대응)
    private static String repeatChar(char ch, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(ch);
        }
        return sb.toString();
    }

    // 이름 마스킹: 전상문 → 전*문
    public static String maskName(String name) {
        if (name == null || name.length() < 2) return name;
        return name.charAt(0) + repeatChar('*', name.length() - 2) + name.charAt(name.length() - 1);
    }

    public static String unmaskName(String masked, String original) {
        return original;
    }

    // 주민번호 마스킹: 900101-1234567 → 900101-1******
    public static String maskSsn(String ssn) {
        return ssn.replaceAll("(\\d{6}-\\d)", "$1******");
    }

    public static String unmaskSsn(String masked, String original) {
        return original;
    }

    // 전화번호 마스킹: 010-1234-5678 → 010-****-5678
    public static String maskPhone(String phone) {
        return phone.replaceAll("(\\d{3}-)\\d{3,4}(-\\d{4})", "$1****$2");
    }

    public static String unmaskPhone(String masked, String original) {
        return original;
    }

    // 이메일 마스킹: abc@domain.com → a**@domain.com
    public static String maskEmail(String email) {
        if (email == null || !email.contains("@")) return email;
        int idx = email.indexOf("@");
        if (idx <= 1) return repeatChar('*', idx) + email.substring(idx);
        return email.charAt(0) + repeatChar('*', idx - 1) + email.substring(idx);
    }

    public static String unmaskEmail(String masked, String original) {
        return original;
    }
}
