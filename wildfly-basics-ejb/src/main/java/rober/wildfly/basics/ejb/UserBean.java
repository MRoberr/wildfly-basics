package rober.wildfly.basics.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import rober.wildfly.basics.common.IUser;
import rober.wildfly.basics.jpa.model.Role;
import rober.wildfly.basics.jpa.model.User;
import rober.wildfly.basics.jpa.model.User_;

@Stateless
public class UserBean implements IUser {

	@PersistenceContext(unitName = "WildflyBasics")
	private EntityManager entityManager;

	@Override
	public int addUser(String name) {

		int id;
		try {
			id = ((Number)entityManager.createNamedQuery("User.max").getSingleResult()).intValue();
		}catch(NullPointerException e) {
			id = 0;
		}
		User newUser = new User();
		newUser.setId(id + 1);
		newUser.setName(name);

		entityManager.persist(newUser);
		return 0;
	}

	@Override
	public int removeUserById(int id) {

		User user = entityManager.find(User.class, id);
		entityManager.remove(user);
		
		return 0;
	}

	@Override
	public int changeUserName(String oldName, String newName) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
		Root<User> userRoot = criteriaQuery.from(User.class);
		
		criteriaQuery.select(userRoot).where(builder.equal(userRoot.get(User_.name), oldName));
		User user = entityManager.createQuery(criteriaQuery).getSingleResult();
		user.setName(newName);
		return 0;
	}

	@Override
	public User searchForUserById(int id) {
		User user = entityManager.find(User.class, id); 
		return user;
	}

	@Override
	public List<User> searchForUserByName(String name) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
		Root<User> userRoot = criteriaQuery.from(User.class);
		
		criteriaQuery.select(userRoot).where(builder.like(userRoot.get(User_.name), "%" + name + "%"));
		List<User> users = entityManager.createQuery(criteriaQuery).getResultList();
		return users;
	}

	@Override
	public List<User> listAllUsers() {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
		Root<User> userRoot = criteriaQuery.from(User.class);
		
		criteriaQuery.select(userRoot);		
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public int setUserRole(String name, List<Role> roles) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User>criteriaQuery = builder.createQuery(User.class);
		Root<User> userRoot = criteriaQuery.from(User.class);
		
		criteriaQuery.select(userRoot).where(builder.equal(userRoot.get(User_.name), name));
		
		User user = entityManager.createQuery(criteriaQuery).getSingleResult();
		user.setRoles(roles);
		
		return 0;
	}


}
