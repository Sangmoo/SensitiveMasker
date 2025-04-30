package com.sangmoo;

public class SensitiveMasker {

    // 문자열 반복 함수 (JDK 1.8 대응)
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

    // 주소 마스킹: 서울시 강남구 테헤란로 123 4층 → 서울시 강남구 테헤란로 ***
    public static String maskAddress(String address, boolean maskFull) {
        if (address == null || address.trim().isEmpty()) return null;

        if (maskFull) { // 1이나 Y면 풀 마스킹
            // 전체 마스킹: 모든 글자를 '*'로 변환
            return repeatChar('*', address.length());
        } else {
            // 부분 마스킹: 앞의 3단어까지만 유지
            // okens.length와 i 값 n으로 제어하면 n단어까지는 노출 0으로 변경하면 전체 마스킹
            String[] tokens = address.split(" ");
            if (tokens.length <= 3) return address;

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                sb.append(tokens[i]).append(" ");
            }
            sb.append("***");
            return sb.toString().trim();
        }
    }

    public static String unmaskAddress(String masked, String original) {
        return original;
    }
}
