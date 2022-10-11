# 🥇FLYAWAY SERVER
> 개발기간 : 2022-09-11 ~ 2022-10-11

## Develop
```
Gradle Project
Java version : Open JDK11
Spring boot 2.7.3
h2 database
MySQL
```
## 실행방법
```
./gradlew build

java -jar /server/fly-away/build/libs/fly-away-0.0.1-SNAPSHOT.jar
```
<details>
<summary>실행하기 위한 yml파일</summary>
<div markdown="1">
  <pre> spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://[ RDS ENDPOINT ADDRESS]:[ PORT NUMBER ]/[ DATABASE NAME]
      username: { username }
      password: { password }
  jpa:
    database: mysql
    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect
    hibernate:
      ddl-auto: create
    show-sql: true
    properties:
      hibernate:
        format-sql: update
    open-in-view: false
  mvc:
    pathmatch:
      matching-strategy: ant_path_matcher
cloud:
  aws:
    credentials:
      accessKey: { AWS S3 ACCESSKEY }
      secretKey: { AWS S3 SECRETKEY }
    s3:
      bucket: { AWS S3 BUCKET NAME }
      defaultBoard: { AWS S3 STATIC DEFAULT BOARD IMAGE URL }
      default: { AWS S3 STATIC DEFAULT MEMBER IMAGE URL }
    region:
      static: { AWS REGION }
    stack:
      auto: false
config:
  domain: { AWS S3 DOMAIN } </pre>
  </div>
</details>
  
<details>
<summary>프로젝트 패키지 구조</summary>
<div markdown="1">
  <img src="https://user-images.githubusercontent.com/104135990/194977628-9ba3c8fc-da4b-4446-a70d-ce7fcff7d0e8.PNG">
  </div>
</details>

## ERD

<img src="https://user-images.githubusercontent.com/104135990/194973694-4c4404ac-9c97-40fe-a642-4a38da634fa2.PNG" width="600" height="450"/>

## API DOCS

[Swagger](https://server.main024.shop/swagger-ui.html)
```
서버 실행 이후 localhost:8080/swagger-ui.html
```

## 기술스택 및 배포구조
<img src="https://user-images.githubusercontent.com/104135990/194977290-08ef627c-a5db-4a74-afca-77ad2e3aeb86.png" width="600" height="450"/>


