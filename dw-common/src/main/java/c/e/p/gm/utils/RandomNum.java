package c.e.p.gm.utils;

import java.util.Random;

/**
 * @date 2019-10-18 - 14:25
 */
public class RandomNum {
    public static final  int getRandInt(int fromNum,int toNum){
        return   fromNum+ new Random().nextInt(toNum-fromNum+1);
    }
}
