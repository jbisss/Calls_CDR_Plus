# Базовый image
FROM openjdk:17-alpine3.14

# Переменная, в которой указывается путь к jar- архиву
ARG JAR_FILE=target/*.jar

#Задаётся рабочая директория, в которой будут выполняться дальнейшие команды (перемещаемся в папку app)
WORKDIR /opt/app

#Jar-файл с локального хоста (путь до него задан в переменной JAR_FILE) копируется в папку app, копии задаётся имя app.jar
COPY ${JAR_FILE} app.jar

COPY /src/main/resources /opt/app/resources

# Команда запуска приложения
ENTRYPOINT ["java","-jar","app.jar"]