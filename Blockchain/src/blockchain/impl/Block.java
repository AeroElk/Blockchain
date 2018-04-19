/**
 * Simple Implementation of a Block
 */
package blockchain.impl;

import blockchain.model.AbstractBlock;
import blockchain.model.BlockIF;
import java.util.Date;
import java.util.logging.Logger;

/**
 * @author 
 */
public class Block extends AbstractBlock
{
    
    private static final Logger LOG = Logger.getLogger(Block.class.getName());            
    
    /**
     * @param data data to be stored in block
     * @param prevBlockHash hash of previous block
     * @return new Block
     * @see blockchain.model.AbstractBlockIF#getNewBlock(java.lang.String, byte[]) 
     */
    @Override
     public BlockIF createNewBlock(String data, byte [] prevBlockHash)
    {
        Block b = new Block();
        b.timestamp = new Date().getTime();
        b.data = data.getBytes();
        b.prevHashBlock = prevBlockHash; 
        b.nonce = 0;
        
        if (!b.calculateHash())
        {
            LOG.info("hashing failed!");
            b = null; 
        }
        return b;
    }
    
    
    /**
     * @return <code>true</code> if successful, <code>false</code> if something went wrong
     * @see blockchain.model.BlockIF#setHash() 
     */
    public boolean calculateHash()
    {
        HashCash hc = new HashCash(this); 
        hash = hc.mineHash();
        return (hash != null);
    }
    
    @Override
    public String getDigest()
    {
        return BlockIF.BLOCK_DIGEST_SHA_256; 
    }
    
    
    
}
