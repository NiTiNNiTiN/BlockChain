package network;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

public class Utility {

	public static String applySha256(String input) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append(0);
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	public static String getStringFromKey(PublicKey key) {
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}
	public static String getStringFromKey(PrivateKey key) {
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}
	
	//Apply ECDSA Signature and return the results
	//Takes in private key and input as parameter -> Signs the input ans return the array of bytes
	public static byte[] applyECDSASig(PrivateKey privateKey, String input) {
		Signature dsa;
		byte[] output = new byte[0];
		try {
			dsa = Signature.getInstance("ECDSA", "BC");
			dsa.initSign(privateKey);
			byte[] strByte = input.getBytes();
			dsa.update(strByte);
			byte[] realSign = dsa.sign();
			output = realSign;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		return output;
	}
	//Verify the signature
	public static boolean verifyECDSASig(PublicKey publicKey, String data, byte[] signature) {
		try {
			Signature ecdsaVerify = Signature.getInstance("ECDSA", "BC");
			ecdsaVerify.initVerify(publicKey);
			ecdsaVerify.update(data.getBytes());
			return ecdsaVerify.verify(signature);
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

}
