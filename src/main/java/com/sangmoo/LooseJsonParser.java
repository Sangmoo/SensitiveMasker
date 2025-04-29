package com.sangmoo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LooseJsonParser {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * 느슨한 JSON 문자열을 정리해서 JsonNode로 변환
     */
    public static JsonNode parse(String inputJson) throws Exception {
        if (inputJson == null) {
            throw new IllegalArgumentException("Input JSON cannot be null");
        }

        String processed = inputJson;

        // 1. 작은 따옴표를 큰 따옴표로 변환
        processed = processed.replace("'", "\"");

        // 2. 마지막 쉼표 제거 (ex: ,} ,] 같은)
        processed = processed.replaceAll(",\\s*([}\\]])", "$1");

        // 3. 라인 주석 제거 (// ~~)
        processed = processed.replaceAll("//.*?(\\r?\\n)", "$1");

        // 4. 블록 주석 제거 (/* ~~ */)
        processed = processed.replaceAll("/\\*.*?\\*/", "");

        // 5. Key에 따옴표 없는 경우 보정 (조심해서 적용)
        processed = processed.replaceAll("(?<=[{,\\s])([a-zA-Z0-9_]+)\\s*:", "\"$1\":");

        return mapper.readTree(processed);
    }
}
