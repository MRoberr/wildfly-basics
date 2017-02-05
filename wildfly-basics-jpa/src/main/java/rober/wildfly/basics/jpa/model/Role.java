package rober.wildfly.basics.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@NamedQueries({
	@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r"),
	@NamedQuery(name="Role.max", query="SELECT max(r.id) FROM Role r")
})
public class Role implements Serializable {

	@Transient
	private static final long serialVersionUID = -793191113308148220L;

	@Id
	private int id;

	@Column(name = "type")
	private String type;

	public Role() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Role [id = " + Integer.toString(id) + "; type = " + type + "]"; 
	}

}