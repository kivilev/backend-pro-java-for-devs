FROM eclipse-temurin:21-jre-alpine

LABEL maintainer="BackendPro Course"
LABEL description="Payment Processing System for Java/Spring Course"

WORKDIR /app

# Создаем непривилегированного пользователя для безопасности
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Копируем JAR файл
COPY target/payment-processing-system-1.0.0.jar app.jar

# Устанавливаем владельца
RUN chown -R appuser:appgroup /app

# Переключаемся на непривилегированного пользователя
USER appuser

# Открываем порт
EXPOSE 8080

# Настройки JVM для контейнера
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

# Запуск приложения
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

