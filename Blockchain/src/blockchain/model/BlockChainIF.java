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
    public boolean initialiseChain(BlockIF block);

    public BlockChainIF addBlock(String data);
    
}
