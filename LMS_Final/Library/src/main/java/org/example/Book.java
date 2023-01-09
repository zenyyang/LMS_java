package org.example;

import java.io.Serializable;

public class Book implements Serializable {
    private String[] id;
    private String[] title;
    private String[] authorId;
    private String[] header;

    public Book(String[] id, String[] title, String[] authorId, String[] header){
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.header = header;
    }

    public int getBookLenghth(){
        return title.length;
    }

    public String[] getBooksId(){
        return id;
    }

    public String[] getBooksTitle(){
        return title;
    }

    public String[] getAuthorId(){
        return authorId;
    }
}

