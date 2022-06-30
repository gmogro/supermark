package Venta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


public class Venta {
	private String fecha;
	private double total;
	private String observacion;
	private boolean estado;
	
	public Venta() {
	}
	public Venta(String fecha, double total, String observacion, boolean estado) {
		super();
		this.fecha = fecha;
		this.total = total;
		this.observacion = observacion;
		this.estado = estado;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public void crearVenta(Connection conexion) 
	{
		Scanner sc = new Scanner(System.in);
		PreparedStatement stmt = null;
		Statement statement = null;
		String sql;
		ResultSet rs;
		try {
			statement = conexion.createStatement();
			sql = "SELECT idproducto,Nombre,Precio FROM producto order by idproducto;";
			rs = statement.executeQuery(sql);
			int option = 0;
			ArrayList<DetalleVenta> listaProductos = new ArrayList<DetalleVenta>();
			do{
				while(rs.next()) 
				{
					System.out.print(rs.getInt("idproducto")+" - ");
					System.out.print(rs.getString("Nombre"));
					System.out.print(rs.getString("Precio"));
					System.out.println();
				}
				System.out.println("#######################");
				System.out.println("Ingrese el producto: ");
				int producto = sc.nextInt();
				sc.nextLine();
				System.out.println("Ingrese la cantidad: ");
				int cantidad = sc.nextInt();
				sc.nextLine();
				sql = "SELECT Precio FROM producto WHERE idproducto = "+producto+";";
				ResultSet rsp = statement.executeQuery(sql);
				double precio = 0;
				while(rsp.next()) 
				{
					precio = rsp.getDouble("Precio");
				}
				DetalleVenta detalleVenta = new DetalleVenta(producto,cantidad, precio);
				listaProductos.add(detalleVenta);
				System.out.println("#######################");
				System.out.println("Desea seguir Selecionando Producto: ");
				System.out.println("1 - SI");
				System.out.println("0 - NO");
				option = sc.nextInt();
				sc.nextLine();
			}while(option == 1);
			
			try {
				statement = conexion.createStatement();
				sql = "SELECT idventa FROM venta order by idventa DESC LIMIT 1;";
				rs = statement.executeQuery(sql);
				int idVenta = 0;
				while(rs.next()) 
				{
					idVenta = rs.getInt("idventa");
				}
				
				stmt = conexion.prepareStatement("INSERT INTO venta VALUES (?,?,?,?)");
				stmt.setInt(1,idVenta+1);
				stmt.setString(2,fecha);
	        	stmt.setDouble(3,total);
	        	stmt.setString(4,observacion);
	        	int response = stmt.executeUpdate();
	        	if (response > 0)
	                System.out.println("Venta Insertada correctamente");
	        	
	        	statement = conexion.createStatement();
				sql = "SELECT iddetalleventa FROM detalle_venta order by iddetalleventa DESC LIMIT 1;";
				rs = statement.executeQuery(sql);
				int idDetalleVenta = 0;
				while(rs.next()) 
				{
					idDetalleVenta = rs.getInt("idventa");
				}
	        	
				for(int i=0;i<listaProductos.size();i++) 
				{
					stmt = conexion.prepareStatement("INSERT INTO detalle_venta VALUES (?,?,?,?,?)");
					stmt.setInt(1,idDetalleVenta+1);
					stmt.setInt(2,listaProductos.get(i).getIdproducto());
					stmt.setInt(3,idVenta+1);
		        	stmt.setInt(4,listaProductos.get(i).getCantidad());
		        	stmt.setDouble(5,listaProductos.get(i).getPrecioUnitario());
		        	response = stmt.executeUpdate();
		        	if (response > 0)
		                System.out.println("DetalleVenta Insertada correctamente");
				}
				
				for(int i=0;i<listaProductos.size();i++) 
				{
					int producto = 0;
					int stock = 0;
					statement = conexion.createStatement();
					sql = "SELECT idproducto,Stock FROM producto WHERE idproducto = "+listaProductos.get(i).getIdproducto();
					rs = statement.executeQuery(sql);
					while(rs.next()) 
					{
						producto= rs.getInt("idproducto");
						stock = rs.getInt("Stock");
					}
					
					stmt = conexion.prepareStatement("UPDATE producto SET Stock=? WHERE idproducto=?");
					int stockActual = stock - listaProductos.get(i).getCantidad();
					stmt.setInt(1,stockActual);
					stmt.setInt(1,producto);
					response = stmt.executeUpdate(); 
		        	if (response > 0)
		                System.out.println("Stock correctament");
				}
			 }catch (SQLException sqle){
		            System.out.println("SQLState: "+ sqle.getSQLState());
		            System.out.println("SQLErrorCode: " + sqle.getErrorCode());
		            sqle.printStackTrace();
		     }catch (Exception e){
		            e.printStackTrace();
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
