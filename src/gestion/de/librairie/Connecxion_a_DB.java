package gestion.de.librairie;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ARNAUD
 */
import java.sql.*;
public class Connecxion_a_DB {
   String urlPilote="com.mysql.jdbc.Driver";
   String urlBaseDonnes="jdbc:mysql://localhost:3306/gestion_de_librairie";
   Connection con;
   //chargement de notre pilote
   
public Connecxion_a_DB(){
   try{
       Class.forName(urlPilote);
}
   catch(ClassNotFoundException ex){
   System.out.println(ex);
}
   //connection a la base de donne
   try{
       con=DriverManager.getConnection(urlBaseDonnes,"root","");
         System.out.println("connection a la base donne reusir");
   }
   catch( SQLException ex){
       System.out.println(ex);
   }
   
}
       public Connection ObtenirConnexion(){
         return con;
     }

}
