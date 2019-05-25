package general;

import java.sql.*;
import java.util.ArrayList;


public class Dao {

	// Acc�der � la BD
	public static void openDataBase() {
		@SuppressWarnings("unused")
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:datas.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}



	// CREATE TABLE when datas doesn't exist
	public static void initializeTables() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:datas.db");
			stmt = c.createStatement();


			String sql ="CREATE TABLE SCORES " + "(NAME STRING     NOT NULL, SCORE INT);";
			stmt.executeUpdate(sql);
			sql="INSERT INTO SCORES (NAME,SCORE) VALUES ('Saint Tocard',0)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}


	/*public static void main( String args[]){
		initializeTables();
	}*/

	public static void addScore(String name,int score) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:datas.db");
			stmt=c.createStatement();
			stmt.executeUpdate("INSERT INTO SCORES (NAME,SCORE) VALUES ('"+name+"',"+score+")");
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.out.println("INSERT INTO SCORES (NAME,SCORE) VALUES ('"+name+"',"+score+")");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			System.out.println("Couldn't load the database");
		}
	}



	public static ArrayList<Doublet> search(){
		Connection c = null;
		Statement stmt = null;

		ArrayList<Doublet> result = new ArrayList<Doublet>();
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:datas.db");
			stmt=c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM SCORES ORDER BY SCORE DESC");

			while(rs.next()) {
				result.add(new Doublet(rs.getString(1),rs.getInt(2)));
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			System.out.println("Couldn't load the database");
		}
		return result;
	}

}
