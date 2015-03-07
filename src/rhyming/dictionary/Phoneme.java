/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rhyming.dictionary;
import java.lang.Math;
import java.util.ArrayList;
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
    public int family() //return 1 for plosive, 2 for frictative, 3 for nasal, and 0 for no family
    {
        if(p.equals("B")||p.equals("D")||p.equals("G")||p.equals("P")||p.equals("T")||p.equals("K")) //If the phoneme is plosive
        {
            return 1;
        }
        if(p.equals("V")||p.equals("Z")||p.equals("ZH")||p.equals("JH")||p.equals("DH")||p.equals("HH")||
                p.equals("F")||p.equals("TH")||p.equals("S")||p.equals("SH")||p.equals("CH")) //If the phoneme is frictative
        {
            return 2;
        }
        if(p.equals("M")||p.equals("N")||p.equals("NG")) //If the phoneme is nasal
        {
            return 3;
        }
        return 0; //Otherwise, no family
    }
    public Boolean isFamily(Phoneme b)
    {
        String s = b.getPhoneme();
        if(s.equals(p)) //If it's the same phoneme
        {
            return false; //Not a family
        }
        int i = this.family();
        if(i==0) //If the first phoneme has no family
        {
            return false; //Not a family
        }
        if(i==1&&(s.equals("B")||s.equals("D")||s.equals("G")||s.equals("P")||s.equals("T")||s.equals("K"))) //If they're both plosive
        {
                return true;
        }
        if(i==2&&(s.equals("V")||s.equals("Z")||s.equals("ZH")||s.equals("JH")||s.equals("DH")||s.equals("HH")||
                s.equals("F")||s.equals("TH")||s.equals("S")||s.equals("SH")||s.equals("CH"))) //If they're both frictative
        {
                return true;
        }
        if(i==3&&(s.equals("M")||s.equals("N")||s.equals("NG"))) //If they're both nasal
        {
                return true;
        }
        return false; //If somehow you got this far
    }
}
