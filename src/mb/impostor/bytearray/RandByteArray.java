/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb.impostor.bytearray;

/**
 *
 * @author mb
 */
import org.apache.commons.lang3.RandomUtils;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;
public class RandByteArray {
    public byte[] randDataGen(String type, int count){
        byte[] randbuf;
        if(type.compareToIgnoreCase("NRandom")==0){
            randbuf = RandomUtils.nextBytes(new Random().nextInt(count));
            return randbuf;
        }
        else if(type.compareToIgnoreCase("Random")==0){
            randbuf = RandomUtils.nextBytes(count);
          
            return randbuf;
        }
        else if(type.compareToIgnoreCase("RandString")==0){
            String s = RandomStringUtils.randomAlphanumeric(count);
        
            return s.getBytes();
        }
        else{
            return null;
        }
        
        
    }
}
