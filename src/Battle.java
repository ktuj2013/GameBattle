
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

    public int input() {
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
        return input;
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


    public void versusBot(Hero hero1, Hero hero2, Random random) {
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
        Random random = new Random();
        String str = "";
        while (true) {
            if (heroes[0].getHpLevel() <= 0) {
                System.out.println("Second player wins. Game over.");
                break;
            } else if (heroes[1].getHpLevel() <= 0) {
                System.out.println("First player wins. Game over.");
                break;
            }
            for (int i = 0; i < heroes.length; i++) {
                if (i == 0) {
                    str = "First";
                } else if (i == 1) {
                    str = "Second";
                }

                if (!heroes[i].getIsGamerPlay()) {
                    versusBot(hero1, heroes[i], random);
                } else {
                    printState(heroes[0], str, heroes[1]);
                    if (str.equals("First")) {
                        System.out.println("Ходит первый игрок, " + heroes[0].getName() + "!");
                    } else {
                        System.out.println("Ходит второй игрок, " + heroes[1].getName() + "!");
                    }
                    System.out.println(str + " hero turn. 1 - attack, 2 - ability, 3 - defense.");
                    int change = input();
                    if (str.equals("First")) {
                        turn(hero1, hero2, change);
                    } else {
                        turn(hero2, hero1, change);
                    }
                }
            }
        }
    }



        public void turn (Hero hero1, Hero hero2, int input) {
            double hp = hero2.getHpLevel();
            if (input == 2) {
                if (hero1.getStaminaLevel() >= 3) {
                    hero2.setHpLevel(hero2.getHpLevel() - hero1.ability(hero2));
                    updateArmorLevel(hero2);
                    hero1.setStaminaLevel(hero1.getStaminaLevel() - 3);
                    System.out.println("У противника здоровье опустилось до " + hero2.getHpLevel() + "(-"
                            + (hp - hero2.getHpLevel()) + ")" +
                            " ,а броня до " + hero2.getArmorLevel());
                } else if (hero1.getStaminaLevel() < 3) {
                    if (outOfStamina(hero1).equals("n") || hero1.getStaminaLevel() == 0) {
                        hero1.setStaminaLevel(hero1.getStaminaLevel() + 1);
                    } else if (outOfStamina(hero1).equals("m")) {
                        System.out.println("Your move?");
                        input = input();
                        turn(hero1, hero2, input);
                    }
                }
            } else if (input == 1) {
                if (hero1.getStaminaLevel() >= 2) {
                    hero2.setHpLevel(hero2.getHpLevel() - hero1.attack(hero2));
                    updateArmorLevel(hero2);
                    hero1.setStaminaLevel(hero1.getStaminaLevel() - 2);
                    System.out.println("У противника здоровье опустилось до " + hero2.getHpLevel() + "(-"
                                    + (hp - hero2.getHpLevel()) + ")" +
                            " ,а броня до " + hero2.getArmorLevel());
                } else if (hero1.getStaminaLevel() < 2) {
                    if (outOfStamina(hero1).equals("n") || hero1.getStaminaLevel() == 0) {
                        hero1.setStaminaLevel(hero1.getStaminaLevel() + 1);
                    } else if (outOfStamina(hero1).equals("m")) {
                        System.out.println("Your move?");
                        input = input();
                        turn(hero1, hero2, input);
                    }
                }
            } else if (input == 3) {
                if (hero1.getStaminaLevel() >= 1) {
                    hero1.passive();
                    System.out.println("Ваш уровень брони теперь равен " + hero1.getArmorLevel());
                } else if (hero1.getStaminaLevel() < 1) {
                    hero1.setStaminaLevel(hero1.getStaminaLevel() + 1);
                }
            }

        }

        public void printState(Hero hero, String title, Hero hero2) {

            System.out.println("=====" + title + "=====");
            System.out.println("hp = " + hero.getHpLevel() + "\t\t|\t\t" + hero2.getHpLevel());
            System.out.println("armor = " + hero.getArmorLevel() + "\t\t|\t\t" + hero2.getArmorLevel());
            System.out.println("stamina = " + hero.getStaminaLevel() + "\t\t|\t\t" + hero2.getStaminaLevel());
            System.out.println("damage = " + hero.getDamage() + "\t\t|\t\t" + hero2.getDamage());
            System.out.println("=====================================");
            System.out.println();
        }
}



