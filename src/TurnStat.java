public class TurnStat {
    private double changeHp;
    private double changeArmour;
    private int changeStamina;

    public TurnStat(double changeHp, double changeArmour, int changeStamina) {
        this.changeHp = changeHp;
        this.changeArmour = changeArmour;
        this.changeStamina = changeStamina;
    }

    public double getChangeHp(double hp1, double hp2) {
        changeHp = hp1 - hp2;
        return changeHp;
    }

    public double getChangeArmour(Hero hero) {
        changeArmour = hero.getArmorLevel();
        return changeArmour;
    }

    public int getChangeStamina(Hero hero) {
        changeStamina = hero.getStaminaLevel();
        return changeStamina;
    }
}
