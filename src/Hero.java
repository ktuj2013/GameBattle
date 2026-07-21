public abstract class Hero implements Damageable{
    private String name;
    private double hp;
    private int stamina;
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
