# SensitiveMasker (JDK 1.8 호환)

Java 1.8 이상 환경에서 사용할 수 있는 **개인정보 마스킹 및 복호화 유틸리티 라이브러리**입니다.  
사용자 이름, 주민등록번호, 전화번호, 이메일, 주소, 계좌번호, 카드번호, IP주소, 차량번호 등  
민감 정보를 **마스킹(Masking)** 하고 필요 시 **복원(Unmasking)** 할 수 있도록 도와줍니다.

JAR 이용시 mvn clean package로 target 폴더에
[sensitive-masker-1.0.0.jar](target/sensitive-masker-1.0.0.jar) 생성 후 사용
---

## 기능 요약

| 항목       | 마스킹 예시                                        | 복호화 지원 |
| -------- | --------------------------------------------- | ------ |
| 이름       | `전상문` → `전*문`                                 | ✅ 지원   |
| 주민등록번호   | `900101-1234567` → `900101-1******`           | ✅ 지원   |
| 전화번호     | `010-1234-5678` → `010-****-5678`             | ✅ 지원   |
| 이메일 (부분) | `abc@domain.com` → `a**@domain.com`           | ✅ 지원   |
| 이메일 (전체) | `abc@domain.com` → `***@***********`          | ✅ 지원   |
| 주소       | `서울시 강남구 테헤란로 123` → `서울시 강남구 테헤란로 ***`       | ✅ 지원   |
| 계좌번호     | `110-234-567890` → `***-***-567890`           | ✅ 지원   |
| 카드번호     | `1234-5678-1234-5678` → `1234-****-****-5678` | ✅ 지원   |
| IP 주소    | `192.168.0.1` → `***.***.0.1`                 | ✅ 지원   |
| 차량번호     | `12가3456` → `**가3456`                         | ✅ 지원   |
| 우편번호     | `04542` → `04***`                             | ✅ 지원   |
| 사업자등록번호  | `123-45-67890` → `***-**-67890`               | ✅ 지원   |
| 여권번호     | `M12345678` → `M*******8`                     | ✅ 지원   |
| 운전면허번호   | `11-123456-78` → `**-******-78`               | ✅ 지원   |

---

## 🧪 예제

```java
import com.sangmoo.SensitiveMasker;

public class Example {
    public static void main(String[] args) {
        System.out.println(SensitiveMasker.maskName("전상문"));
        System.out.println(SensitiveMasker.maskSsn("900101-1234567"));
        System.out.println(SensitiveMasker.maskPhone("010-1234-5678"));
        System.out.println(SensitiveMasker.maskEmail("abc@domain.com"));
        System.out.println(SensitiveMasker.maskEmailFully("abc@domain.com"));
        System.out.println(SensitiveMasker.maskAddress("서울시 강남구 테헤란로 123", false));
        System.out.println(SensitiveMasker.maskAccount("110-234-567890"));
        System.out.println(SensitiveMasker.maskCard("1234-5678-1234-5678"));
        System.out.println(SensitiveMasker.maskIp("192.168.0.1"));
        System.out.println(SensitiveMasker.maskCarNumber("12가3456"));
        System.out.println(SensitiveMasker.maskPostalCode("04542"));
        System.out.println(SensitiveMasker.maskBizNo("123-45-67890"));
        System.out.println(SensitiveMasker.maskPassport("M12345678"));
        System.out.println(SensitiveMasker.maskDriverLicense("11-123456-78"));
    }
}
```

## 복호화 예시
```java
String masked = SensitiveMasker.maskName("전상문");
String original = "전상문";
String unmasked = SensitiveMasker.unmaskName(masked, original); // 전상문
```

## 설치 방법
1. 로컬 JAR 설치
```
mvn install:install-file \
  -Dfile=target/sensitive-masker-1.0.0.jar \
  -DgroupId=com.sangmoo \
  -DartifactId=sensitive-masker \
  -Dversion=1.0.0 \
  -Dpackaging=jar
```
2. Maven 프로젝트에 추가
```
<dependency>
    <groupId>com.sangmoo</groupId>
    <artifactId>sensitive-masker</artifactId>
    <version>1.0.0</version>
</dependency>
```
## 지원 환경
1. Java 1.8 이상 
2. Maven 프로젝트용 JAR 제공
3. 외부 라이브러리 의존성 최소화 (StringBuilder, replaceAll, substring 위주 구현)
