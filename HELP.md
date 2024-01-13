# Summary
Backend: Java21 + Spring boot3 
* http://localhost:10001
* http://localhost:10001/swagger-ui.html
* http://localhost:10001/v3/api-docs

Frontend: Vue + Typescript
* http://localhost:5174/alienClock


# Restful Server
Please make sure you have installed the JDK 21 and setup the $JAVA_HOME for JDK21
### Run in IDE
```
main class: com.n2n.api.ApiApplication
```

### Run with java command
```shell
# 1. build project
cd n2n-interview
./gradlew clean build
# 2. run server 
java -jar ./api/build/libs/*.jar -D
```

### Run with Docker
##### 1. build project
```shell
# create mounted folders for Linux/Macos
mkdir -p ~/n2n/app-log ~/n2n/gc-log
cd n2n-interview
./gradlew clean build bootJar
```
##### 2. build docker
```shell
cd api
docker build -t n2n/gavin:latest -f Dockerfile .
cd ..
```
##### 4. run docker by docker or docker-compose
Option 1, Run by docker
```shell
docker run -it --rm -d -p 10001:10001 -p 11619:11619 \
-v ~/n2n/app-log:/n2n/app-log \
-v ~/n2n/gc-log:/n2n/gc-log \
n2n/gavin
```
Option 2, Run by docker-compose
```shell
docker-compose up
```

### Validate the server
* http://localhost:10001

### Swagger UI
Swagger UI and api documents 
* http://localhost:10001/swagger-ui.html
* http://localhost:10001/v3/api-docs

# Vue Client
Please make sure you have installed the latest pnpm at your local.
```shell
cd n2n-interview
cd frontend
# pnpm debug or pnpm dev
pnpm dev
```
Access the website based on the message:
* http://localhost:5174/alienClock

Follow the instructions in the opened web page