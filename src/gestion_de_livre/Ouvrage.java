/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_de_livre;

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
public class Ouvrage {
    private int id;
    private DefaultTableModel dt;//variable pour les tabeau afficher les donne 
    private String titre;
    private int nb_page;
    private float prix_unitaire;
    private float benefice;
    private Date date_publication;
    private String ISBM;
    private int qt_stock;
    private int qt_seuille;
    private String liste_auteur;
    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setNb_page(int nb_page) {
        this.nb_page = nb_page;
    }

    public void setPrix_unitaire(float prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    public void setBenefice(float benefice) {
        this.benefice = benefice;
    }

    public void setDate_publication(Date date_publication) {
        this.date_publication = date_publication;
    }

    public void setISBM(String ISBM) {
        this.ISBM = ISBM;
    }

    public void setQt_stock(int qt_stock) {
        this.qt_stock = qt_stock;
    }

    public void setQt_seuille(int qt_seuille) {
        this.qt_seuille = qt_seuille;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public int getNb_page() {
        return nb_page;
    }

    public float getPrix_unitaire() {
        return prix_unitaire;
    }

    public float getBenefice() {
        return benefice;
    }

    public Date getDate_publication() {
        return date_publication;
    }

    public String getISBM() {
        return ISBM;
    }

    public int getQt_stock() {
        return qt_stock;
    }

    public int getQt_seuille() {
        return qt_seuille;
    }

    public String getListe_auteur() {
        return liste_auteur;
    }
    

    public void setListe_auteur(String liste_auteur) {
        this.liste_auteur = liste_auteur;
    }
    
    
    
    public Ouvrage(){
    
    }
    Ouvrage(String titre,int nb_page,float prix_unitaire,float benefice,Date date_publication,String ISBM,int qt_stock,int qt_seuille){
    
    }
    
              //inscription d'un utilisateur
      public boolean make_ouvrage(String titre,String ISBM,int nb_page,String date_plublication,int qt_seuille,JTable Tliste_auteur,String rayon,String editeur){
       Statement requete; int taille=Tliste_auteur.getRowCount(); String liste_auteur2="";
       int jk=0;
       Auteur au=new Auteur();
       while(jk<taille){
           String str = Tliste_auteur.getValueAt(jk,1).toString();
           String[] tab = str.split(" ");
           ;
            liste_auteur2=liste_auteur2+","+Tliste_auteur.getValueAt(jk,1).toString();
            au.udate_liste_livre_auteur(titre,tab[0],tab[1]);
            jk=jk+1;
            
       }
        //JOptionPane.showMessageDialog(null,liste_auteur2);
       String txt="";
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="SELECT * FROM ouvrage WHERE  ISBM='"+ISBM+"'";//REQUETE
       String comande_add_user="INSERT INTO ouvrage(titre,ISBM,nb_page,date_publication,qt_seuille,prix_unitaire,benefice,qt_stock,liste_auteur,rayon,prix_vente,editeur) VALUES ('"+titre+"','"+ISBM+"','"+nb_page+"','"+date_plublication+"','"+qt_seuille+"',0,0,0,'"+liste_auteur2+"','"+rayon+"',0,'"+editeur+"')";//change pass
       try{
         requete=maconec.ObtenirConnexion().createStatement();
         ResultSet resul_requet=requete.executeQuery(comande);
          int nbr_user=0;
          while(resul_requet.next()){//compte le nombre de user avec le meme nom passer en parametre
            nbr_user=nbr_user+1;
            }
            if(nbr_user==0){//le rayon nexiste pas l'insere
            requete.executeUpdate(comande_add_user);//on insere le fourniseur                    
            JOptionPane.showMessageDialog(null,"le rayon  a ete ajouter avec sucess");
            return true;

            }else{//si le fourniseur existe deja
                    JOptionPane.showMessageDialog(null,"desole mais Le rayon  existe deja!");
                    return false;
                } 
        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              return false;
              }
       
                  
    }
     
         public void delete_ouvrage(int ID){
       Statement requete; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="DELETE FROM ouvrage WHERE  id='"+ID+"'";//REQUETE
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
      
 //EDIT LE CLIENT
       public boolean edit_collection(String titre,int nb_page,float prix_unitaire,float benefice,String date_publication,String ISBM,int qt_seuille,String liste_auteur,int ID){
                   Statement requete;

       String txt="";
            Connecxion_a_DB maconec=new Connecxion_a_DB(); 
        try{
       requete=maconec.ObtenirConnexion().createStatement();
          int nbr_user=0;
           String test="";
            requete=maconec.ObtenirConnexion().createStatement();
            //change les infos
            String update="UPDATE ouvrage SET titre='"+titre+"',nb_page='"+nb_page+"',prix_unitaire='"+prix_unitaire+"',benefice='"+benefice+"',date_publication='"+date_publication+"',ISBM='"+ISBM+"',qt_seuille='"+qt_seuille+"',liste_auteur='"+liste_auteur+"'  WHERE  id='"+ID+"'";//REQUETE
            requete.executeUpdate(update);  
            JOptionPane.showMessageDialog(null,"success de la modifier ");
            return true;

        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              JOptionPane.showMessageDialog(null,"echect de la modification");
              return false;
              }
       } 
       //affiche all livre 
       public void affiche_alllivre( JTable jTable1){
           //creation model de table
          dt = new DefaultTableModel();
          dt.addColumn("ID");
          dt.addColumn("ISBN");
          dt.addColumn("TITRE");
          dt.addColumn("NOMBRE DE PAGE");
          dt.addColumn("DATE DE PUBLICATION");
          dt.addColumn("QT_STOCK");
          dt.addColumn("QT_SUIEL");
          dt.addColumn("RAYON");
          dt.addColumn("BENEFICE");
          dt.addColumn("PRIX UNITAIRE ACHAT");
          dt.addColumn("PRIX VENTE");
          dt.addColumn("EDITEUR");
          jTable1.setModel(dt);
       Statement requete; 
        Statement requete2; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="SELECT * FROM ouvrage";//REQUETE
       
       try{
         requete=maconec.ObtenirConnexion().createStatement();
         ResultSet resul_requet=requete.executeQuery(comande);
          int nbr_user=0;
          while(resul_requet.next()){//compte le nombre de user avec le meme nom passer en parametre
                nbr_user=nbr_user+1;
                Object [] rayon ={resul_requet.getInt("id")
                ,resul_requet.getString("ISBM").toString()
                ,resul_requet.getString("titre").toString()
                ,resul_requet.getInt("nb_page")
                ,resul_requet.getDate("date_publication")
                ,resul_requet.getInt("qt_stock")
                ,resul_requet.getInt("qt_seuille")
                ,resul_requet.getString("rayon").toString()
                ,resul_requet.getFloat("benefice")
                ,resul_requet.getFloat("prix_unitaire")
                ,resul_requet.getFloat("prix_vente")
                ,resul_requet.getString("editeur").toString()};
                dt.addRow(rayon);        
            }

        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              }
       
                  
    }
              //affiche all livre 
       public void affiche_alllivre_rupture( JTable jTable1){
           //creation model de table
          dt = new DefaultTableModel();
          dt.addColumn("ID");
          dt.addColumn("ISBM");
          dt.addColumn("TITRE");
          dt.addColumn("NOMBRE DE PAGE");
          dt.addColumn("DATE DE PUBLICATION");
          dt.addColumn("QT_STOCK");
          dt.addColumn("QT_SUIEL");
          dt.addColumn("RAYON");
          dt.addColumn("BENEFICE");
          dt.addColumn("PRIX UNITAIRE ACHAT");
          dt.addColumn("PRIX VENTE");
          dt.addColumn("EDITEUR");
          jTable1.setModel(dt);
       Statement requete; 
        Statement requete2; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="SELECT * FROM ouvrage WHERE qt_stock<=qt_seuille";//REQUETE
       
       try{
         requete=maconec.ObtenirConnexion().createStatement();
         ResultSet resul_requet=requete.executeQuery(comande);
          int nbr_user=0;
          while(resul_requet.next()){//compte le nombre de user avec le meme nom passer en parametre
                nbr_user=nbr_user+1;
                Object [] rayon ={resul_requet.getInt("id")
                ,resul_requet.getString("ISBM").toString()
                ,resul_requet.getString("titre").toString()
                ,resul_requet.getInt("nb_page")
                ,resul_requet.getDate("date_publication")
                ,resul_requet.getInt("qt_stock")
                ,resul_requet.getInt("qt_seuille")
                ,resul_requet.getString("rayon").toString()
                ,resul_requet.getFloat("benefice")
                ,resul_requet.getFloat("prix_unitaire")
                ,resul_requet.getFloat("prix_vente")
                ,resul_requet.getString("editeur").toString()};
                dt.addRow(rayon);        
            }

        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              }
       
                  
    }
       //iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii
       
// unuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu-fiche les jcombox
       public void set_liste_choix_isbm_foun(JComboBox f,JComboBox l,JComboBox f2){
           //creation model de table
       Statement requete; 
       Statement requete2; 
       Statement requete3;
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="SELECT * FROM editeur";//REQUETE
       String comande3="SELECT * FROM auteur";//REQUETE
       String comande2="SELECT * FROM rayon";//REQUETE
       try{
         requete=maconec.ObtenirConnexion().createStatement();
         ResultSet resul_requet=requete.executeQuery(comande);
          int nbr_user=0;
          int ID=-1;
          while(resul_requet.next()){//compte le nombre de user avec le meme nom passer en parametre
                nbr_user=nbr_user+1;
                      f.addItem(resul_requet.getString("nom").toString());
                       //f2.addItem(resul_requet.getString("nom").toString());
            }
          requete2=maconec.ObtenirConnexion().createStatement();
          ResultSet resul_requet2=requete.executeQuery(comande2);
                  while(resul_requet2.next()){//compte le nombre de user avec le meme nom passer en parametre
                nbr_user=nbr_user+1;
                      l.addItem(resul_requet2.getString("nom").toString());
            }
                requete3=maconec.ObtenirConnexion().createStatement();
                ResultSet resul_requet3=requete.executeQuery(comande3);
                  while(resul_requet3.next()){//compte le nombre de user avec le meme nom passer en parametre
                nbr_user=nbr_user+1;
                      f2.addItem(resul_requet3.getString("nom")+" "+resul_requet3.getString("prenom"));
            }
        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              }
       
      ////////////////////////            
    } 
  //ioooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
              //affiche all livre 
       public void affiche_au_livre( JTable jTable1,int id){
           //creation model de table
          dt = new DefaultTableModel();
          dt.addColumn(" ");
          dt.addColumn("NOM");
          jTable1.setModel(dt);
       Statement requete; 
        Statement requete2; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="SELECT * FROM ouvrage WHERE id='"+id+"'";//REQUETE
       
       try{
         requete=maconec.ObtenirConnexion().createStatement();
         ResultSet resul_requet=requete.executeQuery(comande);
         String ex1="";
          int nbr_user=0;
          while(resul_requet.next()){//compte le nombre de user avec le meme nom passer en parametre
                nbr_user=nbr_user+1;
                 ex1=resul_requet.getString("liste_auteur").toString();           
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
       ////////////////////////////////////////////////////////////////////////////////////////
   public Ouvrage getouvrage(String IMBS){
       Statement requete; 
       Ouvrage ou=new Ouvrage();
       ou.setISBM(IMBS);
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande="SELECT * FROM ouvrage WHERE ISBM='"+IMBS+"'";
       
       try{
         requete=maconec.ObtenirConnexion().createStatement();
         ResultSet resul_requet=requete.executeQuery(comande);
          int nbr_user=0;
          while(resul_requet.next()){//compte le nombre de user avec le meme nom passer en parametre
                nbr_user=nbr_user+1;
               
                 ou.setBenefice(resul_requet.getFloat("benefice"));
                 ou.setDate_publication(resul_requet.getDate("date_publication"));
                 ou.setListe_auteur(resul_requet.getString("liste_auteur").toString());
                 ou.setNb_page(resul_requet.getInt("nb_page"));
                 ou.setPrix_unitaire(resul_requet.getFloat("prix_unitaire"));
                 ou.setQt_seuille(resul_requet.getInt("qt_seuille"));
                 ou.setQt_stock(resul_requet.getInt("qt_stock"));
                 ou.setTitre(resul_requet.getString("titre").toString());
               
            }

        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              }
       
     return ou;             
    }
   //RECUPEREE LA QUQNTITE EN STOCK D'UN LIVRE 
public int get_qt_ouvrage_stock(String IMBS){
               Statement requete; 
               int qt=0;
               Connecxion_a_DB maconec=new Connecxion_a_DB(); 
               String comande="SELECT * FROM ouvrage WHERE ISBM='"+IMBS+"'";
        try{
         requete=maconec.ObtenirConnexion().createStatement();
         ResultSet resul_requet=requete.executeQuery(comande);
          int nbr_user=0;
          while(resul_requet.next()){//compte le nombre de user avec le meme nom passer en parametre
                nbr_user=nbr_user+1;
                qt=resul_requet.getInt("qt_stock");
            }
        return qt;
        }
        catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
              System.out.println(ex);
              }
           return qt;    
        }
//update la quantite en stock d'un ouvrage
public void udate_qt_ouvrage_stock(String IMBS,int qt){
               Statement requete; 
            Connecxion_a_DB maconec=new Connecxion_a_DB();
            String update="UPDATE ouvrage SET qt_stock='"+qt+"' WHERE  ISBM='"+IMBS+"'";//REQUETE
            
                 try{
         requete=maconec.ObtenirConnexion().createStatement();
         requete.executeUpdate(update);
         
                 }catch(SQLException ex){//SSI LA CONNECTION A LA BASE DE DONNE ECHOU
                     
              System.out.println(ex);
              }
         
        
   
        }
//cree un combox d'imbs
    public void set_liste_choix_isbm(JComboBox l){
           //creation model de table
       Statement requete; 
       Statement requete2; 
       Connecxion_a_DB maconec=new Connecxion_a_DB(); 
       String comande2="SELECT * FROM ouvrage";//REQUETE
       try{
         requete=maconec.ObtenirConnexion().createStatement();
          int nbr_user=0;
          int ID=-1;
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
