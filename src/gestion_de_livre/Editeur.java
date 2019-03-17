/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_de_livre;

import vente_achat.*;
import gestion.de.librairie.Connecxion_a_DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ARNAUD
 */
public class Editeur {
    private DefaultTableModel dt;//variable pour les tabeau afficher les donne 
    private int id;
    private String nom;
    private String ville;
    private String telephone;
    //constructeur de la classe 
    public  Editeur(){
    }
    public  Editeur(String nom,String ville,String telephone){
        this.nom=nom;
        this.ville=ville;
        this.telephone=telephone;
    }
     //geter de la classe
    public int getid(){
    return this.id;
    }
    public String nom(){
    return this.nom;
    }
    public String ville(){
    return this.ville;
    }
    public String telephone(){
    return this.telephone;
    }

          //inscription d'un client
      public boolean make_editeur(String nom,String ville,String telephone){
       Statement requete; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="SELECT * FROM editeur WHERE nom='"+nom+"'";//REQUETE
       String comande_add_user="INSERT INTO editeur(nom,ville,telephone) VALUES ('"+nom+"','"+ville+"','"+telephone+"')";//change pass
        
       try{
         requete=maconec.ObtenirConnexion().createStatement();
         ResultSet resul_requet=requete.executeQuery(comande);
          int nbr_user=0;
          while(resul_requet.next()){//compte le nombre de client avec le meme nom passer en parametre
            nbr_user=nbr_user+1;
            }
            if(nbr_user==0){//le client nexiste pas l'insere
            requete.executeUpdate(comande_add_user);//on insere le fourniseur                    
            JOptionPane.showMessageDialog(null,"L'editeur  "+nom+" a ete ajouter avec sucess et sont mot de pass par defaut est :");
            return true;

            }else{//si le fourniseur existe deja
                    JOptionPane.showMessageDialog(null,"desole mais L'editeur  < "+nom+" > existe deja!");
                    return false;
                } 
        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              return false;
              }
       
                  
    }
      public void delete_editeur(String ID){
       Statement requete; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="DELETE FROM editeur WHERE  id='"+ID+"'";//REQUETE
       try{//SI LA CONNECTION REUSIR
                  requete=maconec.ObtenirConnexion().createStatement();
                  requete.executeUpdate(comande);   
                  JOptionPane.showMessageDialog(null,"l'editeur supprimer avec success");
              }
              catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
                JOptionPane.showMessageDialog(null,"echect de la suppression");
              System.out.println("Eche de la supression");
              }

    }
 //EDIT LE editeur
       public boolean edit_editeur(String nom,String ville,String telephone,int ID){
                   Statement requete;
                   //int ID=-1;
            Connecxion_a_DB maconec=new Connecxion_a_DB(); 
        //String comande="SELECT * FROM user WHERE  user_name='"+user_name+"' AND password='"+password+"'";//REQUETE
        try{
       requete=maconec.ObtenirConnexion().createStatement();
       // ResultSet resul_requet=requete.executeQuery(comande);
          int nbr_user=0;
           String test="";
            requete=maconec.ObtenirConnexion().createStatement();
            //change les infos
            String update="UPDATE editeur SET nom='"+nom+"',ville='"+ville+"',telephone='"+telephone+"'  WHERE id='"+ID+"'";//REQUETE
            requete.executeUpdate(update);  
            JOptionPane.showMessageDialog(null,"l'editeur modifier avec success");
            return true;

        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              JOptionPane.showMessageDialog(null,"echect CHANGEMENT DE MOT DE PASS");
              return false;
              }
       } 
       public int get_id(String nom){
           //creation model de table
       Statement requete; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="SELECT * FROM editeur WHERE nom='"+nom+"'";//REQUETE
       try{
         requete=maconec.ObtenirConnexion().createStatement();
         ResultSet resul_requet=requete.executeQuery(comande);
          int nbr_user=0;
          int ID=-1;
          while(resul_requet.next()){//compte le nombre de user avec le meme nom passer en parametre
                nbr_user=nbr_user+1;
                ID=resul_requet.getInt("id");
            }
           return ID;
        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              return -1;
              }
       
                  
    } 
       public void affiche_alleditor( JTable jTable1){
           //creation model de table
          dt = new DefaultTableModel();
          dt.addColumn("ID");
          dt.addColumn("NOM");
          dt.addColumn("TELEPHONE");
          dt.addColumn("ADRESSE");
          jTable1.setModel(dt);
       Statement requete; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="SELECT * FROM editeur";//REQUETE
       try{
         requete=maconec.ObtenirConnexion().createStatement();
         ResultSet resul_requet=requete.executeQuery(comande);
          int nbr_user=0;
          while(resul_requet.next()){//compte le nombre de user avec le meme nom passer en parametre
                nbr_user=nbr_user+1;
                Object [] editeur ={resul_requet.getInt("id"),resul_requet.getString("nom").toString(),resul_requet.getString("telephone").toString(),resul_requet.getString("ville").toString()};
                dt.addRow(editeur);
            }

        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              }
       
                  
    }
        
}
