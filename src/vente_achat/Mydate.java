/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vente_achat;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author ARNAUD
 */
public class Mydate {
    public String getdate(){
    Date actuelle=new Date(1,1,1);
    DateFormat da=new SimpleDateFormat("YYYY-MM-dd");
    String dat=da.format(actuelle);
    System.out.println(dat);
    return dat;
    }
}
