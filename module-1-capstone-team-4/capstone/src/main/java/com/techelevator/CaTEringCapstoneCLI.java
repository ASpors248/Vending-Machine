package com.techelevator;

import com.techelevator.view.Menu;

import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.*;

public class CaTEringCapstoneCLI {


    private Menu menu;
    private Scanner userInput;
    private BigDecimal currentAmount = new BigDecimal("0.00");
    private Map<String, Items> inventory = new HashMap<>();

    public CaTEringCapstoneCLI(Menu menu) {
        this.userInput = new Scanner(System.in);
        this.menu = menu;
    }

    public CaTEringCapstoneCLI() {

    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        CaTEringCapstoneCLI cli = new CaTEringCapstoneCLI(menu);
        cli.run();
    }

    public Map<String, Items> getInventory(File file) {
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Inventory file not found!");
            System.exit(1);
        }

        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] lineArr = line.split("\\,");
            String itemNumber = lineArr[0];
            String itemName = lineArr[1];
            String itemType = lineArr[2];
            BigDecimal itemPrice = new BigDecimal(lineArr[3]);
            if (itemType.equals("Dessert")) {

                Desserts dessert = new Desserts(itemName, itemPrice, itemType, 7);
                this.inventory.put(itemNumber, dessert);
            } else if (itemType.equals("Munchy")) {

                Munchies munchy = new Munchies(itemName, itemPrice, itemType, 7);
                this.inventory.put(itemNumber, munchy);

            } else if (itemType.equals("Sandwich")) {

                Sandwiches sandwich = new Sandwiches(itemName, itemPrice, itemType, 7);
                this.inventory.put(itemNumber, sandwich);

            } else if (itemType.equals("Drink")) {

                Drinks drink = new Drinks(itemName, itemPrice, itemType, 7);
                this.inventory.put(itemNumber, drink);

            }
        }
        return this.inventory;
    }

    public void run() {
        boolean keepGoing = true;
        File file = new File("catering.csv");
        boolean fileExists = file.exists();
        if (!fileExists) {
            System.out.println("File not found! Please restart and enter valid file name.");
            System.exit(1);
        }
        do {
            getInventory(file);
            this.menu.displayMenu1();
            String menu1Input = userInput.nextLine();

            if (menu1Input.equals("D")) {
                Scanner fileScanner = null;
                try {
                    fileScanner = new Scanner(file);
                } catch (FileNotFoundException e) {
                    System.out.println("Inventory file not found!");
                }

                while (fileScanner.hasNext()) {
                    String line = fileScanner.nextLine();

                    System.out.println(line + ", 7 available");
                }
            } else if (menu1Input.equals("P")) {
                menuSub1();


            } else if (menu1Input.equals("P")) {
                menuSub1();

            } else if (menu1Input.equals("E")) {
                keepGoing = false;
            } else {
                System.out.println("Invalid entry, please try again.");
            }
        } while (keepGoing);

    }

    public void menuSub1() {
        boolean keepGoing = true;

        this.userInput = new Scanner(System.in);
        do {
            this.menu.displayMenu2();
            String menu2Input = userInput.nextLine();
            File newFile = new File("Audit.txt");

            if (menu2Input.equals("M")) {
                System.out.print("Please enter money (1, 5, 10, or 20):");
                String moneyInput = userInput.nextLine();
                BigDecimal amountGiven = new BigDecimal(moneyInput);
                LocalDateTime theDateTime = LocalDateTime.now();
                DateTimeFormatter targetFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
                if (moneyInput.equals("1")) {
                    currentAmount = this.currentAmount.add(amountGiven);
                    System.out.println("You have entered " + amountGiven + " dollars.");
                } else if (moneyInput.equals("5")) {
                    currentAmount = currentAmount.add(amountGiven);
                    System.out.println("You have entered " + amountGiven + " dollars.");
                } else if (moneyInput.equals("10")) {
                    currentAmount = currentAmount.add(amountGiven);
                    System.out.println("You have entered " + amountGiven + " dollars.");
                } else if (moneyInput.equals("20")) {
                    currentAmount = currentAmount.add(amountGiven);
                    System.out.println("You have entered " + amountGiven + " dollars.");
                } else {

                    System.out.println("Invalid entry, please enter a valid amount (1, 5, 10, or 20)");
                }
                PrintWriter writer = null;

                try {

                    writer = new PrintWriter(new FileOutputStream(newFile.getAbsoluteFile(), true));
                } catch (FileNotFoundException e) {

                }
                writer.append(theDateTime.format(targetFormat) + " MONEY FED:     " + "$" + amountGiven + ".00" + "  $" + currentAmount + "\n");
                System.out.println("Current Money Provided: $" + currentAmount);

                writer.flush();
                writer.close();
            } else if (menu2Input.equals("S")) {
                try {

                    System.out.println(this.inventory);
                    System.out.println("Please enter the slot number of your desired item: ");
                    String slotNumber = userInput.nextLine();
                    Items objectFor = inventory.get(slotNumber);
                    BigDecimal amountBeforePurchase = new BigDecimal(String.valueOf(currentAmount));

                    BigDecimal remainingAmount = new BigDecimal(String.valueOf(currentAmount)).subtract(objectFor.getPrice());

                    if (!this.inventory.containsKey(slotNumber)) {
                        System.out.println("Item Doesnt exist, Try again!: ");
                        System.out.println("Money Remaining: " + currentAmount);
                    } else if (objectFor.getItemsLeft() <= 0) {
                        System.out.println("Item no longer available, pick another item");
                        System.out.println("Money Remaining: " + currentAmount);
                    } else if (objectFor.getItemsLeft() > 0 && currentAmount.compareTo(currentAmount.subtract(remainingAmount)) <= 0) {
                        System.out.println("Not enough money for item. Enter more money to purchase.");
                        System.out.println("Money Remaining: " + currentAmount);
                    } else if (objectFor.getItemsLeft() > 0) {
                        objectFor.setItemsLeft(objectFor.getItemsLeft() - 1);
                        currentAmount = remainingAmount;
                        if (objectFor.getItemType().equals("Munchy")) {
                            System.out.println("Munchy, Munchy, so Good!");
                        } else if (objectFor.getItemType().equals("Drink")) {
                            System.out.println("Drinky, Drinky, Slurp Slurp!");
                        } else if (objectFor.getItemType().equals("Dessert")) {
                            System.out.println("Sugar, Sugar, so Sweet!");
                        } else {
                            System.out.println("Sandwich So Delicious, Yum!");
                        }
                        LocalDateTime theDateTime2 = LocalDateTime.now();
                        DateTimeFormatter targetFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
                        PrintWriter writer2 = null;
                        try {
                            writer2 = new PrintWriter(new FileOutputStream(newFile.getAbsoluteFile(), true));
                        } catch (FileNotFoundException e) {

                        }
                        writer2.append(theDateTime2.format(targetFormat) + " " + inventory.get(slotNumber).getName() + " " + slotNumber + " " + "$" + amountBeforePurchase + " " + "$" + currentAmount + "\n");

                        System.out.println(objectFor.getName() + "  " + objectFor.getPrice() + " Money remaining : " + currentAmount);
                        writer2.flush();
                        writer2.close();
                    }

                } catch (NullPointerException e) {
                    System.out.println("Item Doesnt exist, Try again!: ");
                    System.out.println("Money Remaining: " + currentAmount);
                }

            } else if (menu2Input.equals("F")) {
                getChange(currentAmount, newFile);
                keepGoing = false;
            }
        } while (keepGoing);

    }

    public int getChange(BigDecimal currentAmount, File newFile) {
        BigDecimal amountHundred = currentAmount.multiply((new BigDecimal("100")));
        int amountInt = amountHundred.intValue();
        BigDecimal changeGiven = new BigDecimal(amountInt);
        BigDecimal divisor = new BigDecimal("100");
        BigDecimal realChange = changeGiven.divide(divisor);
        int quarters = amountInt / 25;
        int amountMinusQuarters = amountInt - (quarters * 25);
        int dimes = amountMinusQuarters / 10;
        int amountMinusDimesAndQuarters = amountMinusQuarters - (dimes * 10);
        int nickles = amountMinusDimesAndQuarters / 5;
        int amountMinusEverything = amountMinusDimesAndQuarters - (nickles * 5);
        BigDecimal zero = new BigDecimal(String.valueOf(amountMinusEverything));
        currentAmount = zero;
        System.out.println("Here's your change: " + quarters + " quarters " + dimes + " dimes " + nickles + " nickles ");
        System.out.println("Amount in Machine: " + "$" + currentAmount);
        LocalDateTime theDateTime3 = LocalDateTime.now();
        DateTimeFormatter targetFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
        PrintWriter writer3 = null;
        try {
            writer3 = new PrintWriter(new FileOutputStream(newFile.getAbsoluteFile(), true));
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
        }
        writer3.append((theDateTime3.format(targetFormat) + " " + "CHANGE GIVEN: " + "$" + realChange + " " + currentAmount + "\n"));
        writer3.flush();
        writer3.close();

        return quarters + dimes + nickles;
    }


}


