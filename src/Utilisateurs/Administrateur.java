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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ARNAUD
 */
public class Administrateur extends Libraire {
      //inscription d'un utilisateur
      private DefaultTableModel dt;//variable pour les tabeau afficher les donne 
      public boolean make_user(String nom,String prenom,String adresse,String user_name,String indice,String telephone,String type){
       Statement requete; 
       String password="12345";
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="SELECT * FROM user WHERE  user_name='"+user_name+"'";//REQUETE
       String comande_add_user="INSERT INTO user(nom,prenom,password,indice,telephone,adresse,user_name,type) VALUES ('"+nom+"','"+prenom+"','"+password+"','"+indice+"','"+telephone+"','"+adresse+"','"+user_name+"','"+type+"')";//change pass
        
       try{
         requete=maconec.ObtenirConnexion().createStatement();
         ResultSet resul_requet=requete.executeQuery(comande);
          int nbr_user=0;
          while(resul_requet.next()){//compte le nombre de user avec le meme nom passer en parametre
            nbr_user=nbr_user+1;
            }
            if(nbr_user==0){//le fourniseur nexiste pas l'insere
            requete.executeUpdate(comande_add_user);//on insere le fourniseur                    
            JOptionPane.showMessageDialog(null,"L'utilisateur "+user_name+" a ete ajouter avec sucess et sont mot de pass par defaut est :" +password);
            return true;

            }else{//si le fourniseur existe deja
                    JOptionPane.showMessageDialog(null,"desole mais L'utilisateur <"+user_name+ "> existe deja!");
                    return false;
                } 
        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              return false;
              }
       
                  
    }
       
      //SUPPRESSION D'UN UTILISATEUR
      public void delete_user(String ID){
       Statement requete; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="DELETE FROM user WHERE  id='"+ID+"'";//REQUETE
       try{//SI LA CONNECTION REUSIR
                  requete=maconec.ObtenirConnexion().createStatement();
                  requete.executeUpdate(comande);   
                  JOptionPane.showMessageDialog(null,"utilisateur supprimer avec success");
              }
              catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
                JOptionPane.showMessageDialog(null,"echect de la suppression");
              System.out.println("Eche de la supression");
              }

    }
//edit info utilisateur
      //edit password
       public boolean edit_user(String user_name,String nom,String prenom,String telephone,String adresse,String type,int ID){
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
            String update="UPDATE user SET nom='"+nom+"',prenom='"+prenom+"',telephone='"+telephone+"',adresse='"+adresse+"',user_name='"+user_name+"',type='"+type+"'  WHERE id='"+ID+"'";//REQUETE
            requete.executeUpdate(update);  
            JOptionPane.showMessageDialog(null,"utilisateur modifier avec success");
            return true;

        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              JOptionPane.showMessageDialog(null,"echect de la Modification");
              return false;
              }
       } 



       public void affiche_alluser( JTable jTable1){
           //creation model de table
          dt = new DefaultTableModel();
          dt.addColumn("ID");
          dt.addColumn("NOM");
          dt.addColumn("PRENOM");
          dt.addColumn("USER NAME");
          dt.addColumn("TELEPHONE");
          dt.addColumn("ADRESSE");
          dt.addColumn("TYPE");
          jTable1.setModel(dt);
       Statement requete; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="SELECT * FROM user ";//REQUETE
       try{
         requete=maconec.ObtenirConnexion().createStatement();
         ResultSet resul_requet=requete.executeQuery(comande);
          int nbr_user=0;
          while(resul_requet.next()){//compte le nombre de user avec le meme nom passer en parametre
                nbr_user=nbr_user+1;
                Object [] user ={resul_requet.getInt("id"),resul_requet.getString("nom").toString(),resul_requet.getString("prenom").toString(),resul_requet.getString("user_name").toString(),resul_requet.getString("telephone").toString(),resul_requet.getString("adresse").toString(),resul_requet.getString("type").toString()};
                dt.addRow(user);
            }

        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              }
       
                  
    }
       
       public int get_id(String user_name){
           //creation model de table
       Statement requete; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="SELECT * FROM user WHERE user_name='"+user_name+"'";//REQUETE
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
