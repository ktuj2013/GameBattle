import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Warrior extends Hero {
    public Warrior(String name, double hp, int stamina, double armor, double damage, boolean isGamerPlay) {
        super(name, hp, stamina, armor, damage, isGamerPlay);
        this.setPosition(new Position(0, 0));
    }

    @Override
    public void ability(Hero hero) {
        hero.setArmor(hero.getArmorLevel() - 10);
        if (hero.getArmorLevel() - getDamage() > 0) {
            hero.setHpLevel(hero.getHpLevel() - (getDamage() * 0.5));
        } else {
            hero.setHpLevel(hero.getHpLevel() - getDamage());
        }
        setStaminaLevel(getStaminaLevel() - 3);
    }

    @Override
    public void passive() {
        setArmor(getArmorLevel() + 4 * getPassiveStamina());
        setDamage(getDamage() + getPassiveStamina());
        setStaminaLevel(getStaminaLevel() - getPassiveStamina());
    }

    @Override
    public boolean isCellVisible(Position position) {
        return Math.abs(this.getPosition().getX() - position.getX()) <= 5
        && Math.abs(this.getPosition().getY() - position.getY()) <= 5;
    }
}
