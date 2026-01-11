/**
 Автор: Денис Кивилёв
 Online-школа: Backend-pro.ru
 
 Описание: ДЕМО-КОД ДЛЯ ЛЕКЦИИ 2: ОПЕРАТОРЫ И УПРАВЛЯЮЩИЕ КОНСТРУКЦИИ
 
 Этот класс демонстрирует ключевые операторы и управляющие конструкции в Java:
 - Арифметические операторы
 - Операторы сравнения
 - Логические операторы
 - Приведение типов (casting)
 - Условные конструкции (if-else, тернарный оператор)
 - Switch (старый и новый синтаксис)
 - Циклы (for, for-each, while, do-while)
 - Операторы break и continue
 
*/

public class OperatorsDemo {

    public static void main(String[] args) {
        System.out.println("=".repeat(80));
        System.out.println("ДЕМО-КОД: ОПЕРАТОРЫ И УПРАВЛЯЮЩИЕ КОНСТРУКЦИИ В JAVA");
        System.out.println("=".repeat(80));

        demo1_ArithmeticOperators();
        demo2_ComparisonOperators();
        demo3_LogicalOperators();
        demo4_TypeCasting();
        demo5_IfElse();
        demo6_TernaryOperator();
        demo7_SwitchOldStyle();
        demo8_SwitchNewStyle();
        demo9_ForLoop();
        demo10_ForEachLoop();
        demo11_WhileLoop();
        demo12_DoWhileLoop();
        demo13_BreakContinue();

        System.out.println("\n" + "=".repeat(80));
        System.out.println("ДЕМОНСТРАЦИЯ ЗАВЕРШЕНА");
        System.out.println("=".repeat(80));
    }

    /**
     * ДЕМО 1: Арифметические операторы
     * +, -, *, /, %, ++, --
     */
    private static void demo1_ArithmeticOperators() {
        System.out.println("\n--- ДЕМО 1: Арифметические операторы ---");

        int a = 10;
        int b = 3;

        int sum = a + b;        // 13
        int diff = a - b;       // 7
        int product = a * b;    // 30
        int quotient = a / b;   // 3 (целочисленное деление!)
        int remainder = a % b;  // 1 (остаток от деления)

        System.out.println("a = " + a + ", b = " + b);
        System.out.println("Сложение: a + b = " + sum);
        System.out.println("Вычитание: a - b = " + diff);
        System.out.println("Умножение: a * b = " + product);
        System.out.println("Деление: a / b = " + quotient + " (целочисленное!)");
        System.out.println("Остаток: a % b = " + remainder);

        // ВАЖНО: Особенность целочисленного деления
        System.out.println("\n>>> ОСОБЕННОСТЬ целочисленного деления:");
        int result1 = 10 / 3;      // 3 (не 3.333...)
        double result2 = 10 / 3;   // 3.0 (все еще целочисленное деление!)
        double result3 = 10.0 / 3; // 3.333... (дробное деление)

        System.out.println("int result = 10 / 3 = " + result1);
        System.out.println("double result = 10 / 3 = " + result2 + " (все еще целочисленное!)");
        System.out.println("double result = 10.0 / 3 = " + result3 + " (дробное деление)");

        // Инкремент и декремент
        System.out.println("\n>>> Инкремент и декремент:");
        int x = 5;
        System.out.println("x = " + x);
        System.out.println("x++ (постфиксный): " + (x++) + ", после x = " + x);

        x = 5;
        System.out.println("++x (префиксный): " + (++x) + ", после x = " + x);

        x = 5;
        System.out.println("x-- (постфиксный): " + (x--) + ", после x = " + x);

        x = 5;
        System.out.println("--x (префиксный): " + (--x) + ", после x = " + x);
    }

    /**
     * ДЕМО 2: Операторы сравнения
     * ==, !=, >, <, >=, <=
     */
    private static void demo2_ComparisonOperators() {
        System.out.println("\n--- ДЕМО 2: Операторы сравнения ---");

        int x = 10;
        int y = 20;

        System.out.println("x = " + x + ", y = " + y);
        System.out.println("x == y: " + (x == y) + " (равно)");
        System.out.println("x != y: " + (x != y) + " (не равно)");
        System.out.println("x > y: " + (x > y) + " (больше)");
        System.out.println("x < y: " + (x < y) + " (меньше)");
        System.out.println("x >= y: " + (x >= y) + " (больше или равно)");
        System.out.println("x <= y: " + (x <= y) + " (меньше или равно)");

        // ВАЖНО: Сравнение ссылочных типов
        System.out.println("\n>>> ВАЖНО: Сравнение ссылочных типов:");
        String s1 = new String("Hello");
        String s2 = new String("Hello");

        System.out.println("String s1 = new String(\"Hello\")");
        System.out.println("String s2 = new String(\"Hello\")");
        System.out.println("s1 == s2: " + (s1 == s2) + " (сравнение ССЫЛОК)");
        System.out.println("s1.equals(s2): " + s1.equals(s2) + " (сравнение СОДЕРЖИМОГО)");

        System.out.println("\n✅ Для ссылочных типов используйте .equals(), НЕ ==");
    }

