package com.techelevator;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CaTEringCapstoneCLITests {

    private CaTEringCapstoneCLI caTEringCapstoneCLI;

//unable to test file that is hard coded in, meaning it will always exist. And it doesn't affect the getChange output. Just the Print Writer.
    @Before
    public void setup() {
        this.caTEringCapstoneCLI = new CaTEringCapstoneCLI();
    }

    @Test
    public void get_Change_Test() {
        BigDecimal currentAmount = new BigDecimal("5");
        File newFile = new File("Audit.txt");
        int expectedResult = 20;
        int actualResult = caTEringCapstoneCLI.getChange(currentAmount, newFile);

        assertEquals(expectedResult, actualResult);

        BigDecimal currentAmount2 = new BigDecimal("1.15");
        File newFile2 = new File("Audit.txt");
        int expectedResult2 = 6;
        int actualResult2 = caTEringCapstoneCLI.getChange(currentAmount2, newFile2);

        assertEquals(expectedResult2, actualResult2);


        BigDecimal currentAmount3 = new BigDecimal("0");
        File newFile3 = new File("Audit.txt");
        int expectedResult3 = 0;
        int actualResult3 = caTEringCapstoneCLI.getChange(currentAmount3, newFile3);

        assertEquals(expectedResult3, actualResult3);
    }

    @Test
    public void get_Inventory_Test() {
        // 1. make a new csv file called test.csv, in this file just put 3 lines.
        // 2. use that file for the test.
        File file = new File ("CateringTest.txt");

        // 3. You are going to make three objects that correspond to the lines
        // on your test file.
        Items item = new Munchies("Nachos", new BigDecimal("3.85"), "Munchy", 7);
        Items item2 = new Sandwiches("Turkey Sandwich", new BigDecimal("4.85"), "Sandwich",7);
        Items item3 = new Drinks("7Down", new BigDecimal("1.75"), "Drink", 7);
        Items item4 = new Drinks("Chocolate Bar", new BigDecimal("1.75"), "Dessert", 7);
        // 4. to put those three objects into a test map that has <String, Items>
        // This is your expected result
        Map<String, Items> expectedResult = new HashMap<>();
        expectedResult.put("A1", item);
        expectedResult.put("B2", item2);
        expectedResult.put("C3", item3);
        expectedResult.put("A4", item4 );
        // 5. Now run getInventory with the file from step 2 ,and save the result
        // into an actualMap
        Map<String, Items> actualResult = caTEringCapstoneCLI.getInventory(file);

        Items testItem1 = actualResult.get("A1");
        // check the attributes of testItem1
        assertEquals("Nachos", testItem1.getName());
        assertEquals("Munchy", testItem1.getItemType());
        assertEquals(new BigDecimal("3.85"), testItem1.getPrice());

        Items testItem2 = actualResult.get("B2");

        assertEquals("Turkey Sandwich", testItem2.getName());
        assertEquals("Sandwich", testItem2.getItemType());
        assertEquals(new BigDecimal("4.85"), testItem2.getPrice());

        Items testItem3 = actualResult.get("C3");

        assertEquals("7Down", testItem3.getName());
        assertEquals("Drink", testItem3.getItemType());
        assertEquals(new BigDecimal("1.75"), testItem3.getPrice());

        Items testItem4 = actualResult.get("A4");

        assertEquals("Chocolate Bar", testItem4.getName());
        assertEquals("Dessert", testItem4.getItemType());
        assertEquals(new BigDecimal("1.75"), testItem4.getPrice());

    }


}
