/**
 Автор: Денис Кивилёв
 Online-школа: Backend-pro.ru
 
 Описание: ДЕМО-КОД ДЛЯ ЛЕКЦИИ 3: СТРОКИ, МАССИВЫ
 
 Этот класс демонстрирует ключевые концепции работы со строками в Java:
 - String Pool и создание строк
 - Иммутабельность строк
 - Основные методы String
 - Сравнение строк (equals vs ==)
 - Конкатенация строк (String vs StringBuilder)
 - Text Blocks (Java 15+)
 - Работа с null и NullPointerException
 - Форматирование строк
 - Разделение строк (split) и экранирование regex
 
*/

public class StringDemo {
    public static void main(String[] args) {
        // String Pool и создание строк
        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = new String("Hello");

        System.out.println("s1 == s2: " + (s1 == s2)); // true, одна ссылка из пула
        System.out.println("s1 == s3: " + (s1 == s3)); // false, разные объекты
        System.out.println("s1.equals(s3): " + s1.equals(s3)); // true, содержимое одинаковое

        // Иммутабельность строк
        String original = "Hello";
        String modified = original.concat(" World");
        System.out.println("original: " + original); // Hello
        System.out.println("modified: " + modified); // Hello World

        // Основные методы String
        String text = "Java Programming";

        System.out.println("length: " + text.length());
        System.out.println("isEmpty: " + text.isEmpty());
        System.out.println("charAt(0): " + text.charAt(0));
        System.out.println("contains 'Program': " + text.contains("Program"));
        System.out.println("startsWith 'Java': " + text.startsWith("Java"));
        System.out.println("endsWith 'ing': " + text.endsWith("ing"));
        System.out.println("indexOf 'Pro': " + text.indexOf("Pro"));
        System.out.println("substring(5): " + text.substring(5));
        System.out.println("substring(5, 12): " + text.substring(5, 12));
        System.out.println("toUpperCase: " + text.toUpperCase());
        System.out.println("toLowerCase: " + text.toLowerCase());

        // Удаление пробелов
        String spaced = "  Hello World  ";
        System.out.println("trim(): '" + spaced.trim() + "'");
        System.out.println("strip(): '" + spaced.strip() + "'");

        // Замена символов и строк
        System.out.println("replace 'a' with 'o': " + text.replace('a', 'o'));
        System.out.println("replace 'Java' with 'Python': " + text.replace("Java", "Python"));
        System.out.println("replaceAll whitespace with '-': " + text.replaceAll("\\s+", "-"));

        // Разделение строки (split)
        String csv = "John,Doe,30,Developer";
        String[] parts = csv.split(",");
        System.out.print("Split CSV: ");
        for (String part : parts) {
            System.out.print(part + " | ");
        }
        System.out.println();

        // Конкатенация в цикле: плохой и хороший пример
        String badConcat = "";
        for (int i = 0; i < 5; i++) {
            badConcat += i; // создает много объектов
        }
        System.out.println("Bad concat result: " + badConcat);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(i);
        }
        System.out.println("StringBuilder result: " + sb.toString());

        // Форматирование строк
        String name = "John";
        int age = 30;
        String formatted = String.format("Name: %s, Age: %d", name, age);
        System.out.println("Formatted string: " + formatted);

        // Сравнение строк
        String status = "COMPLETED";
        System.out.println("equals: " + "COMPLETED".equals(status));
        System.out.println("equalsIgnoreCase: " + "completed".equalsIgnoreCase(status));
        System.out.println("compareTo (A vs B): " + "A".compareTo("B"));

        // Text Blocks (Java 15+)
        String json = """
                {
                    "name": "John",
                    "age": 30,
                    "city": "Moscow"
                }
                """;
        System.out.println("Text Block JSON:\n" + json);

        // Null и проверка
        String nullable = null;
        if (nullable != null) {
            System.out.println(nullable.toUpperCase());
        } else {
            System.out.println("nullable is null, avoid NullPointerException");
        }
    }
}