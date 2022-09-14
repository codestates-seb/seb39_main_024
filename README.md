# 프로젝트 설계 단계 첨삭 요청

### 프로젝트 주제 요약 : 영상 시청 시간을 기반으로 github의 contibution graph(잔디)와 같은 기능을 적용해 보려고 합니다.

[요구사항 정의서] ![image](https://user-images.githubusercontent.com/104135990/190066026-e28b5215-999e-480e-8150-a4cc244a2dd7.png)

[ERD 구조] ![erd](https://user-images.githubusercontent.com/104135990/190066261-7a1594cc-e8e3-42a9-a35f-08da9b963b31.PNG)


## 의문사항
1. 회원마다 일일 운동시간이 기록된 캘린더 테이블을 설계해야 하는데, 지금 설계한 대로 테이블이 만들어 진다면 날짜별로 회원별 운동기록을 저장하게 되어 컬럼의 개수가 많이 늘어나게 될 것 같습니다. 이것이 맞는지 아니면 더 나은 방법이 있는지 궁금합니다.

2. 전체적인 ERD 설계에 대한 의견이 궁금합니다.

***
## Develop
- Gradle Project
- Java version : Open JDK11
- Spring boot 2.7.3
- h2 database
- MySQL

## API문서 제공 방식
- Swagger-ui를 이용해 FE에 최대한 빠르게 제공
- 이후 Spring rest docs를 도입 예정

## 개발서버
- local-PC를 이용해 8080포트와 3000번포트를 열고 개발 진행

## 일정관리 및 소통
- Github Project 탭의 칸반보드를 이용하여 일정관리
- Discord, Microsoft Teams, Google Meet를 통해 소통
