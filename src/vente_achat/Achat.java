/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vente_achat;

import gestion.de.librairie.Connecxion_a_DB;
import java.util.Date;
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
public class Achat {
    private int id;
    private DefaultTableModel dt;//variable pour les tabeau afficher les donne 
    private String founiseur;
    private int nombre_examplair;
    private float prix_unitaire;
    private float montant_verse;
    private float reste=(nombre_examplair*prix_unitaire)-montant_verse;
    private float benefice;
    private Date date_publication;
    private Date date_achat;
    private String ISBM;
    private String titre;
    private String liste_auteur;
    //constructeur de la classe 
    public  Achat(){
    }


          //inscription d'un client
      public boolean make_Achat(String founiseur,int  nombre_examplair,float prix_unitaire,float benefice,String date_achat,String ISBM,float montant_verse,int id){
       Statement requete; 
       float prix_total=nombre_examplair*prix_unitaire;
       float rest=prix_total-montant_verse;
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       
       String comande_add_user="INSERT INTO achat(founiseur,nombre_examplair,prix_unitaire,date_achat,ISBM,prix_total,montant_verse,reste,PAR) VALUES ('"+founiseur+"','"+nombre_examplair+"','"+prix_unitaire+"','"+date_achat+"','"+ISBM+"','"+prix_total+"','"+montant_verse+"','"+rest+"','"+id+"')";//change pass
      // String comande2="INSERT INTO ouvrage(titre,nb_page,qt_stock,prix_unitaire,ISBM,benefice,date_publication,liste_auteur,qt_seuille) VALUES ('"+titre+"','"+nb_page+"','"+nombre_examplair+"','"+prix_unitaire+"','"+ISBM+"','"+benefice+"','"+date_publication+"','"+liste_auteur+"',50)";//change pass
       String comande3="SELECT * FROM ouvrage WHERE ISBM='"+ISBM+"'";//REQUETE
       //int qt_stock=30;
       try{  
         requete=maconec.ObtenirConnexion().createStatement();
            requete.executeUpdate(comande_add_user);//on insere le fourniseur 
            ResultSet resul_requet=requete.executeQuery(comande3);
              int nbr_user=0;
              
              int qt_stock=0;
            while(resul_requet.next()){//compte le nombre de user avec le meme nom passer en parametre
          
            nbr_user=nbr_user+1;
             qt_stock=resul_requet.getInt("qt_stock")+nombre_examplair;
            JOptionPane.showMessageDialog(null,qt_stock);
            
            }
            float p_vente=prix_unitaire+benefice;
            String update="UPDATE ouvrage SET prix_unitaire='"+prix_unitaire+"',qt_stock='"+qt_stock+"',benefice='"+benefice+"',prix_vente='"+p_vente+"'  WHERE ISBM='"+ISBM+"'";
            requete.executeUpdate(update);
            //requete.executeUpdate(comande2);
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
       String comande="DELETE FROM achat WHERE  id='"+ID+"'";//REQUETE
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
             
            JOptionPane.showMessageDialog(null,"le client modifier avec success");
            return true;

        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              JOptionPane.showMessageDialog(null,"echect CHANGEMENT DE MOT DE PASS");
              return false;
              }
       } 
       
       
       /////////////////////////////////////////////////////////////affiche
              public void affiche_allachat( JTable jTable1){
           //creation model de table
          dt = new DefaultTableModel();
          dt.addColumn("ID");
          dt.addColumn("FOURNISSEUR");
          dt.addColumn("ISBM");
          dt.addColumn("nombre_examplaire");
          dt.addColumn("prix_unitaire");
          dt.addColumn("prix_total");
          dt.addColumn("montant_verse");
          dt.addColumn("reste");
          dt.addColumn("DATE");
          dt.addColumn("PAR");
          jTable1.setModel(dt);
       Statement requete; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="SELECT * FROM achat ";//REQUETE
       try{
         requete=maconec.ObtenirConnexion().createStatement();
         ResultSet resul_requet=requete.executeQuery(comande);
          int nbr_user=0;
          while(resul_requet.next()){//compte le nombre de user avec le meme nom passer en parametre
                nbr_user=nbr_user+1;
                Object [] user ={resul_requet.getInt("id"),resul_requet.getString("founiseur").toString(),resul_requet.getString("ISBM").toString(),resul_requet.getInt("nombre_examplair"),resul_requet.getFloat("prix_unitaire"),resul_requet.getFloat("prix_total"),resul_requet.getFloat("montant_verse"),resul_requet.getFloat("reste"),resul_requet.getDate("date_achat"),resul_requet.getInt("PAR")};
                dt.addRow(user);
            }

        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              }
       
                  
    }

    public void set_liste_choix_isbm_foun(JComboBox f,JComboBox l){
           //creation model de table
       Statement requete; 
       Statement requete2; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="SELECT * FROM editeur";//REQUETE
       String comande2="SELECT * FROM ouvrage";//REQUETE
       try{
         requete=maconec.ObtenirConnexion().createStatement();
         ResultSet resul_requet=requete.executeQuery(comande);
          int nbr_user=0;
          int ID=-1;
          while(resul_requet.next()){//compte le nombre de user avec le meme nom passer en parametre
                nbr_user=nbr_user+1;
                      f.addItem(resul_requet.getString("nom").toString());
            }
          requete2=maconec.ObtenirConnexion().createStatement();
          ResultSet resul_requet2=requete.executeQuery(comande2);
                  while(resul_requet2.next()){//compte le nombre de user avec le meme nom passer en parametre
                nbr_user=nbr_user+1;
                      l.addItem(resul_requet2.getString("ISBM").toString());
            }
        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              }
       
      ////////////////////////            
    } 
        
}
