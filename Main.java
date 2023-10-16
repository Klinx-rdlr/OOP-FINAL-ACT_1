
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner getInput = new Scanner(System.in);
        boolean checker = true;
        while(checker) {
            String option, sourceBase, sourceBase_2, sourceValue;
            System.out.println("Enter Source Base(B8, B16)    :");
            sourceBase = getInput.nextLine();

            int Base;
            if (sourceBase.equals("B8")) {
                Base = 8;
            } else if (sourceBase.equals("B16")) {
                Base = 16;
            } else {
                System.out.println("Invalid source base.");
                continue;
            }

            System.out.println("Enter Source Value:     ");
            sourceValue = getInput.nextLine();
            Decimal num = new Decimal(sourceValue);

            int decimalValue = num.toDecimal(Base);
            System.out.println("Decimal Conversion Result: " + decimalValue);


            System.out.println("Enter Convert-to-base (B12, B14):    ");
            sourceBase_2 = getInput.nextLine();

            int targetBase;
            if (sourceBase_2.equals("B12")) {
                targetBase = 12;
            } else if (sourceBase_2.equals("B14")) {
                targetBase = 14;
            } else {
                System.out.println("Invalid target base.");
                continue;
            }

            BaseConverter baseConverter = new BaseConverter(sourceValue, targetBase);

            String targetValue = baseConverter.convertToTargetBase();
            System.out.println("Target Base Conversion Result: " + targetValue);

            System.out.println("Try Again? [Y/N]");
            if(getInput.nextLine().equals("N")){
                checker = false;
            }
        }
    }
}

class Decimal {
    protected String value;

    public Decimal(String value) {
        this.value = value;
    }

    public int toDecimal(int sourceBase) {
        int decimalValue = 0;
        int power = 0;
        int length = value.length();

        for (int i = length - 1; i >= 0; i--) {
            char digit = value.charAt(i);

            if (Character.isLetter(digit)) {
                int num = (int) (digit - 'A') + 10;
                decimalValue += num * Math.pow(sourceBase, power);
            } else {
                int num = Integer.parseInt(String.valueOf(digit));
                decimalValue += num * Math.pow(sourceBase, power);
            }

            power++;
        }

        return decimalValue;
    }
}

class BaseConverter extends Decimal {
    private int targetBase;

    public BaseConverter(String value, int targetBase) {
        super(value);
        this.targetBase = targetBase;
    }

    public String convertToTargetBase() {
        int decimalValue = toDecimal(targetBase);
        StringBuilder result = new StringBuilder();

        while (decimalValue > 0) {
            int remainder = decimalValue % targetBase;
            char digit;

            if (remainder >= 10) {
                digit = (char) ('A' + remainder - 10);
            } else {
                digit = (char) ('0' + remainder);
            }

            result.insert(0, digit);
            decimalValue /= targetBase;
        }
        return result.toString();
    }
}
