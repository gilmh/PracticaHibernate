package org.hectordam.ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.hectordam.beans.Personajes;
import org.hectordam.beans.Usuarios;
import org.hibernate.Query;
import org.sfsoft.hibernate.util.HibernateUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JComboBox;

public class JPersonajes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtRaza;
	private JTextField txtClase;
	private JTextField txtHistoria;
	private boolean validar;
	private Personajes personaje;
	private JComboBox<String> comboBox;
	private JLabel lblUsuario;

	
	private void listar(){
		
		comboBox.removeAllItems();
		
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Usuarios");
		List<Usuarios> usuarios = query.list();
		
		for(Usuarios usuario: usuarios){
			comboBox.addItem(usuario.getNombre());
		}
		
	}
	
	public void insertar(){
		listar();
		
		txtNombre.setText("");
		txtClase.setText("");
		txtRaza.setText("");
		txtHistoria.setText("");
		
		this.personaje = new Personajes();
		
	}
	
	public void modificar(Personajes personaje){
		listar();
		
		txtNombre.setText(personaje.getNombre());
		txtClase.setText(personaje.getClase());
		txtRaza.setText(personaje.getRaza());
		txtHistoria.setText(personaje.getHistoria());
		comboBox.setSelectedItem(personaje.getUsuarios().getNombre());
		
		this.personaje = personaje;
		
	}
	
	private void aceptar(){
		
		validar = true;
		this.setVisible(false);
		
	}
	
	private void cancelar(){
		
		validar = false;
		this.setVisible(false);
		
	}
	
	public void mostrar(){
		
		this.setVisible(true);
	}
	
	/**
	 * Create the dialog.
	 */
	public JPersonajes() {
		
		setBounds(100, 100, 249, 259);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNombre = new JLabel("Nombre");
			lblNombre.setBounds(10, 29, 46, 14);
			contentPanel.add(lblNombre);
		}
		{
			JLabel lblRaza = new JLabel("Raza");
			lblRaza.setBounds(10, 54, 46, 14);
			contentPanel.add(lblRaza);
		}
		{
			JLabel lblClase = new JLabel("Clase");
			lblClase.setBounds(10, 79, 46, 14);
			contentPanel.add(lblClase);
		}
		{
			JLabel lblHistoria = new JLabel("Historia");
			lblHistoria.setBounds(10, 104, 46, 14);
			contentPanel.add(lblHistoria);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setBounds(66, 26, 136, 20);
			contentPanel.add(txtNombre);
			txtNombre.setColumns(10);
		}
		{
			txtRaza = new JTextField();
			txtRaza.setBounds(66, 51, 136, 20);
			contentPanel.add(txtRaza);
			txtRaza.setColumns(10);
		}
		{
			txtClase = new JTextField();
			txtClase.setColumns(10);
			txtClase.setBounds(66, 76, 136, 20);
			contentPanel.add(txtClase);
		}
		{
			txtHistoria = new JTextField();
			txtHistoria.setColumns(10);
			txtHistoria.setBounds(66, 101, 136, 20);
			contentPanel.add(txtHistoria);
		}
		
		comboBox = new JComboBox();
		comboBox.setBounds(66, 132, 136, 20);
		contentPanel.add(comboBox);
		
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 135, 46, 14);
		contentPanel.add(lblUsuario);
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
		
		setModal(true);
		setLocationRelativeTo(null);
	}

	public boolean isValidar() {
		return validar;
	}

	public void setValidar(boolean validar) {
		this.validar = validar;
	}

	public Personajes getPersonaje() {
		
		personaje.setNombre(txtNombre.getText());
		personaje.setHistoria(txtHistoria.getText());
		personaje.setClase(txtClase.getText());
		personaje.setRaza(txtRaza.getText());
		
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Usuarios where nombre = '" + comboBox.getSelectedItem() + "'");
		Usuarios usuario = (Usuarios) query.uniqueResult();
		
		personaje.setUsuarios(usuario);
		
		return personaje;
	}

	public void setPersonaje(Personajes personaje) {
		this.personaje = personaje;
	}
}
