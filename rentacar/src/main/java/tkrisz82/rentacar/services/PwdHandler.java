package tkrisz82.rentacar.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class PwdHandler {

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
	
}
