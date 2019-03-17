/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilisateurs;
import gestion.de.librairie.Connecxion_a_DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author ARNAUD
 */
public class Libraire {
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String user_name;
    private String password;
    private String  telephone;
    private String indice;
    private String type;
    //constructeur de la classe 
    public  Libraire(){
                this.nom="";
        this.prenom="";
        this.adresse="";
        this.user_name="";
        this.password="";
        this.telephone="";
        this.indice="";
    }
    public  Libraire(String type,String nom,String prenom,String adresse,String user_name,String password,String  telephone,String indice){
        this.nom=nom;
        this.prenom=prenom;
        this.adresse=adresse;
        this.user_name=user_name;
        this.password=password;
        this.telephone=telephone;
        this.indice=indice;
        this.type=type;
    }
    //geter de la classe
    public int getid(){
    return this.id;
    }
    public String gettype(){
    return this.type;
    }
    public String nom(){
    return this.nom;
    }
     public void setid(int id){
    this.id=id;
    }
    public String prenom(){
    return this.prenom;
    }
    public String adresse(){
    return this.adresse;
    }
    public String user_name(){
    return this.user_name;
    }
    public String password(){
    return this.password;
    }
    public String telephone(){
    return this.telephone;
    }
    public String indice(){
    return this.indice;
    }      
    //login d'un utilisateur
        public Libraire login(String user_name,String password){
            Statement requete;
            Libraire lib=new Libraire();
            Connecxion_a_DB maconec=new Connecxion_a_DB(); 
        String comande="SELECT * FROM user WHERE  user_name='"+user_name+"' AND password='"+password+"'";//REQUETE
        try{
       requete=maconec.ObtenirConnexion().createStatement();
        ResultSet resul_requet=requete.executeQuery(comande);
          int nbr_user=0;
          while(resul_requet.next()){//compte le nombre de user avec le meme nom passer en parametre
            nbr_user=nbr_user+1;
            lib=new Libraire(resul_requet.getString("type").toString(),resul_requet.getString("nom").toString(),resul_requet.getString("prenom").toString(),resul_requet.getString("adresse").toString(),resul_requet.getString("user_name").toString(),resul_requet.getString("password").toString(),resul_requet.getString("telephone").toString(),resul_requet.getString("indice").toString());
            lib.setid(resul_requet.getInt("id"));
            }
            if(nbr_user==1){//le user a ete identifier                             
            return lib;

            }else{//echet d'identification du user
                    JOptionPane.showMessageDialog(null,"Erreur « le nom d’utilisateur ou mot de passe est incorrecte »");
                    return lib;
                } 
        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
            JOptionPane.showMessageDialog(null,"probleme d'acces à la base de donnees");
              System.out.println(ex);
              return lib;
              }
        }
        
//get indice mot de passe 
       public String get_indice_password(String user_name){
           Statement requete;
            Connecxion_a_DB maconec=new Connecxion_a_DB(); 
        String comande="SELECT * FROM user WHERE  user_name='"+user_name+"'";//REQUETE
        try{
       requete=maconec.ObtenirConnexion().createStatement();
        ResultSet resul_requet=requete.executeQuery(comande);
        String indice="";
          int nbr_user=0;
          while(resul_requet.next()){//compte le nombre de user avec le meme nom passer en parametre
            nbr_user=nbr_user+1;
            indice=resul_requet.getString("indice").toString();
            }
            if(nbr_user==1){//le user a ete identifier
            return indice;

            }else{//echet d'identification du user
                    JOptionPane.showMessageDialog(null,"l'utilisateur "+user_name+" n'existe pas");
                    return "";
                }
        } 
            catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              return "rien";
              }

       
       }
//edit password
       public boolean edit_password(String user_name,String password,String new_password,String new_indice){
                   Statement requete;
                   int ID=-1;
            Connecxion_a_DB maconec=new Connecxion_a_DB(); 
        String comande="SELECT * FROM user WHERE  user_name='"+user_name+"' AND password='"+password+"'";//REQUETE
        try{
       requete=maconec.ObtenirConnexion().createStatement();
        ResultSet resul_requet=requete.executeQuery(comande);
          int nbr_user=0;
           String test="";
          while(resul_requet.next()){//compte le nombre de user avec le meme nom passer en parametre
            nbr_user=nbr_user+1;
            ID=resul_requet.getInt("id");
            }
            if(nbr_user==1){//le user a ete identifier
            requete=maconec.ObtenirConnexion().createStatement();
            //change les infos
            String update="UPDATE user SET password='"+new_password+"',indice='"+new_indice+"'  WHERE id='"+ID+"'";//REQUETE
            requete.executeUpdate(update);  
            JOptionPane.showMessageDialog(null,"Le mot de pass et l'indice de "+user_name+"on ete changer");
            return true;

            }else{//echet d'identification du user
                    JOptionPane.showMessageDialog(null,"echect CHANGEMENT DE MOT DE PASS");
                    return false;
                } 
        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              return false;
              }
       } 
}
