import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public abstract class Hero implements Damageable{
    private String name;
    private double hp;
    private int stamina;
    public int passiveStamina = 0;          //пассивка на стамину
    private double armor;
    private double damage;
    private boolean isGamerPlay;


    public Hero(String name, double hp, int stamina, double armor, double damage, boolean isGamerPlay) {
        this.name = name;
        this.hp = hp;
        this.stamina = stamina;
        this.armor = armor;
        this.damage = damage;
        this.isGamerPlay = isGamerPlay;
    }

    public void setPassiveStamina(int stamina) {
        this.passiveStamina = stamina;
    }

    public int getPassiveStamina() {
        return passiveStamina;
    }

    public void setIsGamerPlay(boolean isGamerPlay) {
        this.isGamerPlay = isGamerPlay;
    }

    public boolean getIsGamerPlay() {
        return isGamerPlay;
    }

    public void setHpLevel(double hp) {
        this.hp = hp;
    }

    public void setStaminaLevel(int stamina) {
        this.stamina = stamina;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public void setArmor(double armor) {
        this.armor = armor;
    }

    public double getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public double getHpLevel() {
        return hp;
    }

    public int getStaminaLevel() {
        return stamina;
    }

    public double getArmorLevel() {
        return armor;
    }

    public void attack(Hero hero) {
        if (hero.getArmorLevel() - getDamage() > 0) {
            hero.setHpLevel(hero.getHpLevel() - getDamage() * 0.5);
        }
        hero.setHpLevel(hero.getHpLevel() - getDamage());
        setStaminaLevel(getStaminaLevel() - 2);
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ", stamina=" + stamina +
                ", armor=" + armor +
                ", damage=" + damage +
                '}';
    }


}
