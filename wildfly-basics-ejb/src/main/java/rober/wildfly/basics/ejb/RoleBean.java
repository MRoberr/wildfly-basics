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
import rober.wildfly.basics.jpa.model.Role_;

@Stateless
public class RoleBean implements IRole{

	
	@PersistenceContext(unitName = "WildflyBasics")
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

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Role> criteriaQuery = builder.createQuery(Role.class);
		Root<Role> roleRoot = criteriaQuery.from(Role.class);
		
		criteriaQuery.select(roleRoot).where(builder.equal(roleRoot.get(Role_.type), role));
		Role removeRole = entityManager.createQuery(criteriaQuery).getSingleResult();
		entityManager.remove(entityManager.find(Role.class, removeRole.getId()));
		
		return 0;
	}

	@Override
	public int changeRoleName(String oldRole, String newRole) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Role> criteriaQuery = builder.createQuery(Role.class);
		Root<Role> roleRoot = criteriaQuery.from(Role.class);
		
		criteriaQuery.select(roleRoot).where(builder.equal(roleRoot.get(Role_.type), oldRole));
		Role role = entityManager.createQuery(criteriaQuery).getSingleResult();
		role.setType(newRole);
		
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

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Role> criteriaQuery = builder.createQuery(Role.class);
		Root<Role> roleRoot = criteriaQuery.from(Role.class);
		
		criteriaQuery.select(roleRoot).where(builder.equal(roleRoot.get(Role_.type), role));
		
		List<Role> roles = entityManager.createQuery(criteriaQuery).getResultList();
		
		return roles;
	}

	@Override
	public Role searchRoleById(int id) {
		Role role = entityManager.find(Role.class, id);
		return role;
	}

}
