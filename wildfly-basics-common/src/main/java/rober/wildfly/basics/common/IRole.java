package rober.wildfly.basics.common;

import java.util.List;

import rober.wildfly.basics.jpa.model.Role;

public interface IRole {
	
	public static final String jdniName = "java:global/wildfly-basics-ear-0.0.1-SNAPSHOT/wildfly-basics-ejb-0.0.1-SNAPSHOT/RoleBean";
	
	public int createRole(String role);
	public int forgetRole(String role);
	public int changeRoleName(String oldRole, String newRole);
	public List<Role> listExistingRoles();
	public List<Role> searchRoleByName(String role);
	public Role searchRoleById(int id);
	
}
