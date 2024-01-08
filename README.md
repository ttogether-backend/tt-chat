<br />
<p align="center">
<img src="https://drive.google.com/uc?id=1q-O4nCCoD7YdNcBNcE4fzXAMkcCfG3W1" width=500 /> <br />
<b>투게더 트래블</b>은 음식, 여행, 취미 생활을 같이 즐길 동행인을 구하는 웹 서비스입니다.<br />
</p>

<br />

## TT-CHAT

✔️ [투게더트래블 서비스](https://github.com/ttogether-backend)의 Chat API 레파지토리입니다.<br />
✔️ 1:1 채팅 신청, 단체 채팅방 생성, 실시간 채팅 메세지 전송, 채팅 입퇴장 등 채팅과 연관된 서비스를 담당하고 있습니다.<br />
✔️ DDD, 헥사고날 아키텍처를 따릅니다.<br />
✔️ 구글 코드 컨벤션을 따릅니다.<br />

<br />

## 기술 스택
<br />
<p align="center">
<img src="https://img.shields.io/badge/spring boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> 
<img src="https://img.shields.io/badge/websocket-010101?style=for-the-badge">
<img src="https://img.shields.io/badge/JPA-59666C?style=for-the-badge&logo=hibernate&logoColor=white"> <img src="https://img.shields.io/badge/Query DSL-59666C?style=for-the-badge&logo=hibernate&logoColor=white">
<img src="https://img.shields.io/badge/postgresql-4169E1?style=for-the-badge&logo=postgresql&logoColor=white">
<img src="https://img.shields.io/badge/mongodb-47A248?style=for-the-badge&logo=mongodb&logoColor=white">
<img src="https://img.shields.io/badge/kafka-231F20?style=for-the-badge&logo=apachekafka&logoColor=white">
<img src="https://img.shields.io/badge/swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black"> 
<img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"> 
<img src="https://img.shields.io/badge/maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white">

</p>
<br />

## ERD 설계
![erd_table](/resources/erd.png)
- `PostgreSQL`에는 회원, 동행, 채팅과 관련된 정보를 위한 테이블을 생성합니다.
- `MongoDB`에는 각 채팅방에 전송된 메세지를 저장합니다.


## Api 명세서
`TT-CHAT`은 `Swagger`를 사용해 API 명세서를 문서화합니다.

- 스웨거 접속 주소 : http://localhost:9002/swagger-ui/index.html#/
<br />
<br />
