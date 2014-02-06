package org.hectordam.ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import org.hectordam.beans.Mapas;
import org.hectordam.beans.Usuarios;
import org.hibernate.Query;
import org.sfsoft.hibernate.util.HibernateUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JList;
import javax.swing.JScrollPane;

import org.hectordam.Tablas.TablaMapas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JUsuarios extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPoblacion;
	private JTextField txtTelefono;
	private JTextField txtCorreo;
	private JTextField txtNombre;
	private JLabel lbNombre;
	private JLabel lbCorreo;
	private JLabel lbTelefono;
	private JLabel lbPoblacion;
	private boolean validar;
	private Usuarios usuario;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JList<Mapas> list;
	private JLabel lblMapas;
	private JLabel lblListaMapas;
	private JButton btnEliminarMapa;
	
	private DefaultListModel<Mapas> modeloListaMapas = new DefaultListModel<Mapas>();
	private ArrayList<Mapas> lista = new ArrayList<Mapas>();
	private TablaMapas tablaMapas;
	private JButton btnAadirMapa;
	
	
	private void anadir(){
		
		int id = (int) tablaMapas.getModel().getValueAt(tablaMapas.getSelectedRow(), 0);
		
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Mapas where id = " + id);
		Mapas mapa =  (Mapas) query.uniqueResult();
		
		lista.add(mapa);
		
		btnAadirMapa.setEnabled(false);
		
		listar();
	}
	
	private void eliminarMapa(){
		
		lista.remove(list.getSelectedIndex());
		
		listar();
	}
	
	private void listar(){
		
		modeloListaMapas.removeAllElements();
		
		for(Mapas mapa: lista){
			modeloListaMapas.addElement(mapa);
		}
		
	}
	
	private void aceptar(){
		
		validar = true;
		this.setVisible(false);
		
	}
	
	private void cancelar(){
		
		validar = false;
		this.setVisible(false);
		
	}
	
	public void insertar(){
		
		txtNombre.setText("");
		txtCorreo.setText("");
		txtPoblacion.setText("");
		txtTelefono.setText("");
		
		usuario = new Usuarios();
	}
	
	public void modificar(Usuarios usuario){
		
		txtNombre.setText(usuario.getNombre());
		txtPoblacion.setText(usuario.getPoblacion());
		txtCorreo.setText(usuario.getCorreo());
		txtTelefono.setText(usuario.getTelefono());
		
		this.usuario = usuario;
	}
	
	public void mostrar(){
		
		lista.clear();
		listar();
		tablaMapas.listar();
		setVisible(true);
	}
	
	/**
	 * Create the dialog.
	 */
	public JUsuarios() {
		setModal(true);
		setBounds(100, 100, 460, 338);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtPoblacion = new JTextField();
		txtPoblacion.setColumns(10);
		txtPoblacion.setBounds(75, 104, 129, 20);
		contentPanel.add(txtPoblacion);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(75, 73, 129, 20);
		contentPanel.add(txtTelefono);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(75, 42, 129, 20);
		contentPanel.add(txtCorreo);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(75, 11, 129, 20);
		contentPanel.add(txtNombre);
		
		lbNombre = new JLabel("Nombre");
		lbNombre.setBounds(10, 14, 46, 14);
		contentPanel.add(lbNombre);
		
		lbCorreo = new JLabel("Correo");
		lbCorreo.setBounds(10, 45, 46, 14);
		contentPanel.add(lbCorreo);
		
		lbTelefono = new JLabel("Telefono");
		lbTelefono.setBounds(10, 76, 46, 14);
		contentPanel.add(lbTelefono);
		
		lbPoblacion = new JLabel("Poblacion");
		lbPoblacion.setBounds(10, 107, 46, 14);
		contentPanel.add(lbPoblacion);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(214, 39, 220, 221);
		contentPanel.add(scrollPane);
		
		tablaMapas = new TablaMapas();
		tablaMapas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				btnAadirMapa.setEnabled(true);
			}
		});
		scrollPane.setViewportView(tablaMapas);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(75, 135, 129, 96);
		contentPanel.add(scrollPane_1);
		
		list = new JList<Mapas>();
		list.setModel(modeloListaMapas);
		scrollPane_1.setViewportView(list);
		
		lblMapas = new JLabel("Mapas");
		lblMapas.setBounds(10, 137, 46, 14);
		contentPanel.add(lblMapas);
		
		lblListaMapas = new JLabel("Lista Mapas");
		lblListaMapas.setBounds(214, 14, 79, 14);
		contentPanel.add(lblListaMapas);
		
		btnEliminarMapa = new JButton("Borrar Mapa");
		btnEliminarMapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminarMapa();
			}
		});
		btnEliminarMapa.setBounds(75, 237, 129, 23);
		contentPanel.add(btnEliminarMapa);
		
		btnAadirMapa = new JButton("A\u00F1adir Mapa");
		btnAadirMapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				anadir();
			}
		});
		btnAadirMapa.setBounds(303, 10, 131, 23);
		contentPanel.add(btnAadirMapa);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						aceptar();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelar();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		setLocationRelativeTo(null);
	}

	public boolean isValidar() {
		return validar;
	}

	public void setValidar(boolean validar) {
		this.validar = validar;
	}

	public Usuarios getUsuario() {
		
		usuario.setNombre(txtNombre.getText());
		usuario.setTelefono(txtTelefono.getText());
		usuario.setCorreo(txtCorreo.getText());
		usuario.setPoblacion(txtPoblacion.getText());
		usuario.setMapases(new HashSet<Mapas>(lista));
		
		return usuario;
	}
}
