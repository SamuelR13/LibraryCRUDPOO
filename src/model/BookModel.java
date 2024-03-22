package model;

import database.CRUD;
import database.ConfigDB;
import entity.Author;
import entity.Book;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Book objBook = (Book) obj;
        try {
            String sql = "INSERT INTO Libros (title,publicationDate,price,idAutor) VALUES (?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1,objBook.getTitle());
            objPrepare.setString(2,objBook.getPubicationDate());
            objPrepare.setInt(3,objBook.getPrice());
            objPrepare.setInt(4,objBook.getAuthor().getId());
            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while(objResult.next()){
                objBook.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"Insert was succesful");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
        return objBook;
    }

    @Override
    public List<Object> findAll() {
        // 1. Crear lista para guardar lo que nos devuelve la base de datos
        List<Object> listBooks = new ArrayList<>();

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            // 3. Escribimos el query en Sql
            String sql = "SELECT * FROM Libros;";

            //4. Usar el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Ejecutar el query y obtener el resultado (ResulSet)

            ResultSet objResult = objPrepare.executeQuery();

            // 6. Mientras haya un resultado siguiente hacer:
            while (objResult.next()) {

                // 6.1 Crear un coder
                Book objBook = new Book();

                //6.2 Llenar el objeto con la información de la base de datos del objeto ques está iterando
                objBook.setTitle(objResult.getString("title"));
                objBook.setPubicationDate(objResult.getString("publicationDate"));
                objBook.setPrice(objResult.getInt("price"));
                objBook.setAuthor((Author) objResult.getObject("idAutor"));

                //6.3 Agregamos el Coder a la lista
                listBooks.add(objBook);
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        //Paso 7. Cerrar la conexión
        ConfigDB.closeConnection();

        return listBooks;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    @Override
    public boolean delete(Object obj) {
        return false;
    }
}
