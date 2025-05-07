package com.sangmoo;

public class SensitiveMasker {

    private static String repeatChar(char ch, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(ch);
        }
        return sb.toString();
    }

    public static String maskName(String name) {
        if (name == null || name.length() < 2) return name;
        return name.charAt(0) + repeatChar('*', name.length() - 2) + name.charAt(name.length() - 1);
    }

    public static String unmaskName(String masked, String original) {
        return original;
    }

    public static String maskSsn(String ssn) {
        return ssn.replaceAll("(\\d{6}-\\d)", "$1******");
    }

    public static String unmaskSsn(String masked, String original) {
        return original;
    }

    public static String maskPhone(String phone) {
        return phone.replaceAll("(\\d{3}-)\\d{3,4}(-\\d{4})", "$1****$2");
    }

    public static String unmaskPhone(String masked, String original) {
        return original;
    }

    public static String maskEmail(String email) {
        if (email == null || !email.contains("@")) return email;
        int idx = email.indexOf("@");
        if (idx <= 1) return repeatChar('*', idx) + email.substring(idx);
        return email.charAt(0) + repeatChar('*', idx - 1) + email.substring(idx);
    }

    public static String maskEmailFully(String email) {
        if (email == null || !email.contains("@")) return email;
        String[] parts = email.split("@");
        return repeatChar('*', parts[0].length()) + "@" + repeatChar('*', parts[1].length());
    }

    public static String unmaskEmail(String masked, String original) {
        return original;
    }

    public static String maskAddress(String address, boolean maskFull) {
        if (address == null || address.trim().isEmpty()) return null;

        if (maskFull) {
            return repeatChar('*', address.length());
        } else {
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

    public static String maskCard(String card) {
        if (card == null || card.length() < 4) return card;
        return card.replaceAll("(\\d{4})-(\\d{4})-(\\d{4})-(\\d{4})", "$1-****-****-$4");
    }

    public static String unmaskCard(String masked, String original) {
        return original;
    }

    public static String maskIp(String ip) {
        if (ip == null) return null;
        String[] tokens = ip.split("\\.");
        if (tokens.length != 4) return ip;
        return "***.***." + tokens[2] + "." + tokens[3];
    }

    public static String unmaskIp(String masked, String original) {
        return original;
    }

    public static String maskCarNumber(String carNo) {
        if (carNo == null || carNo.length() < 4) return carNo;
        return repeatChar('*', carNo.length() - 4) + carNo.substring(carNo.length() - 4);
    }

    public static String unmaskCarNumber(String masked, String original) {
        return original;
    }

    public static String maskPostalCode(String postal) {
        if (postal == null || postal.length() != 5) return postal;
        return postal.substring(0, 2) + "***";
    }

    public static String maskBizNo(String bizNo) {
        return bizNo.replaceAll("(\\d{3})-(\\d{2})-(\\d{5})", "***-**-$3");
    }

    public static String maskPassport(String passport) {
        if (passport == null || passport.length() < 3) return passport;
        return passport.charAt(0) + repeatChar('*', passport.length() - 2) + passport.charAt(passport.length() - 1);
    }

    public static String maskDriverLicense(String license) {
        return license.replaceAll("(\\d{2})-(\\d{6})-(\\d{2})", "**-******-$3");
    }
} 
