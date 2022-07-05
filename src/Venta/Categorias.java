package Venta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Categorias {
	private String nombre;
	private String descripcion;
	
	
	public Categorias() {
	}
	
	public Categorias(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void crearCategoria(Connection connection) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Nombre de Categoria :");
		String categoria = sc.nextLine(); 
		System.out.println("Descripcion :");
		String descripcion = sc.nextLine();
		
		//insert
		try {
			Statement statement = connection.createStatement();
			String sql = "SELECT idCategoria FROM categoria order by idCategoria DESC LIMIT 1;";
			ResultSet rs = statement.executeQuery(sql);
			int idCategoria = 0;
			
			
			while(rs.next()) 
			{
				idCategoria = rs.getInt("idCategoria");
			}
			
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO categoria VALUES (?,?,?)");
			stmt.setInt(1,idCategoria+1);
			stmt.setString(2,categoria);
        	stmt.setString(3,descripcion);
        	int response = stmt.executeUpdate();
        	 
        	if (response > 0)
                System.out.println("Categoria Insertado correctamente");
        	
		 }catch (SQLException sqle){
	            System.out.println("SQLState: "+ sqle.getSQLState());
	            System.out.println("SQLErrorCode: " + sqle.getErrorCode());
	            sqle.printStackTrace();
	     }catch (Exception e){
	            e.printStackTrace();
	     }
	
	}
	
	public void actualizarCategoria(Connection connection) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("###############################");
		System.out.println("Elije categoria a Actualizar");
		System.out.println("Elija 0 para cancelar");
		System.out.println("###############################");
		System.out.println("Listado de Categoria");
		try {
			Statement stmt = connection.createStatement();
			String sql = "SELECT idCategoria,Nombre FROM categoria";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) 
			{
				String nombre = rs.getString("Nombre");
				String idCategoria = rs.getString("idCategoria");
				//String descripcion = rs.getString("Descripcion");
				System.out.println(idCategoria + " : " + nombre);
			}
		}catch (SQLException se) {
			//resolver errores de JDBC
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("##################################");
		int idcategoria = sc.nextInt();
		sc.nextLine();
		
		if(idcategoria != 0) 
		{
			System.out.println("Nombre de Categoria :");
			String categoria = sc.nextLine(); 
			System.out.println("Descripcion :");
			String descripcion = sc.nextLine();
			try {
				PreparedStatement statement = connection.prepareStatement("UPDATE categoria SET Nombre=?,Descripcion=? WHERE idcategoria=?");
				statement.setString(1,categoria);
				statement.setString(2,descripcion);
				statement.setInt(3,idcategoria);
	        	int response = statement.executeUpdate();
	        	if (response > 0)
	                System.out.println("Categoria Actualizado correctamente");
	        	
			 }catch (SQLException sqle){
		            System.out.println("SQLState: "+ sqle.getSQLState());
		            System.out.println("SQLErrorCode: " + sqle.getErrorCode());
		            sqle.printStackTrace();
		     }catch (Exception e){
		            e.printStackTrace();
		     }
		}
	}
	
	public void eliminarCategoria(Connection connection) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("###############################");
		System.out.println("Elije categoria a Eliminar");
		System.out.println("Elija 0 para cancelar");
		System.out.println("###############################");
		System.out.println("Listado de Categoria");
		try {
			Statement stmt = connection.createStatement();
			String sql = "SELECT idCategoria,Nombre FROM categoria";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) 
			{
				String nombre = rs.getString("Nombre");
				String idCategoria = rs.getString("idCategoria");
				//String descripcion = rs.getString("Descripcion");
				System.out.println(idCategoria + " : " + nombre);
			}
		}catch (SQLException se) {
			//resolver errores de JDBC
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("##################################");
		int idcategoria = sc.nextInt();
		sc.nextLine();
		
		if(idcategoria != 0) 
		{
			try {
				PreparedStatement statement = connection.prepareStatement("DELETE FROM categoria WHERE idcategoria=?");
				statement.setInt(1,idcategoria);
	        	int response = statement.executeUpdate();
	        	if (response > 0)
	                System.out.println("Categoria Eliminado correctamente");
	        	
			 }catch (SQLException sqle){
		            System.out.println("SQLState: "+ sqle.getSQLState());
		            System.out.println("SQLErrorCode: " + sqle.getErrorCode());
		            sqle.printStackTrace();
		     }catch (Exception e){
		            e.printStackTrace();
		     }
		}
	}
}
