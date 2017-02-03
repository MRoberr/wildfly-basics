package rober.wildfly.basics.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import rober.wildfly.basics.common.IRole;
import rober.wildfly.basics.jpa.model.Role;

@Named("mRole")
@ApplicationScoped
public class ManagedRole implements Serializable, IRole {

	private static final long serialVersionUID = 2149223857083936789L;

	private IRole roleBean = null;
	
	@Override
	public int createRole(String role) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int forgetRole(String role) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int changeRoleName(String oldRole, String newRole) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Role> listExistingRoles() {
		return getRoleBean().listExistingRoles();
	}

	@Override
	public List<Role> searchRoleByName(String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role searchRoleById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	private IRole getRoleBean() {

		if (roleBean == null) {

			try {
				InitialContext jndi = new InitialContext();
				roleBean = (IRole) jndi.lookup(IRole.jdniName);
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return roleBean;
	}
}
