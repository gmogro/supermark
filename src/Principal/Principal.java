package Principal;

import java.util.Scanner;

import Entidades.Cliente;
import Utilidades.Conexion;
import Venta.Categorias;
import Venta.Productos;
import Venta.Venta;

public class Principal {
	public static void main(String[] args) {
		
		Conexion conexion = new Conexion("root","Navidad$25","supermark");
		conexion.conectar();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("#################");
		System.out.println("INICIO SESION");
		System.out.println("#################");
		//PEDIR DATOS DE USUARIO PASSWORD
		int option = 1;
		do {
			System.out.println("###############");
			System.out.println("MENU DE SISTEMA");
			System.out.println("###############");
			System.out.println("1 - Categoria");
			System.out.println("2 - Cliente");
			System.out.println("3 - Productos");
			System.out.println("4 - Ventas");
			System.out.println("5 - Reportes");
			System.out.println("0 - SALIR");
			option = sc.nextInt();
			sc.nextLine();
			
			switch (option) {
				case 1: {
						System.out.println("1 - Crear Categoria");
						System.out.println("2 - Actualizar Categoria");
						System.out.println("3 - Eliminar Categoria");
						int optionCategoria = sc.nextInt();
						sc.nextLine();
						switch (optionCategoria) {
							case 1: {
								Categorias categoria = new Categorias();
								categoria.crearCategoria(conexion.getConnection());
								break;
							}
							case 2: {
								Categorias categoria = new Categorias();
								categoria.actualizarCategoria(conexion.getConnection());
								break;
							}
							case 3: {
								Categorias categoria = new Categorias();
								categoria.eliminarCategoria(conexion.getConnection());
								break;
								}
							}
						break;
					}
				case 2: {
					System.out.println("1 - Crear Cliente");
					System.out.println("2 - Actualizar Cliente");
					System.out.println("3 - Eliminar Cliente");
					int optionCliente = sc.nextInt();
					sc.nextLine();
					switch (optionCliente) {
						case 1: {
							Cliente cliente = new Cliente();
							cliente.crearCliente(conexion.getConnection());
							break;
						}
						case 2: {
							Cliente cliente = new Cliente();
							cliente.actualizarCliente(conexion.getConnection());
							break;
						}
						case 3: {
							Cliente cliente = new Cliente();
							cliente.eliminarCliente(conexion.getConnection());
							break;
							}
						}
					break;
					}
				case 3: {
					System.out.println("1 - Crear Producto");
					System.out.println("2 - Actualizar Producto");
					System.out.println("3 - Eliminar Producto");
					System.out.println("4 - Listado de Productos");
					int optionProducto = sc.nextInt();
					sc.nextLine();
					switch (optionProducto) {
						case 1: {
							Productos producto = new Productos();
							producto.crearProducto(conexion.getConnection());
							break;
						}
						case 2: {
							Productos producto = new Productos();
							producto.actualizarProducto(conexion.getConnection());
							break;
						}
						case 3: {
							Productos producto = new Productos();
							producto.eliminarProducto(conexion.getConnection());
							break;
							}
						case 4: {
							Productos producto = new Productos();
							producto.listadoProducto(conexion.getConnection());
							break;
							}
						}
					break;
					}
				case 4: {
					System.out.println("1 - Crear Venta");
					System.out.println("2 - Anular Venta");
					System.out.println("3 - Ver todos los usuarios que realizaron una compra");
					System.out.println("4 - Ver listado de productos seleccionados por el Cliente");
					int optionCliente = sc.nextInt();
					sc.nextLine();
					switch (optionCliente) {
							case 1: {
								Venta venta = new Venta();
								venta.crearVenta(conexion.getConnection());
								break;
							}
							case 2: {
								Venta venta = new Venta();
								venta.anularVenta(conexion.getConnection());
								break;
							}
							case 3: {
								Venta venta = new Venta();
								venta.verClienteVenta(conexion.getConnection());
								break;
							}
							case 4: {
								Venta venta = new Venta();
								venta.verProductoUsuario(conexion.getConnection());
								break;
							}
						}
					break;
					}
				case 5: {
					System.out.println("##################");
					System.out.println("Seccion de Reportes");
					System.out.println("##################");
					break;
				}
			}
			System.out.println("##################");
			System.out.println("¿Cierra el Sistema?");
			System.out.println("0 - SI");
			System.out.println("1 - NO");
			System.out.println("##################");
			option = sc.nextInt();
			sc.nextLine();
		}while(option==1);
		
		System.out.println("FIN DIA LABORAL");
	}
}
