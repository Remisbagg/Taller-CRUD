package com.example.myapplication.classes;

public class User {
    private int document;
    private String user;
    private String names;
    private String lastNames;
    private String pass;

    public User(){

    }
    public User(int document, String user, String names, String lastnames, String pass){
        this.document =document;
        this.user =user;
        this.names =names;
        this.lastNames =lastnames;
        this.pass =pass;
    }

    public int getDocument() {
        return document;
    }

    public void setDocument(int document) {
        this.document = document;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    @Override
    public String toString(){
        return "Document:" + document +
                ", User:'" + user + '\'' +
                ", Names:'" + names + '\'' +
                ", LastNames:'" + lastNames + '\'' +
                ", Pass=**'  '";
    }
}
