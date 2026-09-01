import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Priest extends Hero {
    public Priest(String name, double hp, int stamina, double armor, double damage, boolean isGamerPlay) {
        super(name, hp, stamina, armor, damage, isGamerPlay);
    }


    @Override
    public void ability(Hero hero) {
        if (hero.getArmorLevel() - getDamage() > 0) {
            setHpLevel(getHpLevel() + (getDamage() * 0.25));
            hero.setHpLevel(hero.getHpLevel() - getDamage() * 0.5);
        } else {
            setHpLevel(getHpLevel() + (getDamage() * 0.5));
        }
        hero.setHpLevel(hero.getHpLevel() - getDamage());
        setStaminaLevel(getStaminaLevel() - 3);
    }

    @Override
    public void passive() {
        setArmor(getArmorLevel() + 4 * getPassiveStamina());
        setHpLevel(getHpLevel() + getPassiveStamina());
        setStaminaLevel(getStaminaLevel() - getPassiveStamina());
    }
}
