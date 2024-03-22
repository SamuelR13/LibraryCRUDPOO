package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Esta clase se encargará de establecer y cerrar la conexión con la base de datos
public class ConfigDB {
    //Este atributo tendrá el estado de la conexión
    public static Connection objConnection = null;

    //Método para conectar la BD
    public  static  Connection openConnection(){

        try{
            //Llamamos el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Creamos las variables de conexión
            String url = "jdbc:mysql://bzorqldibu1cp3hxlcho-mysql.services.clever-cloud.com:3306/bzorqldibu1cp3hxlcho";
            String user = "unripwkxbdsvv9hg";
            String password = "T6aRYbk10RbEwwvs3dYt";

            //Establecer la conexión
            objConnection = (Connection) DriverManager.getConnection(url,user,password);
            System.out.println("Me conecté perfectamente!!!!");

        }catch (ClassNotFoundException error){
            System.out.println("ERROR >> Driver no Instalado "+error.getMessage());
        }catch (SQLException error){
            System.out.println("ERROR >> error al conectar con la base de datos"+ error.getMessage());
        }

        return  objConnection;
    }

    //Método para finalizar una conexión
    public static void closeConnection(){
        try{
            //Si hay una conexión activa entonces la cierra
            if ( objConnection != null){
                objConnection.close();
                System.out.println("Se finalizó la conexión con éxito");
            }
        }catch (SQLException e){
            System.out.println("Error: "+ e.getMessage());
        }
    }
}
