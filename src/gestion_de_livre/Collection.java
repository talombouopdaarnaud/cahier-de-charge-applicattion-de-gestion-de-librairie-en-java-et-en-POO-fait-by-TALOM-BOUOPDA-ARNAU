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

/**
 *
 * @author ARNAUD
 */
public class Collection {
    private int id;
    private String nom;
    private int id_editeur;
    //constructeur de la classe 
    public  Collection(){
    }
    public  Collection(String nom,int id_editeur){
        this.nom=nom;
        this.id_editeur=id_editeur;
    }
     //geter de la classe
    public int getid(){
    return this.id;
    }
    public String nom(){
    return this.nom;
    }
    public int id_editeur(){
    return this.id_editeur;
    }

          //inscription d'un client
      public boolean make_collection(String nom,int id_editeur){
       Statement requete; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="SELECT * FROM collection WHERE nom='"+nom+"'";//REQUETE
       String comande_add_user="INSERT INTO collection(nom,id_editeur) VALUES ('"+nom+"','"+id_editeur+"')";//change pass
        
       try{
         requete=maconec.ObtenirConnexion().createStatement();
         ResultSet resul_requet=requete.executeQuery(comande);
          int nbr_user=0;
          while(resul_requet.next()){//compte le nombre de client avec le meme nom passer en parametre
            nbr_user=nbr_user+1;
            }
            if(nbr_user==0){//le client nexiste pas l'insere
            requete.executeUpdate(comande_add_user);//on insere le fourniseur                    
            JOptionPane.showMessageDialog(null,"Le collection  "+nom+" a ete ajouter avec sucess et sont mot de pass par defaut est :");
            return true;

            }else{//si le fourniseur existe deja
                    JOptionPane.showMessageDialog(null,"desole mais La collection  < "+nom+" > existe deja!");
                    return false;
                } 
        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              return false;
              }
       
                  
    }
            public void delete_collection(int ID){
       Statement requete; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="DELETE FROM collection WHERE  id='"+ID+"'";//REQUETE
       try{//SI LA CONNECTION REUSIR
                  requete=maconec.ObtenirConnexion().createStatement();
                  requete.executeUpdate(comande);   
                  JOptionPane.showMessageDialog(null,"collection supprimer avec success");
              }
              catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
                JOptionPane.showMessageDialog(null,"echect de la suppression");
              System.out.println("Eche de la supression");
              }

    }
 //EDIT LE CLIENT
       public boolean edit_collection(String nom,int id_editeur,int ID){
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
            String update="UPDATE collection SET nom='"+nom+"',id_editeur='"+id_editeur+"'  WHERE id='"+ID+"'";//REQUETE
            requete.executeUpdate(update);  
            JOptionPane.showMessageDialog(null,"le client modifier avec success");
            return true;

        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              JOptionPane.showMessageDialog(null,"echect CHANGEMENT DE MOT DE PASS");
              return false;
              }
       } 
        
}
