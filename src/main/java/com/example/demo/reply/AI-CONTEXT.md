<!-- Parent: ../AI-CONTEXT.md -->

# com.example.demo.reply/

## 목적
게시글 댓글 작성 및 삭제 도메인.

## 주요 파일
| 파일명 | 설명 |
| --- | --- |
| Reply.java | 댓글 엔티티 (id, comment, User/Board 외래키) |
| ReplyController.java | 댓글 관련 SSR 요청 처리 |
| ReplyRepository.java | 댓글 데이터 접근 |
| ReplyRequest.java | `Save` 등 요청 DTO |
| ReplyResponse.java | `Max` 등 응답 DTO |
| ReplyService.java | 댓글 작성/삭제 비즈니스 로직 |

## AI 작업 지침
- 댓글 삭제 시에는 작성자(User) 본인인지 세션을 통해 반드시 검증한다.
- `Reply`는 `Board`와 `User`를 각각 `LAZY` 로딩으로 참조한다.
- 댓글 목록은 보통 `BoardResponse.Detail` 안에 포함되어 조회된다.

## 테스트
- 특정 게시글(`board_id`)에 대한 댓글 저장 및 특정 댓글 삭제 검증.
