/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.de.librairie;
import Dao.Info_utilisateur_courant;
import Utilisateurs.Administrateur;
import Utilisateurs.Libraire;
import gestion_de_livre.Auteur;
import gestion_de_livre.Collection;
import gestion_de_livre.Editeur;
import gestion_de_livre.Ouvrage;
import gestion_de_livre.Rayon;
import java.text.SimpleDateFormat;
import java.util.Date;
import vente_achat.Achat;
import vente_achat.Client;
import vente_achat.Facture_vente;
import vente_achat.Vente;
import vues_app.Achat_vue;
import vues_app.Auteur_vue;
import vues_app.Client_vue;
import vues_app.Editeur_vue;
import vues_app.Home;
import vues_app.Livre_vue;
import vues_app.Rayon_vue;
import vues_app.Utilisateurs;
import vues_app.Vente_vue;
import vues_app.change_password;
import vues_app.login;

/**
 *
 * @author ARNAUD
 */
 
public class GESTIONDELIBRAIRIE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new login().setVisible(true);
       
        //new Livre_vue().setVisible(true);
        //new Auteur_vue().setVisible(true);
        //new Client_vue().setVisible(true);
       // new Editeur_vue().setVisible(true);
       // new Utilisateurs().setVisible(true);
        //new Rayon_vue().setVisible(true);
       Info_utilisateur_courant session=new Info_utilisateur_courant();
       Libraire user= new Libraire();
       Administrateur adm= new Administrateur();
       //session.courent_user= new Libraire();
       //Rayon ry= new Rayon();
       //Client c=new Client();
       //Editeur ed=new Editeur();
       Date d=new Date(1,2,3);
       SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
       Vente fac=new Vente();
       //new Vente_vue().setVisible(true);
      // fac.make_vente(12,"ISBM2!500!200!#ghufguy!270!200!#ghufguyrr!10!200!",sdf.format(d),11,1000,10000);
       //Achat ach=new Achat();
       //ach.make_Achat("ko", 10, 100, 50,sdf.format(d), "ghufguy", 500,10);
        
       //fac.make_facture_vente(11, 111, 11111, d);
       //new Achat_vue().setVisible(true);
       Ouvrage ou=new Ouvrage();
       // ou=ou.getouvrage("ISBM2");
       //System.out.println(ou.getTitre());
       //ou.make_ouvrage("tapi velo","arafat", 0, d, 0,"tal","rayon3");
       //ou.delete_ouvrage(19);
      // ou.edit_collection("nouveau", 1111111, 500, 50, d,"ISBM2", 10,"NOUVEAU AUTEUR", 20);
       //ed.make_editeur("agfshd","ynd","tetfde");
       //ed.delete_editeur(2);
       //ed.edit_editeur("seul","douala", "321", 3);
       Auteur au=new Auteur();
       //au.udate_liste_livre_auteur("depense",2);
       //au.make_auteur("talom","arnaud");
       //au.edit_editeur("bouopda","arnaud","compte", 2);
       //au.delete_auteur(1);
       //Collection cl=new Collection();
       //5cl.make_collection("edition clef",12);
       //cl.edit_collection("test", 1000,2);
       //cl.delete_collection(1);
       //c.make_client("arnaud","talom","655667191","yaounde");
       //c.edit_client("papa","toto","samsung","650306",2);
       //c.delete_client(1);
       //ry.make_Rayon("coffre3343");
       //ry.get_livres_rayon("test update rayon");
       //ry.delete_rayon(1);
       // adm.make_user("talla","arnaud","yaoundé","picolo33","picolo33","655667191", "admin");
        //user.login("picolo","12345");
        //user.get_indice_password("picolo");
        //user.edit_password("picolo","655picolo","picolo6555","princesse");
        //adm.delete_user(3);
        //adm.edit_user("rosi","bertille","rosi","123456","douala","test",2);
        
    
}
}