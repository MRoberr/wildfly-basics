package rober.wildfly.basics.common;

import java.util.List;

public interface IRole {
	
	
	public int createRole(String role);
	public int forgetRole(String role);
	public int changeRoleName(String role);
	public List<String> listExistingRoles();
	public List<String> searchRoleByName(String role);
	public String searchRoleById(int id);
	
}
