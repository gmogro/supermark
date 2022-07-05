package Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Utilidades.Conexion;

public class Testpersona {
	public static void main(String[] args) {
		Conexion conexion = new Conexion("root","Navidad$25","supermark");
		System.out.println(conexion.conectar());
		PreparedStatement stmt;
		try {
			stmt = conexion.getConnection().prepareStatement("INSERT INTO persona VALUES (?,?,?,?,?,?,?,?,?)");

	    	stmt.setInt(1,1);
	    	stmt.setString(2,"Test");
	    	stmt.setString(3,"Test");
	    	stmt.setString(4,"Test");
	    	stmt.setString(5,"Test");
	    	stmt.setString(6,"Test");
	    	stmt.setString(7,"Test");
	    	stmt.setString(8,"Test");
	    	stmt.setString(9,"Test");
	    	
	    	int response = stmt.executeUpdate();
	    	if(response>0) 
	    	{
	    		System.out.println("se inserto correctamente");
	    	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
