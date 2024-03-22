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

public class AuthorModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Author objAuthor = (Author) obj;
        try {
            String sql = "INSERT INTO Autores (name,nationality) VALUES (?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1,objAuthor.getName());
            objPrepare.setString(2,objAuthor.getNationality());
            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objAuthor.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"Insert was succesful");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());

        }
        ConfigDB.closeConnection();
        return objAuthor;
    }

    @Override
    public List<Object> findAll() {
        // 1. Crear lista para guardar lo que nos devuelve la base de datos
        List<Object> listAuthors = new ArrayList<>();

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            // 3. Escribimos el query en Sql
            String sql = "SELECT * FROM Autores;";

            //4. Usar el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Ejecutar el query y obtener el resultado (ResulSet)

            ResultSet objResult = objPrepare.executeQuery();

            // 6. Mientras haya un resultado siguiente hacer:
            while (objResult.next()) {

                // 6.1 Crear un coder
                Author objAuthor = new Author();

                //6.2 Llenar el objeto con la información de la base de datos del objeto ques está iterando
                objAuthor.setName(objResult.getString("name"));
                objAuthor.setNationality(objResult.getString("nationality"));

                //6.3 Agregamos el Coder a la lista
                listAuthors.add(objAuthor);
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        //Paso 7. Cerrar la conexión
        ConfigDB.closeConnection();

        return listAuthors;
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
