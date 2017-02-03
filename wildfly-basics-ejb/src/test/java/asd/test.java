package asd;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import rober.wildfly.basics.ejb.UserBean;
import rober.wildfly.basics.jpa.model.Role;
import rober.wildfly.basics.jpa.model.User;

public class test {

	@Test
	public void t() {
		try {
			User user = new User();
			user.setId(6);
			user.setName("test");
			
			List<Role> roles = new ArrayList<>();
			Role r1 = new Role();
			r1.setId(10);
			r1.setType("r1");
			Role r2 = new Role();
			r2.setId(11);
			r2.setType("r2");
			roles.add(r1);
			roles.add(r2);
			
			user.setRoles(roles);
			
			UserBean bean = new UserBean();
			bean.setUserRole("robi", roles);
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
