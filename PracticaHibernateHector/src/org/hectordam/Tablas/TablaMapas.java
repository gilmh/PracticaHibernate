package org.hectordam.Tablas;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.hectordam.beans.Armas;
import org.hectordam.beans.Mapas;
import org.hectordam.beans.Misiones;
import org.hibernate.Query;
import org.hibernate.Session;
import org.sfsoft.hibernate.util.HibernateUtil;

public class TablaMapas extends JTable{

	private DefaultTableModel modelo;
	
	
	public TablaMapas(){
		
		String[] columna = {"Id", "Nombre", "Descripcion", "Dimension"};
		modelo = new DefaultTableModel(columna, 0);
		
		this.setModel(modelo);
		this.removeColumn(this.getColumnModel().getColumn(0));	
		
	}
	
	public void listar(){
		
		modelo.setNumRows(0);
		
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Mapas");
		List<Mapas> mapas = query.list();
		
		for (Mapas mapa : mapas) {
			
			Object[] fila = new Object[]{mapa.getId(), mapa.getNombre(), mapa.getDescripcion(), mapa.getDimension()};
			modelo.addRow(fila); 
		}
		
	}
	
	public void listaAvanzada(String filtro){
		
		modelo.setNumRows(0);
		
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Armas where nombre like '%" + filtro + "%' or descripcion like '%" + filtro + "%'");
		List<Mapas> mapas = query.list();
		
		for (Mapas mapa : mapas) {
			
			Object[] fila = new Object[]{mapa.getId(), mapa.getNombre(), mapa.getDescripcion(), mapa.getDimension()};
			modelo.addRow(fila); 
		}
		
	}
	
	public void insertar(Mapas mapa){
		
		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		
		session.save(mapa);
		
		session.getTransaction().commit();
		session.close();
		
		listar();
		
	}
	
	public void modificar(Mapas mapa){
		
		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		session.update(mapa);
		
		session.getTransaction().commit();
		session.close();
		
		listar();
		
	}
	
	public void eliminar(Mapas mapa){
		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		
		session.delete(mapa);
		
		session.getTransaction().commit();
		session.close();
		
		listar();
	
	}
	
}
