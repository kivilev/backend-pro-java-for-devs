# Репозиторий к урокам курса "Java+Spring для разработчиков: Быстрый старт 🚀"

Автор: Кивилев Денис (kivilev.d@gmail.com)  
Школа: backend-pro.ru  

## Сохранение в github результатов выполнения домашних заданий
1. Создайте репозиторий для сохранения выполненных домашних заданий
2. Создайте Readme.md
- добавьте краткое описание, что за это за репозиторий
- кто автор
3. Для каждой новой домашней работы создавайте каталог
Например, lesson1-first-project
4. Добавьте защиту на прямой commit в main-ветку.
5. Все изменения должны идти через Pull Request/Merge request.
название PR/PR должно совпадать с названием папки.
Например: pr-lesson1-first-project
6. Только после аппрува преподавателем, pull request сливается в main-ветку.

## Рекомендуемая литература
1. "Effective Java" by Joshua Bloch (классика для Java-разработчиков)
2. "Clean Code" by Robert Martin (принципы чистого кода)



# 🔧 СТРУКТУРА КУРСА

## 📖 БЛОК 1: ОСНОВЫ JAVA (11 лекций)

### **Лекция 1: Введение в Java** ✅ *Готова*
- История Java, версии, LTS релизы
- JDK дистрибутивы (Oracle JDK, OpenJDK, Liberica)
- JVM архитектура (bytecode, JIT, GC)
- JRE vs JDK
- Установка IntelliJ IDEA и JDK
- Build tools (Maven vs Gradle)
- Maven Central
- Создание Maven проекта
- Hello World программа
- **Naming conventions (Clean Code)**
- Checkstyle и линтеры
- Open-source Java

---

### **Лекция 2: Основы синтаксиса Java**
**Блок 1: Типы данных и переменные**
- Обзор типов в Java (примитивные vs ссылочные)
- Примитивные типы данных (byte, short, int, long, float, double, char, boolean)
- Ссылочные типы данных
- Wrapper классы (Integer, Double и др.)
- Autoboxing и Unboxing
- Переменные и константы (final)
- **Иммутабельность (Best Practice)**
- Локальный вывод типов (var)

**Блок 2: Операторы и управляющие конструкции**
- Операторы (арифметические, логические, сравнения, присваивания)
- Приведение типов (casting)
- Условные конструкции (if-else, тернарный оператор)
- Switch expressions (Java 14+)
- Циклы (for, while, do-while, for-each)
- Операторы break и continue

---

### **Лекция 3: Строки и массивы**
**Блок 1: Работа со строками**
- Работа со строками (String)
- Неизменяемость строк
- Основные методы String
- StringBuilder для конкатенации
- Text Blocks (Java 15+)
- Концепция null и NullPointerException

**Блок 2: Массивы**
- Одномерные массивы (объявление, инициализация, обход)
- Многомерные массивы
- Операции с массивами (Arrays.sort, Arrays.copyOf и др.)

---

### **Лекция 4: ООП - Основы**
**Блок 1: Классы и инкапсуляция**
- Концепция ООП (инкапсуляция, наследование, полиморфизм, абстракция)
- Классы и объекты
- Поля класса (instance fields, static fields)
- Конструкторы (default, parameterized, overloading)
- Ключевое слово `this`
- Методы класса (instance methods, static methods)
- Getter и Setter методы
- Модификаторы доступа (private, protected, public, default)
- **Инкапсуляция как принцип сокрытия данных**
- Практика: создание классов Client, Account, Payment

**Блок 2: Наследование и полиморфизм**
- Наследование (extends)
- Ключевое слово `super`
- Переопределение методов (@Override)
- Абстрактные классы и методы
- final классы и методы
- Полиморфизм
- Приведение типов (upcasting, downcasting)
- Оператор instanceof
- Object класс (toString, equals, hashCode)
- Иерархия классов

- Практика: создание иерархии классов для платежной системы

---

### **Лекция 5: Интерфейсы и Enums**
**Блок 1: Интерфейсы**
- Интерфейсы (создание, реализация)
- Множественное наследование через интерфейсы
- Default методы в интерфейсах (Java 8+)
- Функциональные интерфейсы
- **Strategy Pattern (через интерфейсы)**
- Практика: создание интерфейсов для платежной системы

