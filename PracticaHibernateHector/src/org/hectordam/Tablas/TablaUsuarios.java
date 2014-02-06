package org.hectordam.Tablas;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.hectordam.beans.Usuarios;
import org.hibernate.Query;
import org.hibernate.Session;
import org.sfsoft.hibernate.util.HibernateUtil;

public class TablaUsuarios extends JTable{

	private DefaultTableModel modelo;
	
	
	
	public TablaUsuarios(){
		
		String[] columna = {"id", "Nombre", "Poblacion", "Telefono", "Correo"};
		modelo = new DefaultTableModel(columna, 0);
		
		this.setModel(modelo);
		this.removeColumn(this.getColumnModel().getColumn(0));
	}
	
	public void listar(){
		
		modelo.setNumRows(0);
		
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Usuarios");
		List<Usuarios> usuarios = query.list();
		
		for (Usuarios usuario : usuarios) {
			
			Object[] fila = new Object[]{usuario.getId(), usuario.getNombre(), usuario.getPoblacion(), usuario.getTelefono(), usuario.getCorreo()};
			modelo.addRow(fila); 
			
		}
		
	}
	
	public void listaAvanzada(String filtro){
		
		modelo.setNumRows(0);
		
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Usuarios where nombre like '%" + filtro + "%' or poblacion like '%" + filtro + "%'");
		List<Usuarios> usuarios = query.list();
		
		for (Usuarios usuario : usuarios) {
			
			Object[] fila = new Object[]{usuario.getId(), usuario.getNombre(), usuario.getPoblacion(), usuario.getTelefono(), usuario.getCorreo()};
			modelo.addRow(fila); 
			
		}		
		
	}
	
	public void insertar(Usuarios usuario){
		
		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		
		session.save(usuario);
		
		session.getTransaction().commit();
		session.close();
		
		listar();
		
	}
	
	public void modificar(Usuarios usuario){
		
		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		session.update(usuario);
		
		session.getTransaction().commit();
		session.close();
		
		listar();
		
	}
	
	public void eliminar(Usuarios usuario){
		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		
		session.delete(usuario);
		
		session.getTransaction().commit();
		session.close();
		
		listar();
	
	}
	
}
