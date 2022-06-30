package Venta;

public class DetalleVenta {
	private int idproducto;
	private int idventa;
	private int cantidad;
	private double precioUnitario;
	
	public DetalleVenta() {
	}
	public DetalleVenta(int idproducto, int idventa, int cantidad, double precioUnitario) {
		this.idproducto = idproducto;
		this.idventa = idventa;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
	}
	public DetalleVenta(int idproducto,int cantidad, double precioUnitario) {
		this.idproducto = idproducto;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
	}
	public int getIdproducto() {
		return idproducto;
	}
	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}
	public int getIdventa() {
		return idventa;
	}
	public void setIdventa(int idventa) {
		this.idventa = idventa;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	
	
}
