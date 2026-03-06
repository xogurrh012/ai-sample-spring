# 프로젝트 컨텍스트: 블로그 애플리케이션

## 1. 프로젝트 개요

이 프로젝트는 Spring Boot를 사용하여 간단한 블로그 기능을 구현하는 것을 목표로 합니다. 사용자는 게시글을 작성하고, 다른 사람의 글을 읽고, 댓글을 남길 수 있습니다.

## 2. 전체 아키텍처

본 프로젝트는 전형적인 **계층형 아키텍처(Layered Architecture)** 와 **도메인 중심 패키지 구조**를 따릅니다.

- **Presentation Layer (Controller):** 사용자의 HTTP 요청을 받아 서비스 계층으로 전달하고, 그 결과를 응답으로 반환합니다.
  - `BoardController`, `UserController`, `ReplyController` 등이 해당됩니다.
  - 서버 사이드 렌더링(SSR)을 위한 일반 Controller와 RESTful API를 위한 `RestController`가 분리될 수 있습니다.

- **Business Layer (Service):** 핵심 비즈니스 로직을 처리합니다.
  - `@Transactional`을 통해 트랜잭션을 관리하며, DTO(Data Transfer Object)를 사용해 각 계층 간 데이터를 전달합니다.
  - `BoardService`, `UserService`, `ReplyService` 등이 해당됩니다.

- **Data Access Layer (Repository):** 데이터베이스와의 통신을 담당합니다.
  - Spring Data JPA를 사용하여 데이터 영속성을 처리합니다.
  - `BoardRepository`, `UserRepository`, `ReplyRepository` 등이 해당됩니다.

- **Domain Model (Entity):** 데이터베이스 테이블과 매핑되는 핵심 객체입니다.
  - `Board`, `User`, `Reply`가 주요 도메인 객체입니다.

## 3. 주요 기술 스택

- **언어:** Java 21
- **프레임워크:** Spring Boot 3.4.3
- **데이터베이스:**
  - H2 (메모리 기반 데이터베이스)
  - Spring Data JPA (ORM)
- **템플릿 엔진:** Mustache (서버 사이드 렌더링용)
- **라이브러리:**
  - Lombok (코드 다이어트)
  - JUnit 5 (테스트)
- **빌드 도구:** Gradle

## 4. 도메인 모델

- **User:** 사용자 정보를 관리합니다. (ID, 이름, 이메일 등)
- **Board:** 게시글 정보를 관리합니다. (제목, 내용, 작성자 등)
- **Reply:** 댓글 정보를 관리합니다. (내용, 작성자, 연관된 게시글 등)

## 5. 핵심 기능

- 사용자 회원가입 및 로그인
- 게시글 목록 조회
- 게시글 상세 조회
- 게시글 작성, 수정, 삭제
- 댓글 작성 및 삭제
