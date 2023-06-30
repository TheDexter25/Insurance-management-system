package com.company;

import java.util.*;

public class Verification {



    //Takes a long number
    //if negative says Invalid
    //if not 10 digits long says Invalid
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


       }while(number<=0 && countPhoneNumber(number)!=10);
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


    public static int countPhoneNumber(long n){
        int count = 0;
        while(n>0){
            n = n/10;
            count += 1;
            continue;
        }
       return count;
    }

}
