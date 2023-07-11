package calc;

import java.util.HashMap;
import java.util.Map;

class calculator {
    public static void main(String[] args) {
        System.out.println(calc("VI / II"));

    }
    public static String calc(String input) {
        String[] parts = input.split(" ");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат выражения");
        }

        String firstOperand = parts[0];
        String operator = parts[1];
        String secondOperand = parts[2];

        int a;
        int b;
        boolean isRoman = false;

        try {
            a = Integer.parseInt(firstOperand);
            b = Integer.parseInt(secondOperand);
        } catch (NumberFormatException e) {
            a = RomanToArabic(firstOperand);
            b = RomanToArabic(secondOperand);
            isRoman = true;
        }

        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new IllegalArgumentException("Числа должны быть от 1 до 10 включительно");
        }

        int result;
        switch (operator) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                throw new IllegalArgumentException("Неверный оператор");
        }

        if (isRoman) {
            if (result < 1) {
                throw new IllegalArgumentException("Результат не может быть отрицательным или нулевым для римских чисел");
            }
            return ArabicToRoman(result);
        } else {
            return String.valueOf(result);
        }
    }

    private static int RomanToArabic(String roman) {
        Map<Character, Integer> romanNumerals = new HashMap<>();
        romanNumerals.put('I', 1);
        romanNumerals.put('V', 5);
        romanNumerals.put('X', 10);
        romanNumerals.put('L', 50);
        romanNumerals.put('C', 100);
        romanNumerals.put('D', 500);
        romanNumerals.put('M', 1000);

        int result = 0;
        int prevValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            int value = romanNumerals.get(roman.charAt(i));
            if (value < prevValue) {
                result -= value;
            } else {
                result += value;
                prevValue = value;
            }
        }

        return result;
    }

    private static String ArabicToRoman(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("Невозможно преобразовать отрицательное число в римскую систему");
        }

        StringBuilder roman = new StringBuilder();
        int[] arabicValues = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanSymbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        int remaining = number;

        for (int i = 0; i < arabicValues.length; i++) {
            int value = arabicValues[i];
            String symbol = romanSymbols[i];

            while (remaining >= value) {
                roman.append(symbol);
                remaining -= value;
            }
        }

        return roman.toString();
    }

}
