package rober.wildfly.basics.common;

import java.util.List;

import rober.wildfly.basics.jpa.model.Role;

public interface IRole {
	
	public static final String jdniName = "java:global/wildfly-basics-ear-0.0.1-SNAPSHOT/wildfly-basics-ejb-0.0.1-SNAPSHOT/RoleBean";
	
	int createRole(String role);
	int forgetRole(String role);
	int changeRoleName(String oldRole, String newRole);
	List<Role> listExistingRoles();
	List<Role> searchRoleByName(String role);
	Role searchRoleById(int id);
	
}
