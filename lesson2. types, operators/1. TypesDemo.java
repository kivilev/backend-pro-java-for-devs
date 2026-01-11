/**
 Автор: Денис Кивилёв
 Online-школа: Backend-pro.ru
 
 Описание: ДЕМО-КОД ДЛЯ ЛЕКЦИИ 2: ТИПЫ ДАННЫХ В JAVA
 
 Этот класс демонстрирует ключевые концепции работы с типами данных в Java:
 - Примитивные vs Ссылочные типы
 - Stack vs Heap память
 - Wrapper классы
 - Autoboxing/Unboxing
 - NullPointerException
 - String и иммутабельность
 - String Pool
 - Default values и var
 
*/
 
public class TypesDemo {

    // Поля класса для демонстрации default values
    static int defaultInt;
    static long defaultLong;
    static double defaultDouble;
    static boolean defaultBoolean;
    static String defaultString;
    static Integer defaultInteger;

    public static void main(String[] args) {
        System.out.println("=".repeat(80));
        System.out.println("ДЕМО-КОД: ТИПЫ ДАННЫХ В JAVA");
        System.out.println("=".repeat(80));

        demo1_PrimitiveTypes();
        demo2_ReferenceTypes();
        demo3_WrapperClasses();
        demo4_AutoboxingUnboxing();
        demo5_NullPointerException();
        demo6_StringImmutability();
        demo7_StringPool();
        demo8_DefaultValues();
        demo9_VarKeyword();

        System.out.println("\n" + "=".repeat(80));
        System.out.println("ДЕМОНСТРАЦИЯ ЗАВЕРШЕНА");
        System.out.println("=".repeat(80));
    }

    /**
     * ДЕМО 1: Примитивные типы (хранятся в Stack)
     * Примитивы содержат само значение, а не ссылку
     */
    private static void demo1_PrimitiveTypes() {
        System.out.println("\n--- ДЕМО 1: Примитивные типы (Primitive Types) ---");

        // 8 примитивных типов в Java
        byte age = 25;                          // 8 бит: -128 до 127
        short year = 2024;                      // 16 бит: -32,768 до 32,767
        int population = 1_000_000;             // 32 бита: -2^31 до 2^31-1
        long distance = 9_460_730_472_580L;     // 64 бита: -2^63 до 2^63-1
        float price = 19.99F;                   // 32 бита: ~±3.4E+38
        double pi = 3.141592653589793;          // 64 бита: ~±1.7E+308
        char grade = 'A';                       // 16 бит: один символ Unicode
        boolean isActive = true;                // true/false

        System.out.println("byte age = " + age);
        System.out.println("short year = " + year);
        System.out.println("int population = " + population);
        System.out.println("long distance = " + distance);
        System.out.println("float price = " + price);
        System.out.println("double pi = " + pi);
        System.out.println("char grade = " + grade);
        System.out.println("boolean isActive = " + isActive);

        // ВАЖНО: При копировании примитивов копируется ЗНАЧЕНИЕ
        int x = 10;
        int y = x;      // копируется значение
        y = 20;         // изменение y не влияет на x

        System.out.println("\n>>> КОПИРОВАНИЕ ПРИМИТИВОВ:");
        System.out.println("x = " + x + " (не изменился)");
        System.out.println("y = " + y + " (изменился)");
    }

    /**
     * ДЕМО 2: Ссылочные типы (хранятся в Heap)
     * Переменная содержит ссылку (адрес) на объект, а не сам объект
     */
    private static void demo2_ReferenceTypes() {
        System.out.println("\n--- ДЕМО 2: Ссылочные типы (Reference Types) ---");

        // StringBuilder - изменяемый класс (mutable)
        StringBuilder sb1 = new StringBuilder("Hello");
        StringBuilder sb2 = sb1;  // копируется ССЫЛКА, не значение

        System.out.println(">>> ДО изменения:");
        System.out.println("sb1 = " + sb1);
        System.out.println("sb2 = " + sb2);

        sb2.append(" World");     // изменяем через sb2

        System.out.println("\n>>> ПОСЛЕ изменения через sb2:");
        System.out.println("sb1 = " + sb1 + " (ИЗМЕНИЛСЯ! потому что sb1 и sb2 указывают на ОДИН объект)");
        System.out.println("sb2 = " + sb2);

        // Сравнение с примитивами
        System.out.println("\n>>> КЛЮЧЕВОЕ ОТЛИЧИЕ:");
        System.out.println("- Примитивы: копируется ЗНАЧЕНИЕ");
        System.out.println("- Ссылочные типы: копируется ССЫЛКА (адрес объекта в Heap)");
    }

