/**
 * Simple Implementation of a Block
 */
package blockchain.impl;

import blockchain.model.AbstractBlock;
import blockchain.model.BlockIF;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

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
     public BlockIF getNewBlock(String data, byte [] prevBlockHash)
    {
        Block b = new Block();
        b.timestamp = new Date().getTime();
        b.data = data.getBytes();
        b.prevHashBlock = prevBlockHash; 
        
        if (!b.setHash())
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
    @Override
    public boolean setHash()
    {
        // concatenate all fields
        StringBuilder sb = new StringBuilder();
        sb.append(timestamp);
        sb.append(new String(data));
        sb.append(new String(prevHashBlock));
        
        try
        {
            MessageDigest digest = MessageDigest.getInstance(getDigest());
            hash = digest.digest(sb.toString().getBytes(StandardCharsets.UTF_8));
            LOG.log(Level.INFO, "created hash: {0}", DatatypeConverter.printHexBinary(hash));
            return true; 
        }
        catch (NoSuchAlgorithmException nsae)
        {
            LOG.log(Level.SEVERE, "Digest {0} unknown", getDigest());
            return false; 
        }
    }
    
    @Override
    public String getDigest()
    {
        return BlockIF.BLOCK_DIGEST_SHA_256; 
    }
    
    
    
}
