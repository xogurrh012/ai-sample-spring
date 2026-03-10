<!-- Parent: ../../../../../AI-CONTEXT.md -->

# com.example.demo/

## 목적
Spring Boot 블로그 프로젝트의 애플리케이션 루트 패키지. 도메인 기반 플랫 구조를 지향한다.

## 주요 파일
| 파일명 | 설명 |
| --- | --- |
| DemoApplication.java | 메인 실행 파일 |

## 하위 디렉토리
- `_core/` - 전역 예외 처리 및 공통 유틸리티
- `user/` - 사용자(User) 도메인 관련 로직
- `board/` - 게시글(Board) 도메인 관련 로직
- `reply/` - 댓글(Reply) 도메인 관련 로직

## AI 작업 지침
- 도메인 하위에 패키지(controller, service, repository)를 추가하지 말고 파일들을 한 폴더에 나열한다.
- `_core/utils/Resp.java`를 사용하여 모든 API 응답을 감싼다.
- 새로운 도메인을 추가할 때 반드시 `AI-GUIDE.md`를 참고하여 `code-convention`을 따른다.

## 테스트
- `src/test/java/com/example/demo/DemoApplicationTests.java`에서 전체 로딩 테스트 수행.
