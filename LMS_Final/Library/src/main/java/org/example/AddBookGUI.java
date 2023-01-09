package org.example;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;

public class AddBookGUI extends JFrame implements ItemListener {

    private JPanel mainPanel;
    private JTextField titleTxt;
    private JTextField bookIdTxt;
    private JTextField genreTxt;
    private JTextField cmtTxt;
    private JComboBox authorCmb;
    private JButton addBtn;
    private JLabel authorlbl;

    private Book book = null;

    private BookInfo bookInfo = null;

    private  Author author = null;

    private String[] names = null;

    private String selectedAuthorName;
    private String selectedAuthorID;


    public AddBookGUI(String title, String PATH) {
        super(title);

        this.setContentPane(mainPanel);

        ArrayList<String> authorList = new ArrayList<String>();
        author = Functions.getAuthor(PATH);

        for (int i = 0; i < author.getAuthorsLength(); i++) {
            authorList.add(author.getAuthorFirstname()[i] + " " + author.getAuthorLastname()[i]);
        }

        names = new String[authorList.size()];

        for (int i = 0; i < authorList.size(); i++) {
            names[i] = authorList.get(i);
        }

        final DefaultComboBoxModel model = new DefaultComboBoxModel(names);

        authorCmb.setModel(model);
        authorCmb.setMaximumRowCount(names.length);

        this.pack();

        authorCmb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedAuthorName = authorCmb.getSelectedItem().toString();
            }
        });



        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] temp = selectedAuthorName.split(" ");

                for (int i = 0; i < author.getAuthorsLength(); i++) {
                    if (author.getAuthorFirstname()[i].equals(temp[0])) {
                        if (author.getAuthorLastname()[i].equals(temp[1])) {
                            selectedAuthorID = author.getAuthorIds()[i];
                        }
                    }
                }

                if ((bookIdTxt.getText().equals("")) || (titleTxt.getText().equals("")) || (genreTxt.getText().equals("")) || (cmtTxt.getText().equals(""))) {
                    JOptionPane.showMessageDialog(null, "Fill in the necessary information!");
                } else {
                    try {
                        Functions.insertBook(PATH, bookIdTxt.getText(), titleTxt.getText(), selectedAuthorID, genreTxt.getText().replace(" ", "/"), cmtTxt.getText().replace(" ", "-"));

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    dispose();
                }
            }
        });
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
    }
}
