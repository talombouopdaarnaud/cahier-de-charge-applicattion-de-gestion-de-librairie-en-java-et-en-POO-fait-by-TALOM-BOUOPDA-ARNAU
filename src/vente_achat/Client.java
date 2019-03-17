/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vente_achat;

import gestion.de.librairie.Connecxion_a_DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ARNAUD
 */
public class Client {
    private DefaultTableModel dt;//variable pour les tabeau afficher les donne 
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String  telephone;
    //constructeur de la classe 
    public  Client(){
    }
    public  Client(String nom,String prenom,String adresse,String  telephone){
        this.nom=nom;
        this.prenom=prenom;
        this.adresse=adresse;
        this.telephone=telephone;
    }
     //geter de la classe
    public int getid(){
    return this.id;
    }
    public String nom(){
    return this.nom;
    }
    public String prenom(){
    return this.prenom;
    }
    public String adresse(){
    return this.adresse;
    }
    public String telephone(){
    return this.telephone;
    }
          //inscription d'un client
      public boolean make_client(String nom,String prenom,String telephone,String adresse){
       Statement requete; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="SELECT * FROM client WHERE nom='"+nom+"' AND prenom='"+prenom+"'";//REQUETE
       String comande_add_user="INSERT INTO client(nom,prenom,telephone,adresse) VALUES ('"+nom+"','"+prenom+"','"+telephone+"','"+adresse+"')";//change pass
        
       try{
         requete=maconec.ObtenirConnexion().createStatement();
         ResultSet resul_requet=requete.executeQuery(comande);
          int nbr_user=0;
          while(resul_requet.next()){//compte le nombre de client avec le meme nom passer en parametre
            nbr_user=nbr_user+1;
            }
            if(nbr_user==0){//le client nexiste pas l'insere
            requete.executeUpdate(comande_add_user);//on insere le fourniseur                    
            JOptionPane.showMessageDialog(null,"Le client  "+nom+" " +prenom+"a ete ajouter avec sucess et sont mot de pass par defaut est :");
            return true;

            }else{//si le fourniseur existe deja
                    JOptionPane.showMessageDialog(null,"desole mais L'utilisateur < "+nom+" " +prenom+" > existe deja!");
                    return false;
                } 
        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              return false;
              }
       
                  
    }
            public void delete_client(String  ID){
       Statement requete; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="DELETE FROM client WHERE  id='"+ID+"'";//REQUETE
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
       public boolean edit_client(String nom,String prenom,String adresse,String telephone,int ID){
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
            String update="UPDATE client SET nom='"+nom+"',prenom='"+prenom+"',adresse='"+adresse+"',telephone='"+telephone+"'  WHERE id='"+ID+"'";//REQUETE
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
 
       public void affiche_allclient( JTable jTable1){
           //creation model de table
          dt = new DefaultTableModel();
          dt.addColumn("ID");
          dt.addColumn("NOM");
          dt.addColumn("PRENOM");
          dt.addColumn("TELEPHONE");
          dt.addColumn("ADRESSE");
          jTable1.setModel(dt);
       Statement requete; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="SELECT * FROM client ";//REQUETE
       try{
         requete=maconec.ObtenirConnexion().createStatement();
         ResultSet resul_requet=requete.executeQuery(comande);
          int nbr_user=0;
          while(resul_requet.next()){//compte le nombre de user avec le meme nom passer en parametre
                nbr_user=nbr_user+1;
                Object [] user ={resul_requet.getInt("id"),resul_requet.getString("nom").toString(),resul_requet.getString("prenom").toString(),resul_requet.getString("telephone").toString(),resul_requet.getString("adresse").toString()};
                dt.addRow(user);
            }

        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              }
       
                  
    }
       public int get_id(String nom,String prenom){
           //creation model de table
       Statement requete; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="SELECT * FROM client WHERE nom='"+nom+"' AND prenom='"+prenom+"'";//REQUETE
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
       //cree un combox de client 
       //cree un combox d'imbs


}
