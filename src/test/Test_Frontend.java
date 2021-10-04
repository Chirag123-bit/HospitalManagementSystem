package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Test;

import com.mysql.cj.util.TestUtils;

import backend.AdminOperations;
import frontend.Register;
import model.Registration;

public class Test_Frontend {
	Registration reg;
	@Before
	public void setUp() {
		reg = new Registration("Test", "User", "For","SetterGetter","Testing");
		
	}
	
	@Test
	public void test_getFname() {
		reg.setFname("Test");
		String actual = reg.getFname();
		assertEquals("Test", actual);
	}
	
	@Test
	public void test_getLname() {
		reg.setLname("User");
		String actual = reg.getLname();
		assertEquals("User", actual);
	}
	
	@Test
	public void test_getUname() {
		reg.setUname("For");
		String actual = reg.getUname();
		assertEquals("For", actual);
	}
}
