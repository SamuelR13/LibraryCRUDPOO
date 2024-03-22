package model;

import database.CRUD;
import database.ConfigDB;
import entity.Author;
import entity.Book;
import entity.Book;
import entity.Book;

import javax.sound.sampled.spi.AudioFileReader;
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
            PreparedStatement objPrepare =objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1,objBook.getTitle());
            objPrepare.setString(2,objBook.getPubicationDate());
            objPrepare.setInt(3,objBook.getPrice());
            objPrepare.setInt(4,objBook.getAuthor());
            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objBook.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"Insert was succesful\n");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());

        }
        ConfigDB.closeConnection();
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
            String sql = "SELECT * FROM Libros INNER JOIN Autores ON Autores.id = Libros.idAutor;";
            //4. Usar el prepareStatement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //5. Ejecutar el query y obtener el resultado (ResulSet)
            ResultSet objResult = objPrepare.executeQuery();
            // 6. Mientras haya un resultado siguiente hacer:
            while (objResult.next()) {
                // 6.1 Crear un coder
                Book objBook = new Book();
                //6.2 Llenar el objeto con la información de la base de datos del objeto ques está iterando
                objBook.setId(objResult.getInt("id"));
                objBook.setTitle(objResult.getString("title"));
                objBook.setPubicationDate(objResult.getString("publicationDate"));
                objBook.setPrice(objResult.getInt("price"));
                objBook.setAuthor(objResult.getInt("Libros.idAutor"));
                //6.3 Agregamos el Book a la lista
                objBook.setName(objResult.getString("Autores.name"));

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
        Book objBook = (Book) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isUpdated = false;
        try {
            String sql = "UPDATE Libros SET title = ?, publicationDate = ?, price = ?, idAutor = ? WHERE id = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1,objBook.getTitle());
            objPrepare.setString(2,objBook.getPubicationDate());
            objPrepare.setInt(3,objBook.getPrice());
            objPrepare.setInt(4,objBook.getAuthor());
            objPrepare.setInt(5,objBook.getId());
            int totalAffectedRows = objPrepare.executeUpdate();
            if (totalAffectedRows>0){
                JOptionPane.showMessageDialog(null, "The update was succesful");
                JOptionPane.showMessageDialog(null,objBook.toString());
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
        Book objBook = (Book) obj;

        //2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //3. Crear una variable de estado
        boolean isDeleted = false;

        try {
            //4. Escribir la sentencia SQL
            String sql = "DELETE FROM Libros WHERE id = ?;";
            //5. Creamos el prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6.Dar valor al ?
            objPrepare.setInt(1, objBook.getId());

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

    public Book findById(int id) {
        //1. Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();
        //2. Crear el coder que vamos retornar
        Book objBook = null;
        try {
            //3. Sentencia SQL
            String sql = "SELECT * FROM Libros WHERE id = ?;";
            //4. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle valor al paremetro del query
            objPrepare.setInt(1, id);

            //6. Ejecutamos el Query
            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()) {
                objBook = new Book();
                objBook.setTitle(objResult.getString("title"));
                objBook.setId(objResult.getInt("id"));
                objBook.setPrice(objResult.getInt("price"));
                objBook.setPubicationDate(objResult.getString("publicationDate"));
                objBook.setAuthor(objResult.getInt("idAutor"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //7.Cerrar la conexión
        ConfigDB.closeConnection();

        return objBook;
    }
    public ArrayList<Book> findByName(String name){
        ArrayList<Book> listBook = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Book objBook = null;
        try {
            String sql = "SELECT * FROM Libros WHERE title LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, "%"+name+"%");
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                objBook = new Book();
                objBook.setId(objResult.getInt("id"));
                objBook.setTitle(objResult.getString("title"));
                objBook.setPrice(objResult.getInt("price"));
                objBook.setPubicationDate(objResult.getString("publicationDate"));
                objBook.setAuthor(objResult.getInt("idAutor"));
                listBook.add(objBook);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return listBook;
    }
    public ArrayList<Book> findByAuthor(int id) {
        //1. Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();
        //2. Crear el coder que vamos retornar
        Author objAuthor = null;
        ArrayList<Book> listBook = new ArrayList<>();
        Book objBook = null;

        try {
            //3. Sentencia SQL
            String sql = "SELECT * FROM Libros WHERE idAutor = ?;";
            //4. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //5. Darle valor al paremetro del query
            objPrepare.setInt(1, id);
            //6. Ejecutamos el Query
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objBook = new Book();
                objBook.setId(objResult.getInt("id"));
                objBook.setTitle(objResult.getString("title"));
                objBook.setPrice(objResult.getInt("price"));
                objBook.setPubicationDate(objResult.getString("publicationDate"));
                objBook.setAuthor(objResult.getInt("idAutor"));
                listBook.add(objBook);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //7.Cerrar la conexión
        ConfigDB.closeConnection();
        return listBook;
    }
}
