package rober.wildfly.basics.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
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

	private UIComponent add;
	private UIComponent remove;
	private String oldName;
	private String newName;
	private UIComponent searchID;
	private UIComponent searchName;
	private String set;

	@Override
	public int addUser(String name) {
		try {
			getUserBean().addUser(name);
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(add.getClientId(FacesContext.getCurrentInstance()),
					new FacesMessage("User already exists."));
		}
		clearSearch();
		return 0;
	}

	@Override
	public int removeUserById(int id) {
		try {
			getUserBean().removeUserById(id);
		} catch (Exception e) {
			
			FacesContext.getCurrentInstance().addMessage(remove.getClientId(FacesContext.getCurrentInstance()),
					new FacesMessage("No user found with that ID."));
		}
		clearSearch();
		return 0;
	}

	@Override
	public int changeUserName(String oldName, String newName) {
		try {
			getUserBean().changeUserName(oldName, newName);
		} catch (Exception e) {
			// TODO: handle exception
		}
		clearSearch();
		return 0;
	}

	@Override
	public User searchForUserById(int id) {
		
		User user = null;
		
		try {
			user = getUserBean().searchForUserById(id);
			
			if (searchList == null) {
	
				searchList = new ArrayList<>();
				searchList.add(user);
			} else {
	
				searchList.clear();
				searchList.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return user;
	}

	@Override
	public List<User> searchForUserByName(String name) {

		try {
			searchList = getUserBean().searchForUserByName(name);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return searchList;
	}

	@Override
	public List<User> listAllUsers() {
		
		List<User> users = new ArrayList<>(); 
		try {
			users = getUserBean().listAllUsers();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return users;
	}

	@Override
	public int setUserRole(String name, List<Role> roles) {
		
		clearSearch();
		try {
			getUserBean().setUserRole(name, roles);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	public String setRole(String name, String[] roles) {

		List<Role> rolesList = new ArrayList<>();

		for (String r : roles) {
			String[] cut = r.split(",");
			Role newRole = new Role();
			newRole.setId(new Integer(cut[0]));
			newRole.setType(cut[1]);

			rolesList.add(newRole);
		}

		setUserRole(name, rolesList);
		return null;
	}

	private IUser getUserBean() {

		if (userBean == null) {

			try {
				InitialContext jndi = new InitialContext();
				userBean = (IUser) jndi.lookup(IUser.jdniName);
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return userBean;
	}

	public List<User> getSearchList() {

		if (searchList == null) {

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

	public UIComponent getAdd() {
		return add;
	}

	public void setAdd(UIComponent add) {
		this.add = add;
	}

	public UIComponent getRemove() {
		return remove;
	}

	public void setRemove(UIComponent remove) {
		this.remove = remove;
	}

	public UIComponent getSearchID() {
		return searchID;
	}

	public void setSearchID(UIComponent searchID) {
		this.searchID = searchID;
	}

	public UIComponent getSearchName() {
		return searchName;
	}

	public void setSearchName(UIComponent searchName) {
		this.searchName = searchName;
	}

	public String getSet() {
		return set;
	}

	public void setSet(String set) {
		this.set = set;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	
}
