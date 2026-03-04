# 코드 컨벤션

이 문서는 우리 프로젝트의 코딩 컨벤션을 간략하게 설명합니다.

## 1. 패키지 구조

- 패키지는 도메인별로 구성됩니다: `com.example.demo.domainName`
- 예: `com.example.demo.board`, `com.example.demo.user`

## 2. 이름 지정 규칙

- **클래스**: 파스칼 케이스 (예: `BoardController`)
- **메서드**: 카멜 케이스 (예: `getBoardList`)
- **데이터베이스 테이블**: `_tb` 접미사를 붙인 스네이크 케이스 (예: `user_tb`, `board_tb`)
- **데이터베이스 컬럼**: 스네이크 케이스 (예: `created_at`)

## 3. 계층형 아키텍처

애플리케이션은 다음 계층으로 나뉩니다:

- **컨트롤러**: 들어오는 HTTP 요청, 데이터 유효성 검사 및 응답 형식 지정을 처리합니다.
- **서비스**: 핵심 비즈니스 로직을 포함합니다. 트랜잭션은 여기서 시작됩니다.
- **리포지토리**: Spring Data JPA를 사용하여 데이터 영속성 및 접근을 담당합니다.
- **엔티티**: 데이터 모델 및 데이터베이스 테이블 구조를 나타냅니다.

## 4. DTO (데이터 전송 객체) 명명 규칙

- 요청 및 응답 DTO는 래퍼 클래스 내부에 정적 중첩 클래스로 정의됩니다.
- 래퍼 클래스의 이름은 도메인 이름을 따릅니다 (예: `UserRequest`, `BoardResponse`).
- 중첩 DTO 클래스 이름은 특정 기능이나 작업을 설명해야 합니다.
- 예:
  ```java
  public class UserRequest {
      @Data
      public static class Login {
          private String username;
          private String password;
      }

      @Data
      public static class Join {
          private String username;
          private String password;
          private String email;
      }
  }
  ```

## 5. JPA 엔티티 규칙

- **지연 로딩**: 모든 엔티티 관계(`@ManyToOne`, `@OneToMany` 등)는 `fetch = FetchType.LAZY`로 구성되어야 합니다.
- **OSIV**: N+1 쿼리 문제를 방지하기 위해 `spring.jpa.open-in-view` 속성은 `false`로 설정됩니다.
- **기본 키**: 기본 키 필드는 `Integer` 타입이어야 합니다.
- **ID 생성**: 자동 키 생성을 위해 `GenerationType.IDENTITY`를 사용합니다.

## 6. Lombok 사용법

- **`@Data`**: DTO 및 엔티티에 사용하여 게터, 세터, `toString` 등을 자동으로 생성합니다.
- **`@NoArgsConstructor`**: JPA 엔티티에 인수 없는 생성자를 추가합니다.
- **`@RequiredArgsConstructor`**: 컨트롤러 및 서비스에서 생성자 기반 의존성 주입을 위해 사용합니다.
- **`@Builder`**: 테스트 또는 서비스 계층에서 객체 생성을 위한 빌더 패턴을 제공하기 위해 엔티티에 사용합니다.

## 7. 의존성 주입

- 항상 `private final` 필드와 함께 생성자 주입을 사용합니다. 이는 불변성을 보장하고 의존성을 명시적으로 만듭니다.
- 예 (서비스 내):
  ```java
  @RequiredArgsConstructor
  @Service
  public class BoardService {
      private final BoardRepository boardRepository;
      private final UserRepository userRepository;
      // ...
  }
  ```
