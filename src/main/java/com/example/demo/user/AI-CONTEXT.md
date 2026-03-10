<!-- Parent: ../AI-CONTEXT.md -->

# com.example.demo.user/

## 목적
사용자 인증 및 회원 관리 도메인.

## 주요 파일
| 파일명 | 설명 |
| --- | --- |
| User.java | 사용자 엔티티 (id, username, password, email) |
| UserController.java | 회원가입/로그인 SSR 요청 처리 |
| UserRepository.java | 사용자 데이터 접근 |
| UserRequest.java | `Join`, `Login` 등 요청 DTO |
| UserResponse.java | `Max`, `Min` 등 응답 DTO |
| UserService.java | 회원가입/로그인 비즈니스 로직 및 세션 관리 지원 |

## AI 작업 지침
- 패스워드 암호화 및 유효성 검사 로직을 서비스 레이어에 포함한다.
- `UserController`는 뷰 페이지를 반환하며, API가 필요하면 `UserApiController`를 생성한다.
- 인증 정보는 `HttpSession`에 "sessionUser" 키로 저장한다.

## 테스트
- `UserRepository`의 `save` 기능 및 `findByUsernameAndPassword` 검증.
