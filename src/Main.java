import java.util.Scanner;

public class Main
{

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char c;
        do {

            Mastermind game = new Mastermind();

            game.Start();

            game.Mid();
            game.End();

            System.out.println("Play again? (Y/N)");
            c = in.next().charAt(0);

        } while (c == 'y' || c == 'Y');


    }
}