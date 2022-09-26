import java.util.*;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        Scanner in = new Scanner(System.in);
        String expression;

        System.out.println("Введите математическое выражение: ");
        expression = in.nextLine();

        try {
            System.out.println(calc(expression));
        } catch (Exception e)
        {
            System.out.println("throws Exception");
        }

        //----------
    }

    public static String calc(String input)
    {
        String otvet = "";
        byte  a, b;
        byte proverkaGlavnay = 0;

        ArrayList<String> array = new  ArrayList<String>(Arrays.asList(input.split(" "))); // разбитие на число, знак, число

        if (array.size() != 3)
        {
            throw new RuntimeException("неверная строка");
        }

        proverkaGlavnay = (byte) (proverkaArab(array.get(0)) + proverkaArab(array.get(2)));

        switch (proverkaGlavnay)
        {
            case(0):
                // римские

                a = Byte.parseByte(transformacia(array.get(0)));
                b = Byte.parseByte(transformacia(array.get(2)));
                otvet = znak(a, array.get(1), b);
                if (Byte.parseByte(otvet) < 0)
                {
                    throw new RuntimeException("неверная строка");
                } else
                {
                    otvet = transformaciaRim(otvet);
                }
                break;
            case(2):
                // арабские

                a = Byte.parseByte(array.get(0));
                b = Byte.parseByte(array.get(2));
                otvet = znak(a, array.get(1), b);
                break;
            default:
                throw new RuntimeException("неверная строка");
        }

        return otvet;
    }

    public static String transformacia(String a)
    {
        String chislo;

        switch(a)
        {
            case  ("I"):
                chislo = "1";
                break;
            case ("II"):
                chislo = "2";
                break;
            case ("III"):
                chislo = "3";
                break;
            case ("IV"):
                chislo = "4";
                break;
            case ("V"):
                chislo = "5";
                break;
            case ("VI"):
                chislo = "6";
                break;
            case ("VII"):
                chislo = "7";
                break;
            case ("VIII"):
                chislo = "8";
                break;
            case ("IX"):
                chislo = "9";
                break;
            case ("X"):
                chislo = "10";
                break;
            default:
                throw new RuntimeException("неверная строка");
        }
        return chislo;
    }

    public static String znak(int chislo1, String a,int chislo2)
    {
        String x;

        switch(a)
        {
            case ("-"):
                x = String.valueOf(chislo1 - chislo2);
                break;
            case ("+"):
                x = String.valueOf(chislo1 + chislo2);
                break;
            case ("*"):
                x = String.valueOf(chislo1 * chislo2);
                break;
            case ("/"):
                x = String.valueOf(chislo1 / chislo2);
                break;
            default:
                throw new RuntimeException("неверная строка");
        }
        return x;
    }

    public static byte proverkaArab(String a)
    {
        byte cifra = 0;

        String [] arab = new String[]{"10","1","2","3","4","5","6","7","8","9"};

        for(int i = 0; i < 10; i ++)
        {

            if(a.equals(arab[i]))
            {
                cifra = (byte) (cifra + 1);
            }
        }

        return cifra;
    }

    public static String transformaciaRim(String a)
    {
        String rim = "";
        byte chislo = Byte.parseByte(a);
        String [] rome = new String[]{"I","IV","V","IX","X","XL","L","XC","C"};
        int[] b = new int[]{1,4,5,9,10,40,50,90,100};
        // трансформировать арабские в римские числа
        for (int i = rome.length - 1; i >= 0; i--) {
            while (chislo >= b[i]) {
                rim = rim + (rome[i]);
                chislo -= b[i];
            }
        }
        return rim;
    }

}