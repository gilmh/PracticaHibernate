package org.hectordam.beans;

// Generated 03-feb-2014 23:09:30 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Personajes generated by hbm2java
 */
@Entity
@Table(name = "personajes", catalog = "juegohibernate")
public class Personajes implements java.io.Serializable {

	private Integer id;
	private Usuarios usuarios;
	private String nombre;
	private String raza;
	private String clase;
	private String historia;
	private Set<Armas> armases = new HashSet<Armas>(0);

	public Personajes() {
	}

	public Personajes(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public Personajes(Usuarios usuarios, String nombre, String raza,
			String clase, String historia, Set<Armas> armases) {
		this.usuarios = usuarios;
		this.nombre = nombre;
		this.raza = raza;
		this.clase = clase;
		this.historia = historia;
		this.armases = armases;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario", nullable = false)
	public Usuarios getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	@Column(name = "nombre", length = 20)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "raza", length = 20)
	public String getRaza() {
		return this.raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	@Column(name = "clase", length = 20)
	public String getClase() {
		return this.clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	@Column(name = "historia", length = 50)
	public String getHistoria() {
		return this.historia;
	}

	public void setHistoria(String historia) {
		this.historia = historia;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "personajes")
	public Set<Armas> getArmases() {
		return this.armases;
	}

	public void setArmases(Set<Armas> armases) {
		this.armases = armases;
	}

}
