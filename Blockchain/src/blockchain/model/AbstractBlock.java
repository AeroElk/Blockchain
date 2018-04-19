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
    
    private static final Logger LOG = Logger.getLogger(AbstractBlock.class.getName());            
      
    /**
     * @see blockchain.model.BlockIF#getNewGenesisBlock() 
     */
    @Override
    public BlockIF getNewGenesisBlock() 
    {
        LOG.info("creating new Genesis Block");
	return getNewBlock("Genesis Block", new byte[0]);
    }    
    
    /**
     * @see blockchain.model.BlockIF#getHash() 
     */
    @Override
    public byte[] getHash()
    {
        return this.hash;
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
