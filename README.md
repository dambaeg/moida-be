# moida-be
모이다 프로젝트 백엔드 서비스 저장소

---
# 로컬 개발환경 세팅

---
## mysql 계정 생성 및 db 추가
```
CREATE USER 'moida'@'localhost' IDENTIFIED BY 'password';
GRANT ALL ON *.* TO 'moida'@'localhost';

CREATE DATABASE moida DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
```

---
## 로컬 서버 시작시 config location 설정
* IntelliJ -> Run -> Edit Configurations...
* moida.ApplicationKt 추가 또는 선택 후 VM Options에 다음 설정 추가

```
-Dspring.config.location=classpath:/config/
```