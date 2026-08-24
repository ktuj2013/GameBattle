
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Battle {
    public Hero[] coin(Hero hero1, Hero hero2) {
        Random random = new Random();
        int coin = random.nextInt(1, 3);
        if (coin == 1) {
            return new Hero[]{hero1, hero2};
        } else {
            return new Hero[]{hero2, hero1};
        }
    }

    public Turns input() {
        int input;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                input = scanner.nextInt();
                if (input > 0 && input <= 3) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again.");
            }
        }
        return switch (input) {
            case 1 -> Turns.attack;
            case 2 -> Turns.ability;
            case 3 -> Turns.passive;
            default -> null;
        };
    }


    public void updateArmorLevel(Hero hero) {
        if (hero.getArmorLevel() - 10.0 < 0) {
            hero.setArmor(0.0);
        } else {
            hero.setArmor(hero.getArmorLevel() - 10.0);
        }
    }

    public String outOfStamina(Hero hero) {
        Scanner scanner = new Scanner(System.in);
        String answer = "";
        if (hero.getIsGamerPlay()) {
            if (hero.getStaminaLevel() > 0) {
                System.out.println("You are out of stamina for this move. Wanna choose your move again or get a nap?");
                System.out.println("m - move, n - nap");
                while (true) {
                    try {
                        answer = scanner.next();
                        if (answer.equals("m") || answer.equals("n")) {
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Try again.");
                    }
                }
            }
        }
        return answer;
    }


    public void versusBot(Hero hero1, Hero hero2) {
        Random random = new Random();
        int input = random.nextInt(1, 4);
        if (hero2.getArmorLevel() == 0 && input == 2) {
            input = 3;
        }
        if (input == 1) {
            if (hero2.getStaminaLevel() < 2) {
                hero2.setStaminaLevel(hero2.getStaminaLevel() + 1);
            } else {
                hero1.setHpLevel(hero1.getHpLevel() - hero2.attack(hero1));
                double dmg = hero2.attack(hero1);
                updateArmorLevel(hero1);
                hero2.setStaminaLevel(hero2.getStaminaLevel() - 2);
                System.out.println(hero2.getName() + " атакует!" + " и наносит" + dmg
                        + " урона, оставляя тебе " + hero1.getHpLevel() + " здоровья и "
                        + hero1.getArmorLevel() + " брони!");
            }
        } else if (input == 2) {
            if (hero2.getStaminaLevel() < 3) {
                hero2.setStaminaLevel(hero2.getStaminaLevel() + 1);
            } else {
                hero1.setHpLevel(hero1.getHpLevel() - hero2.ability(hero1));
                double dmg = hero2.ability(hero1);
                updateArmorLevel(hero1);
                hero2.setStaminaLevel(hero2.getStaminaLevel() - 3);
                System.out.println(hero2.getName() + " использует свою способность, нанося " + dmg
                        + "урона, оставляя тебе " + hero1.getHpLevel() + " здоровья и "
                        + hero1.getArmorLevel() + " брони!");
                if (hero2.getName().equals("Priest")) {
                    double heal = hero2.getDamage() * 0.25;
                    System.out.println("Восстанавливает здоровье на " + heal);
                }
            }
        } else if (input == 3) {
            if (hero2.getStaminaLevel() < 1) {
                hero2.setStaminaLevel(hero2.getStaminaLevel() + 1);
            } else {
                hero2.passive();
                System.out.println(hero2.getName() + " защищается!");
            }
        }
    }

    public void battle(Hero hero1, Hero hero2) {
        Hero[] heroes = coin(hero1, hero2);
        boolean stat = false;
        while (true) {
            if (heroes[0].getHpLevel() <= 0) {
                System.out.println("Second player wins. Game over.");
                break;
            } else if (heroes[1].getHpLevel() <= 0) {
                System.out.println("First player wins. Game over.");
                break;
            }
            System.out.println("Ходит первый игрок, " + heroes[0].getName() + "!");
            System.out.println("First hero turn. 1 - attack, 2 - ability, 3 - defense.");
            turn(heroes[0], heroes[1], stat);
            System.out.println("Ходит второй игрок, " + heroes[1].getName() + "!");
            if (!heroes[1].getIsGamerPlay()) {
                versusBot(hero1, hero2);
            } else {
                System.out.println("Second turn. 1 - attack, 2 - ability, 3 - defense.");
                stat = true;
                turn(heroes[1], heroes[0], stat);
            }
        }
    }


        public void turn(Hero hero1, Hero hero2, boolean stat) {
            double hp1;
            double hp2;
            if (stat) {
                hp1 = hero2.getHpLevel();
                hp2 = hero1.getHpLevel();
            } else {
                hp1 = hero1.getHpLevel();
                hp2 = hero2.getHpLevel();
            }
            Turns turn = input();
            attack(hero1, hero2, turn);
            printState(hero1, hero2, hp1, hp2);
        }


        public void attack (Hero hero1, Hero hero2, Turns turn) {
            double hp = hpStatistic(hero2);
            if (turn == Turns.ability) {
                if (hero1.getStaminaLevel() >= 3) {
                    hero2.setHpLevel(hero2.getHpLevel() - hero1.ability(hero2));
                    updateArmorLevel(hero2);
                    hero1.setStaminaLevel(hero1.getStaminaLevel() - 3);
                    System.out.println("У противника здоровье опустилось до " + hero2.getHpLevel() + "(-"
                            + (hp - hero2.getHpLevel()) + ")" +
                            " ,а броня до " + hero2.getArmorLevel());
                } else if (hero1.getStaminaLevel() < 3) {
                    if (hero1.getStaminaLevel() == 0) {
                        hero1.setStaminaLevel(hero1.getStaminaLevel() + 1);
                    } else if (outOfStamina(hero1).equals("m")) {
                        System.out.println("Your move?");
                        turn = input();
                        attack(hero1, hero2, turn);
                    }
                }
            } else if (turn == Turns.attack) {
                if (hero1.getStaminaLevel() >= 2) {
                    hero2.setHpLevel(hero2.getHpLevel() - hero1.attack(hero2));
                    updateArmorLevel(hero2);
                    hero1.setStaminaLevel(hero1.getStaminaLevel() - 2);
                    System.out.println("У противника здоровье опустилось до " + hero2.getHpLevel() + "(-"
                                    + (hp - hero2.getHpLevel()) + ")" +
                            " ,а броня до " + hero2.getArmorLevel());
                } else if (hero1.getStaminaLevel() < 2) {
                    if (hero1.getStaminaLevel() == 0) {
                        hero1.setStaminaLevel(hero1.getStaminaLevel() + 1);
                    } else if (outOfStamina(hero1).equals("m")) {
                        System.out.println("Your move?");
                        turn = input();
                        attack(hero1, hero2, turn);
                    }
                }

            } else if (turn == Turns.passive) {
                if (hero1.getStaminaLevel() >= 1) {
                    hero1.passive();
                    System.out.println("Ваш уровень брони теперь равен " + hero1.getArmorLevel());
                } else if (hero1.getStaminaLevel() < 1) {
                    hero1.setStaminaLevel(hero1.getStaminaLevel() + 1);
                }
            }

        }

        public double hpStatistic(Hero hero) {
            return hero.getHpLevel();
        }

        public void printState(Hero hero, Hero hero2, double hp1, double hp2) {
            System.out.println("hp = " + hero.getHpLevel() + "\t\t\t|\t\t\t" + hero2.getHpLevel());
            if (hp1 > hero.getHpLevel()) {
                System.out.println("Здоровье первого героя уменьшилось на " + (hp1 - hero.getHpLevel()));
            }
            if (hp2 > hero2.getHpLevel()) {
                System.out.println("Здоровье второго героя уменьшилось на " + (hp2 - hero2.getHpLevel()));
            }
            System.out.println("armor = " + hero.getArmorLevel() + "\t\t\t|\t\t\t" + hero2.getArmorLevel());
            System.out.println("stamina = " + hero.getStaminaLevel() + "\t\t\t|\t\t\t" + hero2.getStaminaLevel());
            System.out.println("damage = " + hero.getDamage() + "\t\t\t|\t\t\t" + hero2.getDamage());
            System.out.println("=====================================");
            System.out.println();
        }
}



