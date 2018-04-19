/*
 */
package blockchain.model;

/**
 * @author 
 */
public interface ProofOfWorkIF  
{
    public long MAX_NONCE = 10000L;
    public int MAX_BITS = 250;
    
    /**
     * mines for the hash 
     * @return the new hash of <code>null</code> if nothing found
     */
    public byte[] mineHash();
    
}
