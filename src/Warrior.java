import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Warrior extends Hero {
    public Warrior(String name, double hp, int stamina, double armor, double damage, boolean isGamerPlay) {
        super(name, hp, stamina, armor, damage, isGamerPlay);
    }

    @Override
    public void ability(Hero hero) {
        hero.setArmor(hero.getArmorLevel() - 10);
        if (getArmorLevel() - getDamage() > 0) {
            setHpLevel(hero.getHpLevel() - (getDamage() * 0.5));
        }
        hero.setHpLevel(hero.getHpLevel() - getDamage());
        setStaminaLevel(getStaminaLevel() - 3);
    }

    @Override
    public void passive() {
        setArmor(getArmorLevel() + 4 * getPassiveStamina());
        setDamage(getDamage() + getPassiveStamina());
        setStaminaLevel(getStaminaLevel() - getPassiveStamina());
    }
}