    /**
     * ДЕМО 3: Логические операторы
     * &&, ||, !
     */
    private static void demo3_LogicalOperators() {
        System.out.println("\n--- ДЕМО 3: Логические операторы ---");

        boolean a = true;
        boolean b = false;

        System.out.println("a = " + a + ", b = " + b);
        System.out.println("a && b: " + (a && b) + " (И, AND)");
        System.out.println("a || b: " + (a || b) + " (ИЛИ, OR)");
        System.out.println("!a: " + (!a) + " (НЕ, NOT)");

        // Короткое замыкание (Short-circuit evaluation)
        System.out.println("\n>>> Короткое замыкание (Short-circuit):");
        int x = 0;
        boolean result = (x > 0) && (10 / x > 2);
        // Если x <= 0, второе условие НЕ проверяется
        // Избегаем деления на ноль!

        System.out.println("x = " + x);
        System.out.println("(x > 0) && (10 / x > 2) = " + result);
        System.out.println("✅ Второе условие НЕ выполнилось, избежали деления на ноль!");

        // Пример с ||
        x = 10;
        boolean result2 = (x < 0) || (10 / x > 1);
        System.out.println("\nx = " + x);
        System.out.println("(x < 0) || (10 / x > 1) = " + result2);
        System.out.println("✅ Первое условие false, второе выполнилось");
    }

    /**
     * ДЕМО 4: Приведение типов (Type Casting)
     * Неявное (widening) и явное (narrowing)
     */
    private static void demo4_TypeCasting() {
        System.out.println("\n--- ДЕМО 4: Приведение типов (Type Casting) ---");

        // Неявное приведение (Widening) - автоматическое
        System.out.println(">>> Неявное приведение (Widening):");
        int i = 100;
        long l = i;          // int -> long (автоматически)
        double d = l;        // long -> double (автоматически)

        System.out.println("int i = " + i);
        System.out.println("long l = i -> " + l + " (int -> long)");
        System.out.println("double d = l -> " + d + " (long -> double)");

        // Явное приведение (Narrowing) - с потенциальной потерей данных
        System.out.println("\n>>> Явное приведение (Narrowing):");
        double d2 = 9.99;
        int i2 = (int) d2;   // дробная часть отбрасывается

        System.out.println("double d2 = " + d2);
        System.out.println("int i2 = (int) d2 -> " + i2 + " (дробная часть отбросилась!)");

        // Потеря данных при переполнении
        System.out.println("\n>>> Потеря данных при переполнении:");
        int bigValue = 130;
        byte smallValue = (byte) bigValue;  // переполнение!

        System.out.println("int bigValue = " + bigValue);
        System.out.println("byte smallValue = (byte) bigValue -> " + smallValue);
        System.out.println("❌ Переполнение! byte может хранить только -128 до 127");

        // Операторы присваивания
        System.out.println("\n>>> Операторы присваивания:");
        int x = 10;
        System.out.println("x = " + x);
        x += 5;  // x = x + 5
        System.out.println("x += 5 -> " + x);
        x -= 3;  // x = x - 3
        System.out.println("x -= 3 -> " + x);
        x *= 2;  // x = x * 2
        System.out.println("x *= 2 -> " + x);
        x /= 4;  // x = x / 4
        System.out.println("x /= 4 -> " + x);
        x %= 4;  // x = x % 4
        System.out.println("x %= 4 -> " + x);
    }

    /**
     * ДЕМО 5: Условная конструкция if-else
     */
    private static void demo5_IfElse() {
        System.out.println("\n--- ДЕМО 5: Условная конструкция if-else ---");

        int balance = 1000;

        System.out.println("balance = " + balance);

        if (balance > 5000) {
            System.out.println("Результат: VIP клиент");
        } else if (balance > 1000) {
            System.out.println("Результат: Стандартный клиент");
        } else {
            System.out.println("Результат: Базовый клиент");
        }

        // Пример с разными балансами
        System.out.println("\n>>> Примеры с разными балансами:");
        checkBalance(7000);
        checkBalance(3000);
        checkBalance(500);
    }

