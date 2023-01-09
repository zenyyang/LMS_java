package org.example;

import java.io.Serializable;

public class BookInfo implements Serializable {
    private String[] bookId;
    private String[] genre;
    private String[] cmt;

    public BookInfo(String[] bookId, String[] genre, String[] cmt){
        this.bookId = bookId;
        this.genre = genre;
        this.cmt = cmt;
    }

    public int getBookInfoLenghth(){
        return bookId.length;
    }

    public String[] getBooksInfoId(){
        return bookId;
    }

    public String[] getBooksGenre(){
        return genre;
    }

    public String[] getBooksCmt(){
        return cmt;
    }
}
