package tkrisz82.rentacar.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class PwdHandler {

	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	// HASH PASSWORD
	public String hashPassword(String pwd)  {
		
		String result = "";
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			md.update(pwd.getBytes());
			
			byte[] resultArray = md.digest();
			
			StringBuilder sb = new StringBuilder();
			
			for(byte b : resultArray) {
				sb.append(String.format("%02x", b));
			}
			
			result = sb.toString();
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	public boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
	}
	
}
