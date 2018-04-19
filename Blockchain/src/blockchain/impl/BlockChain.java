/**
 * simple implementaion of a Blockchain

 */
package blockchain.impl;

import blockchain.model.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author 
 */
public class BlockChain extends AbstractBlockChain
{
    
    private static final Logger LOG = Logger.getLogger(AbstractBlockChain.class.getName());        
    
    @Override
    public boolean initialiseChain(BlockIF block)
    {
        boolean ret = super.initialiseChain(block);
        setInitalised(ret);
        return ret; 
    }
    
    @Override
    public BlockChain addBlock(String data)
    {
        // check init
        if (isInitialised())
        {
            // get size
            int size = getChain().size();
            if (size < 1)
            {
                // empty chain, generate genesis block
                LOG.info("BlockChain empty -> inserting GenesisBlock");
                BlockIF genesisBlock = getBlockIF().createNewGenesisBlock(); 
                if (genesisBlock != null)
                {
                    getChain().add(genesisBlock);
                    size++;
                    LOG.info("added block # 1");
                }
                else
                {
                    LOG.severe("BlockChain initialisiation failed!");
                    return null; 
                }   
            }    

            // get hash of last block & add new
            byte [] hashPrev = getChain().get(size -1).getHash();
            BlockIF b = getBlockIF().createNewBlock(data, hashPrev);
            getChain().add(b);
            LOG.log(Level.INFO, "added block # {0}", getChain().size());
        }
        else
        {
            LOG.severe("BlockChain not initialised! use initialiseChain first!");
        }

        return this; 
    }
    
}
