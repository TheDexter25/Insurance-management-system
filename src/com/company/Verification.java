package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class Verification {


    public static String validatePhoneNumber(Scanner sc){
        String phoneNo = "";
        do{
           phoneNo  = sc.nextLine();

            if (!verifyPhoneNumber(phoneNo)) {
                System.out.println("Invalid Phone Number!");
            }
        }while(!verifyPhoneNumber(phoneNo));

        return phoneNo;
    }
    public static boolean verifyPhoneNumber(String phoneNumber){

        if(phoneNumber.length() != 10){
            return false;
        }

        for(char c:phoneNumber.toCharArray()){
            if(!Character.isDigit(c)){
                return false;
            }
        }

       return true;
    }

    public static String validatePolicyNumber(Scanner sc){
        String policyNo = "";
        do{
            policyNo = sc.nextLine();

            if(!verifyPolicyNumber(policyNo)){
                System.out.println("Invalid Policy Number!");
            }
        }while(!verifyPolicyNumber(policyNo));

        return policyNo;
    }



    public static boolean verifyPolicyNumber(String s){
        if(s.length()!=4){
            return false;
        }

        for(char c:s.toCharArray()){
            if(!Character.isDigit(c)){
                return false;
            }
        }

        return true;
    }

    public static String validateDate(Scanner sc){
        String result = " ";
        do{
            result = sc.nextLine();
            if(verifyDate(result)!= true){
                System.out.println("Invalid Date!");
            }
        }while(verifyDate(result)!=true);
        return result;
    }

    public static boolean verifyDate(String s){
//        return Pattern.matches("[123]{1}\\d{1}-[01]{1}\\d{1}-\\d{4}",s);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false); // Disable lenient parsing
        try {
            dateFormat.parse(s);
            return true;
        } catch (ParseException e) {
            return false;
        }

    }



}
