package org.example;

import java.io.Serializable;

public class Author implements Serializable {
    private String[] id;
    private String[] firstname;
    private String[] lastname;
    private String[] country;
    private String[] header;

    public Author(String[] id, String[] firstname, String[] lastname,  String[] country, String[] header){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.country = country;
        this.header = header;
    }

    public int getAuthorsLength(){
        return firstname.length;
    }

    public String[] getAuthorIds(){
        return id;
    }

    public String[] getAuthorLastname(){
        return lastname;
    }

    public String[] getAuthorFirstname(){
        return firstname;
    }

    public String[] getAuthorCountry(){
        return country;
    }

    public String[] getAuthorHeader(){
        return header;
    }
}

