# Loose JSON Parser

Java 기반으로 느슨한 JSON 입력을 자동 전처리하여 안전하게 `JsonNode`로 변환해주는 라이브러리입니다.

---

## 주요 기능
- 작은따옴표(`'`) ➔ 큰따옴표(`"`) 자동 변환
- 마지막 쉼표(,) 자동 제거
- 주석(`//`, `/* ... */`) 자동 제거
- 키(Key) 이름 자동 보정 (따옴표 없는 키 → 따옴표 추가)

---

## 설치 방법 (로컬 수동 설치)
mvn clean install 해서 target/loose-json-parser-1.0.0.jar 파일 생성


1. JAR을 로컬 Maven 프로젝트로 Install 하는 방법
로컬에 jar을 이용한 install 방법
```
mvn install:install-file \
  -Dfile=target/loose-json-parser-1.0.0.jar \
  -DgroupId=com.sangmoo \
  -DartifactId=loose-json-parser \
  -Dversion=1.0.0 \
  -Dpackaging=jar
```

2. 다른 프로젝트의 pom.xml에 추가하는 방법
```
<dependency>
    <groupId>com.sangmoo</groupId>
    <artifactId>loose-json-parser</artifactId>
    <version>1.0.0</version>
</dependency>

```

3. 실제 사용법(Java 코드)
```
import com.sangmoo.LooseJsonParser;
import com.fasterxml.jackson.databind.JsonNode;

public class Main {
    public static void main(String[] args) throws Exception {
        String looseJson = "{id:1, name:'홍길동', }";

        JsonNode parsed = LooseJsonParser.parse(looseJson);

        System.out.println(parsed.toPrettyString());
    }
}
```

4. 출력 결과 
```
{
  "id": 1,
  "name": "홍길동"
}
```

---

### 참고
- JDK 1.8 이상
- 내부적으로 Jackson 2.7.5 사용