package Entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Cliente extends Persona{
	private String cuil;
	private String resposabilidadAfip;

	public Cliente() {
	}
	public Cliente(String nombre, String apellido, String domicilio, String documento, String provincia,
			String codigoPostal, String fechaNacimiento, String telefono,String cuil, String resposabilidadAfip) {
		super(nombre, apellido,domicilio, documento, provincia,
				codigoPostal,fechaNacimiento,telefono);
		this.cuil = cuil;
		this.resposabilidadAfip = resposabilidadAfip;
	}
	
	public String getCuil() {
		return cuil;
	}
	public void setCuil(String cuil) {
		this.cuil = cuil;
	}
	public String getResposabilidadAfip() {
		return resposabilidadAfip;
	}
	public void setResposabilidadAfip(String resposabilidadAfip) {
		this.resposabilidadAfip = resposabilidadAfip;
	}
	
	//CRUD
	
	public void crearCliente(Connection conexion) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("#######################");
		System.out.println("Datos del Cliente");
		System.out.println("#######################");
		System.out.println("Apellido :");
		String apellido = sc.nextLine(); 
		System.out.println("Nombre :");
		String nombre = sc.nextLine();
		System.out.println("Domicilio :");
		String domicilio = sc.nextLine();
		System.out.println("Documento :");
		String documento = sc.nextLine();
		System.out.println("Provincia :");
		String provincia = sc.nextLine();
		System.out.println("Codigo Postal :");
		String codigoPostal = sc.nextLine();
		System.out.println("Fecha de Nacimiento : ");
		String fechaNacimiento = sc.nextLine();
		System.out.println("Telefono : ");
		String telefono = sc.nextLine();
		System.out.println("Cuil : ");
		String cuil = sc.nextLine();
		System.out.println("Responsabilidad en Afip : ");
		String afip = sc.nextLine();
		
		Statement statement = null;
		String sql;
		ResultSet rs;
		PreparedStatement stmt;
		
		try {
			//ULTIMO ID REGISTRADO EN LA TABLA
			statement = conexion.createStatement();
			sql = "SELECT idpersona FROM persona order by idpersona DESC LIMIT 1;";
			rs = statement.executeQuery(sql);
			int idpersona = 0;
			while(rs.next()) 
			{
				idpersona = rs.getInt("idpersona");
			}
			
			stmt = conexion.prepareStatement("INSERT INTO persona VALUES (?,?,?,?,?,?,?,?,?)");
        	stmt.setInt(1,idpersona+1);
        	stmt.setString(2,nombre);
        	stmt.setString(3,apellido);
        	stmt.setString(4,domicilio);
        	stmt.setString(5,documento);
        	stmt.setString(6,provincia);
        	stmt.setString(7,codigoPostal);
        	stmt.setString(8,fechaNacimiento);
        	stmt.setString(9,telefono);
        	
        	int response = stmt.executeUpdate();
        	if(response>0) 
        	{
        		System.out.println("se inserto persona correctamente");
        	}
        	
        	sql = "SELECT idcliente FROM cliente order by idcliente DESC LIMIT 1;";
			rs = statement.executeQuery(sql);
			int idcliente = 0;
			while(rs.next()) 
			{
				idcliente = rs.getInt("idcliente");
			}
        	
			stmt = conexion.prepareStatement("INSERT INTO cliente VALUES (?,?,?,?)");
        	stmt.setInt(1,idcliente+1);
        	stmt.setInt(2,idpersona+1);
        	stmt.setString(3,cuil);
        	stmt.setString(4,afip);
        	
        	response = stmt.executeUpdate();
        	if(response>0) 
        	{
        		System.out.println("se inserto cliente correctamente");
        	}
		} catch (SQLException sqle){
            System.out.println("SQLState: "+ sqle.getSQLState());
            System.out.println("SQLErrorCode: " + sqle.getErrorCode());
            sqle.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
	}
	
	public void actualizarCliente(Connection conexion) 
	{
		Scanner sc = new Scanner(System.in);
		Statement statement = null;
		String sql;
		ResultSet rs;
		PreparedStatement stmt;
		
		try {
			statement = conexion.createStatement();
			sql = "SELECT idcliente,Nombre,Apellido,Documento "
					+ "FROM cliente AS c INNER JOIN persona AS per ON c.idpersona = per.idpersona "
					+ "order by idcliente;";
			rs = statement.executeQuery(sql);
			System.out.println("Seleccione Cliente");
			while(rs.next()) 
			{
				int idcliente = rs.getInt("idcliente");
				String apellido = rs.getString("Apellido");
				String nombre = rs.getString("Nombre");
				String documento = rs.getString("Documento");
				System.out.println(idcliente + " - " + apellido + " " + nombre + " " + documento);
			}
			System.out.println("Cancelar seleccione 0");
			int cliente  = sc.nextInt();
			sc.nextLine();
			
			if(cliente!=0) {
				System.out.println("#######################");
				System.out.println("Datos del Cliente");
				System.out.println("#######################");
				System.out.println("Apellido :");
				String apellido = sc.nextLine(); 
				System.out.println("Nombre :");
				String nombre = sc.nextLine();
				System.out.println("Domicilio :");
				String domicilio = sc.nextLine();
				System.out.println("Documento :");
				String documento = sc.nextLine();
				System.out.println("Provincia :");
				String provincia = sc.nextLine();
				System.out.println("Codigo Postal :");
				String codigoPostal = sc.nextLine();
				System.out.println("Fecha de Nacimiento : ");
				String fechaNacimiento = sc.nextLine();
				System.out.println("Telefono : ");
				String telefono = sc.nextLine();
				System.out.println("Cuil : ");
				String cuil = sc.nextLine();
				System.out.println("Responsabilidad en Afip : ");
				String afip = sc.nextLine();
				
				statement = conexion.createStatement();
				sql = "SELECT idpersona FROM cliente WHERE idcliente = "+cliente+";";
				rs = statement.executeQuery(sql);
				int idpersona = 0;
				while(rs.next()) 
				{
					idpersona = rs.getInt("idpersona");
				}
				
				stmt = conexion.prepareStatement("UPDATE persona SET "
						+ " Nombre = ?,"
						+ " Apellido = ?,"
						+ " Direccion = ?,"
						+ " Documento = ?,"
						+ " Provincia = ?,"
						+ " CodigoPostal = ?,"
						+ " FechaNacimiento=?,"
						+ " Telefono = ?"
						+ "WHERE idPersona = ?");
	        	stmt.setString(1,nombre);
	        	stmt.setString(2,apellido);
	        	stmt.setString(3,domicilio);
	        	stmt.setString(4,documento);
	        	stmt.setString(5,provincia);
	        	stmt.setString(6,codigoPostal);
	        	stmt.setString(7,fechaNacimiento);
	        	stmt.setString(8,telefono);
	        	stmt.setInt(9, idpersona);
	        	
	        	int response = stmt.executeUpdate();
	        	if(response>0) 
	        	{
	        		System.out.println("se actualizo persona correctamente");
	        	}
				stmt = conexion.prepareStatement("UPDATE cliente SET "
						+ "Cuil = ?,"
						+ "Afip = ?"
						+ "WHERE idcliente = ?;");
	        	stmt.setString(1,cuil);
	        	stmt.setString(2,afip);
	        	stmt.setInt(3, cliente);
	        	
	        	response = stmt.executeUpdate();
	        	if(response>0) 
	        	{
	        		System.out.println("se actualizo cliente correctamente");
	        	}
			 }
		}catch (SQLException sqle){
            System.out.println("SQLState: "+ sqle.getSQLState());
            System.out.println("SQLErrorCode: " + sqle.getErrorCode());
            sqle.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
	}
	
	public void eliminarCliente(Connection conexion) 
	{
		Scanner sc = new Scanner(System.in);
		Statement statement = null;
		String sql;
		ResultSet rs;
		PreparedStatement stmt;
		
		try {
			statement = conexion.createStatement();
			sql = "SELECT idcliente,Nombre,Apellido,Documento "
					+ "FROM cliente AS c INNER JOIN persona AS per ON c.idpersona = per.idpersona "
					+ "order by idcliente;";
			rs = statement.executeQuery(sql);
			System.out.println("Seleccione Cliente a Eliminar");
			while(rs.next()) 
			{
				int idcliente = rs.getInt("idcliente");
				String apellido = rs.getString("Apellido");
				String nombre = rs.getString("Nombre");
				String documento = rs.getString("Documento");
				System.out.println(idcliente + " - " + apellido + " " + nombre + " " + documento);
			}
			System.out.println("Cancelar seleccione 0");
			int cliente  = sc.nextInt();
			
			if(cliente!=0) {
				statement = conexion.createStatement();
				sql = "SELECT idpersona FROM cliente WHERE idcliente = "+cliente+";";
				rs = statement.executeQuery(sql);
				int idpersona = 0;
				while(rs.next()) 
				{
					idpersona = rs.getInt("idpersona");
				}
				
				stmt = conexion.prepareStatement("DELETE FROM cliente WHERE idcliente = ?;");
	        	stmt.setInt(1, cliente);
	        	
	        	int response = stmt.executeUpdate();
	        	if(response>0) 
	        	{
	        		System.out.println("se elimino cliente correctamente");
	        	}
				stmt = conexion.prepareStatement("DELETE FROM persona WHERE idPersona = ?");
	        	stmt.setInt(1, idpersona);
	        	
	        	response = stmt.executeUpdate();
	        	if(response>0) 
	        	{
	        		System.out.println("se elimino persona correctamente");
	        	}
			 }
		}catch (SQLException sqle){
            System.out.println("SQLState: "+ sqle.getSQLState());
            System.out.println("SQLErrorCode: " + sqle.getErrorCode());
            sqle.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
	}
}










