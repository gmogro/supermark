package Test;

import Utilidades.Conexion;

public class TestConexion {
	public static void main(String[] args) {
		Conexion cnn = new Conexion("root","Navidad$25","supermark");
		
		System.out.println(cnn.conectar());
		cnn.desconectar();
	}
}
