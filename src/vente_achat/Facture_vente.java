/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vente_achat;

import gestion.de.librairie.Connecxion_a_DB;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author ARNAUD
 */
public class Facture_vente {
    private int id;
    private int id_client;
    private int id_livre;
    int quantite;
    float prix_a_payer;
    float benefice;
    Date date;
    //constructeur de la classe 
    public  Facture_vente(){
    }
    public  Facture_vente(int id_client,int id_livre,int quantite,Date date){
        this.id_client=id_client;
        this.id_livre=id_livre;
        this.quantite=quantite;
        this.date=date;
    }
     //geter de la classe
    public int getid(){
    return this.id;
    }
    public int id_client(){
    return this.id_client;
    }
    public int id_livre(){
    return this.id_livre;
    }
    public int quantite(){
    return this.quantite;
    }
    public Date date(){
    return this.date;
    }
          //inscription d'un client
      public boolean make_facture_vente(int id_client,int id_livre,int quantite,Date date){
       Statement requete; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande_add_user="INSERT INTO vente(id_client,id_livre,quantite,date,prix_total_a_payer,benefice) VALUES ('"+id_client+"','"+id_livre+"','"+quantite+"','"+date+"','"+id_livre+"','"+id_livre+"')";//change pass
        
       try{
         requete=maconec.ObtenirConnexion().createStatement();


            requete.executeUpdate(comande_add_user);//on insere le fourniseur                    
            JOptionPane.showMessageDialog(null,"L'acha a ete enregister avec suceee ete ajouter avec sucess et sont mot de pass par defaut est :");
            return true;

        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              return false;
              }
       
                  
    }
      public void delete_Facture_vente(int ID){
       Statement requete; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="DELETE FROM vente WHERE  id='"+ID+"'";//REQUETE
       try{//SI LA CONNECTION REUSIR
                  requete=maconec.ObtenirConnexion().createStatement();
                  requete.executeUpdate(comande);   
                  JOptionPane.showMessageDialog(null,"client supprimer avec success");
              }
              catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
                JOptionPane.showMessageDialog(null,"echect de la suppression");
              System.out.println("Eche de la supression");
              }

    }
 //EDIT LE CLIENT
       public boolean edit_facture_vente(int id_client,int id_livre,int quantite,Date date,int ID){
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
            String update="UPDATE vente SET id_client='"+id_client+"',id_livre='"+id_livre+"',date='"+date+"',quantite='"+quantite+"'  WHERE id='"+ID+"'";//REQUETE
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
