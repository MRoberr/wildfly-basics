package rober.wildfly.basics.common;

import java.util.List;

import rober.wildfly.basics.jpa.model.User;

public interface IUser {

	public static final String jdniName = "java:global/wildfly-basics-ear-0.0.1-SNAPSHOT/wildfly-basics-ejb-0.0.1-SNAPSHOT/UserBean";
	
	public int addUser(String name);
	public int removeUser(int id);
	public int changeUserName(String oldName, String newName);
	public User searchForUserById(int id);
	public List<User> searchForUserBy(String name);
	public List<User> listAllUsers();
	public int setUserRole(String name, String role);
	
}
