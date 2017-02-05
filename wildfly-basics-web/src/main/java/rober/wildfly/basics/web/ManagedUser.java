package rober.wildfly.basics.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import rober.wildfly.basics.common.IUser;
import rober.wildfly.basics.jpa.model.Role;
import rober.wildfly.basics.jpa.model.User;

@Named("mUser")
@ApplicationScoped
public class ManagedUser implements Serializable, IUser {

	private static final long serialVersionUID = -4331042725514143791L;

	private IUser userBean = null;
	private List<User> searchList = null; 
	private List<Role> selected = null;
	
	@Override
	public int addUser(String name) {
		getUserBean().addUser(name);
		clearSearch();
		return 0;
	}

	@Override
	public int removeUserById(int id) {
		getUserBean().removeUserById(id);
		clearSearch();
		return 0;
	}

	@Override
	public int changeUserName(String oldName, String newName) {
		getUserBean().changeUserName(oldName, newName);
		clearSearch();
		return 0;
	}

	@Override
	public User searchForUserById(int id) {
		
		User user = getUserBean().searchForUserById(id);;
		if(searchList == null) {
			
			searchList = new ArrayList<>();
			searchList.add(user);
		} else {
			
			searchList.clear();
			searchList.add(user);
		}		
		return user;
	}

	@Override
	public List<User> searchForUserByName(String name) {
		
		searchList = getUserBean().searchForUserByName(name);
		return searchList;
	}

	@Override
	public List<User> listAllUsers() {
		return getUserBean().listAllUsers();
	}

	@Override
	public int setUserRole(String name, List<Role> roles) {
		clearSearch();
		System.out.println("managed beanbe");
		System.out.println(roles);
		getUserBean().setUserRole(name, roles);
		return 0;
	}
	
	private IUser getUserBean() {
		
		if(userBean == null) {
			
			try {
				InitialContext jndi = new InitialContext();
				userBean = (IUser) jndi.lookup(IUser.jdniName);
			}catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return userBean;			
	}
	
	public List<User> getSearchList() {
		
		if(searchList == null) {
			
			searchList = new ArrayList<>();
		}
		
		return searchList;
	}
	
	public void setSearchList(List<User> searchList) {
		
		this.searchList = searchList;
	}
	
	private void clearSearch() {
		
		searchList = null;
	}
	

	public List<Role> getSelected() {
		return selected;
	}

	public void setSelected(List<Role> roles) {
		this.selected = roles;
	} 
	
}
