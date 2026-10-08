import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Rogue extends Hero {
    public Rogue(String name, double hp, int stamina, double armor, double damage, boolean isGamerPlay) {
        super(name, hp, stamina, armor, damage, isGamerPlay);
    }

    @Override
    public boolean isCellVisible(Position position) {
        return false;
    }


    @Override
    public void ability(Hero hero) {
        hero.setHpLevel(hero.getHpLevel() - getDamage());
        setStaminaLevel(getStaminaLevel() - 3);
    }

    @Override
    public void passive() {
        setArmor(getArmorLevel() + 7 * getPassiveStamina());
        setStaminaLevel(getStaminaLevel() - getPassiveStamina());
    }

}
