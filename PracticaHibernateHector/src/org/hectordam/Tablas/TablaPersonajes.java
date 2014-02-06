package org.hectordam.Tablas;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.hectordam.beans.Personajes;
import org.hectordam.beans.Usuarios;
import org.hibernate.Query;
import org.hibernate.Session;
import org.sfsoft.hibernate.util.HibernateUtil;

public class TablaPersonajes extends JTable{

	private DefaultTableModel modelo;
	
	
	public TablaPersonajes(){
		
		String[] columna = {"Id", "Nombre", "Raza", "Clase", "Historia"};
		modelo = new DefaultTableModel(columna, 0);
		
		this.setModel(modelo);
		this.removeColumn(this.getColumnModel().getColumn(0));
		
	}
	
	public void listar(){
		
		modelo.setNumRows(0);
		
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Personajes");
		List<Personajes> personajes = query.list();
		
		for (Personajes personaje : personajes) {
			
			Object[] fila = new Object[]{personaje.getId(), personaje.getNombre(), personaje.getRaza(), personaje.getClase(), personaje.getHistoria()};
			modelo.addRow(fila); 
			
		}
		
	}
	
	public void listaAvanzada(String filtro){
		
		modelo.setNumRows(0);
		
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Personajes where nombre like '%" + filtro + "%' or raza like '%" + filtro + "%'");
		List<Personajes> personajes = query.list();
		
		for (Personajes personaje : personajes) {
			
			Object[] fila = new Object[]{personaje.getId(), personaje.getNombre(), personaje.getRaza(), personaje.getClase(), personaje.getHistoria()};
			modelo.addRow(fila); 
			
		}	
		
	}
	
	public void insertar(Personajes personaje){
		
		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		
		session.save(personaje);
		
		session.getTransaction().commit();
		session.close();
		
		listar();
		
	}
	
	public void modificar(Personajes personaje){
		
		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		session.update(personaje);
		
		session.getTransaction().commit();
		session.close();
		
		listar();
		
	}
	
	public void eliminar(Personajes personaje){
		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		
		session.delete(personaje);
		
		session.getTransaction().commit();
		session.close();
		
		listar();
	
	}

	
	
}
