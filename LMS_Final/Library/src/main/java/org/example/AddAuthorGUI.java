package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddAuthorGUI extends JFrame{
    private JPanel mainPanel;
    private JTextField authorIDtxt;
    private JTextField firstnameTxt;
    private JTextField lastnameTxt;
    private JTextField BDTxt;
    private JTextField CORTxt;
    private JButton addBtn;
    private Author author = null;

    public AddAuthorGUI(String title, String PATH){
        super(title);
        this.setContentPane(mainPanel);

        author = Functions.getAuthor(PATH);

        this.pack();

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((authorIDtxt.getText().equals("")) || (firstnameTxt.getText().equals("")) || (lastnameTxt.getText().equals("")) || (CORTxt.getText().equals(""))) {
                    JOptionPane.showMessageDialog(null, "Fill in the necessary information!");
                } else {
                    try {
                        Functions.insertAuthor(PATH, authorIDtxt.getText(), firstnameTxt.getText(), lastnameTxt.getText(), CORTxt.getText());

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    dispose();
                }
            }
        });
    }
}
