package com.company;

import java.util.*;

public class Verification {

    public static long verifyPhoneNumber(Scanner sc){
        long number;
        do {
            while(!sc.hasNext()){
                System.out.println("Enter a valid phone number!");
                sc.next();
            }
           while(!sc.hasNextLong()){
               System.out.println("Enter a valid phone number!");
               sc.next();
           }
           number = sc.nextLong();
       }while(number<=0);
       sc.nextLine();

       return number;
    }

    public static long verifyPolicyNumber(Scanner sc){
        long result;
        do{
            while(!sc.hasNext()){
                System.out.println("Please Enter some Input: ");
                sc.next();
            }
            while(!sc.hasNextLong()){
                System.out.println("Enter a valid Policy Number");
                sc.next();
            }
            result = sc.nextLong();
        }while(result<=0);
        sc.nextLine();
        return result;
    }


    public static boolean countPhoneNumber(long n){
        int count = 0;
        boolean result = false;
        while(n>0){
            n = n/10;
            count += 1;
            continue;
        }
        if(count ==10){
            result = true;
        }
        return result;
    }

}
