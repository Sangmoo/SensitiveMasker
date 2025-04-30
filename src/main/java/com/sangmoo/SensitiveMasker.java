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

    // 계좌번호 마스킹: 110-234-567890 → ***-***-567890
    public static String maskAccount(String account) {
        if (account == null || account.length() < 6) return account;
        String[] parts = account.split("-");
        if (parts.length < 3) return repeatChar('*', account.length());
        return repeatChar('*', parts[0].length()) + "-" +
               repeatChar('*', parts[1].length()) + "-" +
               parts[2];
    }

    public static String unmaskAccount(String masked, String original) {
        return original;
    }

    // 카드번호 마스킹: 1234-5678-1234-5678 → 1234-****-****-5678
    public static String maskCard(String card) {
        if (card == null || card.length() < 4) return card;
        return card.replaceAll("(\\d{4})-(\\d{4})-(\\d{4})-(\\d{4})", "$1-****-****-$4");
    }

    public static String unmaskCard(String masked, String original) {
        return original;
    }

    // IP 주소 마스킹: 192.168.0.1 → ***.***.0.1
    public static String maskIp(String ip) {
        if (ip == null) return null;
        String[] tokens = ip.split("\\.");
        if (tokens.length != 4) return ip;
        return "***.***." + tokens[2] + "." + tokens[3];
    }

    public static String unmaskIp(String masked, String original) {
        return original;
    }

    // 차량 번호 마스킹: 12가3456 → **가3456
    public static String maskCarNumber(String carNo) {
        if (carNo == null || carNo.length() < 4) return carNo;
        return repeatChar('*', carNo.length() - 4) + carNo.substring(carNo.length() - 4);
    }

    public static String unmaskCarNumber(String masked, String original) {
        return original;
    }
}
