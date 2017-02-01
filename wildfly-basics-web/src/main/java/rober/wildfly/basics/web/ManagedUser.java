package rober.wildfly.basics.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import rober.wildfly.basics.common.IUser;
import rober.wildfly.basics.jpa.model.User;

@Named("mUser")
@ApplicationScoped
public class ManagedUser implements Serializable, IUser {

	private static final long serialVersionUID = -4331042725514143791L;

	private IUser userBean = null;
	
	@Override
	public int addUser(String name) {
		
		getUserBean().addUser(name);
		return 0;
	}

	@Override
	public int removeUser(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int changeUserName(String oldName, String newName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User searchForUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> searchForUserBy(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> listAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int setUserRole(String name, String role) {
		// TODO Auto-generated method stub
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

	
}
