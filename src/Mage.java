import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Mage extends Hero {
    public Mage(String name, double hp, int stamina, double armor, double damage, boolean isGamerPlay) {
        super(name, hp, stamina, armor, damage, isGamerPlay);
    }

    @Override
    public void ability(Hero hero) {
        double fireball = getDamage() * 2;
        if (hero.getArmorLevel() - fireball > 0) {
            hero.setHpLevel(hero.getHpLevel() - getDamage());
        }
        hero.setHpLevel(hero.getHpLevel() - fireball);
    }

    @Override
    public void passive() {
        setArmor(getArmorLevel() + 5 * getPassiveStamina());
        setStaminaLevel(getStaminaLevel() - getPassiveStamina());
    }

}
