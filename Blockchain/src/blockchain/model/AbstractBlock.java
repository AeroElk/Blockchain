/**
 * Simple Implementation of a Block
 */
package blockchain.model;

import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter; 
/**
 * @author 
 */
public abstract class AbstractBlock implements BlockIF
{
    protected long   timestamp = 0L;          // current timestamp
    protected byte[] data = null;             // stores acutal data
    protected byte[] prevHashBlock = null;    // hash of previous block
    protected byte[] hash = null;             // hash
    protected long   nonce = 0; 
    
    private static final Logger LOG = Logger.getLogger(AbstractBlock.class.getName());            
      
    /**
     * @see blockchain.model.BlockIF#createNewGenesisBlock() 
     */
    @Override
    public BlockIF createNewGenesisBlock() 
    {
        LOG.info("creating new Genesis Block");
	return createNewBlock("Genesis Block", new byte[0]);
    }    
    
    /**
     * @see blockchain.model.BlockIF#getHash() 
     */
    @Override
    public byte[] getHash()
    {
        return this.hash;
    }

    public void setHash(byte [] hash)
    {
        this.hash = hash; 
    }

    
    public long getNonce()
    {
        return this.nonce;
    }
    
    public void setNonce(long nonce)
    {
        this.nonce = nonce;
    }
    
    public String getData()
    {
        return new String(this.data);
    }
    
    public String getPrevHashBlock()
    {
        return new String(this.prevHashBlock);
    }
    
    public String getTimestamp()
    {
        return new Long(this.timestamp).toString();
    }
            
    
    /**
     * toString override 
     * @return 
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("data:").append(new String(data));
        sb.append("; ");
        sb.append("hash:").append(DatatypeConverter.printHexBinary(hash));
        sb.append("; ");
        sb.append("prevHash:").append(DatatypeConverter.printHexBinary(prevHashBlock));
      
        return sb.toString();    
    }
    
    
}
