package com.company;

import java.sql.*;
import java.util.Scanner;

public class Database {

        private Connection connecthis(){
            Connection c = null;
//            String formattedResult = "id = %d, name = %s, phone-number = %s, expiry-date = %s";

            try{
//                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection(Properties.CONNECTION_URL);

//                Statement statement = c.createStatement();
//                ResultSet resultSet = statement.executeQuery("select * from client");
//
//                while (resultSet.next()) {
//                    System.out.println(resultSet.getString("id"));
//                }

            } catch (Exception e){
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);

            }
            System.out.println("Connected to database succesfully");
            return c;
        }

        public void insert(Client client){

            String sql = "INSERT INTO client(policyNumber, policyName, name, phoneNumber, startDate, expiryDate) VALUES (" +
                    "?,?,?,?,?,?)";

            try(Connection cn = this.connecthis(); PreparedStatement preparedStatement = cn.prepareStatement(sql)){

                preparedStatement.setString(1,client.getPolicyNumber());
                preparedStatement.setString(2,client.getPolicyName());
                preparedStatement.setString(3,client.getName());
                preparedStatement.setString(4,client.getContact());
                preparedStatement.setString(5,client.getStartDate());
                preparedStatement.setString(6,client.getExpiryDate());

                preparedStatement.executeUpdate();
                System.out.println("Created a new client record!");
            }catch (Exception e ){
                System.out.println(e.getMessage());
            }

        }

        public void update(String s){
            String sql = "";
            try(Connection cn = this.connecthis()){
                Scanner sc = new Scanner(System.in);
                System.out.println("--- WHAT DO YOU WANT TO UPDATE? ---");
                System.out.println("1. Policy Number");
                System.out.println("2. Phone Number");
                System.out.println("3. Dates");
                System.out.println("--- PLEASE CHOOSE AN OPTION ---");
                int choice = sc.nextInt();
                sc.nextLine();
                switch(choice){
                    case 1:
                        sql = "UPDATE client SET policyNumber = ? WHERE policyNumber = "+s;
                        System.out.print("Policy Number: ");
                        String policyNumber = sc.nextLine();
                        PreparedStatement psmt = cn.prepareStatement(sql);
                        psmt.setString(1,policyNumber);
                        psmt.executeUpdate();
                        System.out.println("Record Updated!");
                        break;

                    case 2:
                        sql = "UPDATE client SET phoneNumber = ? WHERE policyNumber = "+s;
                        System.out.print("Phone Number: ");
                        String phoneNumber = sc.nextLine();
                        PreparedStatement psmt1 = cn.prepareStatement(sql);
                        psmt1.setString(1,phoneNumber);
                        psmt1.executeUpdate();
                        System.out.println("Record Updated!");
                        break;

                    case 3:
                        sql = "UPDATE client SET startDate = ?,expiryDate = ? WHERE policyNumber = "+s;
                        System.out.print("Start Date: ");
                        String startDate = sc.nextLine();
                        System.out.print("Expiry Date: ");
                        String expiryDate = sc.nextLine();
                        PreparedStatement psmt2 = cn.prepareStatement(sql);
                        psmt2.setString(1,startDate);
                        psmt2.setString(2,expiryDate);
                        psmt2.executeUpdate();
                        System.out.println("Record Updated!");
                        break;
                    default:
                        System.out.println("Invalid Choice!");
                        break;
                }


            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }

        }

//    public static void main(String[] args) {
//        Database db = new Database();
//        db.connecthis();
//    }

}
