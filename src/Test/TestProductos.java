package Test;


import Utilidades.Conexion;
import Venta.Productos;

public class TestProductos {
	public static void main(String[] args) {
		Conexion conexion = new Conexion("root","Navidad$25","supermark");
		conexion.conectar();
		
		Productos producto = new Productos();
		
		producto.crearProducto(conexion.getConnection());
		//producto.actualizarProducto(conexion.getConnection());
		//producto.eliminarProducto(conexion.getConnection());
	}
}
