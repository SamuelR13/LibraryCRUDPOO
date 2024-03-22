package controller;

import entity.Author;
import model.AuthorModel;

import javax.swing.*;

public class AuthorController {
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
}
