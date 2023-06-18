package com.company;

import java.sql.*;
import java.util.Scanner;

public class Database {

    private Connection createConnection() {
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(Properties.CONNECTION_URL);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }
        System.out.println("Connected to database succesfully");
        return c;
    }

    public void insert(Client client) {

        String sql = "INSERT INTO client(policyNumber, policyName, name, phoneNumber, startDate, expiryDate) VALUES (" +
                "?,?,?,?,?,?)";

        try (Connection cn = this.createConnection(); PreparedStatement preparedStatement = cn.prepareStatement(sql)) {

            preparedStatement.setString(1, client.getPolicyNumber());
            preparedStatement.setString(2, client.getPolicyName());
            preparedStatement.setString(3, client.getName());
            preparedStatement.setString(4, client.getContact());
            preparedStatement.setString(5, client.getStartDate());
            preparedStatement.setString(6, client.getExpiryDate());

            preparedStatement.executeUpdate();
            System.out.println("Created a new client record!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void update(String s) {
        try (Connection cn = this.createConnection()) {
            Scanner sc = new Scanner(System.in);
            System.out.println("--- WHAT DO YOU WANT TO UPDATE? ---");
            System.out.println("1. Policy Number");
            System.out.println("2. Phone Number");
            System.out.println("3. Dates");
            System.out.println("4. Exit");
            System.out.println("--- PLEASE CHOOSE AN OPTION ---");
            int choice = sc.nextInt();
            sc.nextLine();
            String sql = "UPDATE client SET %s = '%s' WHERE policyNumber = '%s' ";
            String sql1 = "UPDATE client SET %s = '%s', %s = '%s' WHERE policyNumber = '%s'";
            PreparedStatement psmt = null;
            switch (choice) {
                case 1:
                    System.out.print("Policy Number: ");
                    String policyNumber = sc.nextLine();
                    psmt = cn.prepareStatement(String.format(sql, "policyNumber", policyNumber, s));
//                        psmt.setString(1,"policyNumber");
//                        psmt.setString(2, policyNumber);
//                        psmt.setString(3, s);
                    psmt.executeUpdate();
                    System.out.println("Record Updated!");
                    break;

                case 2:
                    System.out.print("Phone Number: ");
                    String phoneNumber = sc.nextLine();
                    psmt = cn.prepareStatement(String.format(sql, "phoneNumber", phoneNumber, s));
                        /*psmt.setString(1,"phoneNumber");
                        psmt.setString(2,phoneNumber);
                        psmt.setString(3,s);*/
                    psmt.executeUpdate();
                    System.out.println("Record Updated!");
                    break;

                case 3:
                    System.out.print("Start Date: ");
                    String startDate = sc.nextLine();
                    System.out.print("Expiry Date: ");
                    String expiryDate = sc.nextLine();
                    psmt = cn.prepareStatement(String.format(sql1, "startDate", startDate, "expiryDate", expiryDate, s));
//                        psmt.setString(1,"startDate");
//                        psmt.setString(2,startDate);
//                        psmt.setString(3,s);
                    psmt.executeUpdate();
                    System.out.println("Record Updated!");
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid Choice!");
                    break;
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    public void delete(String s) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Are you sure you want to delete this record? (Y/N)");
        String result = sc.nextLine();
        if (result.equals("Y")) {
        } else if (result.equals("N")) {
            return;
        } else {
            System.out.println("Invalid response");
            return;
        }
        try (Connection cn = this.createConnection()) {
            PreparedStatement psmt = cn.prepareStatement("DELETE FROM client where policyNumber = '" + s + "'");
            psmt.executeUpdate();
            System.out.println("Record Deleted!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void find(Scanner sc){
        System.out.println("--- FIND A CLIENT ---");
        System.out.println("1. Show all");
        System.out.println("2. Through Policy Number");
        System.out.println("3. Through Name of Client");
        System.out.println("4. Get all Clients with same Policy name");
        System.out.println("5. Exit");
        System.out.println("--- PLEASE CHOOSE AN OPTION ---");
        int choice = sc.nextInt();

        try(Connection cn = this.createConnection(); Statement smt = cn.createStatement();){
            ResultSet rs = null;
            switch(choice){
                case 1:
                    rs = smt.executeQuery("SELECT * FROM client");
                    while(rs.next()){
                        System.out.println("Policy Number: "+rs.getString("policyNumber")+
                                ", Policy Name: "+rs.getString("policyName")+
                                ", Name: "+rs.getString("name")+
                                ", Phone Number: "+ rs.getString("phoneNumber")+
                                ", Start Date: "+rs.getString("startDate")+
                                ", Expiry Date: "+rs.getString("expiryDate"));
                    }
                    break;
                case 2:
                    sc.nextLine();
                    System.out.println("Enter Policy Number: ");
                    String policyN = sc.nextLine();
                    rs = smt.executeQuery("SELECT * FROM client WHERE policyNumber = '"+policyN+"'");
                    while(rs.next()){
                        System.out.println("Policy Number: "+rs.getString("policyNumber")+
                                ", Policy Name: "+rs.getString("policyName")+
                                ", Name: "+rs.getString("name")+
                                ", Phone Number: "+ rs.getString("phoneNumber")+
                                ", Start Date: "+rs.getString("startDate")+
                                ", Expiry Date: "+rs.getString("expiryDate"));
                    }
                    break;
                case 3:
                    sc.nextLine();
                    System.out.println("Enter the name of client: ");
                    String name = sc.nextLine();
                    rs = smt.executeQuery("SELECT * FROM client WHERE name = '"+name+"'");
                    while(rs.next()){
                        System.out.println("Policy Number: "+rs.getString("policyNumber")+
                                ", Policy Name: "+rs.getString("policyName")+
                                ", Name: "+rs.getString("name")+
                                ", Phone Number: "+ rs.getString("phoneNumber")+
                                ", Start Date: "+rs.getString("startDate")+
                                ", Expiry Date: "+rs.getString("expiryDate"));
                    }
                    break;
                case 4:
                    sc.nextLine();
                    System.out.println("Enter the name of the policy: ");
                    String policyName = sc.nextLine();
                    rs = smt.executeQuery("SELECT * FROM client WHERE policyName = '"+policyName+"'");
                    while(rs.next()){
                        System.out.println("Policy Number: "+rs.getString("policyNumber")+
                                ", Policy Name: "+rs.getString("policyName")+
                                ", Name: "+rs.getString("name")+
                                ", Phone Number: "+ rs.getString("phoneNumber")+
                                ", Start Date: "+rs.getString("startDate")+
                                ", Expiry Date: "+rs.getString("expiryDate"));
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid Choice!");
                    break;
            }


        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }


}
