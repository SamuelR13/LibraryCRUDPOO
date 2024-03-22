package controller;

import entity.Book;
import model.BookModel;
import model.CoderModel;

import javax.swing.*;

public class BookController {
    public static void getAll() {
        BookModel objModel = new BookModel();
        String listCoders = "🤷‍ BOOK LIST \n";

        for (Object iterador : objModel.findAll()) {
            //Convertimos del Object a Coder
            Book objCoder = (Book) iterador;
            listCoders += objCoder.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listCoders);
    }
    public static void create(){
        BookModel objModel = new BookModel();

        String title = JOptionPane.showInputDialog(null,"Enter the new book´s title");
        String publicationDate = JOptionPane.showInputDialog(null, "Enter the publication date");
        int price =Integer.parseInt(JOptionPane.showInputDialog(null,"Enter the price"));

    }


}
