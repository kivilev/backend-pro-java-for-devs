/**
 Автор: Денис Кивилёв
 Online-школа: Backend-pro.ru
 
 Описание: ДЕМО-КОД ДЛЯ ЛЕКЦИИ 3: СТРОКИ, МАССИВЫ
 
 Этот класс демонстрирует ключевые концепции работы с массивами в Java:
 - Объявление и инициализация массивов
 - Доступ к элементам и длина массива
 - Обход массивов (for и for-each)
 - Двумерные и зубчатые массивы
 - Класс Arrays и его методы
 - Преобразование массива в List
 - Varargs
 - Default values
 - Stack vs Heap память для массивов
 
*/


import java.util.Arrays;
import java.util.List;

public class ArrayDemo {
    public static void main(String[] args) {
        // Объявление и инициализация массивов
        int[] numbers1 = new int[5]; // по умолчанию все 0
        numbers1[0] = 10;
        numbers1[1] = 20;

        int[] numbers2 = {1, 2, 3, 4, 5};
        int[] numbers3 = new int[]{6, 7, 8, 9, 10};

        // Доступ к элементам и длина
        System.out.println("numbers2[2]: " + numbers2[2]);
        System.out.println("numbers2 length: " + numbers2.length);

        // Изменение элемента
        numbers2[2] = 100;
        System.out.println("After change: " + Arrays.toString(numbers2));

        // Обход массива классическим for
        System.out.println("Classic for loop:");
        for (int i = 0; i < numbers2.length; i++) {
            System.out.println("numbers2[" + i + "] = " + numbers2[i]);
        }

        // Обход массива for-each
        System.out.println("For-each loop:");
        for (int num : numbers2) {
            System.out.println(num);
        }

        // Двумерные массивы
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println("Element matrix[1][2]: " + matrix[1][2]);

        // Обход двумерного массива вложенными циклами
        System.out.println("Nested loops for 2D array:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        // Обход двумерного массива for-each
        System.out.println("For-each loops for 2D array:");
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }

        // Зубчатые массивы (Jagged arrays)
        int[][] jagged = new int[3][];
        jagged[0] = new int[]{1, 2};
        jagged[1] = new int[]{3, 4, 5, 6};
        jagged[2] = new int[]{7, 8, 9};

        System.out.println("Jagged array:");
        for (int[] row : jagged) {
            System.out.println(Arrays.toString(row));
        }

        // Класс Arrays: сортировка, копирование, сравнение
        int[] arr = {5, 2, 8, 1, 9};
        Arrays.sort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));

        int[] copy = Arrays.copyOf(arr, arr.length);
        System.out.println("Copied array: " + Arrays.toString(copy));

        boolean equals = Arrays.equals(arr, copy);
        System.out.println("Arrays.equals: " + equals);

        // Преобразование массива в List
        String[] languages = {"Java", "Python", "JavaScript"};
        List<String> list = Arrays.asList(languages);
        System.out.println("List from array: " + list);

        // Varargs пример
        System.out.println("Sum varargs: " + sum(1, 2, 3, 4, 5));
        System.out.println("Sum varargs empty: " + sum());

    }

    // Метод с varargs
    public static int sum(int... numbers) {
        int total = 0;
        for (int n : numbers) {
            total += n;
        }
        return total;
    }
}