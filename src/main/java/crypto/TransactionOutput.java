package crypto;

import java.security.PublicKey;

import network.Utility;

public class TransactionOutput {

	public String id;
	public PublicKey reciepient;		//new owner of the coin
	public float value;					//Amount of Coin they own
	public String parentTransactioId;	//Id of transaction this id was created in

	public TransactionOutput(PublicKey publicKey, float value, String parentTransactionId) {
		this.reciepient = publicKey;
		this.value = value;
		this.parentTransactioId = parentTransactionId;
		this.id = Utility.applySha256(Utility.getStringFromKey(publicKey) + Float.toString(value) + parentTransactionId);
	}

	public Boolean isMine(PublicKey publicKey) {
		return (publicKey == reciepient);
	}
}
