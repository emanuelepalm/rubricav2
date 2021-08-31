package com.palmieri.models;

import java.lang.reflect.Field;
import java.util.Random;

public class Contatti {

    private String firstName;
    private String lastName;
    private String number;
    private String email;
    private String uid;

    public Contatti(String firstName,String lastName, String number, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.email = email;
        this.uid = generateId(firstName, lastName);
    }
    public Contatti(String firstName,String lastName, String number, String email,String uid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.email = email;
        this.uid = uid;
    }

    public Contatti() {

    }

    /**
     *  Getter e Setter
     */
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public String generateId(String firstName, String lastName) {
        String id = "";
        Random random = new Random();
        for(int j = 0; j<6; j++) {
            char idChar = (char) (random.nextInt(26) + 'a');
            id += idChar;
        }
        if(lastName.length() > 0) {
            id += "-" + firstName.charAt(0) + lastName.charAt(0);
        } else {
            id += "-" +firstName.charAt(0)+"x";
        }
        return id;
    }
    @Override
    public boolean equals(Object obj) {
        for(Field f:  obj.getClass().getDeclaredFields()){
            try {
              if(!f.get(this).equals(f.get(obj))){
                  System.out.println("aaaaaaaaa");
                  return false;

              }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }


}
