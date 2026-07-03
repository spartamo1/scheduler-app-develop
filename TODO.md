
### Lv 0. API 명세 및 ERD 작성   `필수`

- [ ]  **API 명세서 작성하기**
    - [ ]  API명세서는 프로젝트 root(최상위) 경로의 `README.md` 에 작성
    - 참고) API 명세서 작성 가이드
        - API 명세서란 API명, 요청 값(파라미터), 반환 값, 인증/인가 방식, 데이터 및 전달 형식 등 API를 정확하게 호출하고 그 결과를 명확하게 해석하는데 필요한 정보들을 일관된 형식으로 기술한 것을 의미합니다.
        - request 및 response는 json(링크) 형태로 작성합니다.

      [예) [서점] 책 API 설계하기](https://app.notion.com/e612dc3ef51483c29c3781125bebb679?pvs=21)

        - 예시

          !image.png


        > API 명세서 추천 무료 Tool
        > 
        > 
        > Document your APIs in Postman | Postman Learning Center
        > 


### Lv 1. 일정 CRUD  `필수`

- [ ]  일정을 생성, 전체 조회, 단건 조회, 수정, 삭제할 수 있습니다.
- [ ]  일정은 아래 필드를 가집니다.
    - [ ]  `작성 유저명`, `할일 제목`, `할일 내용`, `작성일`, `수정일` 필드
    - [ ]  `작성일`, `수정일` 필드는 `JPA Auditing`을 활용합니다.


### Lv 2. 유저 CRUD  `필수`

- [ ]  유저를 생성, 전체 조회, 단건 조회, 수정, 삭제할 수 있습니다.
- [ ]  유저는 아래와 같은 필드를 가집니다.
    - [ ]  `유저명`, `이메일`, `작성일`, `수정일` 필드
    - [ ]  `작성일`, `수정일` 필드는 `JPA Auditing`을 활용합니다.
- [ ]  연관관계 구현
    - [ ]  일정은 이제 `작성 유저명` 필드 대신 `유저 고유 식별자` 필드를 가집니다.


### Lv 3. 회원가입  `필수`

- [ ]  유저에 `비밀번호` 필드를 추가합니다.
    - 비밀번호는 8글자 이상이어야합니다.
    - 비밀번호 암호화는 도전 기능에서 수행합니다.


### Lv 4. 로그인(인증)  `필수`

- [ ]  **설명**
    - [ ]  **Cookie/Session**을 활용해 로그인 기능을 구현합니다.
- [ ]  **조건**
    - [ ]  `이메일`과 `비밀번호`를 활용해 로그인 기능을 구현합니다.
    - [ ]  필요한 API들에서 세션을 활용합니다.

---

## 3️⃣ 도전 기능 가이드

### Lv 5. 다양한 예외처리  `도전`

- [ ]  Validation을 활용해 다양한 예외처리를 적용합니다.
    - [ ]  @RestControllerAdvice를 활용하여 validation 에러 상황을 클라이언트에게 전달합니다.
- [ ]  정해진 예외처리 항목이 있는 것이 아닌 프로젝트를 분석하고 예외사항을 지정해 봅니다.
    - [ ]  Ex) 할일 제목은 10글자 이내, 유저명은 4글자 이내

### Lv 6. 비밀번호 암호화  `도전`

- [ ]  Lv.3에서 추가한 `비밀번호` 필드에 들어가는 비밀번호를 암호화합니다.
    - [ ]  암호화를 위한 `PasswordEncoder`를 직접 만들어 사용합니다.
        - PasswordEncoder 참고 코드
            1. `build.gradle` 에 아래의 의존성을 추가해주세요.

                ```java
                implementation 'at.favre.lib:bcrypt:0.10.2'
                ```

            2. `config` 패키지가 없다면 추가하고, 아래의 클래스를 추가해주세요.

                ```java
                import at.favre.lib.crypto.bcrypt.BCrypt;
                import org.springframework.stereotype.Component;
                
                @Component
                public class PasswordEncoder {
                
                    public String encode(String rawPassword) {
                        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
                    }
                
                    public boolean matches(String rawPassword, String encodedPassword) {
                        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
                        return result.verified;
                    }
                }
                ```



### Lv 7. 댓글 CRUD  `도전`

- [ ]  생성한 일정에 댓글을 남길 수 있습니다.
    - [ ]  댓글과 일정은 연관 관계를 가집니다. →  `3주차 연관관계 매핑 참고!`
- [ ]  댓글을 저장, 전체 조회할 수 있습니다.
- [ ]  댓글은 아래와 같은 필드를 가집니다.
    - [ ]  `댓글 내용`, `작성일`, `수정일`, `유저 고유 식별자`, `일정 고유 식별자` 필드
    - [ ]  `작성일`, `수정일` 필드는 `JPA Auditing`을 활용하여 적용합니다.


### Lv 8. 일정 페이징 조회  `도전`

- 키워드
    - **데이터베이스**
        - offset / limit : SELECT 쿼리에 적용해서 데이터를 제한 범위에 맞게 조회할 수 있습니다.
    - **페이징**
        - Pageable : Spring Data JPA에서 제공되는 페이징 관련 인터페이스입니다.
        - PageRequest : Spring Data JPA에서 제공되는 페이지 요청 관련 클래스입니다.
- [ ]  일정을 Spring Data JPA의 `Pageable`과 `Page` 인터페이스를 활용하여 페이지네이션을 구현
    - [ ]  `페이지 번호`와 `페이지 크기`를 쿼리 파라미터로 전달하여 요청하는 항목을 나타냅니다.
    - [ ]  `할일 제목`, `할일 내용`, `댓글 개수`, `일정 작성일`, `일정 수정일`, `일정 작성 유저명` 필드를 조회합니다.
    - [ ]  디폴트 `페이지 크기`는 10으로 적용합니다.
- [ ]  일정의 `수정일`을 기준으로 내림차순 정렬합니다.