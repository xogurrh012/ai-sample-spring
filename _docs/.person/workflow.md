# 아이디(username) 중복 체크 기능 개발 워크플로우

## 1. 목표
- 회원가입 시, 사용자가 입력한 아이디(username)가 이미 데이터베이스에 존재하는지 실시간으로 확인하는 API를 개발합니다.

## 2. API 설계
- **URL**: `/api/users/check-username`
- **Method**: `POST`
- **Request Body**:
    ```json
    {
        "username": "사용자가 입력한 아이디"
    }
    ```
- **Response Body (성공 시)**:
    - 아이디 사용 가능:
        ```json
        {
            "status": 200,
            "msg": "성공",
            "body": {
                "available": true
            }
        }
        ```
    - 아이디 중복 (사용 불가):
        ```json
        {
            "status": 200,
            "msg": "성공",
            "body": {
                "available": false
            }
        }
        ```

---

## 3. 개발 순서

### Step 1: Repository - `findByUsername` 메서드 추가
- **파일**: `src/main/java/com/example/demo/user/UserRepository.java`
- **내용**: `username`으로 `User`를 조회하는 메서드를 추가합니다. JPA Query Method 기능을 활용합니다.

```java
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
```

### Step 2: Request/Response DTO 추가
- **파일**: `src/main/java/com/example/demo/user/UserRequest.java`
- **내용**: 중복 체크 요청을 받을 `CheckUsernameDTO` 내부 클래스를 추가합니다.

```java
public class UserRequest {
    // ... 기존 코드 ...

    @Data
    public static class CheckUsernameDTO {
        private String username;
    }
}
```

- **파일**: `src/main/java/com/example/demo/user/UserResponse.java`
- **내용**: 중복 체크 결과를 응답할 `CheckUsernameDTO` 내부 클래스를 추가합니다.

```java
public class UserResponse {
    // ... 기존 코드 ...

    @Data
    public static class CheckUsernameDTO {
        private Boolean available;

        public CheckUsernameDTO(Boolean available) {
            this.available = available;
        }
    }
}
```

### Step 3: Service - 중복 확인 로직 추가
- **파일**: `src/main/java/com/example/demo/user/UserService.java`
- **내용**: `username`을 받아 `UserRepository`를 통해 중복 여부를 확인하고, 결과를 `ResponseDTO`로 반환하는 메서드를 작성합니다.

```java
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    // ... 기존 코드 ...

    public UserResponse.CheckUsernameDTO checkUsername(UserRequest.CheckUsernameDTO reqDTO) {
        Optional<User> userOptional = userRepository.findByUsername(reqDTO.getUsername());
        
        if (userOptional.isPresent()) {
            return new UserResponse.CheckUsernameDTO(false); // 이미 존재하므로 사용 불가
        } else {
            return new UserResponse.CheckUsernameDTO(true); // 사용 가능
        }
    }
}
```

### Step 4: Controller - API 엔드포인트 추가
- **파일**: `src/main/java/com/example/demo/user/UserController.java`
- **내용**: API 요청을 받아 `Service`에 처리를 위임하고, 결과를 `Resp` 객체로 감싸 반환하는 `@RestController`를 생성하거나 기존 컨트롤러에 메서드를 추가합니다.
- **규칙**: "Data를 응답할때는 @RestController를 따로 만들고, 주소는 /api를 앞에 붙인다." 규칙에 따라 `UserApiController`를 새로 만드는 것을 권장합니다.

**새로운 `UserApiController.java` 생성 예시**
```java
@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/users/check-username")
    public Resp<?> checkUsername(@RequestBody UserRequest.CheckUsernameDTO reqDTO) {
        UserResponse.CheckUsernameDTO respDTO = userService.checkUsername(reqDTO);
        return Resp.ok(respDTO);
    }
}
```

### Step 5: 프론트엔드 연동 (가이드)
- 회원가입 폼의 `username` 입력 필드에 `onkeyup` 또는 `onblur` 이벤트를 추가합니다.
- JavaScript `fetch` API를 사용해 `/api/users/check-username`으로 `POST` 요청을 보냅니다.
- 응답 결과(`available`)에 따라 사용자에게 메시지(예: "사용 가능한 아이디입니다.", "이미 사용 중인 아이디입니다.")를 표시합니다.
