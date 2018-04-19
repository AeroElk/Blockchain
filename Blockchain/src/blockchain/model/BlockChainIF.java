/**
 * simple interface of a Blockchain
 */
package blockchain.model;

/**
 *
 * @author 
 */
public interface BlockChainIF
{
    /**
     * initalise chain
     * @param block
     * @return 
     */
    public boolean initialiseChain(BlockIF block); 

    /**
     * add block to blockchain
     * @param data
     * @return 
     */
    public BlockChainIF addBlock(String data);
    
}
