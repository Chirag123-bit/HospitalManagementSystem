package test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;

import org.junit.Before;
import org.junit.Test;

import backend.AdminOperations;
import frontend.Register;

public class Test_Backend {
	AdminOperations obj;
	@Before
	public void setUp() {
		obj = new AdminOperations();
		
	}
	@Test
	public void test_getDoctors() {
		ResultSet actual = obj.getDoctors();
		assertNotNull(actual);
	}
	
	@Test
	public void test_getNurses() {
		ResultSet actual = obj.getNurses();
		assertNotNull(actual);
	}
	
	@Test
	public void test_getPatients() {
		ResultSet actual = obj.getPatients();
		assertNotNull(actual);
	}
	
	@Test
	public void test_register() {
		boolean actual = obj.register("Nabin", "Bhattarai", "nabin3", "Doctor", "Abcd");
		assertTrue(actual);
	}
	
	@Test
	public void test_addBed() {
		//"INSERT INTO `bed`(`ward`,`bed`) VALUES(?, ?)"; 
		boolean actual = obj.addBed(1,"Test");
		assertTrue(actual);
	}
	

}
