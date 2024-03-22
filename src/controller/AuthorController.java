package controller;

import entity.Author;
import model.AuthorModel;

import javax.swing.*;

public class AuthorController {
    public static void getAll() {
        AuthorModel objModel = new AuthorModel();
        String listAuthors = "🤷AUTORES LIST \n";

        for (Object iterador : objModel.findAll()) {
            //Convertimos del Object a Author
            Author objAuthor = (Author) iterador;
            listAuthors += objAuthor.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listAuthors);
    }

    public static String getAllString() {
        AuthorModel objModel = new AuthorModel();
        String listAuthors = "🤷AUTORES LIST \n";

        for (Object iterador : objModel.findAll()) {
            //Convertimos del Object a Author
            Author objAuthor = (Author) iterador;
            listAuthors += objAuthor.toString() + "\n";
        }

        return listAuthors;
    }
    public static void create(){
        AuthorModel objModel = new AuthorModel();

        String name = JOptionPane.showInputDialog(null,"Enter the new Aurthor´s name");
        String nationality = JOptionPane.showInputDialog(null,"Enter the new Aurthor´s nationality");

        Author objAuthor = new Author();
        objAuthor.setName(name);
        objAuthor.setNationality(nationality);

        objModel.insert(objAuthor);

        JOptionPane.showMessageDialog(null, objAuthor.toString());
    }
    public static void delete() {
        AuthorModel objAuthorModel = new AuthorModel();

        String listAuthors = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listAuthors + "\n Enter the Id the coder to delete: "));

        Author objAuthor = objAuthorModel.findById(idDelete);


        if (objAuthor== null){
            JOptionPane.showMessageDialog(null,"Author not found");
        }else {
            int confirm = JOptionPane.showConfirmDialog(null,"Are you sure want  to delete the  Author? All Author's books will be delete too \n"+ objAuthor.toString());

            if (confirm == 0) objAuthorModel.delete(objAuthor);

        }
    }
    public static void  update(){
        AuthorModel objModel = new AuthorModel();
        String listAuthors = getAllString();
        int  searchId =  Integer.parseInt(JOptionPane.showInputDialog(null,  listAuthors + "\nEnter the id to update"));
        Author objAuthor =objModel.findById(searchId);

        if(objAuthor==null){
            JOptionPane.showMessageDialog(null,"Id not found");
        }else{
            String name = JOptionPane.showInputDialog(null,"Enter the new Aurthor´s name",objAuthor.getName());
            String nationality = JOptionPane.showInputDialog(null,"Enter the new Aurthor´s nationality",objAuthor.getNationality());
            objAuthor.setName(name);
            objAuthor.setNationality(nationality);
            objModel.update(objAuthor);
        }
    }
    public static void getByName(){
        AuthorModel objModel = new AuthorModel();
        String name = JOptionPane.showInputDialog("Enter the name for search");
        String Authors = "";
        for (Author temporal :objModel.findByName(name)){
            if (temporal == null) {
                JOptionPane.showMessageDialog(null,"Autor not found");
            }else{
                Authors+=temporal.toString();
            }
        }
        JOptionPane.showMessageDialog(null,Authors);
    }
}
