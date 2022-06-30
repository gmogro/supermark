package Test;

import Entidades.Cliente;
import Utilidades.Conexion;

public class TestCliente {

	public static void main(String[] args) {
		Conexion conexion = new Conexion("root","Navidad$25","supermark");
		System.out.println(conexion.conectar());
		Cliente cliente = new Cliente();
		
		cliente.crearCliente(conexion.getConnection());
		
		//cliente.actualizarCliente(conexion.getConnection());
		//cliente.eliminarCliente(conexion.getConnection());
	}

}
