import java.io.UnsupportedEncodingException;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String[] questions = {"1+1", "2*2", "1+2+3", "6/2", "11+23", "11.1+23", "1+1*3", "(11.5+15.4)+10.1", "23-(29.3-12.5)", "10 - (2 + 3 * (7 - 5))"};

        for (String question : questions) {
            Double value = calculate(question);
            System.out.println("QUESTION: " + question + " = ANSWER : " + value);
        }
    }

    /*
    Enter sum into calculate function. Replace all the spaces in to no spaces.
    */
    public static double calculate(String sum) {
        if (sum == null || sum.length() == 0) {
            return 0.00;
        }
        return calc(sum.replace(" ", ""));
    }

    public static Double calc(String expression) {
        String[] stringArray = new String[]{expression};
//        System.out.println("Array in The List :" + Arrays.toString(stringArray));
        double leftVal = getNextCal(stringArray);
        expression = stringArray[0];
//        System.out.println(" Expression:" + expression);
        if (expression.length() == 0) {
            return leftVal;
        }
        char operator = expression.charAt(0);
        expression = expression.substring(1);
        while (operator == '*' || operator == '/') {
            stringArray[0] = expression;
            double rightVal = getNextCal(stringArray);
            expression = stringArray[0];
            if (operator == '*') {
                leftVal = leftVal * rightVal;
            } else {
                leftVal = leftVal / rightVal;
            }
            if (expression.length() > 0) {
                operator = expression.charAt(0);
                expression = expression.substring(1);
            } else {
                return leftVal;
            }
        }


        if (operator == '+') {
            return leftVal + calc(expression);
        } else {
            return leftVal - calc(expression);
        }

    }


    /*handle the bracket */
    private static double getNextCal(String[] exp) {
        double res;
        if (exp[0].startsWith("(")) {
            int open = 1;
            int i = 1;
            while (open != 0) {
                if (exp[0].charAt(i) == '(') {
                    open++;
                } else if (exp[0].charAt(i) == ')') {
                    open--;
                }
                i++;
            }
//            System.err.println(exp[0].substring(1, i - 1));

            res = calc(exp[0].substring(1, i - 1));
            exp[0] = exp[0].substring(i);
        } else {
            int i = 1;
//            System.out.println("Length of the string : " + exp[0].length());
            if (exp[0].charAt(0) == '-') {
                i++;
            }
            while (exp[0].length() > i && checkIsNo((int) exp[0].charAt(i))) {
                i++;
            }
            res = Double.parseDouble(exp[0].substring(0, i));
            exp[0] = exp[0].substring(i);
//            System.out.println("If exp Start With No " + res);

        }
        return res;
    }

    /*check the string is no */
    private static boolean checkIsNo(int c) {
        int zero = (int) '0';
        int nine = (int) '9';
        return (c >= zero && c <= nine) || c == '.';
    }

}