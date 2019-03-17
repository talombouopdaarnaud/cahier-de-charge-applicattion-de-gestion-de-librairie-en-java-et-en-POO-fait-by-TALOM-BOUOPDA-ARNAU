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
public class Auteur {
     private DefaultTableModel dt;//variable pour les tabeau afficher les donne 
    private int id;
    private String nom;
    private String prenom;
    private String liste_livre;
    //constructeur de la classe 
    public  Auteur(){
    }
    public  Auteur(String nom,String prenom){
        this.nom=nom;
        this.prenom=prenom;
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
       //inscription d'un client
      public boolean make_auteur(String nom,String prenom){
       Statement requete; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="SELECT * FROM auteur WHERE nom='"+nom+"' AND prenom='"+prenom+"'";//REQUETE
       String comande_add_user="INSERT INTO auteur(nom,prenom,liste_livre) VALUES ('"+nom+"','"+prenom+"','')";//change pass
        
       try{
         requete=maconec.ObtenirConnexion().createStatement();
         ResultSet resul_requet=requete.executeQuery(comande);
         
          int nbr_user=0;
          while(resul_requet.next()){//compte le nombre de client avec le meme nom passer en parametre
            nbr_user=nbr_user+1;
            }
            if(nbr_user==0){//le client nexiste pas l'insere
            requete.executeUpdate(comande_add_user);//on insere le fourniseur                    
            JOptionPane.showMessageDialog(null,"L'auteur  "+nom+" a ete ajouter avec sucess et sont mot de pass par defaut est :");
            return true;

            }else{//si le fourniseur existe deja
                    JOptionPane.showMessageDialog(null,"desole mais cet auteur  < "+nom+" > existe deja!");
                    return false;
                } 
        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              return false;
              }
       
                  
    }
      public void delete_auteur(int ID){
       Statement requete; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="DELETE FROM auteur WHERE  id='"+ID+"'";//REQUETE
       try{//SI LA CONNECTION REUSIR
                  requete=maconec.ObtenirConnexion().createStatement();
                  requete.executeUpdate(comande);   
                  JOptionPane.showMessageDialog(null,"l'auteur supprimer avec success");
              }
              catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
                JOptionPane.showMessageDialog(null,"echect de la suppression");
              System.out.println("Eche de la supression");
              }

    }
 //EDIT LE editeur
       public boolean edit_editeur(String nom,String prenom,int ID){
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
            String update="UPDATE auteur SET nom='"+nom+"',prenom='"+prenom+"'  WHERE id='"+ID+"'";//REQUETE
            requete.executeUpdate(update);  
            JOptionPane.showMessageDialog(null,"l'auteur modifier avec success");
            return true;

        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              JOptionPane.showMessageDialog(null,"echect CHANGEMENT DE MOT DE PASS");
              return false;
              }
       }
       
       
       
    public String udate_liste_livre_auteur( String nomlivre,String nom,String prenom){
          /*dt2 = new DefaultTableModel();
          dt2.addColumn("ID");
          dt2.addColumn("NOM");
          dt2.addColumn("LISTE DES LIVRE");
          jTable2.setModel(dt2);*/
          Statement requete2; 
             
          Connecxion_a_DB maconec=new Connecxion_a_DB(); 
          String comande2="SELECT * FROM auteur WHERE nom='"+nom+"' AND prenom='"+prenom+"' ";//REQUETE
             try{
             requete2=maconec.ObtenirConnexion().createStatement();
            
             ResultSet resul_requet2=requete2.executeQuery(comande2);
                int nb2=0;
                String liste=""; 
                while(resul_requet2.next()){
                liste=resul_requet2.getString("liste_livre").toString();
               liste=liste+","+nomlivre;
                }
             Statement requete;
             requete=maconec.ObtenirConnexion().createStatement();
             String update="UPDATE auteur SET liste_livre='"+liste+"'  WHERE nom='"+nom+"' AND prenom='"+prenom+"'";//REQUETE
             requete.executeUpdate(update);
             JOptionPane.showMessageDialog(null,liste);
                  return(liste);
             }  catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              return"";
              } 
        
        }
    public void affiche_allauteur( JTable jTable1){
           //creation model de table
          dt = new DefaultTableModel();
          dt.addColumn("ID");
          dt.addColumn("NOM");
          dt.addColumn("PRENOM");
          dt.addColumn("liste_livres");
          jTable1.setModel(dt);
       Statement requete; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="SELECT * FROM auteur ";//REQUETE
       try{
         requete=maconec.ObtenirConnexion().createStatement();
         ResultSet resul_requet=requete.executeQuery(comande);
          int nbr_user=0;
          while(resul_requet.next()){//compte le nombre de user avec le meme nom passer en parametre
                nbr_user=nbr_user+1;
                Object [] user ={resul_requet.getInt("id"),resul_requet.getString("nom").toString(),resul_requet.getString("prenom").toString(),resul_requet.getString("liste_livre").toString()};
                dt.addRow(user);
            }

        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              }
       
                  
    }
    //affiche les livre d'un auteur dans un tableau
               public void affiche_allauteur_livre_a( JTable jTable1, int id){
           //creation model de table
          dt = new DefaultTableModel();
          dt.addColumn(" ");
          dt.addColumn("livres");
          jTable1.setModel(dt);
       Statement requete; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="SELECT * FROM auteur WHERE id='"+id+"'";//REQUETE
       try{
         requete=maconec.ObtenirConnexion().createStatement();
         ResultSet resul_requet=requete.executeQuery(comande);
          int nbr_user=0;
          String ex1="";
          while(resul_requet.next()){//compte le nombre de user avec le meme nom passer en parametre
                nbr_user=nbr_user+1;
                ex1=resul_requet.getString("liste_livre").toString(); 
            }
            String[] list=ex1.split(",");
          int taille=list.length;
          int ik=0;
          while(ik<taille){
                        Object [] rayon ={ik+1,list[ik]};
                dt.addRow(rayon);
                ik=ik+1;
          }

        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              }
       
                  
    }
//tyyyyyyyyyyyyyyyyyyyyyyyyytytytytytyty

        
}
