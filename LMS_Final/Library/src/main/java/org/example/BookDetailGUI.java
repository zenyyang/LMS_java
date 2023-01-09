package org.example;

import javax.swing.*;
import java.nio.file.Path;

public class BookDetailGUI extends JFrame {
    private JPanel mainPanel;
    private JTextArea comments_lbl;
    private JLabel bookid_lbl;
    private JLabel title_lbl;
    private JLabel genre_lbl;
    private JLabel author_lbl;
    private JTextField textField1;

    private Book books = null;
    private Author authors = null;
    private BookInfo bookInfo = null;

    public BookDetailGUI(String title, String bookId, String authorId, String PATH){
        super(title);

        this.setContentPane(mainPanel);


        books = Functions.getBook(PATH);
        authors = Functions.getAuthor(PATH);
        bookInfo = Functions.getBookInfo(PATH);

        for (int i = 0; i < books.getBookLenghth(); i++){
            if(books.getBooksId()[i].equals(bookId)){
                bookid_lbl.setText(books.getBooksId()[i]);
                title_lbl.setText(books.getBooksTitle()[i].replace("-", " "));
                break;
            }
        }
        for (int i = 0; i < bookInfo.getBookInfoLenghth(); i++){
            if(bookInfo.getBooksInfoId()[i].equals(bookId)){
                comments_lbl.setText(bookInfo.getBooksCmt()[i].replace("-", " "));
                genre_lbl.setText(bookInfo.getBooksGenre()[i]);
                break;
            }
        }
        for (int j = 0; j < authors.getAuthorsLength(); j++){
            if(authors.getAuthorIds()[j].equals(authorId)){
                author_lbl.setText(authors.getAuthorFirstname()[j] + " " + authors.getAuthorLastname()[j]);
                break;
            }
        }

        this.pack();
    }
}
