public class Goblin extends Villain{
    public Goblin(String name, double hp, int stamina, double armor, double damage){
        super(name, hp, stamina, armor, damage);
    }

    @Override
    public double attack(Villain villain){
        if (getArmorLevel() - getDamage() > 0) {
            return getDamage() * 0.6;
        }
        return getDamage();
    }

    @Override
    public double ability(Villain villain){
        if (getArmorLevel() - getDamage() > 0) {
            return getDamage() * 0.8;
        }
        return getDamage();
    }

    @Override
    public String loot(Villain villain){
        String[] loot = new String[3];
        return "";
    }
}
