/**
 * simple implementaion of a Blockchain

 */
package blockchain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
public abstract class AbstractBlockChain implements BlockChainIF
{
    private BlockIF block; 
    
    private final List<BlockIF> chain = new ArrayList();
    
    private static final Logger LOG = Logger.getLogger(AbstractBlockChain.class.getName());  
    
    private boolean initialized = false; 
 
    @Override
    public boolean initialiseChain(BlockIF block)
    {
        this.block = block;
        LOG.log(Level.INFO, "hashing Blocks with {0}", block.getDigest()); 
        return true; 
    }

    @Override
    public abstract AbstractBlockChain addBlock(String data);
    
    public BlockIF getBlockIF()
    {
        return block; 
    }
    
    public List<BlockIF> getChain()
    {
        return chain; 
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        int count = 0; 
        sb.append("{");
        for (BlockIF b : chain)
        {
            sb.append("\n[(").append(count++).append(") ").append(b.toString()).append("]");
        }
        sb.append("\n}");
        
        return sb.toString();

    }
    
    public boolean isInitialised()
    {
        return initialized;
    }
    
    public void setInitalised(boolean initialized)
    {
        this.initialized = initialized;
    }
            
    
}