**Блок 2: Enums**
- Enums (перечисления)
- Enum с полями и методами
- Enum в switch
- Создание PaymentStatus, AccountStatus, ClientStatus

- Практика: 
-- рефакторинг проекта с использованием enum и интерфейсов
-- Создание PaymentStatus, AccountStatus, ClientStatus

---

### **Лекция 6: Collections Framework**
**Блок 1: List и Set**
- Интерфейс Collection
- List интерфейс
- ArrayList (внутреннее устройство, операции)
- Когда использовать ArrayList
- Set интерфейс
- HashSet (внутреннее устройство, уникальность)
- Итераторы

**Блок 2: Map**
- Map интерфейс
- HashMap (внутреннее устройство, hash collision)
- Методы Map (put, get, containsKey, remove и др.)
- Обход Map (entrySet, keySet, values)

**Блок 3: Best Practices**
- **DRY (Don't Repeat Yourself)** - избегание дублирования кода
- **KISS (Keep It Simple, Stupid)** - простота решений

- Практика: работа со списками клиентов и платежей (ArrayList, HashMap)

---

### **Лекция 7: Обработка исключений**
- Иерархия исключений (Throwable, Exception, RuntimeException, Error)
- Checked vs Unchecked исключения
- try-catch-finally
- try-with-resources (AutoCloseable)
- throw и throws
- Создание собственных исключений
- **Best practices обработки исключений (Clean Code)**
- **Не использовать исключения для control flow**
- **Информативные сообщения об ошибках**
- Практика: создание BusinessException, ResourceNotFoundException

---

### **Лекция 8: Функциональное программирование и Stream API**
**Блок 1: Lambda и функциональные интерфейсы**
- Lambda выражения
- Функциональные интерфейсы (Predicate, Function, Consumer, Supplier)
- Method references

**Блок 2: Stream API**
- Stream API (создание, промежуточные и терминальные операции)
- map, filter, reduce
- collect (Collectors.toList, toSet, toMap)
- sorted, distinct, limit, skip
- flatMap
- Группировка и агрегация (groupingBy, counting, summingDouble)

**Блок 3: Optional**
- Optional (создание, методы, best practices)
- **Избегание null через Optional**
- **Функциональный подход vs императивный**
- Практика: фильтрация платежей, расчет статистики через Stream API

---

### **Лекция 9: Работа с датами и деньгами**
**Блок 1: java.time API**
- Проблемы с Date и Calendar (legacy API)
- java.time API (Java 8+)
- LocalDate, LocalTime, LocalDateTime
- ZonedDateTime (работа с часовыми поясами)
- Instant, Duration, Period
- DateTimeFormatter (форматирование и парсинг)
- **Clock для тестирования** *(используется в проекте!)*
- **Иммутабельность в java.time API**
- Практика: использование Clock для получения текущего времени

**Блок 2: BigDecimal**
- Проблемы точности с float/double
- BigDecimal для финансовых операций
- Операции с BigDecimal (add, subtract, multiply, divide)
- Округление и масштабирование (setScale, RoundingMode)
- **Best practices для финансовых расчетов**
- Практика: рефакторинг Payment с использованием BigDecimal и ZonedDateTime

---

### **Лекция 10: Generics**
- Параметризованные типы
- Generic классы
- Generic методы
- Wildcards (?, ? extends T, ? super T)
- **Best practices использования Generics**
- **YAGNI принцип (не усложнять без необходимости)**
- Практика: создание generic утилитных классов

---

### **Лекция 11: Unit-тестирование (JUnit 5 + Mockito)**

**Часть 1: JUnit 5 основы**
- JUnit 5 архитектура
- @Test, @DisplayName
- @BeforeEach, @AfterEach, @BeforeAll, @AfterAll
- Assertions (assertEquals, assertNotNull, assertNull, assertTrue)
- assertDoesNotThrow, assertThrows
- **AAA паттерн (Arrange-Act-Assert)**
- **Test Naming Conventions**
- **Что тестировать unit-тестами**
- Практика: unit тесты для Mapper классов
- Практика: unit тесты для Validator классов (PaymentValidator, ClientValidationService)

**Часть 2: Mockito**
- Зачем нужны моки
- Mockito концепция
- @Mock, @InjectMocks
- @ExtendWith(MockitoExtension.class)
- Mockito.when().thenReturn()
- Mockito.when().thenThrow()
- Mockito.verify()
- Stubbing методов
- **Тестирование в изоляции (Unit Testing Best Practices)**
- Практика: тесты с моками для Service слоя

**Часть 3: Параметризованные тесты**
- @ParameterizedTest
- @ValueSource, @CsvSource, @MethodSource
- Практика: параметризованные тесты для валидации

---

## 📖 БЛОК 2: SPRING FRAMEWORK (2 лекции)

### **Лекция 12: Spring Core и Dependency Injection**
**Блок 1: Dependency Injection и IoC**
- **Dependency Injection Pattern (паттерн проектирования)**
- Inversion of Control (IoC)
- Dependency Injection (DI)
- ApplicationContext
- @Component, @Service, @Repository
- @Autowired (field, constructor, setter injection)
- **Constructor Injection как Best Practice**
- @Configuration и @Bean
- @Primary
- Bean scope (singleton, prototype)
- **Singleton Pattern через Spring**

**Блок 2: Layered Architecture и SOLID**
- **Layered Architecture: разделение на слои**
  - Controller слой (REST API)
  - Service слой (бизнес-логика)
  - Repository/DAO слой (доступ к данным)
- **Service Layer Pattern**
- **Repository Pattern (введение)**
- **SOLID принципы в Spring приложении:**
  - **Single Responsibility Principle** - каждый слой имеет одну ответственность
  - **Open/Closed Principle** - расширение через интерфейсы, закрыт для модификации
  - **Dependency Inversion Principle** - зависимость от абстракций (интерфейсов), а не от конкретных реализаций
- **Separation of Concerns (разделение ответственности)**
- **Dependency Direction (зависимости сверху вниз)**: Controller → Service → Repository
- Практика: создание Service слоя с правильной архитектурой

---

### **Лекция 13: Spring Boot и конфигурация**
- Spring Boot концепция
- @SpringBootApplication
- Auto-configuration
- Spring Boot Starters
- application.properties vs application.yml
- @Value
- Profiles (dev, test, prod)
- @ConditionalOnProperty *(используется в SchedulingConfig)*
- Embedded сервер (Tomcat)
- **Configuration Management Best Practices**
- Jackson ObjectMapper конфигурация *(AppConfig)*
- Практика: настройка Spring Boot приложения
- Практика: создание конфигурации с @ConditionalOnProperty

---

## 📖 БЛОК 3: РАБОТА С БАЗАМИ ДАННЫХ (5 лекций)

### **Мини-лекция 14: Транзакции и блокировки** *(Offline-лекция)*
- ACID свойства
- Транзакции (BEGIN, COMMIT, ROLLBACK)
- Уровни изоляции (READ UNCOMMITTED, READ COMMITTED, REPEATABLE READ, SERIALIZABLE)
- Pessimistic Locking (блокировки FOR UPDATE)
- **FOR UPDATE SKIP LOCKED** *(используется в проекте для конкурентной обработки!)*
- Deadlock и способы предотвращения
- Lock timeout
- Практика: обработка конкурентных платежей с Pessimistic Locking

---

### **Лекция 15: Hibernate, Spring Data JPA (основы, отношения)**
**Блок 1: ORM и Entity**
- ORM концепция
- JPA vs Hibernate
- Entity классы (@Entity, @Table)
- @Id, @GeneratedValue, @SequenceGenerator
- @Column (name, nullable, unique, length)
- Типы генерации ID (AUTO, IDENTITY, SEQUENCE, TABLE)
- EntityManager и Persistence Context
- Жизненный цикл сущностей (transient, managed, detached, removed)
- **@Index** *(используется во всех Entity!)*
- **@UniqueConstraint** *(используется в проекте)*
- **Entity Design Best Practices**

**Блок 2: Отношения между Entity**
- @ManyToOne, @OneToMany
- @JoinColumn
- FetchType (LAZY, EAGER)
- CascadeType
- orphanRemoval
- N+1 problem и способы решения
- Практика: создание Entity классов для проекта
- Практика: связи между Client, Account, Payment

---

### **Лекция 16: Spring Data JPA: Advanced**
**Блок 1: Repository Pattern**
- **Repository Pattern (детальное изучение)**
- JpaRepository интерфейс
- Методы запросов (findBy, countBy, deleteBy, existsBy)
- Query derivation
- @Query аннотация (JPQL)
- JPQL (Java Persistence Query Language)
- @Param

**Блок 2: Native queries и блокировки**
- Native queries (@Query с nativeQuery = true)
- **Native query с FOR UPDATE SKIP LOCKED** *(как в PaymentRepository)*
- @Lock (LockModeType.PESSIMISTIC_WRITE)
- @QueryHints (lock timeout)

**Блок 3: Projections и Pagination**
- **DTO Projections (DTO Pattern в Repository слое)**
- Interface projections
- Pagination (Pageable, Page, PageRequest)
- Sorting (Sort)

**Блок 4: JPA Advanced**
- JPA Auditing (@CreatedDate, @LastModifiedDate, @EntityListeners)
- AttributeConverter (@Converter) *(ZonedDateTimeConverter, StatusConverter)*
- **Query Optimization Best Practices**
- Практика: создание репозиториев для проекта
- Практика: пагинация платежей
- Практика: блокировки для конкурентного доступа

---

### **Лекция 17: Spring Transactions**
- @Transactional
- Transaction isolation
- readOnly транзакции *(используется в проекте!)*
- rollbackFor и noRollbackFor
- **Transaction Management Best Practices**
- **Где размещать @Transactional (Service vs Repository)**
- Практика: транзакционная обработка платежей

**Тестирование репозиториев**
- @DataJpaTest
- H2 in-memory database для тестов
- @Transactional в тестах (автоматический rollback)
- **Test Data Management**
- Практика: интеграционные тесты для Repository слоя

---

### **Лекция 18: JDBC и JdbcTemplate**
**Блок 1: JDBC основы**
- JDBC API
- DriverManager и DataSource
- Connection, Statement, PreparedStatement
- ResultSet (обработка результатов)
- SQLException
- Connection pooling

**Блок 2: Spring JDBC**
- Spring JDBC
- **DAO Pattern (Data Access Object)**
- JdbcTemplate (queryForObject, query, update)
- RowMapper *(как в PaymentReportDao)*
- Batch операции
- **Когда использовать JdbcTemplate vs JPA**
- **Performance considerations**
- Практика: создание DAO слоя с JdbcTemplate для отчетов (PaymentReportDao, ClientReportDao, AccountReportDao)

---

### **Мини-Лекция 19: Liquibase** *(Offline-лекция)*
- Миграции базы данных
- Liquibase концепция
- Changelog файлы (XML, YAML, SQL)
- Changesets
- Preconditions
- Rollback
- Версионирование схемы БД
- Интеграция с Spring Boot
- **Database Migration Best Practices**
- Практика: создание миграций для проекта (V001, V002)

---

## 📖 БЛОК 4: REST API (3 лекции)

### **Мини-Лекция 20: Что такое REST API** *(Offline-лекция)*
- REST принципы
- HTTP методы (GET, POST, PUT, PATCH, DELETE)
- HTTP статус коды (200, 201, 400, 404, 409, 500)
- **REST API Design Best Practices**

---

### **Лекция 21: Spring MVC и DTO Pattern**
**Блок 1: Spring MVC**
- @RestController
- @RequestMapping
- @GetMapping, @PostMapping, @PatchMapping, @DeleteMapping
- @PathVariable
- @RequestParam
- @RequestBody
- ResponseEntity
- Content-Type и Accept headers

**Блок 2: DTO Pattern**
- **DTO Pattern (Data Transfer Object) - детальное изучение**
- Request DTO vs Response DTO
- **Зачем нужны DTO (Separation of Concerns, версионирование API)**
- **Mapper Pattern (Entity ↔ DTO)**
- Mapper классы (ручной маппинг) *(PaymentMapper, ClientMapper, AccountMapper)*
- **Builder Pattern для создания DTO**

**Блок 3: Pagination и Architecture**
- Pageable и Page в REST API
- PageRequest и Sort
- Параметры пагинации (page, size, sort)
- **REST API Layered Architecture:**
  - Controller → Service → Repository
  - **Dependency Direction (зависимости сверху вниз)**
- Практика: создание REST API для Client, Account, Payment
- Практика: создание DTO и Mapper для всех сущностей
- Практика: пагинация платежей

**Тестирование контроллеров**
- @WebMvcTest
- MockMvc
- @MockBean
- Тестирование GET, POST, PATCH endpoints
- Тестирование с параметрами пагинации
- **Controller Testing Best Practices**
- Практика: интеграционные тесты для REST API контроллеров

---

### **Лекция 22: Валидация и обработка исключений в REST API**
**Блок 1: Валидация**
- Jakarta Validation (JSR 380)
- @Valid
- @NotNull, @NotBlank, @NotEmpty
- @Size, @Min, @Max
- @Positive, @PositiveOrZero
- @DecimalMin, @Digits
- @Email, @Pattern
- **Валидация через сервисы** *(PaymentValidator, ClientValidationService — альтернатива кастомным валидаторам)*
- MethodArgumentNotValidException
- Практика: валидация Request DTO (PaymentCreateRequestDto)

**Блок 2: Обработка исключений**
- @ExceptionHandler
- @RestControllerAdvice
- GlobalExceptionHandler
- Обработка различных типов исключений:
  - ResourceNotFoundException → 404
  - BusinessException → 400
  - LockTimeoutException → 409 (Conflict)
  - MethodArgumentNotValidException → 400
- Кастомные исключения (BusinessException, ResourceNotFoundException, AccountLockTimeoutException)
- ErrorResponse DTO
- Маппинг исключений на HTTP статусы
- **Exception Handling Best Practices**
- **Централизованная обработка ошибок**
- Практика: централизованная обработка ошибок (GlobalExceptionHandler)

---

## 📖 БЛОК 5: ДОПОЛНИТЕЛЬНЫЕ ТЕМЫ (7 лекций)

### **Лекция 23: Spring Scheduling** *(Offline-лекция)*
- @Scheduled
- @EnableScheduling
- @ConditionalOnProperty для включения/выключения *(как в SchedulingConfig)*
- fixedRate vs fixedDelay
- fixedRateString с @Value
- initialDelay
- Cron expressions
- **Background Processing Best Practices**
- Практика: фоновая обработка платежей (PaymentProcessingService)

---

### **Лекция 24: Логирование** *(Offline-лекция)*
- SLF4J
- Создание логгера: `LoggerFactory.getLogger(MyClass.class)`
- Уровни логирования (DEBUG, INFO, WARN, ERROR)
- log.info, log.debug, log.warn, log.error
- Параметризованные сообщения: `log.info("Processing payment: id={}", id)`
- Конфигурация уровней в application.yml
- **Logging Best Practices:**
  - Что логировать (входы/выходы методов, ошибки, важные события)
  - Уровни логирования (когда какой использовать)
  - Производительность (ленивое вычисление параметров)
  - Безопасность (не логировать sensitive data: пароли, токены)
- Практика: добавление логирования в сервисы проекта

---

### **Лекция 25: API Documentation и финализация проекта**
- OpenAPI/Swagger
- SpringDoc OpenAPI
- @Tag, @Operation, @Schema *(используется в проекте)*
- Swagger UI
- **API Documentation Best Practices**
- **Code Review Checklist**
- **Performance Best Practices:**
  - Database query optimization
  - N+1 problem
  - Connection pooling
- **Security Best Practices:**
  - Input validation
  - SQL injection prevention
  - Sensitive data handling
- **Clean Code принципы (обобщение)**
- Финализация Payment Processing System
- Деплой приложения

---

### **Лекция 26: Lombok** *(Бонусная лекция)*
- Lombok аннотации:
  - @Getter, @Setter
  - @NoArgsConstructor, @AllArgsConstructor, @RequiredArgsConstructor
  - **@Builder (Builder Pattern)**
  - @Data, @ToString, @EqualsAndHashCode
  - @Slf4j *(замена ручного создания логгера)*
- **Lombok Best Practices**
- **Когда использовать и когда избегать Lombok**
- Рефакторинг проекта с использованием Lombok

---

### **Лекция 27: Maven, Docker, инструменты качества** *(Бонусная лекция)*
**Блок 1: Maven**
- Maven lifecycle (clean, compile, test, package, install)
- Dependency scope (compile, runtime, test, provided)
- Maven plugins
- **Maven Best Practices**

**Блок 2: Docker**
- Docker основы
- Dockerfile *(создан для проекта)*
  - FROM, WORKDIR, COPY, RUN, EXPOSE, ENTRYPOINT
  - Безопасность: непривилегированный пользователь
  - JVM настройки для контейнера
- Docker Compose
- docker-compose.yml для проекта (PostgreSQL, pgAdmin)
- **Containerization Best Practices**

**Блок 3: Инструменты качества**
- Checkstyle (конфигурация, правила)
- SpotBugs (статический анализ)
- **Code Quality Best Practices**
- Практика: сборка и запуск проекта в Docker

---

### **Лекция 28: Интеграционные тесты и @SpringBootTest** *(Бонусная лекция)*
- Отличие интеграционных тестов от unit-тестов
- @SpringBootTest
- @TestConfiguration
- Test profiles (application-test.yml)
- **Integration Testing Best Practices:**
  - Test data setup
  - Test isolation
  - Test performance
- Практика: интеграционные тесты для полного flow (Controller → Service → Repository)

---

### **Лекция 29: SOLID и рефакторинг** *(Бонусная лекция)*
**Блок 1: Обзор SOLID принципов**
- **Single Responsibility Principle (SRP)**
  - Каждый класс должен иметь одну причину для изменения
  - Примеры из проекта: PaymentService, PaymentValidator, PaymentMapper
  - Антипаттерн: "God Class" (класс, который делает всё)
  
- **Open/Closed Principle (OCP)**
  - Открыт для расширения, закрыт для модификации
  - Примеры: Strategy Pattern через интерфейсы
  - Расширение функциональности через новые реализации интерфейсов
  
- **Liskov Substitution Principle (LSP)**
  - Объекты подклассов должны корректно заменять объекты базового класса
  - Примеры правильного и неправильного наследования
  - Контракты методов и их соблюдение в подклассах
  
- **Interface Segregation Principle (ISP)**
  - Клиенты не должны зависеть от интерфейсов, которые они не используют
  - Разделение "толстых" интерфейсов на специализированные
  - Примеры из проекта: разделение Repository интерфейсов
  
- **Dependency Inversion Principle (DIP)**
  - Зависимость от абстракций, а не от конкретных реализаций
  - Примеры: Spring DI, зависимость от интерфейсов Repository
  - Инверсия зависимостей через IoC контейнер

**Блок 2: Code Smells и рефакторинг**
- **Распространенные Code Smells:**
  - Long Method (длинные методы)
  - Large Class (большие классы)
  - Duplicate Code (дублирование кода)
  - Feature Envy (зависть к функциональности)
  - Data Clumps (группы данных)
  
- **Техники рефакторинга:**
  - Extract Method
  - Extract Class
  - Move Method
  - Replace Conditional with Polymorphism

**Блок 3: Практика**
- **Рефакторинг Payment Processing System:**
  - Анализ текущего кода на соответствие SOLID
  - Выявление нарушений принципов
  - Применение рефакторинга
  - Улучшение архитектуры проекта
  
- **Когда применять паттерны, а когда нет (YAGNI)**
  - You Aren't Gonna Need It
  - Баланс между гибкостью и простотой
  - Преждевременная оптимизация vs техдолг


## 🎯 ИТОГОВАЯ СТАТИСТИКА

- **БЛОК 1: ОСНОВЫ JAVA**: 11 лекций
- **БЛОК 2: SPRING FRAMEWORK**: 2 лекции
- **БЛОК 3: РАБОТА С БАЗАМИ ДАННЫХ**: 5 лекций (включая 2 offline)
- **БЛОК 4: REST API**: 3 лекции (включая 1 offline)
- **БЛОК 5: ДОПОЛНИТЕЛЬНЫЕ ТЕМЫ**: 7 лекций (включая 2 offline, 4 бонусные)

**Всего**: 29 лекций (23 основные + 5 offline + 4 бонусные)

