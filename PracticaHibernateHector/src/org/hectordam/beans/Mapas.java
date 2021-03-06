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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Mapas generated by hbm2java
 */
@Entity
@Table(name = "mapas", catalog = "juegohibernate")
public class Mapas implements java.io.Serializable {

	private Integer id;
	private String nombre;
	private String descripcion;
	private String dimension;
	private Set<Usuarios> usuarioses = new HashSet<Usuarios>(0);

	public Mapas() {
	}

	public Mapas(String nombre, String descripcion, String dimension,
			Set<Usuarios> usuarioses) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.dimension = dimension;
		this.usuarioses = usuarioses;
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

	@Column(name = "nombre", length = 20)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion", length = 30)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "dimension", length = 20)
	public String getDimension() {
		return this.dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuarios_mapas", catalog = "juegohibernate", joinColumns = { @JoinColumn(name = "id_mapa", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "id_usuario", nullable = false, updatable = false) })
	public Set<Usuarios> getUsuarioses() {
		return this.usuarioses;
	}

	public void setUsuarioses(Set<Usuarios> usuarioses) {
		this.usuarioses = usuarioses;
	}

	@Override
	public String toString() {
		return nombre;
	}
	
}
