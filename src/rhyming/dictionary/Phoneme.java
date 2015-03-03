/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rhyming.dictionary;
import java.lang.Math;
/**
 *
 * @author Alain
 */
public class Phoneme {
    private String p;
    
        public Phoneme(String phoneme)
        {
            phoneme = p;
        }
        
    public String getPhoneme()
    {
        return p;
    }
    
    public Boolean isVowel()
    {
        char z = p.charAt(0); //Look at the first character
        return "AEIOUaeiou".indexOf(z) != -1; //If this first character is not in this String of vowels indexOf returns -1, 
                                              //so any output other than -1 means the character is a vowel.
    }
    
    public Boolean isEqual(Phoneme b)
    {
        String pShort = p.substring(0, Math.min(p.length(), 2));  //The two phonemes are shortened to a maximum of two characters, this is to ignore vowel stress
        String bShort = b.getPhoneme().substring(0, Math.min(b.getPhoneme().length(), 2));
        return pShort.equals(bShort); //The two shortened phonemes are equated
    }
}
