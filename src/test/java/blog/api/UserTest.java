package blog.api;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.junit.Test;

public class UserTest {
	private User toTest = new User();

	@Test
	public void testGetSetUserName() throws Exception {
		String userName = "Mohan";
		toTest.setUsername(userName);
		assertEquals(toTest.getUsername(), userName);

	}
	
	@Test
	public void testGetSetPwd() throws Exception {
		String pwd = "Star";
		toTest.setPwd(pwd);
		assertEquals(toTest.getPwd(), pwd);

	}
	
	@Test
	public void testGetSetMailId() throws Exception {
		String mailId = "mohakum2@cisco.com";
		toTest.setMailid(mailId);
		assertEquals(toTest.getMailid(), mailId);
	}
	
	@Test
	public void testGetSetTagLine() throws Exception {
		String tagLine = "Blog o blog";
		toTest.setTagLine(tagLine);
		assertEquals(toTest.getTagLine(), tagLine);
	}
}
