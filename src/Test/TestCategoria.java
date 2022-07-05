package Test;

import Utilidades.Conexion;
import Venta.Categorias;

public class TestCategoria {
	public static void main(String[] args) {
		
		Conexion conexion = new Conexion("root","Navidad$25","supermark");
		
		System.out.println(conexion.conectar());
		
		Categorias categoria = new Categorias();
		
		categoria.crearCategoria(conexion.getConnection());
		//categoria.actualizarCategoria(conexion.getConnection());
		//categoria.eliminarCategoria(conexion.getConnection());
		//conexion.desconectar();
	}
}
