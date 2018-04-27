package crypto;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;

import network.Utility;

public class Transaction {

	//Hash code of transaction being used as id
	public String transactionId;
	public PublicKey sender;
	public PublicKey receiver;
	public float value;
	//Made up of private key, to stop misuse of wallet
	public byte[] signature;
	
	public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
	public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();
	
	//Count of transactions
	private static int sequence = 0;
	
	public Transaction(PublicKey from, PublicKey to, float value, ArrayList<TransactionInput> inputs) {
		this.sender = from;
		this.receiver = to;
		this.value = value;
		this.inputs = inputs;
	}
	
	//Calculate Hash for each transaction
	public void calculateHash() {
		//Change sequence so that no two thread makes same hash for multiple transactions
		sequence ++;
		Utility.applySha256(Utility.getStringFromKey(sender) + Utility.getStringFromKey(receiver) + Float.toString(value) + sequence);
	}
	
	public void generateSignature(PrivateKey privateKey) {
		String data = Utility.getStringFromKey(sender) + Utility.getStringFromKey(receiver) + Float.toString(value);
		signature = Utility.applyECDSASig(privateKey, data);		
	}
	
	public boolean verifySignature() {
		String data = Utility.getStringFromKey(sender) + Utility.getStringFromKey(receiver) + Float.toString(value);
		return Utility.verifyECDSASig(sender, data, signature);
	}
}
