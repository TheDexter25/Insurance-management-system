package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.*;

public class Main {



    /*
    - Create a client
    - Update an existing client
    - Delete an existing client
    - Get details of a client by id
    - Get details of a client by phone number
    - Get details of a client by client name
    - Get all clients
    - Get all clients with policy name
    - Get all clients with policy number
    - Get all clients with policy expiring withing some time
    - Get all clients with expiry date withing a given range of dates
    */

    public static void main(String[] args) {
        Database db = new Database();
        Scanner scn = new Scanner(System.in);
        boolean isRunning = true;
//        List<Client> clients = new ArrayList<>();

        while (isRunning) {
            // Print the action menu

            System.out.println("##### Welcome to CRM #####");
            System.out.println("Select an option from below:");
            System.out.println("1. Create a client.");
            System.out.println("2. Update a client.");
            System.out.println("3. Delete a client.");
            System.out.println("4. Find a client.");
            System.out.println("5. Quit");
            System.out.println("\nSelection : ");

            // Read which action item is selected

            int actionItem = scn.nextInt();

            switch (actionItem) {
                case 1:
                    Client client = Client.readClientUsingPrompt(scn);
                    db.insert(client);
                    System.out.println(client);
                    break;
                case 2:
//                    System.out.println("Updating a client");
                    String s = Client.updateClientUsingPrompt(scn);
                    db.update(s);
                    break;
                case 3:
                    System.out.println("Deleting a client");
                    break;
                case 4:
                    System.out.println("\n\nClient list:\n\n");
//                    for(Client clientItem : clients) {
//                        System.out.println(clientItem);
//                    }
//                    System.out.println("\n\n");
                    break;
                case 5:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid action item selected.");
                    break;
            }
        }

        scn.close();
    }
}
