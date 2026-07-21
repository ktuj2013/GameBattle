import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Rogue extends Hero {
    public Rogue(String name, double hp, int stamina, double armor, double damage, boolean isGamerPlay) {
        super(name, hp, stamina, armor, damage, isGamerPlay);
    }

    @Override
    public double attack(Hero hero) {
        if (getArmorLevel() - getDamage() > 0) {
            return getDamage() * 0.5;
        }
        return getDamage();
    }

    @Override
    public double ability(Hero hero) {
        return getDamage();
    }

    @Override
    public void passive() {
        int input;
        if (!this.getIsGamerPlay()) {
            Random inp = new Random();
            if (this.getStaminaLevel() == 1) {
                input = 1;
            } else {
                input = inp.nextInt(1, this.getStaminaLevel());
            }
        } else {
            System.out.println("Сколько стамины вы готовы потратить на защиту?");
            while (true) {
                Scanner sc = new Scanner(System.in);
                try {
                    input = sc.nextInt();
                    if (input <= getStaminaLevel() && input > 0) {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Нет такого количества стамины.");
                }
            }
        }
        setArmor(getArmorLevel() + 7 * input);
        setStaminaLevel(getStaminaLevel() - input);
    }
}
