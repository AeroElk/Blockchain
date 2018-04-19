/**
 * defines Block-Interface for a Blockchain
 */
package blockchain.model;

/**
 * @author 
 */
public interface BlockIF
{
    static final String BLOCK_DIGEST_SHA_256 = "SHA-256";
    
    /**
     * creates new Genesis Block, called from within a new Blockchain
     *
     * @return
     */
    public BlockIF getNewGenesisBlock();
    
    /**
     * creates new Block
     * 
     * @param data
     * @param prevBlockHash
     * @return 
     */
    public BlockIF getNewBlock(String data, byte [] prevBlockHash);
    
    
    /**
     * sets hash for Block 
     * 
     * @return 
     */
    public boolean setHash();
    
    /**
     * gets hash if Block 
     * 
     * @return 
     */
    public byte[] getHash();
    
    /**
     * returns the used digest 
     * @return 
     */
    public String getDigest();

    
}
