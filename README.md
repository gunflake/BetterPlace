# Better Place

## Coworker
- 김원겸
- 김형수
- 남혁민
- 노하람
- 황현지

## Version
- 코드 소스 : 1.0  
- JDK     : 12

## 사용 스택 
- gradle
- Spring boot
- MYSQL
- AWS EC2, RDS
- THYMELEAF
- GIT(GitHub)
- SourceTree

## 작업관리 프로그램
- Trello

## AWS 서버 접속 방법

**AWS 접속하기 위해서는 AWS 계정에서 자신의 IP를 변경 후 접속해야 한다.**

1. Mac OS 실행 방법  
    A. betterplace.pem 파일을 ssh관리 폴더(~/.ssh/)에 복사 붙여넣기한다.  
    B. config 파일에 Host, HostName, User, pem파일 등록  
    C. ssh 등록한Host이름으로 실행 (ssh betterplace)  

2. Windows OS 실행 방법
    A. putty 설치 (혹은 비슷한 프로그램 설치)  
    B. betterplace.pem 파일을 다운 받은 후 PuTTYgen 프로그램 실행 후 pem 파일을 .ppk 파일로 변환  
    C. Putty 실행 후 Auth에 변환한 ppk 파일 등록 후 user@public IP 주소로 실행한다.  
    
## SpringBoot 실행 방법

1. 프로젝트 root 폴더에서 ./buildew clean build 실행  
2. 생성된 build/libs/ 안에 있는 생성된 jar 파일을 실행 (java -jar 생성된jar파일 옵션)  
    1. Default = Local Properties  
    2. --spring.profiles.active=aws 옵션을 주면 application-aws.properties 파일을 읽는다.  