    private static void checkBalance(int balance) {
        System.out.print("balance = " + balance + " -> ");
        if (balance > 5000) {
            System.out.println("VIP клиент");
        } else if (balance > 1000) {
            System.out.println("Стандартный клиент");
        } else {
            System.out.println("Базовый клиент");
        }
    }

    /**
     * ДЕМО 6: Тернарный оператор
     * Краткая форма if-else для присваивания
     */
    private static void demo6_TernaryOperator() {
        System.out.println("\n--- ДЕМО 6: Тернарный оператор ---");

        int age = 20;
        String category = (age >= 18) ? "Взрослый" : "Несовершеннолетний";

        System.out.println("age = " + age);
        System.out.println("category = (age >= 18) ? \"Взрослый\" : \"Несовершеннолетний\"");
        System.out.println("Результат: " + category);

        // Эквивалентный код через if-else
        System.out.println("\n>>> Эквивалентный код через if-else:");
        String category2;
        if (age >= 18) {
            category2 = "Взрослый";
        } else {
            category2 = "Несовершеннолетний";
        }
        System.out.println("Результат: " + category2);

        // Еще примеры
        System.out.println("\n>>> Еще примеры:");
        System.out.println("age = 15 -> " + ((15 >= 18) ? "Взрослый" : "Несовершеннолетний"));
        System.out.println("age = 25 -> " + ((25 >= 18) ? "Взрослый" : "Несовершеннолетний"));
    }

    /**
     * ДЕМО 7: Switch (старый стиль, до Java 14)
     */
    private static void demo7_SwitchOldStyle() {
        System.out.println("\n--- ДЕМО 7: Switch (старый стиль, до Java 14) ---");

        String status = "PENDING";
        String message;

        System.out.println("status = \"" + status + "\"");

        switch (status) {
            case "PENDING":
                message = "Ожидает обработки";
                break;
            case "COMPLETED":
                message = "Завершен";
                break;
            case "FAILED":
                message = "Ошибка";
                break;
            default:
                message = "Неизвестный статус";
                break;
        }

        System.out.println("Результат: " + message);

        // Примеры с разными статусами
        System.out.println("\n>>> Примеры с разными статусами:");
        printStatusOld("PENDING");
        printStatusOld("COMPLETED");
        printStatusOld("FAILED");
        printStatusOld("UNKNOWN");
    }

    private static void printStatusOld(String status) {
        System.out.print("status = \"" + status + "\" -> ");
        String message;
        switch (status) {
            case "PENDING":
                message = "Ожидает обработки";
                break;
            case "COMPLETED":
                message = "Завершен";
                break;
            case "FAILED":
                message = "Ошибка";
                break;
            default:
                message = "Неизвестный статус";
                break;
        }
        System.out.println(message);
    }

    /**
     * ДЕМО 8: Switch Expression (новый стиль, Java 14+)
     */
    private static void demo8_SwitchNewStyle() {
        System.out.println("\n--- ДЕМО 8: Switch Expression (новый стиль, Java 14+) ---");

        String status = "PENDING";

        System.out.println("status = \"" + status + "\"");

        // Новый синтаксис switch expression
        String message = switch (status) {
            case "PENDING" -> "Ожидает обработки";
            case "COMPLETED" -> "Завершен";
            case "FAILED" -> "Ошибка";
            default -> "Неизвестный статус";
        };

        System.out.println("Результат: " + message);

        System.out.println("\n>>> ПРЕИМУЩЕСТВА нового switch:");
        System.out.println("✅ Нет break (автоматически)");
        System.out.println("✅ Возвращает значение");
        System.out.println("✅ Компилятор проверяет полноту (exhaustiveness)");

        // Множественные значения
        System.out.println("\n>>> Множественные значения в case:");
        int dayOfWeek = 6;
        String dayType = switch (dayOfWeek) {
            case 1, 2, 3, 4, 5 -> "Рабочий день";
            case 6, 7 -> "Выходной";
            default -> "Некорректный день";
        };

        System.out.println("dayOfWeek = " + dayOfWeek + " -> " + dayType);

        // Примеры
        System.out.println("\n>>> Примеры:");
        for (int day = 1; day <= 8; day++) {
            String type = switch (day) {
                case 1, 2, 3, 4, 5 -> "Рабочий день";
                case 6, 7 -> "Выходной";
                default -> "Некорректный день";
            };
            System.out.println("День " + day + " -> " + type);
        }
    }

