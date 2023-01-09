package org.example;

import java.io.*;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// reference https://www.e-iceblue.com/Tutorials/Java/Spire.XLS-for-Java/Program-Guide/Cells/Java-insert-hide-and-delete-Excel-rows-and-columns.html

public class Functions implements Serializable {

    private static Book book = null;

    private static Author author = null;

    public Functions(){}

    public static Author getAuthor(String path){
        ArrayList<String> headerRaw = new ArrayList<>();
        ArrayList<String> authorIdRaw = new ArrayList<>();
        ArrayList<String> firstnameRaw = new ArrayList<>();
        ArrayList<String> lastnameRaw = new ArrayList<>();
        ArrayList<String> birthdayRaw = new ArrayList<>();
        ArrayList<String> countryResRaw = new ArrayList<>();
        ArrayList<String> output = new ArrayList<>();

        String[] header = {};
        String[] authorId = {};
        String[] firstname = {};
        String[] lastname = {};
        String[] birthday = {};
        String[] country = {};

        try {
            // obtaining input bytes from a file
            FileInputStream file = new FileInputStream(new File(path));

            // creating workbook instance that refers to .xls file
            Workbook wb = new XSSFWorkbook(file);

            // creating a sheet object to retrieve the object
            XSSFSheet shh = (XSSFSheet) wb.getSheetAt(1);

            Iterator<Row> rowIterator = shh.iterator();
            while(rowIterator.hasNext()){
                Row row = rowIterator.next();

                Iterator<Cell> cellIterator = row.iterator();
                while(cellIterator.hasNext()){
                    Cell cell = cellIterator.next();

                    if (row.getRowNum() == 0){
                        headerRaw.add(cell.getStringCellValue());
                    } else {
                        if (cell.getColumnIndex() == 0){
                            authorIdRaw.add(cell.getStringCellValue());
                        } else if (cell.getColumnIndex() == 1){
                            firstnameRaw.add(cell.getStringCellValue());
                        } else if (cell.getColumnIndex() == 2){
                            lastnameRaw.add(cell.getStringCellValue());
                        } else if (cell.getColumnIndex() == 3){
                            countryResRaw.add(cell.getStringCellValue());
                        }
                    }
                }
            }
            wb.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        output.add(headerRaw + ";" + authorIdRaw + ";" + firstnameRaw + ";" + lastnameRaw + ";" + countryResRaw);

        String[] data = output.toString().split(";");

        header = data[0].split(",");
        header[0] = header[0].replace("[", "");
        header[header.length-1] = header[header.length-1].replace("]", "");

        authorId = data[1].split(",");
        firstname = data[2].split(",");
        lastname = data[3].split(",");
        country = data[4].split(",");

        for ( int i = 0; i < authorId.length; i++){
            if ( i == 0){
                firstname[i] = firstname[i].replace("[", "");

                lastname[i] = lastname[i].replace("[", "");

                country[i] = country[i].replace("[", "");

                authorId[i] = authorId[i].replace("[", "");
            }
            else if ( i == authorId.length-1){
                firstname[i] = firstname[i].replace("]", "");
                firstname[i] = firstname[i].replace(" ", "");

                lastname[i] = lastname[i].replace("]", "");
                lastname[i] = lastname[i].replace(" ", "");

                country[i] = country[i].replace("]", "");
                country[i] = country[i].replace(" ", "");

                authorId[i] = authorId[i].replace("]", "");
                authorId[i] = authorId[i].replace(" ", "");
            }
            else {
                firstname[i] = firstname[i].replace(" ", "");

                lastname[i] = lastname[i].replace(" ", "");

                country[i] = country[i].replace(" ", "");

                authorId[i] = authorId[i].replace(" ", "");
            }
        }

        Author authors = new Author(authorId, firstname, lastname, country, header);

        return authors;
    }

    public static Book getBook(String path){
        ArrayList<String> headerRaw = new ArrayList<>();
        ArrayList<String> bookIdRaw = new ArrayList<>();
        ArrayList<String> bookTitleRaw = new ArrayList<>();
        ArrayList<String> authorIdRaw = new ArrayList<>();
        ArrayList<String> output = new ArrayList<>();

        String[] header = {};
        String[] bookId = {};
        String[] bookTitle = {};
        String[] authorId = {};

        try {
            // obtaining input bytes from a file
            FileInputStream file = new FileInputStream(new File(path));

            // creating workbook instance that refers to .xls file
            Workbook wb = new XSSFWorkbook(file);

            // creating a sheet object to retrieve the object
            XSSFSheet shh = (XSSFSheet) wb.getSheetAt(0);

            Iterator<Row> rowIterator = shh.iterator();
            while(rowIterator.hasNext()){
                Row row = rowIterator.next();

                Iterator<Cell> cellIterator = row.iterator();
                while(cellIterator.hasNext()){
                    Cell cell = cellIterator.next();

                    if (row.getRowNum() == 0){
                        headerRaw.add(cell.getStringCellValue());
                    } else {
                        if (cell.getColumnIndex() == 0){
                            bookIdRaw.add(cell.getStringCellValue());
                        } else if (cell.getColumnIndex() == 1){
                            bookTitleRaw.add(cell.getStringCellValue());
                        } else if (cell.getColumnIndex() == 2){
                            authorIdRaw.add(cell.getStringCellValue());
                        }
                    }
                }
            }
            wb.close();
        } catch (Exception e){}

        output.add(headerRaw + ";" + bookIdRaw + ";" + bookTitleRaw + ";" + authorIdRaw);

        String[] data = output.toString().split(";");

        header = data[0].split(",");
        header[0] = header[0].replace("[", "");
        header[header.length-1] = header[header.length-1].replace("]", "");

        bookId = data[1].split(",");
        bookTitle = data[2].split(",");
        authorId = data[3].split(",");

        for ( int i = 0; i < bookTitle.length; i++){
            if ( i == 0){
                bookId[i] = bookId[i].replace("[", "");

                bookTitle[i] = bookTitle[i].replace("[", "");

                authorId[i] = authorId[i].replace("[", "");
            }
            else if ( i == bookTitle.length-1){
                bookId[i] = bookId[i].replace("]", "");
                bookId[i] = bookId[i].replace(" ", "");

                bookTitle[i] = bookTitle[i].replace("]", "");
                bookTitle[i] = bookTitle[i].replace(" ", "");

                authorId[i] = authorId[i].replace("]", "");
                authorId[i] = authorId[i].replace(" ", "");
            }
            else {
                bookId[i] = bookId[i].replace(" ", "");

                bookTitle[i] = bookTitle[i].replace(" ", "");

                authorId[i] = authorId[i].replace(" ", "");
            }
        }

        Book books = new Book(bookId, bookTitle, authorId, header);

        return books;
    }

    public static BookInfo getBookInfo(String path){
        ArrayList<String> headerRaw = new ArrayList<>();
        ArrayList<String> bookInfoIdRaw = new ArrayList<>();
        ArrayList<String> bookGenreRaw = new ArrayList<>();
        ArrayList<String> bookCmtRaw = new ArrayList<>();
        ArrayList<String> output = new ArrayList<>();

        String[] header = {};
        String[] bookInfoId = {};
        String[] bookGenre = {};
        String[] bookCmt = {};

        try {
            // obtaining input bytes from a file
            FileInputStream file = new FileInputStream(new File(path));

            // creating workbook instance that refers to .xls file
            Workbook wb = new XSSFWorkbook(file);

            // creating a sheet object to retrieve the object
            XSSFSheet shh = (XSSFSheet) wb.getSheetAt(2);

            Iterator<Row> rowIterator = shh.iterator();
            while(rowIterator.hasNext()){
                Row row = rowIterator.next();

                Iterator<Cell> cellIterator = row.iterator();
                while(cellIterator.hasNext()){
                    Cell cell = cellIterator.next();

                    if (row.getRowNum() == 0){
                        headerRaw.add(cell.getStringCellValue());
                    } else {
                        if (cell.getColumnIndex() == 2){
                            bookInfoIdRaw.add(cell.getStringCellValue());
                        } else if (cell.getColumnIndex() == 3){
                            bookGenreRaw.add(cell.getStringCellValue());
                        } else if (cell.getColumnIndex() == 4){
                            bookCmtRaw.add(cell.getStringCellValue());
                        }
                    }
                }
            }
            wb.close();
        } catch (Exception e){}

        output.add(headerRaw + ";" + bookInfoIdRaw + ";" + bookGenreRaw + ";" + bookCmtRaw);

        String[] data = output.toString().split(";");

        header = data[0].split(",");
        header[0] = header[0].replace("[", "");
        header[header.length-1] = header[header.length-1].replace("]", "");

        bookInfoId = data[1].split(",");
        bookGenre = data[2].split(",");
        bookCmt = data[3].split(",");

        for ( int i = 0; i < bookInfoId.length; i++){
            if ( i == 0){
                bookInfoId[i] = bookInfoId[i].replace("[", "");

                bookGenre[i] = bookGenre[i].replace("[", "");

                bookCmt[i] = bookCmt[i].replace("[", "");
            }
            else if ( i == bookInfoId.length-1){
                bookInfoId[i] = bookInfoId[i].replace("]", "");
                bookInfoId[i] = bookInfoId[i].replace(" ", "");

                bookGenre[i] = bookGenre[i].replace("]", "");
                bookGenre[i] = bookGenre[i].replace(" ", "");

                bookCmt[i] = bookCmt[i].replace("]", "");
                bookCmt[i] = bookCmt[i].replace(" ", "");
            }
            else {
                bookInfoId[i] = bookInfoId[i].replace(" ", "");

                bookGenre[i] = bookGenre[i].replace(" ", "");

                bookCmt[i] = bookCmt[i].replace(" ", "");
            }
        }

        BookInfo booksInfo = new BookInfo(bookInfoId, bookGenre, bookCmt);

        return booksInfo;
    }

    public static void insertBook (String path, String bookId, String title, String authorId, String genre, String cmt) throws IOException {

        book = Functions.getBook(path);

        FileInputStream file = new FileInputStream(new File(path));

        XSSFWorkbook workbook = new XSSFWorkbook(file);

        XSSFRow row;
        XSSFSheet bookSSH = (XSSFSheet) workbook.getSheetAt(0);
        XSSFSheet bookInfoSSH = (XSSFSheet) workbook.getSheetAt(2);

        Map<String, Object[]> bookData
                = new TreeMap<String, Object[]>();

        Map<String, Object[]> bookInfoData
                = new TreeMap<String, Object[]>();

        bookData.put(
                String.valueOf(book.getBookLenghth() + 1),
                new Object[] { bookId, title, authorId });

        bookInfoData.put(
                String.valueOf(book.getBookLenghth() + 1),
                new Object[] { bookId, bookId, bookId, genre, cmt});

        Set<String> bookKeyId = bookData.keySet();
        Set<String> bookInfoKeyId = bookInfoData.keySet();

        for(String key : bookKeyId){
            row = bookSSH.createRow(book.getBookLenghth()+1);
            Object[] objectArr = bookData.get(key);
            int cellid = 0;

            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String)obj);
            }
        }

        for(String key : bookInfoKeyId){
            row = bookInfoSSH.createRow(book.getBookLenghth()+1);
            Object[] objectArr = bookInfoData.get(key);
            int cellid = 0;

            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String)obj);
            }
        }

        FileOutputStream out = new FileOutputStream(
                new File(path));

        workbook.write(out);
        out.close();
    }

    public static void insertAuthor (String path, String id, String firstname, String lastname, String COR) throws IOException {
        author = Functions.getAuthor(path);

        FileInputStream file = new FileInputStream(new File(path));

        XSSFWorkbook workbook = new XSSFWorkbook(file);

        XSSFRow row;
        XSSFSheet authorSSH = (XSSFSheet) workbook.getSheetAt(1);

        Map<String, Object[]> authorData
                = new TreeMap<String, Object[]>();

        authorData.put(
                String.valueOf(author.getAuthorsLength() + 1),
                new Object[] { id, firstname, lastname, COR});

        Set<String> authorKeyId = authorData.keySet();

        for(String key : authorKeyId){
            row = authorSSH.createRow(author.getAuthorsLength()+1);
            Object[] objectArr = authorData.get(key);
            int cellid = 0;

            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String)obj);
            }
        }

        FileOutputStream out = new FileOutputStream(
                new File(path));

        workbook.write(out);
        out.close();
    }

    public static void deleteData(String path, int selectedRow) throws IOException {
        book = Functions.getBook(path);

        FileInputStream file = new FileInputStream(path);

        XSSFWorkbook workbook = new XSSFWorkbook(file);

        XSSFSheet bookSSH = (XSSFSheet) workbook.getSheetAt(0);
        XSSFSheet bookSSH1 = (XSSFSheet) workbook.getSheetAt(2);

        XSSFRow rowToDelete = bookSSH.createRow(selectedRow);
        XSSFRow rowToDeleteBookInfo = bookSSH1.createRow(selectedRow);

        bookSSH.removeRow(rowToDelete);
        bookSSH1.removeRow(rowToDeleteBookInfo);

        FileOutputStream out = new FileOutputStream(
                new File(path));

        workbook.write(out);
        out.close();
    }
}

