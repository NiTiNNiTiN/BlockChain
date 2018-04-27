package crypto;

public class TransactionInput {

	//References the transaction output's transactionid
	public String transactionOutpuitId;
	//UTXO -> unspent transaction output (Bitcoin's COnvention)
	public TransactionOutput UTXO;
	
	public TransactionInput(String transactionOutpuitId){
		this.transactionOutpuitId = transactionOutpuitId;
	}
}
