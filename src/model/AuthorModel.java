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
            String sql = "INSERT INTO Autores (name,nationality) VALUES (?,?);";
            PreparedStatement objPrepare =objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
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
                objAuthor.setId(objResult.getInt("id"));
                objAuthor.setName(objResult.getString("name"));
                objAuthor.setNationality(objResult.getString("nationality"));
                //6.3 Agregamos el Author a la lista
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
        Author objAuthor = (Author) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isUpdated = false;
        try {
            String sql = "UPDATE Autores SET name = ?, nationality = ? WHERE id = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            System.out.println(objAuthor);
            objPrepare.setString(1,objAuthor.getName());
            objPrepare.setString(2,objAuthor.getNationality());
            objPrepare.setInt(3,objAuthor.getId());
            int totalAffectedRows = objPrepare.executeUpdate();
            if (totalAffectedRows>0){
                JOptionPane.showMessageDialog(null, "The update was succesful");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        //1. Convertir el objeto a la entidad
        Author objAuthor = (Author) obj;
        //2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        //3. Crear una variable de estado
        boolean isDeleted = false;
        try {
            //4. Escribir la sentencia SQL
            String sql = "DELETE FROM Autores WHERE id = ?;";
            //5. Creamos el prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6.Dar valor al ?
            objPrepare.setInt(1, objAuthor.getId());

            //7. Ejecutamos el Query (executeUpdate) devuelve el número de registros afectados
            int totalAffectedRows = objPrepare.executeUpdate();

            //Si las filas afectadas fueron mayor a cero quiere decir que si eliminó algo
            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "The update was successful.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //8. Cerramos la conexión
        ConfigDB.closeConnection();
        return isDeleted;
    }


    public Author findById(int id) {
        //1. Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();
        //2. Crear el coder que vamos retornar
        Author objAuthor = null;

        try {
            //3. Sentencia SQL
            String sql = "SELECT * FROM Autores WHERE id = ?;";
            //4. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle valor al paremetro del query
            objPrepare.setInt(1, id);

            //6. Ejecutamos el Query
            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()) {
                objAuthor = new Author();
                objAuthor.setName(objResult.getString("name"));
                objAuthor.setId(objResult.getInt("id"));
                objAuthor.setNationality(objResult.getString("nationality"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //7.Cerrar la conexión
        ConfigDB.closeConnection();

        return objAuthor;
    }
    public ArrayList<Author> findByName(String name){
        ArrayList<Author> listAuthor = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Author objAuthor = null;
        try {
            String sql = "SELECT * FROM Autores WHERE name LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%"+name+"%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                objAuthor = new Author();
                objAuthor.setId(objResult.getInt("id"));
                objAuthor.setName(objResult.getString("name"));
                objAuthor.setNationality(objResult.getString("nationality"));
                listAuthor.add(objAuthor);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return listAuthor;
    }
}
