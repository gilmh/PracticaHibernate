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

public class TablaMisiones extends JTable{

	private DefaultTableModel modelo;
	
	
	public TablaMisiones(){
		
		String[] columna = {"Id", "Nombre", "Descripcion", "Puntos"};
		modelo = new DefaultTableModel(columna, 0);
		
		this.setModel(modelo);
		this.removeColumn(this.getColumnModel().getColumn(0));
		
	}
	
	public void listar(){
		
		modelo.setNumRows(0);
		
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Misiones");
		List<Misiones> misiones = query.list();
		
		for (Misiones mision : misiones) {
			
			Object[] fila = new Object[]{mision.getId(), mision.getNombre(), mision.getDescripcion(), mision.getPuntos()};
			modelo.addRow(fila); 
		}
		
	}
	
	public void listaAvanzada(String filtro){
		
		modelo.setNumRows(0);
		
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Armas where nombre like '%" + filtro + "%' or descripcion like '%" + filtro + "%'");
		List<Misiones> misiones = query.list();
		
		for (Misiones mision : misiones) {
			
			Object[] fila = new Object[]{mision.getId(), mision.getNombre(), mision.getDescripcion(), mision.getPuntos()};
			modelo.addRow(fila); 
		}
		
	}
	
	public void insertar(Misiones mision){
		
		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		
		session.save(mision);
		
		session.getTransaction().commit();
		session.close();
		
		listar();
		
	}
	
	public void modificar(Misiones mision){
		
		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		session.update(mision);
		
		session.getTransaction().commit();
		session.close();
		
		listar();
		
	}
	
	public void eliminar(Misiones mision){
		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		
		session.delete(mision);
		
		session.getTransaction().commit();
		session.close();
		
		listar();
	
	}
	
}
