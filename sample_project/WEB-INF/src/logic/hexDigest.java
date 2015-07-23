package logic;

import org.apache.commons.codec.digest.DigestUtils;

public class hexDigest{

	static byte[] data_sha256;
	
	public static final String SHA256 = "SHA-256";

	public byte[] makeDigest(String data){
		
		data_sha256 = DigestUtils.sha256(data);

		return data_sha256;
	
	}
}

	