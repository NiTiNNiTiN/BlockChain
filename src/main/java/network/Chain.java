package network;
import java.util.ArrayList;

import com.google.gson.GsonBuilder;

public class Chain {

	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 5;

	public static void main(String[] args) {
		blockchain.add( new Block("FirstBlock", "0"));
		System.out.println("Mining First Block :");
		blockchain.get( ( blockchain.size()-1)).mineBlock(difficulty);

		blockchain.add( new Block("SecondBlock", blockchain.get( ( blockchain.size()-1)).hash));
		System.out.println("Mining Second Block :");
		blockchain.get( ( blockchain.size()-1)).mineBlock(difficulty);

		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);

		System.out.println("BlockChain validity : " + isChainValid());
		System.out.println( blockchainJson);
	}

	public static boolean isChainValid() {
		Block currentBlock;
		Block previousBlock;

		for(int i = 1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);

			//Check if Current block's Hash turns out out be equal to calculated Hash 
			if( !currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("Current Hash isn't equal to the calculated Hash");
				return false;
			}
			//Compare Current Block's PreviousHash to Previous Blocks Hash
			if( currentBlock.previousHash != previousBlock.hash) {
				System.out.println("Current Block's previous Hash isn't equal to the previous Hash");
				return false;
			}
			//Check if Hash is Mined or Solved
			//new String(new char[difficulty]).replace('\0','0') -> It initializes a String with length of difficulty and having 0 at every place
			if( !currentBlock.hash.substring(0, difficulty).equals(new String(new char[difficulty]).replace('\0','0'))) {
				System.out.println("The Block hasn't been mined");
				return false;
			}
		}
		return true;
	}
}
