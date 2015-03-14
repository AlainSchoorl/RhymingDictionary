/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RhymingDictionary;
import java.util.ArrayList;
/**
 *
 * @author TheBigCheese
 */
public class PhonemeList extends ArrayList<Phoneme>{
    public Boolean equals(PhonemeList b)
    {
        if(this.size()!=b.size())
        {
            return false;
        }
        for(int i = 0; i < this.size(); i++)
        {
            if(!this.get(i).equals(b.get(i)))
            {
                return false;
            }
        }
        return true;
    }
}
