### 검증 및 구현 방식

- interceptor
    - 특정 API 호출 시 사용자 권한 확인을 위해 `interceptor` 활용
- 로그인 사용자
    - 로그인한 사용자의 정보를 담은 Dto 클래스를 통해 API 호출시 이를 파라미터로 받아 정보를 확인할 수 있음
    - 이를 통해 유저 정보 및 권한 관리가 용이
- ENUM
  - Error_type 과 Account_type을 ENUM을 활용하여 코드의 가독성을 높임
