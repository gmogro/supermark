package Venta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Productos {
	private String nombre;
	private double precio;
	private int stock;
	private int idcategoria;
	private String descripcion;
	
	public Productos() {
	}

	public Productos(String nombre, double precio, int stock, int idcategoria, String descripcion) {
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.idcategoria = idcategoria;
		this.descripcion = descripcion;
	}

	public void crearProducto(Connection connection) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Nombre del Producto :");
		String nombre = sc.nextLine(); 
		System.out.println("precio :");
		double precio = sc.nextDouble();
		sc.nextLine();
		System.out.println("stock :");
		int stock = sc.nextInt();
		sc.nextLine();
		System.out.println("######################");
		System.out.println("Seleccione Categoria :");
		System.out.println("######################");
		
		Statement statement = null;
		String sql;
		ResultSet rs;
		int idcategoria = 0;
		try {
			statement = connection.createStatement();
			sql = "SELECT idCategoria,Nombre FROM categoria order by idCategoria;";
			rs = statement.executeQuery(sql);
			while(rs.next()) 
			{
				System.out.print(rs.getInt("idCategoria")+" - ");
				System.out.print(rs.getString("Nombre"));
				System.out.println();
			}
			idcategoria = sc.nextInt();
			sc.nextLine();
		}catch (SQLException sqle){
            System.out.println("SQLState: "+ sqle.getSQLState());
            System.out.println("SQLErrorCode: " + sqle.getErrorCode());
            sqle.printStackTrace();
	     }catch (Exception e){
	            e.printStackTrace();
	     }
		
		System.out.println("descripcion :");
		String descripcion = sc.nextLine(); 
		
		try {
			sql = "SELECT idproducto FROM producto order by idproducto DESC LIMIT 1;";
			rs = statement.executeQuery(sql);
			int idproducto = 0;
			while(rs.next()) 
			{
				idproducto = rs.getInt("idproducto");
			}
			
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO producto VALUES (?,?,?,?,?,?)");
			stmt.setInt(1,idproducto+1);
			stmt.setInt(2,idcategoria);
			stmt.setString(3,nombre);
			stmt.setDouble(4,precio);
			stmt.setInt(5,stock);
        	stmt.setString(6,descripcion);
        	int response = stmt.executeUpdate();
        	 
        	if (response > 0)
                System.out.println("Producto Insertado correctamente");
        	
		 }catch (SQLException sqle){
	            System.out.println("SQLState: "+ sqle.getSQLState());
	            System.out.println("SQLErrorCode: " + sqle.getErrorCode());
	            sqle.printStackTrace();
	     }catch (Exception e){
	            e.printStackTrace();
	     }
	
	}
	
	public void actualizarProducto(Connection connection) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("###############################");
		System.out.println("Elije producto a Actualizar");
		System.out.println("Elija 0 para cancelar");
		System.out.println("###############################");
		System.out.println("Listado de Producto");
		try {
			Statement stmt = connection.createStatement();
			String sql = "SELECT idproducto,Nombre FROM producto ORDER BY idproducto";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) 
			{
				String idproducto = rs.getString("idproducto");
				String nombre = rs.getString("Nombre");
				System.out.println(idproducto + " : " + nombre);
			}
		}catch (SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("##################################");
		int idproducto = sc.nextInt();
		sc.nextLine();
		
		if(idproducto != 0) 
		{
			System.out.println("Nombre del Producto :");
			String nombre = sc.nextLine(); 
			System.out.println("precio :");
			double precio = sc.nextDouble();
			sc.nextLine();
			System.out.println("stock :");
			int stock = sc.nextInt();
			sc.nextLine();
			System.out.println("######################");
			System.out.println("Seleccione Categoria :");
			System.out.println("######################");
			
			Statement statement = null;
			String sql;
			ResultSet rs;
			int idcategoria = 0;
			try {
				statement = connection.createStatement();
				sql = "SELECT idCategoria,Nombre FROM categoria order by idCategoria;";
				rs = statement.executeQuery(sql);
				while(rs.next()) 
				{
					System.out.print(rs.getInt("idCategoria")+" - ");
					System.out.print(rs.getString("Nombre"));
				}
				idcategoria = sc.nextInt();
				sc.nextLine();
			}catch (SQLException sqle){
	            System.out.println("SQLState: "+ sqle.getSQLState());
	            System.out.println("SQLErrorCode: " + sqle.getErrorCode());
	            sqle.printStackTrace();
		     }catch (Exception e){
		            e.printStackTrace();
		     }
			
			System.out.println("descripcion :");
			String descripcion = sc.nextLine();
			
			try {
				PreparedStatement stmt = connection.prepareStatement("UPDATE producto SET "
						+ "Nombre=?,Precio=?,Stock=?,idcategoria=?,Descripcion=? WHERE idproducto=?");
				stmt.setString(1,nombre);
				stmt.setDouble(2,precio);
				stmt.setInt(3,stock);
				stmt.setInt(4,idcategoria);
	        	stmt.setString(5,descripcion);
	        	stmt.setInt(6,idproducto);
	        	int response = stmt.executeUpdate();
	        	 
	        	if (response > 0)
	                System.out.println("Producto actualizado correctament");
	        	
			 }catch (SQLException sqle){
		            System.out.println("SQLState: "+ sqle.getSQLState());
		            System.out.println("SQLErrorCode: " + sqle.getErrorCode());
		            sqle.printStackTrace();
		     }catch (Exception e){
		            e.printStackTrace();
		     }
		}
	}
	
	public void eliminarProducto(Connection connection) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("###############################");
		System.out.println("Elije producto a Eliminar");
		System.out.println("Elija 0 para cancelar");
		System.out.println("###############################");
		System.out.println("Listado de Producto");
		try {
			Statement stmt = connection.createStatement();
			String sql = "SELECT idproducto,Nombre FROM producto ORDER BY idproducto";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) 
			{
				String idproducto = rs.getString("idproducto");
				String nombre = rs.getString("Nombre");
				System.out.println(idproducto + " : " + nombre);
			}
		}catch (SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("##################################");
		int idproducto = sc.nextInt();
		sc.nextLine();
		
		if(idproducto != 0) 
		{
	        try{
	        	PreparedStatement stmt = connection.prepareStatement("DELETE FROM producto WHERE idproducto=?");
	        	stmt.setString(1,idproducto+"");
	        	
	        	int response = stmt.executeUpdate();
	        	
	        	if (response > 0)
	                System.out.println("Eliminado correctamente");
	        	
	        }catch (SQLException se) {
	        	se.printStackTrace();
	        }
		}
	}
	
	public void listadoProducto(Connection connection) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("###############################");
		System.out.println("Listado de Producto");
		System.out.println("###############################");
		try {
			Statement stmt = connection.createStatement();
			String sql = "SELECT idproducto,p.Nombre,p.Precio,c.nombre FROM producto p "
					+ "INNER JOIN categoria AS c ON p.idcategoria = c.idCategoria "
					+ "WHERE p.stock > 0 "
					+ "ORDER BY idproducto";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) 
			{
				String idproducto = rs.getString("idproducto");
				String nombre = rs.getString("Nombre");
				System.out.println(idproducto + " : " + nombre);
			}
		}catch (SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("##################################");
	}

}
