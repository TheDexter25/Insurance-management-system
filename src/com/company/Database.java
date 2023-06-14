package com.company;

import java.sql.*;

public class Database {

        private Connection connecthis(){
            Connection c = null;
            String formattedResult = "id = %d, name = %s, phone-number = %s, expiry-date = %s";

            try{
                Class.forName("org.sqlite.JDBC");
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

//    public static void main(String[] args) {
//        Database db = new Database();
//        db.connecthis();
//    }

}