    /**
     * ДЕМО 3: Wrapper классы (обертки для примитивов)
     * Используются для коллекций, generics, nullable значений
     */
    private static void demo3_WrapperClasses() {
        System.out.println("\n--- ДЕМО 3: Wrapper классы (обертки) ---");

        // Примитив -> Wrapper
        byte b = 10;        // примитив
        Byte bWrapper = b;  // wrapper

        short s = 100;
        Short sWrapper = s;

        int i = 1000;
        Integer iWrapper = i;

        long l = 10000L;
        Long lWrapper = l;

        float f = 3.14F;
        Float fWrapper = f;

        double d = 2.71;
        Double dWrapper = d;

        char c = 'Z';
        Character cWrapper = c;

        boolean bool = true;
        Boolean boolWrapper = bool;

        System.out.println("int -> Integer: " + iWrapper);
        System.out.println("double -> Double: " + dWrapper);
        System.out.println("char -> Character: " + cWrapper);

        // ПОЧЕМУ нужны wrapper классы?
        System.out.println("\n>>> 4 ПРИЧИНЫ использовать wrapper классы:");
        System.out.println("1. Коллекции: List<Integer>, Map<String, Double>");
        System.out.println("2. Nullable значения: Integer x = null (примитив не может быть null)");
        System.out.println("3. Методы утилиты: Integer.parseInt(\"123\"), Double.valueOf(\"3.14\")");
        System.out.println("4. Generics: требуют ссылочные типы");

        // Примеры методов-утилит
        Integer parsed = Integer.parseInt("999");
        Double parsedDouble = Double.valueOf("3.14159");
        System.out.println("\nInteger.parseInt(\"999\") = " + parsed);
        System.out.println("Double.valueOf(\"3.14159\") = " + parsedDouble);
    }

    /**
     * ДЕМО 4: Autoboxing и Unboxing
     * Автоматическое преобразование между примитивами и wrapper классами
     */
    private static void demo4_AutoboxingUnboxing() {
        System.out.println("\n--- ДЕМО 4: Autoboxing и Unboxing ---");

        // Autoboxing: примитив -> wrapper (автоматически)
        int primitive = 42;
        Integer wrapper = primitive;  // autoboxing: int -> Integer
        System.out.println("Autoboxing: int 42 -> Integer " + wrapper);

        // Unboxing: wrapper -> примитив (автоматически)
        Integer wrapperValue = 100;
        int primitiveValue = wrapperValue;  // unboxing: Integer -> int
        System.out.println("Unboxing: Integer 100 -> int " + primitiveValue);

        // В выражениях тоже работает автоматически
        Integer a = 10;
        Integer b = 20;
        int sum = a + b;  // unboxing происходит автоматически
        System.out.println("Autoboxing/Unboxing в выражениях: " + a + " + " + b + " = " + sum);

        System.out.println("\n>>> ВАЖНО: Осторожно с null при unboxing!");
    }

    /**
     * ДЕМО 5: NullPointerException (NPE)
     * Самая частая ошибка в Java - попытка обратиться к null объекту
     */
    private static void demo5_NullPointerException() {
        System.out.println("\n--- ДЕМО 5: NullPointerException (NPE) ---");

        // Пример 1: Вызов метода на null объекте
        String name = null;
        try {
            int length = name.length();  // NPE!
        } catch (NullPointerException e) {
            System.out.println("❌ NPE при вызове метода: name.length() на null объекте");
        }

        // Пример 2: Unboxing null wrapper класса
        Integer count = null;
        try {
            int value = count;  // unboxing null -> NPE!
        } catch (NullPointerException e) {
            System.out.println("❌ NPE при unboxing: Integer null -> int");
        }

        // КАК ИЗБЕЖАТЬ NPE
        System.out.println("\n>>> Способы избежать NPE:");

        // Способ 1: Проверка на null
        String safeName = null;
        if (safeName != null) {
            System.out.println("Длина: " + safeName.length());
        } else {
            System.out.println("✅ Проверка на null: safeName = null, метод не вызван");
        }

        // Способ 2: Использование default значения
        Integer nullableCount = null;
        int safeValue = (nullableCount != null) ? nullableCount : 0;
        System.out.println("✅ Default значение при null: " + safeValue);
    }

    /**
     * ДЕМО 6: String и иммутабельность (Immutability)
     * String не может быть изменен после создания
     */
    private static void demo6_StringImmutability() {
        System.out.println("\n--- ДЕМО 6: String и иммутабельность ---");

        String s1 = "Initial";
        String s2 = s1;  // s2 указывает на тот же объект "Initial"

        System.out.println(">>> ДО изменения:");
        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2);

        s1 = s1 + " Modified";  // Создается НОВЫЙ объект "Initial Modified"
                                 // s1 теперь указывает на новый объект
                                 // "Initial" остается без изменений

        System.out.println("\n>>> ПОСЛЕ изменения s1:");
        System.out.println("s1 = " + s1 + " (указывает на новый объект)");
        System.out.println("s2 = " + s2 + " (НЕ ИЗМЕНИЛСЯ! указывает на старый объект 'Initial')");

