package com.company;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Client {
    private String name;
    private String contact;
    private String policyNumber;
    private String policyName;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    private String startDate;
    private String expiryDate;

    public Client(String name, String contact, String policyNumber, String policyName,String startDate, String expiryDate) {
        this.name = name;
        this.contact = contact;
        this.policyNumber = policyNumber;
        this.policyName = policyName;
        this.startDate = startDate;
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }


    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    @Override
    public String toString() {
        return "Client:  " +
                "name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", policyNumber='" + policyNumber + '\'' +
                ", policyName='" + policyName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", expiryDate='" + expiryDate + '\'';
    }

    public static Client readClientUsingPrompt(Scanner scn) {
        scn.nextLine();
        System.out.println("Name: ");
        String name = scn.nextLine();
        System.out.println("Contact: ");
        String contact = Verification.validatePhoneNumber(scn);
        System.out.println("Policy Name: ");
        String policyName = scn.nextLine();
        System.out.println("Policy Number: ");
        String policyNumber = Verification.validatePolicyNumber(scn);
        System.out.println("Start Date(DD-MM-YYYY):");
        String startDate = Verification.validateDate(scn);
        System.out.println("Expiry Date(DD-MM-YYYY):");
        String expiryDate = Verification.validateDate(scn);

        return new Client(name, contact, policyNumber, policyName,startDate,expiryDate);
    }

    public static String getPolicyNumberUsingPrompt(Scanner scn){
        scn.nextLine();
        System.out.println("Enter the Policy Number: ");
        String policyNumber = Verification.validatePolicyNumber(scn);
        return policyNumber;
    }
}
