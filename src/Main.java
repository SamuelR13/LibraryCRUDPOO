import controller.AuthorController;
import controller.BookController;
import entity.Book;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String option = "";
        String option1 = "";
        String option2 = "";

        do {
            option = JOptionPane.showInputDialog("""
                    1. Menú Autores.
                    2. Menú Libros.
                    3. Exit
                                        
                    Choose an option:
                    """);
            switch (option) {
                case "1":
                    do {
                        option1 = JOptionPane.showInputDialog("""
                        1. Agregar Autores.
                        2. Eliminar Autores.
                        3. Editar Autores.
                        4. Buscar por Nombre.
                        5. Listar Autores.
                        6. Salir
                                        
                        Choose an option:
                    """);
                        switch (option1) {
                            case "1":
                                AuthorController.create();
                                break;

                            case "2":
                                AuthorController.delete();
                                break;

                            case "3":
                                AuthorController.update();
                                break;

                            case "4":
                                AuthorController.getByName();
                                break;
                            case "5":
                                AuthorController.getAll();
                                break;
                        }
                    } while (!option1.equals("6"));
                    break;


                case "2":
                    do {
                        option2 = JOptionPane.showInputDialog("""
                        1. Agregar Libro.
                        2. Eliminar Libro.
                        3. Editar Libro.
                        4. Buscar por Nombre.
                        5. Buscar por ID.
                        6. Buscar por Autor.
                        7. Listar Libro.
                        8. Salir
                                        
                        Choose an option:
                    """);
                        switch (option2) {
                            case "1":
                                BookController.create();
                                break;

                            case "2":
                                BookController.delete();
                                break;

                            case "3":
                                BookController.update();
                                break;
                            case "4":
                                BookController.getByName();
                                break;
                            case "5":
                                BookController.getById();
                                break;
                            case "6":
                                BookController.getByAutor();
                                break;
                            case "7":
                                BookController.getAll();
                                break;

                        }
                    } while (!option2.equals("8"));
                    break;


            }

        } while (!option.equals("3"));
    }
}