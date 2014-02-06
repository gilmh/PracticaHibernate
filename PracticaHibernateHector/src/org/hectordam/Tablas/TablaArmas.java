package org.hectordam.Tablas;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.hectordam.beans.Armas;
import org.hectordam.beans.Personajes;
import org.hibernate.Query;
import org.hibernate.Session;
import org.sfsoft.hibernate.util.HibernateUtil;

public class TablaArmas extends JTable{

	private DefaultTableModel modelo;
	
	
	public TablaArmas(){
		
		String[] columna = {"Id", "Nombre", "Descripcion", "Ataque"};
		modelo = new DefaultTableModel(columna, 0);
		
		this.setModel(modelo);
		this.removeColumn(this.getColumnModel().getColumn(0));
		
	}
	
	public void listar(){
		
		modelo.setNumRows(0);
		
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Armas");
		List<Armas> armas = query.list();
		
		for (Armas arma : armas) {
			
			Object[] fila = new Object[]{arma.getId(), arma.getNombre(), arma.getDescripcion(), arma.getAtaque()};
			modelo.addRow(fila); 
			
		}
		
	}
	
	public void listaAvanzada(String filtro){
		
		modelo.setNumRows(0);
		
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Armas where nombre like '%" + filtro + "%' or descripcion like '%" + filtro + "%'");
		List<Armas> armas = query.list();
		
		for (Armas arma : armas) {
			
			Object[] fila = new Object[]{arma.getId(), arma.getNombre(), arma.getDescripcion(), arma.getAtaque()};
			modelo.addRow(fila); 
			
		}
		
	}
	
	public void insertar(Armas armas){
		
		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		
		session.save(armas);
		
		session.getTransaction().commit();
		session.close();
		
		listar();
		
	}
	
	public void modificar(Armas armas){
		
		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		session.update(armas);
		
		session.getTransaction().commit();
		session.close();
		
		listar();
		
	}
	
	public void eliminar(Armas armas){
		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		
		session.delete(armas);
		
		session.getTransaction().commit();
		session.close();
		
		listar();
	
	}
	
}
