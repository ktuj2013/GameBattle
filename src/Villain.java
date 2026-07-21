public abstract class Villain implements Adventerous{
    private String name;
    private double hp;
    private int stamina;
    private double armor;
    private double damage;

    public Villain(String name, double hp, int stamina, double armor, double damage) {
        this.name = name;
        this.hp = hp;
        this.stamina = stamina;
        this.armor = armor;
        this.damage = damage;
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
}
