package rober.wildfly.basics.common;

import java.util.List;

import rober.wildfly.basics.jpa.model.Role;
import rober.wildfly.basics.jpa.model.User;

public interface IUser {

	public static final String jdniName = "java:global/wildfly-basics-ear-0.0.1-SNAPSHOT/wildfly-basics-ejb-0.0.1-SNAPSHOT/UserBean";
	
	public int addUser(String name) throws Exception;
	public int removeUserById(int id) throws Exception;
	public int changeUserName(String oldName, String newName) throws Exception;
	public User searchForUserById(int id) throws Exception;
	public List<User> searchForUserByName(String name) throws Exception;
	public List<User> listAllUsers() throws Exception;
	public int setUserRole(String name, List<Role> roles) throws Exception;
	
}
