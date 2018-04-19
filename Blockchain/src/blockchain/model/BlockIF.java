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
    public BlockIF createNewGenesisBlock();
    
    /**
     * creates new Block
     * 
     * @param data
     * @param prevBlockHash
     * @return 
     */
    public BlockIF createNewBlock(String data, byte [] prevBlockHash);
    
    
    /**
     * sets hash for Block 
     * 
     * @return 
     */
    public void setHash(byte [] hash);
    
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
    
    /**
     * returns the current nonce
     * @return 
     */
    public long getNonce();

    /**
     * sets the nonce
     * @param nonce 
     */
    public void setNonce(long nonce);

    /**
     * returns data partition of block in string representation
     * @return 
     */
    public String getData();
    
    /**
     * previous hash block
     * @return 
     */
    public String getPrevHashBlock();
    
    /**
     * internal timestamp of block
     * @return 
     */
    public String getTimestamp();
    
}
