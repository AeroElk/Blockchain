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
    
    public String getData();
   
    public String getTimestamp();
    
    public String getPrevHashBlock();
    
    public long getNonce();
    
    public void setNonce(long nonce);
    
}
