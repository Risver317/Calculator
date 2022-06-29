import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String num = scanner.nextLine();
        System.out.println(calc(num));
    }


    public static String calc(String input) throws Exception {
        String[] parts = input.split(" ");
        List<String> roman = Arrays.asList ("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X");
        if (parts.length != 3) {
            throw new Exception("‘ормат математической операции не удовлетвор€ет заданию - два операнда и один оператор");
        }
        int operand1 = 0;
        int operand2 = 0;
        boolean isRoman = false;
        try {

            operand1 = Integer.parseInt(parts[0]);
            operand2 = Integer.parseInt(parts[2]);
            if (operand1 < 1 || operand1 > 10 || operand2 < 1 || operand2 > 10){
                throw new Exception(" ");
            }
        } catch (Exception e) {
            if (roman.containsAll(Arrays.asList(parts[0], parts[2]))){
                operand1 = roman.indexOf(parts[0]) + 1;
                operand2 = roman.indexOf(parts[2]) + 1;
                isRoman = true;
                } else {
                throw new Exception("¬вел не правильные числа");
            }

            }

        int result = switch (parts[1]) {
            case ("+") -> operand1 + operand2;
            case ("-") -> operand1 - operand2;
            case ("*") -> operand1 * operand2;
            case ("/") -> operand1 / operand2;
            default -> throw new Exception("¬вели не корректный знак");
        };
        if (isRoman){
            if(result < 1){
                throw new Exception("B римской системе нет отрицательных чисел");
            }
            return RomanNumber.toRoman(result);
        }

        return String.valueOf(result);
    }
}

class RomanNumber {

    private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();

    static {

        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

    }

    public final static String toRoman(int number) {
        int l =  map.floorKey(number);
        if ( number == l ) {
            return map.get(number);
        }
        return map.get(l) + toRoman(number-l);
    }
}
