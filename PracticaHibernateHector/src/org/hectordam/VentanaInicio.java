package org.hectordam;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

import org.hectordam.beans.Login;
import org.hectordam.beans.Usuarios;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.sfsoft.hibernate.util.HibernateUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class VentanaInicio {

	private JFrame frame;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JTextField txtUsuario;
	private JLabel lblUsuario;
	private JTextField txtPassword;
	private JLabel lblPassword;
	private VentanaPrincipal principal;
	
	
	private void aceptar(){
		
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Login where usuario = :nombre and password = :password");
		query.setParameter("nombre", txtUsuario.getText());
		query.setParameter("password", txtPassword.getText());
		
		Login login = (Login) query.uniqueResult();
		
		if(login == null){
			JOptionPane.showMessageDialog(null, "los campos no son validos");
			return;
		}
		
		frame.setVisible(false);
		principal.mostrar();
	}
	
	private void inicializar(){
		
		principal = new VentanaPrincipal(this);
		
		try {
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSession();
		
		} catch (HibernateException he) {
			he.printStackTrace();
		}
		
	}
	
	public void mostrar(){
		
		txtPassword.setText("");
		txtUsuario.setText("");
		frame.setVisible(true);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio window = new VentanaInicio();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaInicio() {
		initialize();
		inicializar();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 314, 148);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aceptar();
			}
		});
		btnAceptar.setBounds(87, 73, 89, 23);
		frame.getContentPane().add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancelar.setBounds(193, 73, 89, 23);
		frame.getContentPane().add(btnCancelar);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(87, 11, 195, 20);
		frame.getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setBounds(10, 11, 67, 14);
		frame.getContentPane().add(lblUsuario);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(87, 42, 195, 20);
		frame.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(10, 42, 67, 14);
		frame.getContentPane().add(lblPassword);
		
		frame.setLocationRelativeTo(null);
	}
}
