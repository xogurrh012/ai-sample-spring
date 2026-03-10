# Demo Project Root

## 목적
Spring Boot 3.3.4 기반의 블로그 서비스 프로토타입 프로젝트. SSR(Mustache)과 REST API를 병행하여 제공한다.

## 주요 파일
| 파일명 | 설명 |
| --- | --- |
| build.gradle | 프로젝트 의존성 및 빌드 설정 |
| settings.gradle | 프로젝트 이름 및 모듈 설정 |
| AI-GUIDE.md | AI 가이드라인 및 가용 스킬 정의 |
| GEMINI.md | Gemini CLI 전용 지침 (최우선순위) |
| README.md | 프로젝트 개요 및 로컬 실행 방법 |

## 하위 디렉토리
- `.ai/` - AI 에이전트용 규칙 및 스킬 정의
- `.person/` - 개인화된 작업 가이드 및 워크플로우
- `src/` - 소스 코드 및 리소스
- `gradle/` - Gradle 래퍼 설정

## AI 작업 지침
- 모든 코드는 `AI-GUIDE.md`와 `.ai/rules/code-convention.md`를 엄격히 준수해야 한다.
- 도메인 기반 플랫 구조를 유지하며 레이어 기반 패키징을 지양한다.
- 인증은 `HttpSession`을 사용하며 별도 요청 없이는 Spring Security를 도입하지 않는다.

## 테스트
- `./gradlew test` 명령어로 전체 테스트 실행 가능.

## 의존성
- Spring Boot Starter Web, Data JPA, Mustache, H2, Lombok.
