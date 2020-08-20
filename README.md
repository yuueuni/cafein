[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2Fzzsza%2Fkyle-school)](https://hits.seeyoufarm.com)
[![SamSung Badge](https://img.shields.io/github/languages/count/wlgh325/S03P12A203)]

<div align=center>

# Cafe SNS Project
## [Cafe人](https://i3a203.p.ssafy.io)

</div>

# 프로젝트 소개
- ==사용자의 성향== 에 맞는 다양한 카페들을 추천 해주는 서비스 Cafe 人
- ==맞춤형 카페== 를 선정해 카페와 사람을 잇다
- 카페인처럼 한 번 빠지면 헤어나오지 못하는 ==중독성==이 강한 서비스

---

## 주요 기능
### 카페 정보 제공
- 서울 주요 카페 정보 제공
- 카페 리뷰 기반 키워드 추출
- 키워드 기반 5가지 유형 카페 분류
- 리뷰가 추가 됨에 따라 계속해서 갱신되는 키워드!

### 카페 추천
- 설문 진행 후, 결과 유형별 맞춤형 카페 추천
- 좋아요(LIKE), 발도장(STAMP) 카페 기반 협업 필터링 추천
- 최다 및 최신 좋아요, 발도장 카페 추천

---

# 사용 언어 및 기술 스택
## 사용언어 & 프레임 워크 & API
### FrontEnd
![JavaScript Badge](https://img.shields.io/badge/-JavaScript-yellow?style=flat-square&logo=JavaScript)
![Vuetify Badge](https://img.shields.io/badge/-Vuetify-black?style=flat-square&logo=Vuetify)
![Vue.js Badge](https://img.shields.io/badge/-Vue.js-green?style=flat-square&logo=Vue.js)
![NGINX Badge](https://img.shields.io/badge/-NGINX-green?style=flat-square&logo=NGINX)
![Font Awesome Badge](https://img.shields.io/badge/-Vue.js-green?style=flat-square&logo=FontAwesome)
vuecookies, infinitscroll

### BackEnd
![Java Badge](https://img.shields.io/badge/-Java-orange?style=flat-square&logo=Java)
![Spring Badge](https://img.shields.io/badge/-SpringBoot3.0.6-brightgreen?style=flat-square&logo=Spring)]
![Docker Badge](https://img.shields.io/badge/-Docker-blue?style=flat-square&logo=Docker)
![Jenkins Badge](https://img.shields.io/badge/-Jenkins-red?style=flat-square&logo=Jenkins)
![Mysql Badge](https://img.shields.io/badge/-Mysql-informational?style=flat-square&logo=Mysql)
![JSON Web Tokens Badge](https://img.shields.io/badge/-SpringBoot-black?style=flat-square&logo=JsonWebTokens)
mybatis

### Data
![Python Badge](https://img.shields.io/badge/-Python-9cf?style=flat-square&logo=Python)
konlpy, selenium, openpyxl, networkx, hanspell

## API 및 라이브러리

---

# Install
## BackEnd(Spring)
Jenkins를 이용하여 push 할때 마다 자동으로 빌드
Jenkins, BackEnd Spring 서버 모두 Docker로 작성

<detail>
<summary>1. Dockerfile 작성</summary>
<div markdown="1">

```Docker
# Start with a base image containing Java runtime
FROM java:8

# Add Author info
LABEL maintainer="xxx@gmail.com"

# Add a volume to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/spring_back_team-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} cafesns-springboot.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/cafesns-springboot.jar"]
```

</detail>

<detail>
<summary>2. SHELL SCRIPT 작성 및 실행</summary>
<div markdown="1">

```Bash
#!/bin/bash

docker stop spring
docker rm spring
docker rmi cafesns-springboot
docker build -t cafesns-springboot . && docker run --name spring -d -v /home/ubuntu/data/thumb_img:/home/data/thumb_img -v /var/www/i3a203.p.ssafy.io:/var/www/i3a203.p.ssafy.io -v /home/ubuntu/data/images:/home/data/images -v /home/ubuntu/env:/home/env -p 5000:8080 cafesns-springboot
```
</detail>

## Mysql Server 설치
Mysql은 설정할게 많아서 docker-compose를 이용해서 작성해보았다
<detail>
<summary>1. Docker-compose 작성</summary성

```Docker
# docker-compose.yml
version: "3" # 파일 규격 버전
services: # 이 항목 밑에 실행하려는 컨테이너 들을 정의
    db:
      image: mysql:8.0.17 # 사용할 이미지
      container_name: db # 컨테이너 이름 설정
      ports:
              - "3306:3306" # 접근 포트 설정 (외부:내부)
      environment: # -e 옵션
        MYSQL_ROOT_PASSWORD: "xxx" # MYSQL PW 설정
        MYSQL_DATABASE: "cafeproject"
      command: # 명령어 실행
        - --character-set-server=utf8mb4
        - --collation-server=utf8mb4_unicode_ci
      volumes:
        - /home/ubuntu/docker/data:/var/lib/mysql # -v 옵션
```

</detail>

2. 실행
docker로 서버를 올리고 외부에서 접속할 수 있도록 계정을 만들고 권한을 설정한다.
```
$ docker-compose up -d
$ docker exec -it db bash
$ mysql -u root -p

mysql> CREATE user 'jiho'@'%' IDENTIFIED BY 'xxxx';
mysql> GRANT ALL PRIVILEGES ON cafeproject.* To 'jiho'@'%';
mysql> flush privileges;
```

## FrontEnd(Vue.js)
Vue도 Docker를 이용하여 생성한다

1. Dockerfile 작성

<detail>
<summary>Dockerfile</summary>

```Docker
# build stage
FROM node:lts-alpine as build-stage
WORKDIR /app
COPY package*.json ./
RUN yarn install
COPY . .

#RUN npm install @vue/cli-service
#build stage
RUN yarn build

# production stage
FROM nginx:stable-alpine as production-stage
COPY --from=build-stage /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
```

</detail>

3. 자동 빌드를 위한 Shell Script 작성
```Bash
#!/bin/bash
app_name='vuejs-app'
image_name='cafesns-vuejs'
docker stop ${app_name}
docker rm ${app_name}
docker rmi ${image_name}

repository_name='s03p12a203'

if [ -d ${repository_name} ]; then
        rm -rf ${repository_name}
fi

git clone -b frontend --single-branch https://lab.ssafy.com/s03-webmobile2-sub2/${repository_name}.git && cd ${repository_name}/vue_front/cafesns
docker build -t ${image_name} . && docker run -d -it -p 80:80 -v /home/ubuntu/data/thumb_img:/home/data/thumb_img -v /home/ubuntu/docker/nginx/conf:/etc/nginx/conf.d --name ${app_name} ${image_name}
```

# Cafe人 서비스
1. 