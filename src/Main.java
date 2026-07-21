import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Battle battle1 = new Battle();
        /*Hero[] player1 = {new Mage("Mage", 70.0, 12, 25.0, 14.0, true),
                new Rogue("Rogue", 80.0, 14, 30.0, 15.0, true),
                new Priest("Priest", 90.0, 12, 35.0, 12.0, true),
                new Warrior("Warrior", 100.0, 16, 40.0, 16.0, true)
        };

        Hero[] player2 = {new Mage("Mage", 70.0, 12, 25.0, 14.0, true),
                new Rogue("Rogue", 80.0, 14, 30.0, 15.0, true),
                new Priest("Priest", 90.0, 12, 35.0, 12.0, true),
                new Warrior("Warrior", 100.0, 16, 40.0, 16.0, true)
        };*/



        for (int i = 0; i < Players.values().length; i++) {
            System.out.println((i + 1) + ")" + Players.values()[i]);
        }
        System.out.println("Welcome to the game of battle, Player 1! Choose your hero!");
        Hero firstHero = chooseHero();
        String pvpOrPve;
        while (true) {
            try {
                String f = input("PVP or PVE?");
                if ((f != null && f.equals("PVP")) || (f != null && f.equals("PVE"))) {
                    pvpOrPve = f;
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Try again!");
            }
        }

        Hero secondHero = null;
        if (pvpOrPve.equals("PVE")) {
            String adv;
            while (true) {
                try {
                    String f = input("Если хочешь на тренировку, нажми 1, если в подземелье, то 2?");
                    if ((f != null && f.equals("1")) || (f != null && f.equals("2"))) {
                        adv = f;
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Try again!");
                }
            }
            if (adv.equals("2")) {

            }
            secondHero = randomHero();
            System.out.println(secondHero);
            secondHero.setIsGamerPlay(false);
        } else {
            for (int i = 0; i < Players.values().length; i++) {
                System.out.println((i + 1) + ")" + Players.values()[i]);
            }
            System.out.println("Welcome to the game of battle, Player 2! Choose your hero!");
            secondHero = chooseHero();
        }

        //Hero[] heroes = battle1.coin(firstHero, secondHero);
        battle1.battle(firstHero, secondHero);
    }

    public static Hero createPlayer(Players player) {
        if (player == Players.Mage) {
            return new Mage("Mage", 70.0, 12, 25.0, 14.0, true);
        }
        if (player == Players.Priest) {
            return new Priest("Priest", 90.0, 12, 35.0, 12.0, true);
        }
        if (player == Players.Rogue) {
            return new Rogue("Rogue", 80.0, 14, 30.0, 15.0, true);
        }
        if (player == Players.Warrior) {
            return new Warrior("Warrior", 100.0, 16, 40.0, 16.0, true);
        }
        return null;
    }

    public static Hero chooseHero() {
        Scanner scanner = new Scanner(System.in);
        int numberHero = scanner.nextInt();
        while (numberHero < 1 || numberHero > Players.values().length) {
            System.out.println("Такого героя нет, попробуй снова!");
            numberHero = scanner.nextInt();
        }
        return createPlayer(Players.values()[numberHero - 1]);
    }

    public static Hero randomHero() {
        Random random = new Random();
        int index = random.nextInt(Players.values().length);
        return createPlayer(Players.values()[index - 1]);
    }

    public static String input(String label) {
        System.out.println(label);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }
}

