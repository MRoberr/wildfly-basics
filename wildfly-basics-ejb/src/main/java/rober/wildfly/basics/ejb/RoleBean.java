package rober.wildfly.basics.ejb;

import java.util.List;

import javax.ejb.Stateless;

import rober.wildfly.basics.common.IRole;

@Stateless
public class RoleBean implements IRole{

	@Override
	public int createRole(String role) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int forgetRole(String role) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int changeRoleName(String role) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> listExistingRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> searchRoleByName(String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String searchRoleById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
