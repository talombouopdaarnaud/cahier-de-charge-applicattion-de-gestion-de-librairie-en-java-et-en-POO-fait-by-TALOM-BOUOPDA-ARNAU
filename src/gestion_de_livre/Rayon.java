/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_de_livre;

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
public class Rayon {
     private DefaultTableModel dt;//variable pour les tabeau afficher les donne 
      private DefaultTableModel dt2;
    private int id;
    private String nom;
    private String liste;
        public Rayon() {

    }
        public  Rayon(String nom){
        this.nom=nom;
        this.liste="";
    }
        //geter
        //geter de la classe
    public int getid(){
    return this.id;
    }
    public String nom(){
    return this.nom;
    }
    public String prenom(){
    return this.nom;
    }
    public String adresse(){
    return this.liste;
    }
    ///cree un rayon dans notre librairoie
          //inscription d'un utilisateur
      public boolean make_Rayon(String nom){
       Statement requete; 
       String txt="";
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="SELECT * FROM rayon WHERE  nom='"+nom+"'";//REQUETE
       String comande_add_user="INSERT INTO rayon(nom,liste_des_livres) VALUES ('"+nom+"','"+txt+"')";//change pass
        
       try{
         requete=maconec.ObtenirConnexion().createStatement();
         ResultSet resul_requet=requete.executeQuery(comande);
          int nbr_user=0;
          while(resul_requet.next()){//compte le nombre de user avec le meme nom passer en parametre
            nbr_user=nbr_user+1;
            }
            if(nbr_user==0){//le rayon nexiste pas l'insere
            requete.executeUpdate(comande_add_user);//on insere le fourniseur                    
            JOptionPane.showMessageDialog(null,"le rayon "+nom+" a ete ajouter avec sucess");
            return true;

            }else{//si le fourniseur existe deja
                    JOptionPane.showMessageDialog(null,"desole mais Le rayon <"+nom+ "> existe deja!");
                    return false;
                } 
        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              return false;
              }
       
                  
    }
      //suprimer rayon
      //SUPPRESSION D'UN UTILISATEUR
      public void delete_rayon(String ID){
       Statement requete; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="DELETE FROM rayon WHERE  id='"+ID+"'";//REQUETE
       try{//SI LA CONNECTION REUSIR
                  requete=maconec.ObtenirConnexion().createStatement();
                  requete.executeUpdate(comande);   
                  JOptionPane.showMessageDialog(null,"le rayon supprimer avec success");
              }
              catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
                JOptionPane.showMessageDialog(null,"echect de la suppression");
              System.out.println("Eche de la supression");
              }

    }
      //edit rayon
      
             public boolean edit_rayon(int ID,String newnom){
            Statement requete;
            Connecxion_a_DB maconec=new Connecxion_a_DB(); 
        
        try{
        requete=maconec.ObtenirConnexion().createStatement();
        //change les infos
        String update="UPDATE rayon SET nom='"+newnom+"'  WHERE id='"+ID+"'";//REQUETE
        requete.executeUpdate(update);  
        JOptionPane.showMessageDialog(null,"modification reussir");
        return true;   
        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              return false;
              }
       }
    //recupere la liste des livre dans un rayon
        public boolean get_livres_rayon(String nom){
                   Statement requete;
                   int ID=-1;
            Connecxion_a_DB maconec=new Connecxion_a_DB(); 
        String comande="SELECT * FROM rayon WHERE  nom='"+nom+"'";//REQUETE
        try{
       requete=maconec.ObtenirConnexion().createStatement();
        ResultSet resul_requet=requete.executeQuery(comande);
          int nbr_user=0;
           String liste="";
          while(resul_requet.next()){//compte le nombre de user avec le meme nom passer en parametre
            nbr_user=nbr_user+1;
            liste=resul_requet.getString("liste_des_livres").toString();
            }
            
            JOptionPane.showMessageDialog(null,"vtre liste est :<<<<"+liste+"");
            return true;

           
        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              return false;
              }
       }
      //AFFICHE ALL RAYON
               public void affiche_allrayon( JTable jTable1){
           //creation model de table
          dt = new DefaultTableModel();
          dt.addColumn("ID");
          dt.addColumn("NOM");
          jTable1.setModel(dt);
       Statement requete; 
        Statement requete2; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="SELECT * FROM rayon ";//REQUETE
       
       try{
         requete=maconec.ObtenirConnexion().createStatement();
         ResultSet resul_requet=requete.executeQuery(comande);
          int nbr_user=0;
          while(resul_requet.next()){//compte le nombre de user avec le meme nom passer en parametre
                nbr_user=nbr_user+1;
                Object [] rayon ={resul_requet.getInt("id"),resul_requet.getString("nom").toString()};
                dt.addRow(rayon);        
            }

        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              }
       
                  
    }
               ///RECUPER LA LISTE DES LIVRE D'UN RAYON
    public void get_livre_rayon( String nom,JTable jTable2){
          dt2 = new DefaultTableModel();
          dt2.addColumn(" ");
          dt2.addColumn("TITRE");
          dt2.addColumn("ISBM");
          jTable2.setModel(dt2);
          Statement requete2; 
          Connecxion_a_DB maconec=new Connecxion_a_DB(); 
          String comande2="SELECT * FROM ouvrage WHERE rayon='"+nom+"' ";//REQUETE
             try{
             requete2=maconec.ObtenirConnexion().createStatement();
             ResultSet resul_requet2=requete2.executeQuery(comande2);
                int nb2=0;
                while(resul_requet2.next()){
                    nb2=nb2+1;
                Object [] livre ={nb2,resul_requet2.getString("ISBM").toString(),resul_requet2.getString("titre").toString()};
                dt2.addRow(livre);
                }
             }  catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              } 
        
        }
                
                
    public int get_id(String nom){
           //creation model de table
       Statement requete; 

       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="SELECT * FROM rayon WHERE nom='"+nom+"'";//REQUETE
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
}