        // Сравнение с изменяемым StringBuilder
        System.out.println("\n>>> Сравнение String (immutable) vs StringBuilder (mutable):");

        StringBuilder sb1 = new StringBuilder("Hello");
        StringBuilder sb2 = sb1;
        sb1.append(" World");

        System.out.println("StringBuilder sb1 = " + sb1);
        System.out.println("StringBuilder sb2 = " + sb2 + " (ИЗМЕНИЛСЯ вместе с sb1)");

        System.out.println("\n>>> ПРЕИМУЩЕСТВА иммутабельности:");
        System.out.println("✅ Thread-safe (безопасно в многопоточности)");
        System.out.println("✅ Предсказуемость (нет побочных эффектов)");
        System.out.println("✅ Безопасность (защита от случайных изменений)");
    }

    /**
     * ДЕМО 7: String Pool
     * Java оптимизирует память, помещая строковые литералы в String Pool
     */
    private static void demo7_StringPool() {
        System.out.println("\n--- ДЕМО 7: String Pool ---");

        // Строковые литералы помещаются в String Pool
        String str1 = "Java";
        String str2 = "Java";

        // Сравнение ссылок (==) проверяет, указывают ли переменные на ОДИН объект
        System.out.println("str1 == str2: " + (str1 == str2) + " (true, потому что оба указывают на ОДИН объект в String Pool)");

        // Создание через new создает НОВЫЙ объект в Heap (НЕ в String Pool)
        String str3 = new String("Java");

        System.out.println("str1 == str3: " + (str1 == str3) + " (false, потому что str3 - новый объект в Heap)");
        System.out.println("str1.equals(str3): " + str1.equals(str3) + " (true, содержимое одинаковое)");

        System.out.println("\n>>> ВАЖНО: Для сравнения String ВСЕГДА используйте .equals(), НЕ ==");
        System.out.println("- == сравнивает ССЫЛКИ (адреса объектов)");
        System.out.println("- .equals() сравнивает СОДЕРЖИМОЕ");
				
				// С Integer в диапазоне от -128 до 127 такая же история из-за Integer Pool'a
				Integer i = 23;
        Integer j = 23;
        System.out.println(i == j);
    }

    /**
     * ДЕМО 8: Default Values (значения по умолчанию)
     * Поля класса автоматически инициализируются, локальные переменные - НЕТ
     */
    private static void demo8_DefaultValues() {
        System.out.println("\n--- ДЕМО 8: Default Values (значения по умолчанию) ---");

        System.out.println(">>> Поля класса (автоматически инициализируются):");
        System.out.println("int defaultInt = " + defaultInt);
        System.out.println("long defaultLong = " + defaultLong);
        System.out.println("double defaultDouble = " + defaultDouble);
        System.out.println("boolean defaultBoolean = " + defaultBoolean);
        System.out.println("String defaultString = " + defaultString);
        System.out.println("Integer defaultInteger = " + defaultInteger);

        // Локальные переменные НЕ инициализируются автоматически
        // int localVar;  // НЕ инициализирована
        // System.out.println(localVar);  // ОШИБКА компиляции!

        System.out.println("\n>>> ВАЖНО:");
        System.out.println("✅ Поля класса: автоматически инициализируются (0, false, null)");
        System.out.println("❌ Локальные переменные: НЕ инициализируются, нужно явно присвоить значение");
    }

    /**
     * ДЕМО 9: var keyword (локальный вывод типов, Java 10+)
     * Компилятор автоматически определяет тип переменной
     */
    private static void demo9_VarKeyword() {
        System.out.println("\n--- ДЕМО 9: var keyword (Java 10+) ---");

        // Компилятор определяет тип автоматически
        var name = "John Doe";              // String
        var age = 30;                       // int
        var salary = 75000.50;              // double
        var isActive = true;                // boolean

        System.out.println("var name = \"John Doe\" -> тип: String");
        System.out.println("var age = 30 -> тип: int");
        System.out.println("var salary = 75000.50 -> тип: double");
        System.out.println("var isActive = true -> тип: boolean");

        // Используйте var когда тип очевиден
        var list = new java.util.ArrayList<String>();  // ArrayList<String>
        var map = new java.util.HashMap<String, Integer>();  // HashMap<String, Integer>

        System.out.println("\n>>> BEST PRACTICE:");
        System.out.println("✅ Используйте var когда тип очевиден из инициализации");
        System.out.println("❌ НЕ используйте var если это ухудшает читаемость");

        System.out.println("\n>>> ОГРАНИЧЕНИЯ var:");
        System.out.println("- Нужна инициализация: var x; // ОШИБКА");
        System.out.println("- Нельзя с null: var y = null; // ОШИБКА");
        System.out.println("- Только локальные переменные (не поля класса)");
    }
}