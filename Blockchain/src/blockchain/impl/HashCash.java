/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchain.impl;

import blockchain.model.BlockIF;
import blockchain.model.ProofOfWorkIF;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author 
 */
public class HashCash implements ProofOfWorkIF
{
    private static final Logger LOG = Logger.getLogger(HashCash.class.getName());      

    protected static final BigInteger ZERO = new BigInteger("0"); 

    
    @Override
    public byte[] mineHash(final BlockIF block, final int targetBits)
    {
        // prepare target
        BigInteger target = new BigInteger("1"); 
        target = target.shiftLeft(MAX_BITS - targetBits);        
        LOG.log(Level.INFO, "target: {0}", target.toString(10));
        
        // get message digest 
        MessageDigest digest = getMessageDigest(block);
        if (digest == null) return null; 
        
        // prepare data
        StringBuilder base = new StringBuilder();
        base.append(block.getTimestamp());
        base.append(block.getData());
        base.append(block.getPrevHashBlock());

        // start mining 
        LOG.log(Level.INFO, "mining for target{0}", block.getNonce() < MAX_NONCE);
        byte[] hash = null; 
        for ( ; block.getNonce() < MAX_NONCE; )
        {
            String toHash = base.toString() + block.getNonce();
            hash = digest.digest(toHash.getBytes(StandardCharsets.UTF_8));
            LOG.log(Level.INFO, "created hash: {0}", DatatypeConverter.printHexBinary(hash));
            
            // check if target reached
            BigInteger calc = new BigInteger(hash);
            boolean belowTarget = calc.compareTo(target) < 0; // -1 -> lower than target = ok
            boolean aboveZero = calc.compareTo(ZERO) > 0; // 1 -> higher than 0 = ok
            
            LOG.log(Level.INFO, "belowTarget: {0}; aboveZero: {1}; {2}; {3}", new Object[]{belowTarget, aboveZero, DatatypeConverter.printHexBinary(hash), calc.toString()});
            if (belowTarget && aboveZero)
            {
                LOG.info("critera matched");
                break; 
            }
            else
            {
                block.setNonce(block.getNonce() + 1);
                LOG.log(Level.INFO, "incrementing nonce to {0}", block.getNonce());
            }
        }
    return hash;
    }
    
    public boolean verifyHash(final BlockIF block)
    {
        // get message digest 
        MessageDigest digest = getMessageDigest(block);
        if (digest == null) return false; 
        
        // prepare data
        StringBuilder base = new StringBuilder();
        base.append(block.getTimestamp());
        base.append(block.getData());
        base.append(block.getPrevHashBlock());
        base.append(block.getNonce());
        
        byte [] verify = digest.digest(base.toString().getBytes(StandardCharsets.UTF_8));
        LOG.log(Level.INFO, "created verify hash: {0}", DatatypeConverter.printHexBinary(verify));
        boolean equals = Arrays.equals(block.getHash(), verify);
        if (equals)
        {
            LOG.log(Level.INFO, "hash verified");
        }
        else
        {
            LOG.log(Level.INFO, "hash not verified");
        }
        
        return equals; 
    }
    
    private MessageDigest getMessageDigest(BlockIF block)
    {
        MessageDigest md = null; 
        try
        {
            md = MessageDigest.getInstance(block.getDigest());
        }
        catch (NoSuchAlgorithmException nsae)
        {
            LOG.log(Level.SEVERE, "Digest {0} unknown", block.getDigest());
        }
        return md; 

    }
}
