package asd;

import org.junit.Test;

import rober.wildfly.basics.ejb.UserBean;

public class test {

	@Test
	public void t() {
		try {
			UserBean userBean = new UserBean();
			userBean.addUser("malacka");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
