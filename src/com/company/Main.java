package com.company;
import java.awt.desktop.SystemEventListener;
import java.util.*;
import java.lang.String;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Main {
    //static String[] hotel = new String[8];
    static String[] firstName = new String[8];
    static String[] surName = new String[8];
    static String[] ccNumber = new String[8];
    static int[] noOfGuests = new int[8];

    public static void main(String[] args) throws IOException {
        Scanner menu = new Scanner(System.in);
        //String roomName = "";
        String[] menuText = {"V: Views All rooms", "A: Adds customer to room", "E: Display Empty rooms", "D: Delete customer from room", "F: Find room from customer name", "S: Store program data into file", "L: Load program data from file", "O: View guests Ordered alphabetically by name.","Q: to Quit"};
        //int roomNum = 0;

        initialise(firstName,surName,noOfGuests,ccNumber);
        System.out.println("------------------------------------------------");
        System.out.println("Menu");
        System.out.println("Additional Information Program");
        for (int i = 0; i < menuText.length; i++) {
            System.out.println(menuText[i]);
        }
        String option = "";
        System.out.println("Enter option");
        option = menu.next().toUpperCase();
        while (!option.equals("Q")) {

            switch (option) {
                case "S":
                    storeDate(firstName,surName,noOfGuests,ccNumber);
                    break;
                case "O":
                    order();
                    break;
                case "L":
                    loadData(firstName);
                    break;
                case "V":
                    viewRooms(firstName,surName,noOfGuests,ccNumber);
                    break;
                case "E":
                    viewEmptyRooms(firstName);
                    break;
                case "D":
                    deleteCustomer(firstName,surName,noOfGuests,ccNumber);
                    break;
                case "A":
                    addCustomers(firstName,surName,noOfGuests,ccNumber);
                    break;
                case "F":
                    findRoom(firstName,surName,noOfGuests,ccNumber);
                    break;

            }

            System.out.println("Enter option");
            option = menu.next().toUpperCase();

        }



    }

    private static void initialise(String[] firstName, String[] surName,int[] noOfGuests,String[] ccNumber) {
        for (int x = 0; x < 8; x++){
            firstName[x] = "e";
            surName[x] = "e";
            ccNumber[x] = "e";
            noOfGuests[x] = 0;
        }
        //System.out.println( "initilise ");
    }

    private static void viewRooms(String[] firstName, String[] surName,int[] noOfGuests,String[] ccNumber) {
        System.out.println("Room No\tFirstname\tSurname\tCCNumber\tNo. of Guests");
        for (int x = 0; x < 8; x++) {
            System.out.print(x+1);
            System.out.print( "\t\t" + firstName[x]);
            System.out.print( "\t\t\t" + surName[x]);
            System.out.print( "\t\t\t" + ccNumber[x]);
            System.out.println( "\t\t\t" + noOfGuests[x]);



        }
    }

    private static void addCustomers(String[] firstName, String[] surName,int[] noOfGuests,String[] ccNumber) {
        Scanner input = new Scanner(System.in);
        int roomNum,guests;
        String name1,name2,creditCard;

        for (int x = 0; x < 8; x++) {
            if (isEmpty(firstName[x])) System.out.println("room " + (x + 1) + " is empty");
        }
        System.out.println("Enter room number (1-8) :");
        roomNum = input.nextInt();
        if (roomNum <= firstName.length) {
            if (!isEmpty(firstName[roomNum - 1])) {
                System.out.println("Room is occupied");
            }else {
                System.out.println("Enter First Name " + roomNum + " :");
                name1 = input.next();
                firstName[roomNum - 1] = name1;
                System.out.println(" Enter Last Name   :");
                name2 = input.next();
                surName[roomNum - 1] = name2;
                System.out.println(" Enter numbers of guests   :");
                guests = input.nextInt();
                noOfGuests[roomNum -1 ] = guests;
                System.out.println(" Enter credit card number   :");
                creditCard = input.next();
                ccNumber[roomNum-1] = creditCard;
            }
        }else{
            System.out.println("Invalid room number");
        }
    }
    private static void viewEmptyRooms (String[] rooms){
        for (int x = 0; x < 8; x++) {
            if (rooms[x].equals("e")){
                System.out.println("room " + (x + 1) + " is empty");
            }
        }
    }

    private static void deleteCustomer (String[] firstName, String[] surName,int[] noOfGuests,String[] ccNumber){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter room number to be deleted");
        int roomNum = input.nextInt();
        System.out.println("room number " + roomNum + " has been deleted from the record");
        firstName[roomNum-1] = "e";
        surName[roomNum-1] = "e";
        noOfGuests[roomNum-1] = 0;
        ccNumber[roomNum-1] = "e";
    }

    private static int findRoom (String[] firstName, String[] surName,int[] noOfGuests,String[] ccNumber){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter first name");
        String name = input.next().toLowerCase(); // convert input to lower case
        System.out.println("Enter last name");
        String name2 = input.next().toLowerCase();
        for (int x = 0; x < 8; x++) {
            if (isEmpty(name) == false) {
                if (firstName[x].toLowerCase().equals(name) && surName[x].toLowerCase().equals(name2)) { //compare with lowercased names of customers
                    System.out.println("Found " + name + " " + name2 + " in room number " + (x+1));
                    System.out.println("Guests: " + noOfGuests[x] + " Card Number:" + ccNumber[x] );
                    return 0;
                }

            }

        }
        System.out.println("Not found");
        return 1;
    }
    private static void storeDate  (String[] firstName, String[] surName,int[] noOfGuests,String[] ccNumber) throws IOException {
        File myObj = new File("hotel.txt");
        myObj.createNewFile();  //A new file is created using the method
        FileWriter myWriter = new FileWriter(myObj.getName());
        for(int x = 0; x<firstName.length; x++){
            myWriter.write(firstName[x] + " " +surName[x] + "  Guests " + noOfGuests[x] + " Card Number " + ccNumber[x] +" " + "\n");
        }
        myWriter.close();
        System.out.println("Added Successfully");  //Once the file is created a message is printed


    }
    private static void loadData (String[] firstName) throws IOException {
        int x = 0;
        File myObj = new File("hotel.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            firstName[x] = data;  //data taken from file is stored to array
            x++;
        }
        myReader.close();
        System.out.println("Loaded Successfully");  //prints message when successfully loaded


    }


    private static boolean isEmpty (String input ){
        if (input.equals("e")) {
            return true;
        } else {
            return false;
        }
    }

    private static void order() {
        int count = 0;
        String temp;

        //String[] a = new String[hotel.length+1];
        for (int i = 0; i < firstName.length; i++) {
            if (!isEmpty(firstName[i])) {
                count = count + 1;
                //a[i] = hotel[i];
            }
        }
        //int[] roomNo = new int[count];
        String[] str = new String[count];
        count = 0;
        for(int i = 0; i < firstName.length; i++){
            if(!isEmpty(firstName[i])){
                str[count] = firstName[i] + " " + surName[i];
                //roomNo[count] = i+1;
                count = count + 1;
            }

        }
        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count; j++) {
                if (str[i].toLowerCase().compareTo(str[j].toLowerCase()) > 0) {
                    temp = str[i];
                    str[i] = str[j];
                    str[j] = temp;
                    /*temp1 = roomNo[i];
                    roomNo[i] = roomNo[j];
                    roomNo[j] = temp1;*/
                }
            }
        }
        for(int i = 0;i < count; i++){
            System.out.println(str[i]);
        }
    }
}