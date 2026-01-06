# Lesson 1. Введение в Java

Автор: Кивилев Денис (kivilev.d@gmail.com)  
Школа: backend-pro.ru  

## Структура проекта

my-project/  
├── src/ # Исходный код  
│    ├── main/  
│    │ ├── java/ # Java-классы  
│    │ │ └── ru/backendpro/   #Имя пакета  
│    │ │   └── Main.java  
│    │ └── resources/ # Конфигурационные файлы, properties  
│    └── test/ # Тесты  
│      └── java/  
│        └── ru/backendpro/  
│          └── MainTest.java  
├── target/ # Скомпилированные файлы (генерируется автоматически)  
│    └── classes/  
│      └── ru/backendpro/  
│        └── Main.class  
└── pom.xml # Конфигурация Maven (зависимости, плагины)  

## POM.xml - очень упрощенное содержимое файла
```
<project>
    <modelVersion>4.0.0</modelVersion>
    <groupId>ru.backendpro</groupId>
    <artifactId>my-project</artifactId>
    <version>1.0.0</version>
    
    <dependencies>
    </dependencies>
</project>
```

## Команды Maven
```
mvn clean # Очистить target/  
mvn compile # Скомпилировать код  
mvn test # Запустить тесты  
mvn package # Создать JAR/WAR  
mvn install # Установить в локальный репозиторий  
mvn spring-boot:run # Запустить Spring Boot приложение  
```
```
java -cp payment-processing-system-1.0.0.jar ru.backendpro.MyApp  
```

