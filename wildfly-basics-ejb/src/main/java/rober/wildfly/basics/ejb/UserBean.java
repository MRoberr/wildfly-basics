package rober.wildfly.basics.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;

import rober.wildfly.basics.common.IUser;
import rober.wildfly.basics.jpa.User;

@Stateless
public class UserBean implements IUser{

	@PersistenceContext(unitName = "WildflyBasics")
	private EntityManager entityManager;
	
	@Override
	public int addUser(String name) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		int id = ((Number) entityManager.createNamedQuery("User.max").getSingleResult()).intValue();
		System.out.println(id);
		User newUser = new User();
		newUser.setId(id);
		newUser.setName(name);
		
		entityManager.persist(newUser);
		
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

}
