package network;
import java.util.Date;

public class Block {

	public String hash;
	public String previousHash;
	private String data;
	private long timestamp;

	private int nonce;
	
	Block(String data, String previousHash) {
		this.data = data;
		this.previousHash = previousHash;
		this.timestamp = new Date().getTime();
		this.hash = calculateHash();
	}

	public String calculateHash() {
		return Utility.applySha256(previousHash + Long.toString(timestamp) + Integer.toString(nonce) + data);
	}
	
	public void mineBlock(int difficulty) {
		//Below initializes a String with length of difficulty and having 0 at every place
		String target = new String(new char[5]).replace('\0','0');
		while(!hash.substring(0,difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! " + hash);
	}
}
