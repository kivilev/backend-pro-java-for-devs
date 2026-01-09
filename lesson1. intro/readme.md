# Lesson 1. Введение в Java

Автор: Кивилев Денис (kivilev.d@gmail.com)  
Школа: backend-pro.ru  

### Оглавление
* [Материалы к уроку](#материалы-к-уроку)
* [LIVE CODING БЛОК](#livecode1)
* [СКВОЗНАЯ РАБОТА № 1](#homework)


## Материалы к уроку

### Полезные ссылки
История Java-версий - https://en.wikipedia.org/wiki/Java_version_history  
JDK Liberica - https://bell-sw.com/pages/downloads/#jdk-21-lts  
IDEA Community + Ultimate в одном дистрибутиве - https://www.jetbrains.com/ru-ru/idea/download/  
Download Maven - https://maven.apache.org/download.cgi  
Официальная документация по Java21 - https://docs.oracle.com/en/java/javase/21/
Google Java Style Guide - https://google.github.io/styleguide/javaguide.html

### Структура проекта
```
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
```
### POM.xml - очень упрощенное содержимое файла
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

### Команды Maven
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

### Добавление плагина checkstyle по проверке кода
```
<plugin>
		<groupId>org.apache.maven.plugins</groupId>
		<artifactId>maven-checkstyle-plugin</artifactId>
		<version>${maven-checkstyle-plugin.version}</version>
		<configuration>
				<configLocation>checkstyle.xml</configLocation>
				<consoleOutput>true</consoleOutput>
				<failsOnError>true</failsOnError>
		</configuration>
		<executions>
				<execution>
						<phase>verify</phase>
						<goals>
								<goal>check</goal>
						</goals>
				</execution>
		</executions>
</plugin>
```
Так же нужно в корень проекта добавить файл: checkstyle.xml (находится в папке checkstyle-rules)


## <a name="livecode1"></a>💻 LIVE CODING БЛОК с преподавателем

### Часть 1: Установка и настройка окружения (15 минут)

#### Шаг 1: Установка JDK (5 минут)
**Действия преподавателя:**
1. Открыть https://bell-sw.com/pages/downloads/
2. Выбрать **Liberica JDK 21 LTS**
3. Выбрать операционную систему (Windows/macOS/Linux)
4. Скачать **Full JDK** (не JRE)
5. Установить с настройками по умолчанию
6. Прописать `JAVA_HOME` в переменных окружения. Например: `C:\Users\user1\.jdks\liberica-full-17.0.12`

**Проверка установки:**
```bash
# Открыть терминал (cmd/PowerShell/Terminal)
java -version
# Ожидаемый вывод:
# openjdk version "21.0.1" 2023-10-17 LTS
# OpenJDK Runtime Environment (build 21.0.1+12-LTS)
# OpenJDK 64-Bit Server VM (build 21.0.1+12-LTS, mixed mode, sharing)

javac -version
# Ожидаемый вывод:
# javac 21.0.1
```

**Что делает студент параллельно:**
- Устанавливает JDK на свой компьютер
- Проверяет версию через терминал

---

#### Шаг 2: Установка Maven (5 минут)
Maven нужен для сборки проекта и запуска команд `mvn clean compile`, `mvn test`, `mvn package`.  

**Действия преподавателя:**
1. [Скачать Maven](https://maven.apache.org/download.cgi) (binary zip) с официального сайта Apache.
2. Распаковать, например:
   - Windows: `C:\tools\apache-maven-3.x.x\`
   - macOS/Linux: `~/tools/apache-maven-3.x.x/` или `/opt/apache-maven-3.x.x/`
3. Добавить переменные окружения:
   - `MAVEN_HOME` → путь к Maven
   - `PATH` → добавить `%MAVEN_HOME%\bin` (Windows) или `$MAVEN_HOME/bin` (macOS/Linux)

**Проверка установки:**
```bash
mvn -version
# Ожидаемо: Apache Maven 3.x.x + информация о Java 21
```

**Что делает студент параллельно:**
- Устанавливает Maven (или убеждается, что Maven доступен через IntelliJ)
- Проверяет `mvn -version`

---

#### Шаг 3: Установка IntelliJ IDEA (5 минут)
**Действия преподавателя:**
1. Открыть https://www.jetbrains.com/idea/download/
2. Скачать и установить
4. Запустить IntelliJ IDEA
5. Пропустить импорт настроек (первый запуск)
6. Выбрать тему (Light/Dark)

**Что делает студент параллельно:**
- Устанавливает IntelliJ IDEA Community Edition
- Запускает IDE

---

#### Шаг 4: Настройка IntelliJ IDEA (5 минут)
**Действия преподавателя:**
1. **Настройка JDK:**
   - File → Project Structure → SDKs
   - Нажать "+" → Add JDK
   - Выбрать путь к установленному JDK 21
   - Нажать OK

2. **Настройка Maven:**
   - File → Settings → Build, Execution, Deployment → Build Tools → Maven
   - Проверить, что Maven bundled (встроенный) или выбран установленный Maven (если ставили Вариант B)

3. **Настройка кодировки:**
   - File → Settings → Editor → File Encodings
   - Установить UTF-8 для всех полей

**Что делает студент параллельно:**
- Повторяет настройки в своей IDE

---

### Часть 2: Создание первого Maven-проекта (10 минут)

#### Шаг 5: Создание проекта через IntelliJ IDEA
**Действия преподавателя:**

1. **Создать новый проект:**
   - File → New → Project
   - Выбрать **Java**
   - Name: `hello-world-app`
   - Location: выбрать папку
	 - Build system: Maven
   - JDK: выбрать JDK 21 (можно добавить каталог с уже установленной JDK21 или добавить в IDEA - Download JDK)
   - В Advanced Setting указать имя пакета (`ru.backendpro`) и артефакта (`hello-world-app`)
   - Нажать Create

2. **Структура созданного проекта:**
```
hello-world-app/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── ru/backendpro/
│   │           └── Main.java
│   └── test/
│        └── java/
└── pom.xml
```

3. **Объяснить структуру:**
   - `src/main/java/` — основной код приложения
   - `src/test/java/` — тесты
   - `pom.xml` — конфигурация Maven

4. **Открыть `pom.xml` и объяснить:**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>ru.backendpro</groupId>
    <artifactId>payment-processing</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

</project>
```

---

#### Шаг 6: Использование Maven Central — подключаем JUnit5 и пишем первый тест
**Цель:** вручную через Maven Central найти `org.junit.jupiter » junit-jupiter`, взять **последнюю стабильную** версию (**не** `RC`, **не** `M`) и добавить в `pom.xml`. Затем создать простой тест и запустить его из IDE и через Maven.

**Действия преподавателя:**
1. Открыть Maven Central.
2. В поиске найти артефакт: **`org.junit.jupiter:junit-jupiter`**.
3. Выбрать **последнюю стабильную версию**:
   - НЕ брать версии с суффиксами `-RC...`, `-M...` (milestone).
4. Открыть `pom.xml` и добавить зависимость JUnit Jupiter:

```xml
<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>ПОСЛЕДНЯЯ_СТАБИЛЬНАЯ_ВЕРСИЯ</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

5. Создать папку тестов (если её нет):
   - `src/test/java`
6. Создать тестовый класс: `src/test/java/ru/backendpro/MainTest.java`:

```java
package ru.backendpro;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    @Test
    public void test() {
        assertTrue(true);
    }
}
```

7. Запуск теста из IntelliJ IDEA:
   - открыть `MainTest`
   - нажать зелёный треугольник слева от метода `test()` (или у класса)

8. Запуск тестов через Maven (в терминале IntelliJ / системном терминале):
```bash
mvn test
```

**Что делает студент параллельно:**
- Сам находит `org.junit.jupiter:junit-jupiter` на Maven Central
- Берёт **стабильную** последнюю версию (без `RC` и `M`)
- Добавляет зависимость в `pom.xml`
- Создаёт `MainTest`
- Запускает тест из IDE и командой `mvn test`

---

### Часть 3: Hello World программа (10 минут)

#### Шаг 7: Написание первой программы
**Действия преподавателя:**

1. **Удалить сгенерированный `Main.java`**

2. **Создать новый класс:**
   - Правый клик на `ru.backendpro` → New → Java Class
   - Имя: `HelloWorld`

3. **Написать код:**
```java
package ru.backendpro;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

4. **Объяснить каждую строку:**
```java
package ru.backendpro;  // Объявление пакета (аналог схемы в Oracle)

public class HelloWorld {  // Объявление класса (public = доступен извне)

    // main — точка входа в программу (как BEGIN в PL/SQL блоке)
    public static void main(String[] args) {
        // System.out.println — вывод в консоль (аналог DBMS_OUTPUT.PUT_LINE)
        System.out.println("Hello, World!");
    }
}
```

**Связь с опытом Oracle Developer:**
> 💡 **Аналогия с PL/SQL:**
> ```sql
> -- PL/SQL
> BEGIN
>     DBMS_OUTPUT.PUT_LINE('Hello, World!');
> END;
> 
> -- Java
> public static void main(String[] args) {
>     System.out.println("Hello, World!");
> }
> ```

5. **Запустить программу:**
   - Правый клик на `HelloWorld.java` → Run 'HelloWorld.main()'
   - Или нажать зеленый треугольник рядом с `main`

6. **Показать вывод в консоли:**
```
Hello, World!

Process finished with exit code 0
```

**Что делает студент параллельно:**
- Создает класс `HelloWorld` в своем проекте
- Пишет код
- Запускает программу
- Видит вывод в консоли

---

#### Шаг 8: Компиляция через Maven
**Действия преподавателя:**

1. **Открыть терминал в IntelliJ IDEA:**
   - View → Tool Windows → Terminal

2. **Скомпилировать проект:**
```bash
mvn clean compile
```

3. **Объяснить вывод:**
```
[INFO] Scanning for projects...
[INFO] Building hello-world-app 1.0-SNAPSHOT
[INFO] Compiling 1 source file to target/classes
[INFO] BUILD SUCCESS
```

4. **Показать созданные файлы:**
```
target/
└── classes/
    └── ru/backendpro/
        └── HelloWorld.class  ← Байткод
```

5. **Запустить через командную строку:**
```bash
java -cp target/classes ru.backendpro.HelloWorld
# Вывод: Hello, World!
```

**Объяснить:**
- `mvn clean compile` — компиляция через Maven
- `java -cp target/classes ru.backendpro.HelloWorld` — запуск через JVM
  - `-cp` — classpath (где искать классы)
  - `ru.backendpro.HelloWorld` — полное имя класса (пакет + имя)

**Что делает студент параллельно:**
- Компилирует свой проект через Maven
- Запускает программу через командную строку

---

### Часть 4: Настройка линтеров (5 минут)

#### Шаг 9: Добавление Checkstyle
**Действия преподавателя:**

1. **Открыть `pom.xml`**

2. **Добавить плагин Checkstyle:**
```xml
<build>
    <plugins>
        <!-- Checkstyle для проверки стиля кода -->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-checkstyle-plugin</artifactId>
            <version>3.6.0</version>
            <configuration>
                <configLocation>checkstyle.xml</configLocation>
                <consoleOutput>true</consoleOutput>
                <failsOnError>true</failsOnError>
            </configuration>
            <executions>
                <execution>
                    <phase>verify</phase>
                    <goals>
                        <goal>check</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

3. **Добавить файл `checkstyle.xml` в корень проекта**  
Можно взять из `\lesson1\checkstyle-rules\`

4. **Запустить проверку:**
```bash
mvn checkstyle:check
```

5. **Объяснить вывод:**
```
[INFO] Starting audit...
[WARN] HelloWorld.java:1: Missing package-info.java file.
[INFO] Audit done.
```

6. **Исправить предупреждения (если есть)**

**Что делает студент параллельно:**
- Добавляет Checkstyle plugin в свой `pom.xml`
- Размещает `checkstyle.xml` в корне проекта
- Запускает проверку
- Исправляет предупреждения

---

### Часть 5: Демонстрация открытого кода Java (5 минут)

#### Шаг 10: Просмотр исходного кода стандартных классов
**Действия преподавателя:**

1. **Открыть класс `String`:**
   - В коде написать `String`
   - Ctrl+Click (Cmd+Click на macOS) на `String`
   - Откроется исходный код класса `String`

2. **Показать метод `isBlank()`:**
```java
public boolean isBlank() {
    return indexOfNonWhitespace() == length();
}
```

3. **Показать метод `isEmpty()`:**
```java
public boolean isEmpty() {
    return value.length == 0;
}
```

4. **Объяснить разницу:**
   - `isEmpty()` — проверяет, что строка имеет длину 0
   - `isBlank()` — проверяет, что строка пустая или содержит только пробелы

5. **Написать пример:**
```java
public class StringExample {
    public static void main(String[] args) {
        String empty = "";
        String blank = "   ";

        System.out.println("empty.isEmpty(): " + empty.isEmpty());   // true
        System.out.println("empty.isBlank(): " + empty.isBlank());   // true

        System.out.println("blank.isEmpty(): " + blank.isEmpty());   // false
        System.out.println("blank.isBlank(): " + blank.isBlank());   // true
    }
}
```

6. **Запустить и показать вывод:**
```
empty.isEmpty(): true
empty.isBlank(): true
blank.isEmpty(): false
blank.isBlank(): true
```

**Объяснить преимущество открытого кода:**
- Можно посмотреть, как реализованы стандартные классы
- Можно учиться на примере кода от экспертов
- Можно понять, почему метод работает именно так

**Связь с опытом Oracle Developer:**
> 💡 **Отличие от Oracle:** В Oracle PL/SQL код стандартных пакетов (DBMS_OUTPUT, UTL_FILE и др.) закрыт — вы видите только спецификацию (заголовки процедур), но не тело (implementation). В Java весь код открыт — вы можете посмотреть реализацию любого класса.

**Что делает студент параллельно:**
- Открывает исходный код класса `String`
- Изучает методы `isEmpty()` и `isBlank()`
- Пишет и запускает пример


---

## <a name="homework"></a> 🏠 СКВОЗНАЯ РАБОТА № 1

### Задание: Создать Maven-проект и написать простейшую программу
**Описание:**
Создайте Maven-проект с названием `payment-processing` и реализуйте программу для расчета комиссии за платеж.

**Требования:**
1. Через IDE создать Maven-проект с `groupId=ru.backendpro`, `artifactId=payment-processing`
2. Создать класс `PaymentProcessingApplication` в пакете `ru.backendpro`
3. В нем реализовать метод `main`, который выводит строку "This is payment processing system application"
4. Добавить Checkstyle в `pom.xml` и файл checkstyle.xml
5. Запустить проверку `mvn checkstyle:check` и исправить все предупреждения, если будут.
6. Скомпилировать проект через Maven: `mvn clean compile` (командная строка или меню в IDE)
7. Запустить программу через IntelliJ IDEA
8. Добавить зависимости JUnit5 последней стабильной версии
9. Создать простой тест как в Live coding
10. Создайте новый PR и запушьте изменения в GitHub


**Критерии оценки:**
- ✅ Проект создан с правильной структурой
- ✅ Код компилируется без ошибок
- ✅ Программа выводит правильные результаты
- ✅ Checkstyle не выдает ошибок
- ✅ Соблюдены соглашения по именованию
