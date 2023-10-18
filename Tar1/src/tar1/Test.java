/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tar1;

/**
 *
 * @author SIMO LAAOUIBI
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class Test {

    public static void creation() throws ClassNotFoundException {
        //Information d'accès à la base de données		
        String user = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/tr";
        Connection cn = null;
        Statement st = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection(url, user, password);
            //Etape 3 : Création d'un statement
            st = cn.createStatement();
            String req = "create table DevData (Developpeurs varchar (32),jour char (11),nbscripts integer)";
            //Etape 4 : Exécution de la requête
            st.executeUpdate(req);
        } catch (SQLException e) {
            System.out.println("Erreur SQL");
        } finally {
            try {
                st.close();
                cn.close();
            } catch (SQLException ex) {
                System.out.println("Impossible de libérer les ressources");
            }
        }
    }

    public static void insertion(DevData d) {
        String user = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/tr";
        Connection cn = null;
        Statement st = null;
        try {
            cn = DriverManager.getConnection(url, user, password);
            st = cn.createStatement();
            String req = "insert into devdata values ('" + d.getDevloppeur() + "','" + d.getJour() + "','"
                    + d.getNbscripts() + "')";
            st.executeUpdate(req);
        } catch (SQLException e) {
            System.out.println("Erreur SQL");
        } finally {
            try {
                st.close();
                cn.close();
            } catch (SQLException ex) {
                System.out.println("Impossible de libérer les ressources");
            }
        }

    }

    public static void exojbdc1() {
        //Information d'accès à la base de données
        String user = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/tr";
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            cn = DriverManager.getConnection(url, user, password);
            //Etape 3 : Création d'un statement
            st = cn.createStatement();
            String req = "select developpeurs ,jour,max(nbscripts) from devdata group by jour";

            //Etape 4 : Exécution de la requête
            rs = st.executeQuery(req);

            //Etape 5 : Parcours de ResultSet
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getInt(3));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                //Etape 6 : Libérer les ressources de la mémoire
                st.close();
                cn.close();
            } catch (SQLException ex) {
                System.out.println("Impossible de libérer les ressources");
            }
        }
    }

    public static List<String> exojbdc2() {
        List<String> list = new ArrayList<>();
        //Information d'accès à la base de données
        String user = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/tr";
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            cn = DriverManager.getConnection(url, user, password);
            //Etape 3 : Création d'un statement
            st = cn.createStatement();
            String req = "select developpeurs ,sum(nbscripts) as c from devdata group by developpeurs order by c desc";

            //Etape 4 : Exécution de la requête
            rs = st.executeQuery(req);
            //Etape 5 : Parcours de ResultSet
            while (rs.next()) {
                list.add(rs.getString(1) + ":" + rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("erreur");
        } finally {
            try {
                st.close();
                cn.close();
            } catch (SQLException ex) {
                System.out.println("Impossible de libérer les ressources");
            }
        }
        return list;
    }

    public static void exojbdc3() {
        //Information d'accès à la base de données
        String user = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/tr";
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            cn = DriverManager.getConnection(url, user, password);
            //Etape 3 : Création d'un statement
            st = cn.createStatement();
            String req = "select sum(nbscripts) from devdata";

            //Etape 4 : Exécution de la requête
            rs = st.executeQuery(req);
            //Etape 5 : Parcours de ResultSet
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                //Etape 6 : Libérer les ressources de la mémoire
                st.close();
                cn.close();
            } catch (SQLException ex) {
                System.out.println("Impossible de libérer les ressources");
            }
        }
    }

    public static void exojbdc4(String nom) {
        //Information d'accès à la base de données
        String user = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/tr";
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            cn = DriverManager.getConnection(url, user, password);
            //Etape 3 : Création d'un statement
            st = cn.createStatement();
            String req = "select developpeurs,sum(nbscripts) from devdata group by developpeurs having developpeurs='" + nom + "'";

            //Etape 4 : Exécution de la requête
            rs = st.executeQuery(req);
            //Etape 5 : Parcours de ResultSet
            while (rs.next()) {
                System.out.println(rs.getString(1) + ":" + rs.getInt(2));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                //Etape 6 : Libérer les ressources de la mémoire
                st.close();
                cn.close();
            } catch (SQLException ex) {
                System.out.println("Impossible de libérer les ressources");
            }
        }
    }

    public static void main(String[] args) {
		//creation("DevData");
		/*insertion(new DevData("ALAMI","LUNDI",1));
         insertion(new DevData("WAFI","LUNDI",2));
         insertion(new DevData("SALAMI","MARDI",9));
         insertion(new DevData("SAFI","MARDI",2));
         insertion(new DevData("ALAMI","MARDI",2));
         insertion(new DevData("SEBAHI","MERCREDI",2));
         insertion(new DevData("WAFI","JEUDI",3));
         insertion(new DevData("ALAOUI","VENDREDI",9));
         insertion(new DevData("WAFI","VENDREDI",3));
         insertion(new DevData("SEBAHI","VENDREDI",4));
         */
        exojbdc1();
		//System.out.println(exojbdc2());
        //exojbdc4("alami");
        //exojbdc3();

    }

}
