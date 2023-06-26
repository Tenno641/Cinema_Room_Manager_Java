import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Machine m = new Machine();

        while (true) {
            System.out.println("Write action (buy, fill, take):");
            String action = sc.next();

            switch (action) {
                case "buy" -> {
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                    m.buy(sc.next().charAt(0));
                }

                case "fill" -> {
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                    m.setAmountOfWater(sc.nextInt());

                    System.out.println("Write how many ml of milk you want to add:");
                    m.setAmountOfMilk(sc.nextInt());

                    System.out.println("Write how many grams of coffee beans you want to add:");
                    m.setAmountOfCoffee(sc.nextInt());

                    System.out.println("Write how many disposable cups you want to add:");
                    m.setCups(sc.nextInt());

                    m.checkingPossibility();
                }

                case "take" -> m.take();

                case "remaining" -> m.print();

                case "exit" -> {
                    return;
                }

            }

        }

    }

}

class Machine {

    private int possibleEspresso;
    private int possibleLatte;
    private int possibleCappuccino;

    private int amountOfWater = 400;
    private int amountOfMilk = 540;
    private int amountOfCoffee = 120;
    private int cups = 9;
    private int money = 550;

    public void buy(char choice) {

        switch (choice) {
            case '1' -> {

            if(isEspressoPossible()) {
                    System.out.println("I have enough resources, making you coffee!");
                    makeEspresso();
                } else {
                    System.out.println("Sorry, not enough material!");
                }

            }

            case '2' -> {

                if (isLattePossible()) {
                    System.out.println("I have enough resources, making you coffee!");
                    makeLatte();
                } else {
                    System.out.println("Sorry, not enough material!");
                }

            }

            case '3' -> {
                if ((isCappuccinoPossible())) {
                    System.out.println("I have enough resources, making you coffee!");
                    makeCappuccino();
                } else {
                    System.out.println("Sorry, not enough material!");
                }
            }

            default -> { }

        }

    }

    public void checkingPossibility() {
        possibleEspresso = Math.min(amountOfWater / 250, amountOfCoffee / 16 );
        possibleLatte = Math.min(amountOfWater / 350, Math.min(amountOfCoffee / 20, amountOfMilk / 75 ));
        possibleCappuccino = Math.min(amountOfWater / 200, Math.min(amountOfCoffee / 12, amountOfMilk / 100 ));
    }

    private boolean isEspressoPossible() {
        return 1 >= possibleEspresso;
    }

    private boolean isLattePossible() {
        return 1 >= possibleLatte;
    }

    private boolean isCappuccinoPossible() {
        return 1 >= possibleCappuccino;
    }

    private void makeEspresso() {
        this.amountOfWater -= 250;
        this.amountOfCoffee -= 16;
        this.cups -= 1;
        this.money += 4;
    }

    private void makeLatte() {
        this.amountOfWater -= 350;
        this.amountOfMilk -= 75;
        this.amountOfCoffee -= 20;
        this.cups -= 1;
        this.money += 7;
    }

    private void makeCappuccino() {
        this.amountOfWater -= 200;
        this.amountOfMilk -= 100;
        this.amountOfCoffee -= 12;
        this.cups -= 1;
        this.money += 6;
    }

    public void setAmountOfWater(int amountOfWater) {
        this.amountOfWater += amountOfWater;
    }

    public void setAmountOfMilk(int amountOfMilk) {
        this.amountOfMilk += amountOfMilk;
    }

    public void setAmountOfCoffee(int amountOfCoffee) {
        this.amountOfCoffee += amountOfCoffee;
    }

    public void setCups(int cups) {
        this.cups += cups;
    }

    public void take() {
        System.out.printf("I gave you $%d\n", money);
        setMoney();
    }

    private void setMoney() {
        this.money = 0;
    }

    public void print() {
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water\n", amountOfWater);
        System.out.printf("%d ml of milk\n", amountOfMilk);
        System.out.printf("%d g of coffee beans\n", amountOfCoffee);
        System.out.printf("%d disposable cups\n", cups);
        System.out.printf("$%d of money\n", money);
    }

}
