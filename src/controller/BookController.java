package controller;

import entity.Author;
import entity.Book;
import model.AuthorModel;
import model.BookModel;
import model.BookModel;

import javax.swing.*;

public class BookController {
    public static void getAll() {
        BookModel objModel = new BookModel();
        String listBooks = "🤷‍ BOOK LIST \n";
        for (Object iterador : objModel.findAll()) {
            //Convertimos del Object a Book
            Book objBook = (Book) iterador;
            listBooks += objBook.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listBooks);
    }
    public static String getAllString() {
        BookModel objModel = new BookModel();
        String listBooks = "🤷‍️BOOKS LIST\n";

        for (Object iterador : objModel.findAll()) {
            //Convertimos del Object a Book
            Book objBook = (Book) iterador;
            listBooks += objBook.toString() + "\n";
        }

        return listBooks;
    }
    public static void create(){
        BookModel objModel = new BookModel();
        AuthorModel objAuthorModel = new AuthorModel();
        AuthorController objAuthorController = new AuthorController();
        int id = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter the Author ID\n" + "\n" + objAuthorController.getAllString()));
        Author objAuthor = objAuthorModel.findById(id);
        if (objAuthor==null){
            JOptionPane.showMessageDialog(null,"Author not found");
        }else{
            String title = JOptionPane.showInputDialog(null,"Enter the new book´s title");
            String publicationDate = JOptionPane.showInputDialog(null, "Enter the publication date\nYYYY/MM/DD");
            int price =Integer.parseInt(JOptionPane.showInputDialog(null,"Enter the price"));
            Book objBook = new Book();
            objBook.setTitle(title);
            objBook.setPubicationDate(publicationDate);
            objBook.setPrice(price);
            objBook.setAuthor(objAuthor.getId());
            objModel.insert(objBook);
            JOptionPane.showMessageDialog(null,"Insert was succesful\n" + objBook.toString());
        }
    }
    public static void delete() {
        BookModel objBookModel = new BookModel();

        String listBooks = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listBooks + "\n Enter ID`s book to delete: "));

        Book objBook = objBookModel.findById(idDelete);


        if (objBook== null){
            JOptionPane.showMessageDialog(null,"Book not found");
        }else {
            int confirm = JOptionPane.showConfirmDialog(null,"Are you sure want  to delete the book ? \n"+ objBook.toString());

            if (confirm == 0) objBookModel.delete(objBook);

        }
    }
    public static void  update(){
        BookModel objModel = new BookModel();
        String listBooks = getAllString();
        int  searchId =  Integer.parseInt(JOptionPane.showInputDialog(null,  listBooks + "\nEnter the id to update"));
        Book objBook =objModel.findById(searchId);

        if(objBook==null){
            JOptionPane.showMessageDialog(null,"Id not found");
        }else{
            String title = JOptionPane.showInputDialog(null,"Enter the new book´s title",objBook.getTitle());
            String publicationDate = JOptionPane.showInputDialog(null, "Enter the publication date",objBook.getPubicationDate());
            int price =Integer.parseInt(JOptionPane.showInputDialog(null,"Enter the price",objBook.getPrice()));

            objBook.setTitle(title);
            objBook.setPubicationDate(publicationDate);
            objBook.setPrice(price);
            objModel.update(objBook);
        }
    }
    public static void getByName(){
        BookModel objModel = new BookModel();
        String name = JOptionPane.showInputDialog("Enter the name for search");
        String Authors = "";
        for (Book temporal :objModel.findByName(name)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Coder not found");
            }else{
                Authors+=temporal.toString();
            }
        }
        JOptionPane.showMessageDialog(null,Authors);
    }
    public static void getById(){
        BookModel objModel = new BookModel();
        int id = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter the id for searching")) ;
        Book objBook = new Book();
        objBook = objModel.findById(id);
            if ( objBook == null) {
                JOptionPane.showMessageDialog(null,"Book not found");
            }else{
                JOptionPane.showMessageDialog(null,objBook.toString());
            }
    }
    public  static void getByAutor(){
        BookModel objModel = new BookModel();
        AuthorModel objAuthorModel = new AuthorModel();
        AuthorController objController = new AuthorController();
        String listAuthors = AuthorController.getAllString();
        int id = Integer.parseInt(JOptionPane.showInputDialog(listAuthors + "\n Enter the ID's Autor: "));
        String Authors = "";
        for (Book temporal :objModel.findByAuthor(id)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Coder not found");
            }else{
                Authors+=temporal.toString();
            }
        }
        JOptionPane.showMessageDialog(null,Authors);
    }


}
