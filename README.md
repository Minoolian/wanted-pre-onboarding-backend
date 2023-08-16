## 지원자의 성명
이민우

## 애플리케이션의 실행 방법 (엔드포인트 호출 방법 포함)
```bash
./gradlew build
java -jar build/libs/preonboarding-0.0.1-SNAPSHOT.jar 
```

Endpoint : http://localhost:8080

## 데이터베이스 테이블 구조
![image](https://github.com/Minoolian/wanted-pre-onboarding-backend/assets/44282342/ff38cc9c-bb80-4042-abfa-3f292eb0cb2b)

## 구현한 API의 동작을 촬영한 데모 영상 링크
https://drive.google.com/file/d/1DpvJoiDzZq57E2x9uoGfCq_dp8scKARs/view?usp=drive_link

## 구현 방법 및 이유에 대한 간략한 설명
1. Board 게시판의 생성, 수정, 삭제는 **로그인 후 JWT 토큰**을 Header에 "Bearer jwtToken"으로 API를 호출해야만 정상 동작하도록 하였습니다. (수정 및 삭제는 작성자 본인에게만 권한이 있기 때문)
2. Board의 조회는 어떤 사용자에 **관계없이** 조회가 가능하도록 구성하였습니다.
3. User-Board Table의 **연관관계를 OneToMany**로 구성하였습니다. 
4. 인증과 인가는 **Spring Security**를 이용하여 직접 구현보다 용이하게 구성하였고, 그 위에 Security Filter를 **JWT를 이용한 방식으로 Custom**하여 심화 구성하였습니다.

## API 명세(request/response 포함)
https://documenter.getpostman.com/view/25040897/2s9Y5R15q4

