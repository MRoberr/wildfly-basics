package rober.wildfly.basics.web;

import java.io.Serializable;
import java.util.ArrayList;
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
	private List<Role> searchResult = null; 
	
	@Override
	public int createRole(String role) {
		getRoleBean().createRole(role);
		return 0;
	}

	@Override
	public int forgetRole(String role) {
		getRoleBean().forgetRole(role);
		return 0;
	}

	@Override
	public int changeRoleName(String oldRole, String newRole) {
		getRoleBean().changeRoleName(oldRole, newRole);
		return 0;
	}

	@Override
	public List<Role> listExistingRoles() {
		return getRoleBean().listExistingRoles();
	}

	@Override
	public List<Role> searchRoleByName(String role) {
		searchResult = getRoleBean().searchRoleByName(role);
		return searchResult;
	}

	@Override
	public Role searchRoleById(int id) {
		
		Role role = getRoleBean().searchRoleById(id);;
		if(searchResult == null) {
			
			searchResult = new ArrayList<>();
			searchResult.add(role);
		} else {
			
			searchResult.clear();
			searchResult.add(role);
		}		
		return role;

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
	
	public List<Role> getSearchResult() {
		return this.searchResult;
	}
	
	public void setSearchResult(List<Role> searchResult) {
		this.searchResult = searchResult;
	}
}
