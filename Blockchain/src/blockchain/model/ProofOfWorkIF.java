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
    
    public byte[] mineHash();
    
}
