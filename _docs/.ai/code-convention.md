# AI Skill: 프로젝트 코딩 컨벤션

이 문서는 AI가 이 프로젝트의 코드를 작성하거나 수정할 때 따라야 할 구체적인 코딩 규칙과 가이드라인을 정의합니다.

**Skill ID**: `ProjectCodingConventions`
**Version**: `1.0`

---

## 규칙 요약

### 1. 패키지 구조 (`package-structure`)
- **설명**: 패키지는 반드시 도메인별로 구성해야 합니다.
- **예시**: `com.example.demo.domainName` (예: `com.example.demo.board`, `com.example.demo.user`)

### 2. 이름 규칙 (`naming-conventions`)
- **설명**: 코드 요소 유형에 따라 지정된 이름 규칙을 따릅니다.
- **규칙**:
    - **클래스**: `PascalCase` (예: `BoardController`)
    - **메서드**: `camelCase` (예: `getBoardList`)
    - **데이터베이스 테이블**: `snake_case`에 `_tb` 접미사 추가 (예: `user_tb`, `board_tb`)
    - **데이터베이스 컬럼**: `snake_case` (예: `created_at`)

### 3. 계층형 아키텍처 (`layered-architecture`)
- **설명**: 애플리케이션은 `Controller`, `Service`, `Repository`, `Entity` 계층으로 나뉩니다.
- **계층별 책임**:
    - **Controller**: HTTP 요청 처리, 데이터 유효성 검사, 응답 형식 지정
    - **Service**: 핵심 비즈니스 로직 포함, 트랜잭션 시작 지점
    - **Repository**: Spring Data JPA를 사용한 데이터 영속성 및 접근 담당
    - **Entity**: 데이터 모델 및 데이터베이스 테이블 구조 표현

### 4. DTO 명명 규칙 (`dto-naming`)
- **설명**: 요청(Request) 및 응답(Response) DTO는 반드시 래퍼 클래스 내의 정적 중첩 클래스(static nested classes)로 정의해야 합니다.
- **가이드라인**:
    - 래퍼 클래스 이름은 도메인 이름을 따릅니다 (예: `UserRequest`, `BoardResponse`).
    - 중첩 DTO 클래스 이름은 특정 기능(feature)을 설명해야 합니다 (예: `Login`, `Join`).
- **예시**:
  ```java
  public class UserRequest {
      @Data
      public static class Login { /* ... */ }

      @Data
      public static class Join { /* ... */ }
  }
  ```

### 5. JPA 엔티티 규칙 (`jpa-entity-rules`)
- **설명**: JPA 엔티티에 대한 특정 규칙입니다.
- **가이드라인**:
    - 모든 엔티티 관계(`@ManyToOne`, `@OneToMany` 등)는 반드시 `fetch = FetchType.LAZY`를 사용해야 합니다.
    - OSIV (`spring.jpa.open-in-view`)는 `false`로 설정해야 합니다.
    - 기본 키(Primary key) 필드는 `Integer` 타입을 사용해야 합니다.
    - 기본 키 자동 생성을 위해 `GenerationType.IDENTITY`를 사용해야 합니다.

### 6. Lombok 사용법 (`lombok-usage`)
- **설명**: 표준화된 Lombok 어노테이션 사용법입니다.
- **어노테이션**:
    - **`@Data`**: DTO 및 엔티티에서 상용구 코드(boilerplate code) 생성을 위해 사용합니다.
    - **`@NoArgsConstructor`**: JPA 엔티티에 인자가 없는 생성자를 제공하기 위해 사용합니다.
    - **`@RequiredArgsConstructor`**: 컨트롤러 및 서비스에서 생성자 기반 의존성 주입을 위해 사용합니다.
    - **`@Builder`**: 엔티티에서 객체 생성을 위한 빌더 패턴을 제공하기 위해 사용합니다.

### 7. 의존성 주입 (`dependency-injection`)
- **설명**: 항상 `private final` 필드를 사용하는 생성자 주입을 사용해야 합니다.
- **이유**: 불변성을 보장하고 의존성을 명확하게 만듭니다.
- **예시**:
  ```java
  @RequiredArgsConstructor
  @Service
  public class BoardService {
      private final BoardRepository boardRepository;
      // ...
  }
  ```
