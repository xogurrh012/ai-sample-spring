<!-- Parent: ../AI-CONTEXT.md -->

# com.example.demo.board/

## 목적
블로그 게시글 CRUD 및 목록/상세 조회 도메인.

## 주요 파일
| 파일명 | 설명 |
| --- | --- |
| Board.java | 게시글 엔티티 (id, title, content, User 외래키) |
| BoardController.java | 게시글 SSR 요청 처리 및 페이지 렌더링 |
| BoardRepository.java | 게시글 데이터 접근 |
| BoardRequest.java | `Save`, `Update` 등 요청 DTO |
| BoardResponse.java | `Max`, `Min`, `Detail` 등 응답 DTO |
| BoardService.java | 게시글 CRUD 및 연관된 유저 정보 처리 |

## AI 작업 지침
- `Board` 엔티티 생성 시 반드시 `User` 연관관계(`FetchType.LAZY`)를 포함한다.
- 목록 조회 시 `BoardResponse.Min`을 사용하고, 상세 조회 시 `BoardResponse.Detail`을 사용한다.
- `BoardController`의 모든 쓰기 요청은 세션 유저와 게시글 작성자가 일치하는지 확인한다.

## 테스트
- 게시글 저장(`save`), 목록 조회(`findAll`), 삭제(`deleteById`) 기능 검증.