    /**
     * ДЕМО 9: Цикл for
     */
    private static void demo9_ForLoop() {
        System.out.println("\n--- ДЕМО 9: Цикл for ---");

        System.out.println(">>> Классический for:");
        for (int i = 0; i < 5; i++) {
            System.out.println("Итерация: " + i);
        }

        // С шагом 2
        System.out.println("\n>>> for с шагом 2:");
        for (int i = 0; i < 10; i += 2) {
            System.out.println("i = " + i);
        }

        // В обратном порядке
        System.out.println("\n>>> for в обратном порядке:");
        for (int i = 5; i > 0; i--) {
            System.out.println("i = " + i);
        }

        // Вложенные циклы
        System.out.println("\n>>> Вложенные циклы (таблица умножения 3x3):");
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                System.out.print(i + "x" + j + "=" + (i * j) + "  ");
            }
            System.out.println();
        }
    }

    /**
     * ДЕМО 10: Цикл for-each
     */
    private static void demo10_ForEachLoop() {
        System.out.println("\n--- ДЕМО 10: Цикл for-each ---");

        int[] numbers = {10, 20, 30, 40, 50};

        System.out.println(">>> Обход массива через for-each:");
        for (int num : numbers) {
            System.out.println("num = " + num);
        }

        // Сравнение for vs for-each
        System.out.println("\n>>> Сравнение for vs for-each:");

        System.out.println("Классический for (с индексом):");
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("numbers[" + i + "] = " + numbers[i]);
        }

        System.out.println("\nfor-each (без индекса):");
        for (int num : numbers) {
            System.out.println("num = " + num);
        }

        System.out.println("\n>>> КОГДА использовать:");
        System.out.println("✅ for - когда нужен индекс или сложная логика");
        System.out.println("✅ for-each - когда просто обходим массив/коллекцию");
    }

    /**
     * ДЕМО 11: Цикл while
     */
    private static void demo11_WhileLoop() {
        System.out.println("\n--- ДЕМО 11: Цикл while ---");

        System.out.println(">>> Цикл while:");
        int count = 0;
        while (count < 5) {
            System.out.println("count = " + count);
            count++;
        }

        // Пример: подсчет цифр в числе
        System.out.println("\n>>> Пример: подсчет цифр в числе:");
        int number = 12345;
        int digitCount = 0;
        int temp = number;

        while (temp > 0) {
            temp = temp / 10;
            digitCount++;
        }

        System.out.println("Число " + number + " содержит " + digitCount + " цифр");
    }

    /**
     * ДЕМО 12: Цикл do-while
     */
    private static void demo12_DoWhileLoop() {
        System.out.println("\n--- ДЕМО 12: Цикл do-while ---");

        System.out.println(">>> Цикл do-while:");
        int i = 0;
        do {
            System.out.println("i = " + i);
            i++;
        } while (i < 5);

        // РАЗНИЦА: do-while выполняется минимум 1 раз
        System.out.println("\n>>> РАЗНИЦА: do-while выполняется минимум 1 раз:");

        System.out.println("while (false):");
        int x = 0;
        while (x > 10) {  // условие false, цикл не выполнится
            System.out.println("Это не напечатается");
        }
        System.out.println("Цикл while не выполнился");

        System.out.println("\ndo-while (false):");
        int y = 0;
        do {
            System.out.println("Это напечатается ОДИН раз (хотя условие false)");
        } while (y > 10);  // условие false, но тело выполнилось 1 раз
    }

    /**
     * ДЕМО 13: Операторы break и continue
     */
    private static void demo13_BreakContinue() {
        System.out.println("\n--- ДЕМО 13: Операторы break и continue ---");

        // break - выход из цикла
        System.out.println(">>> break - выход из цикла:");
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                System.out.println("Встретили i = 5, выходим из цикла");
                break;
            }
            System.out.println("i = " + i);
        }

        // continue - пропуск текущей итерации
        System.out.println("\n>>> continue - пропуск итерации:");
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                continue;  // пропускаем четные числа
            }
            System.out.println("i = " + i + " (нечетное)");
        }

        // Практический пример: поиск в массиве
        System.out.println("\n>>> Практический пример: поиск элемента:");
        int[] numbers = {10, 20, 30, 40, 50};
        int target = 30;
        int foundIndex = -1;

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == target) {
                foundIndex = i;
                System.out.println("Нашли " + target + " на позиции " + i);
                break;  // нашли, можно прекратить поиск
            }
        }

        if (foundIndex == -1) {
            System.out.println("Элемент не найден");
        }

        // continue для фильтрации
        System.out.println("\n>>> continue для фильтрации (только положительные числа):");
        int[] mixedNumbers = {-5, 10, -3, 20, -1, 30};
        for (int num : mixedNumbers) {
            if (num < 0) {
                continue;  // пропускаем отрицательные
            }
            System.out.println("Положительное число: " + num);
        }
    }
}