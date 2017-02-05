package rober.wildfly.basics.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import rober.wildfly.basics.common.IRole;
import rober.wildfly.basics.jpa.model.Role;
import rober.wildfly.basics.jpa.model.User;

@Stateless
public class RoleBean implements IRole{

	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public int createRole(String role) {
		
		int id;
		try {
			id = ((Number)entityManager.createNamedQuery("Role.max").getSingleResult()).intValue();
		}catch(NullPointerException e) {
			id = 0;
		}
		Role newRole = new Role();
		newRole.setId(id + 1);
		newRole.setType(role);

		entityManager.persist(newRole);
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

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Role> criteriaQuery = builder.createQuery(Role.class);
		Root<Role> userRoot = criteriaQuery.from(Role.class);
		
		criteriaQuery.select(userRoot);		
		return entityManager.createQuery(criteriaQuery).getResultList();
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

}
