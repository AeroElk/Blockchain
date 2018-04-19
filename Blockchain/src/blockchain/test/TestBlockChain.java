/**
 * testprogramm für blockchain 
 */
package blockchain.test;

import blockchain.impl.Block;
import blockchain.impl.BlockChain;
import java.util.logging.Logger;

/**
 * @author
 */
public class TestBlockChain
{
    
    private static final Logger LOG = Logger.getLogger(TestBlockChain.class.getName());    
    
    public static void main (String []args )
    {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT %4$4.4s [%2$s] %5$s%6$s%n");         
        
        LOG.info("TestBlockChain startup");
        
        BlockChain b =  new BlockChain();
        b.initialiseChain(new Block());
       // HashCash hc = new HashCash(new Block());
        
        b.addBlock("bla");
        b.addBlock("blubb");
        b.addBlock("muh!");
            
        LOG.info(b.toString());
    }
}
