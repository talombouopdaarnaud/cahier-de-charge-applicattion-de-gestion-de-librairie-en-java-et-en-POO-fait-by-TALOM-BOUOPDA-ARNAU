/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vente_achat;

import gestion.de.librairie.Connecxion_a_DB;
import gestion_de_livre.Ouvrage;
import java.sql.Date;
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
public class Vente {
    private DefaultTableModel dt2;
        private DefaultTableModel dt;
    private int id;
    private int id_client;
    private int id_livre;
    int quantite;
    float prix_a_payer;
    float benefice;
    int id_vendeur;
    Date date;
    //constructeur de la classe 
    public  Vente(){
    }
    public  Vente(int id_client,int id_livre,int quantite,Date date){
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
      public boolean make_vente(String id_client,String  articles_qt_pt,String date,int id_vendeur,float benef,float prix_total){
       
       // INBS|QT_acheter|PTotal|#
       String[] tab = articles_qt_pt.split("#");
       Ouvrage ou=new Ouvrage();
       int taille=tab.length;
       int w=0;
       while(w<taille){
          System.out.println(tab[w]);
          String[] tab2 = tab[w].split("!");
           int taille2=tab2.length;
           int qt_stk=0;
           System.out.println("voici les detaille sur le livre n0 "+w+"bravo!!!!!");
           qt_stk=ou.get_qt_ouvrage_stock(tab2[0]);
           ou.udate_qt_ouvrage_stock(tab2[0],qt_stk-Integer.parseInt(tab2[1]));
           //System.out.println("ISBM="+tab2[0]);
           //System.out.println("QT="+tab2[1]);
           //System.out.println("PT="+tab2[2]);

          w=w+1;
       }
       Statement requete;
       //Ouvrage ou=new Ouvrage();
      Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande_add_user="INSERT INTO vente(id_client,articles,date,prix_total_a_payer,benefice,id_vendeur) VALUES ('"+id_client+"','"+articles_qt_pt+"','"+date+"','"+prix_total+"','"+benef+"','"+id_vendeur+"')";//change pass
        
       try{
            requete=maconec.ObtenirConnexion().createStatement();
            requete.executeUpdate(comande_add_user);//on insere le fourniseur                    
            //JOptionPane.showMessageDialog(null,"L'acha a ete enregister avec suceee ete ajouter avec sucess et sont mot de pass par defaut est :");
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
///// affiche tous les achat
                      ///RECUPER LA LISTE DES LIVRE D'UN RAYON
    public void aff_all_livre(JTable jTable2){
          dt2 = new DefaultTableModel();
          dt2.addColumn("id");
          dt2.addColumn("nom client");
          dt2.addColumn("date");
         dt2.addColumn("prix_total_a_payer");
         dt2.addColumn("benefice");
         dt2.addColumn("id_vendeur");
         dt2.addColumn("articles");
          jTable2.setModel(dt2);
          Statement requete2; 
          Connecxion_a_DB maconec=new Connecxion_a_DB(); 
          String comande2="SELECT * FROM vente";//REQUETE
             try{
             requete2=maconec.ObtenirConnexion().createStatement();
             ResultSet resul_requet2=requete2.executeQuery(comande2);
                int nb2=0;
                while(resul_requet2.next()){
                    nb2=nb2+1;
                Object [] vente ={resul_requet2.getString("id").toString(),resul_requet2.getString("id_client").toString(),resul_requet2.getDate("date"),resul_requet2.getString("prix_total_a_payer").toString(),resul_requet2.getFloat("benefice"),resul_requet2.getInt("id_vendeur"),resul_requet2.getString("articles").toString()};
                dt2.addRow(vente);
                }
             }  catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              } 
        
        }
 //afficher tous les livre qcher avec les detaille d'un achat
        //affiche les livre d'un auteur dans un tableau
               public void affiche_vente( JTable jTable1, int id){
           //creation model de table
          dt = new DefaultTableModel();
          dt.addColumn("ISBN");
          dt.addColumn("Quantité");
          dt.addColumn("PRIX_T");
          jTable1.setModel(dt);
       Statement requete; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="SELECT * FROM vente WHERE id='"+id+"'";//REQUETE
       try{
         requete=maconec.ObtenirConnexion().createStatement();
         ResultSet resul_requet=requete.executeQuery(comande);
          int nbr_user=0;
          String ex1="";
          while(resul_requet.next()){//compte le nombre de user avec le meme nom passer en parametre
                nbr_user=nbr_user+1;
                ex1=resul_requet.getString("articles").toString(); 
            }
            String[] list=ex1.split("#");
          int taille=list.length;
          int ik=0;
          while(ik<taille){
                     String[] list2=list[ik].split("!");
                        Object [] rayon ={list2[0],list2[1],list2[2]};
                dt.addRow(rayon);
                ik=ik+1;
          }

        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              }
       
                  
    }
               //
    public void set_liste_choix_client(JComboBox l){
           //creation model de table
       Statement requete; 
       Statement requete2; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande2="SELECT * FROM client";//REQUETE
       try{
         requete=maconec.ObtenirConnexion().createStatement();
          int nbr_user=0;
          int ID=-1;
          requete2=maconec.ObtenirConnexion().createStatement();
          ResultSet resul_requet2=requete.executeQuery(comande2);
                  while(resul_requet2.next()){//compte le nombre de user avec le meme nom passer en parametre
                nbr_user=nbr_user+1;
                      l.addItem(resul_requet2.getString("nom").toString()+" "+resul_requet2.getString("prenom").toString());
            }
        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              }
       
      ////////////////////////            
    } 
             
}
