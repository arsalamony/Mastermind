import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Mastermind
{
    enum enColor {  white, black, red, green, blue, yellow, pink, orange}; // 1,2,3 ... 8

    private int codeLength = 4;

    private int maxAttempts = 10;

    private int[] secretCode;

    private int[][] code;

    private int[][] answers;

    private boolean Win;

    public Mastermind()
    {
        code = new int[maxAttempts][codeLength];

        answers = new int[maxAttempts][codeLength];

        secretCode = new int[codeLength];

        Win = false;

        for (int i = 0; i < codeLength; i++)
        {
            secretCode[i] = getRandomNumber(1, 8);
        }

    }

    public void Start()
    {
        System.out.println("Hello In MasterMind");
        System.out.println("Try Guess Secret Code This Combine From Six Colors");
        System.out.println("white 1 black 2 red 3 green 4 blue 5 yellow 6 pink 7 orange 8");
        System.out.println("Set Space Between Every Two Numbers");
    }

    public void Mid()
    {
        Scanner in = new Scanner(System.in);

        for (int i = 0; i < maxAttempts; i++)
        {
            PrintTryNum(i+1);

            for (int j = 0; j < codeLength; j++)
                code[i][j] = in.nextInt();

            SetAnswers(code , i);
            EncryptSubAnswers(answers[i]);
            PrintSubAnswer(answers, i);
            Win = Winner(answers, i);
            if(Win) break;
        }
    }

    public void End()
    {
        if(Win)
        {
            System.out.println("Winner Winner Chicken Dinner");
            return;
        }

        System.out.println("Lost");

        PrintResult();
    }

    private void PrintTryNum(int i)
    {
        if(i == 1) System.out.print(i + "st try");
        else if (i == 2) System.out.print(i + "nd Try");
        else if (i == 3) System.out.print(i + "rd Try");
        else System.out.print(i + "th Try : ");
    }

    private void SetAnswers(int Code[][], int rowNumber)
    {
        int[] temp = Arrays.copyOf(secretCode, secretCode.length);

        for (int i = 0; i < codeLength; i++)
        {
            int x = Code[rowNumber][i];

            for (int j = 0; j < temp.length; j++)
            {
                if(x == temp[j])
                {
                    if(j == i)
                    {
                        answers[rowNumber][i] = 2;
                        temp[j] = -1;
                        break;
                    }
                    else
                    {
                        answers[rowNumber][i] = 1;
                        temp[j] = -1;
                        break;
                    }
                }
            }
        }
    }

    private void EncryptSubAnswers(int[] arr)
    {
        int temp = arr[3];
        arr[3] = arr[0];
        arr[0] = temp;

        temp = arr[2];
        arr[2] = arr[1];
        arr[1] = temp;
    }

    private void PrintSubAnswer(int Code[][], int rowNumber)
    {
        System.out.print("          ||  ");
        for (int i = 0; i < codeLength; i++)
        {
            System.out.print(Code[rowNumber][i] + "  ");
        }
        System.out.println();
    }

    private boolean Winner(int Code[][], int rowNumber) // when subAnswer be black(2)
    {
        for (int i = 0; i < codeLength; i++)
        {
            if(Code[rowNumber][i] != 2) return false;
        }

        return true;
    }

    private void PrintResult()
    {
        System.out.println("Answers :");
        for (int e : secretCode)
        {
            System.out.print("  " + e);
        }
    }

    private int getRandomNumber(int min, int max)
    {
        Random random = new Random();

        int range = max - min + 1;

        return random.nextInt(range) + min;
    }
}
