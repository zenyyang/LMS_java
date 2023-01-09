package org.example;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

// reference https://www.javatpoint.com/java-swing
public class GUI extends JFrame{
    private JPanel mainPanel;
    private JPanel side_panel;
    private JButton import_btn;
    private JTable excelTable;
    private JTextField searchText;
    private JLabel titleLabel;
    private JLabel idLabel;
    private JButton refreshBtn;
    private JButton removeBtn;
    private JButton addBookBtn;
    private JButton addAuthorBtn;
    private Book books = null;
    private Author authors = null;
    private DefaultTableModel model = new DefaultTableModel();

    private String selectedBookId = null;

    private String selectedAuthorId = null;

    public String PATH = null;

    public int row = -1;
    public GUI(String title){

        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);


        import_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser jf = new JFileChooser();

                // set extension for file chooser
                FileNameExtensionFilter nameExtensionFilter = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
                jf.setFileFilter(nameExtensionFilter);

                // set title for file chooser
                jf.setDialogTitle("Select Excel File");

                File excelFile = null;

                model.addColumn("Book ID");
                model.addColumn("Title");
                model.addColumn("Author ID");

                int result = jf.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION){
                    excelFile = jf.getSelectedFile();
                    PATH = excelFile.getAbsolutePath();
                    books = Functions.getBook(PATH);
                    authors = Functions.getAuthor(PATH);

                    if (model.getRowCount() > 0) {
                        model.getDataVector().removeAllElements();
                        excelTable.setModel(model);
                    }

                    excelTable.setModel(model);
                    excelTable.setDefaultEditor(Object.class, null);

                    for (int i = 0; i < books.getBookLenghth(); i++){
                        model.addRow(new Object[] {books.getBooksId()[i], books.getBooksTitle()[i], books.getAuthorId()[i]});
                    }

                    JOptionPane.showMessageDialog(null, "Import Successfully!");
                }
            }
        });

        excelTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                row = excelTable.getSelectedRow();

                titleLabel.setText(model.getValueAt(row, 1).toString());
                idLabel.setText(model.getValueAt(row, 0).toString());

                selectedBookId = model.getValueAt(row, 0).toString();
                selectedAuthorId = model.getValueAt(row, 2).toString();
            }
        });

        searchText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                searchFilter(searchText.getText());
            }
        });

        excelTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFrame bookGUI = new BookDetailGUI("Book Detail", selectedBookId, selectedAuthorId, PATH);
                bookGUI.pack();
                bookGUI.setVisible(true);
            }
        });

        this.pack();

        addBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new AddBookGUI("Add Book", PATH);
                frame.setVisible(true);
            }
        });

        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.getRowCount() > 0) {
                    model.getDataVector().removeAllElements();
                    excelTable.setModel(model);
                }

                books = Functions.getBook(PATH);
                authors = Functions.getAuthor(PATH);

                excelTable.setModel(model);
                excelTable.setDefaultEditor(Object.class, null);

                for (int i = 0; i < books.getBookLenghth(); i++){
                    model.addRow(new Object[] {books.getBooksId()[i], books.getBooksTitle()[i], books.getAuthorId()[i]});
                }
            }
        });

        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (row >= 0) {
                    try {
                        Functions.deleteData(PATH, row+1);

                        JOptionPane.showMessageDialog(null, "Book Removed");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        addAuthorBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new AddAuthorGUI("Add Author", PATH);
                frame.setVisible(true);
            }
        });
    }
    private void searchFilter(String query){
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(model);
        excelTable.setRowSorter(trs);

        trs.setRowFilter(RowFilter.regexFilter("(?i)" + query));
    }
}
