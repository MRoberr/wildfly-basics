package rober.wildfly.basics.jpa.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.criteria.Fetch;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQueries({
	@NamedQuery(name="User.findAll", query="SELECT u FROM User u"),
	@NamedQuery(name="User.max", query="SELECT max(u.id) FROM User u")
})
public class User implements Serializable {

	private static final long serialVersionUID = 2601911083004125008L;

	@Id
	private int id;

	private String name;

	//uni-directional many-to-many association to Role
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch=FetchType.EAGER )
	@JoinTable(
		name="users_roles"
		, joinColumns={
			@JoinColumn(name="user_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="role_id")
			}
		)
	private List<Role> roles;

	public User() {
		roles = new ArrayList<>();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		System.out.println("itt");
		this.roles = roles;
	}
	
	public String getSRoles() {
		
		String stringRoles = ""; 
		
		for(Role r: roles) {
			stringRoles += r.getType() + " ";
		}
		
		return stringRoles;
	}

}