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
    private  ArrayList<String> groups;
        public Phoneme(String phoneme)
        {
            phoneme = p;
            groups.add("B"); //0 Plosive start
            groups.add("D"); //1
            groups.add("G"); //2
            groups.add("P"); //3
            groups.add("T"); //4
            groups.add("K"); //5 Plosive end
            groups.add("V"); //6 Frictative start
            groups.add("Z"); //7
            groups.add("ZH"); //8
            groups.add("JH"); //9
            groups.add("DH"); //10
            groups.add("HH"); //11
            groups.add("F"); //12
            groups.add("TH"); //13
            groups.add("S"); //14
            groups.add("SH"); //15
            groups.add("CH"); //16 Frictative end
            groups.add("M"); //17 Nasal start
            groups.add("N"); //18
            groups.add("NG"); //19 Nasal end
            groups.add("EY"); //20 Long start  A
            groups.add("IY"); //21 E
            groups.add("AY"); //22 I
            groups.add("OW"); //23 O
            groups.add("UW"); //24 U
            groups.add("AW"); //25 O
            groups.add("OY"); //26 Long end O
            groups.add("AE"); //27 Short start   A
            groups.add("EH"); //28 E
            groups.add("IH"); //29 I
            groups.add("AA"); //30 O
            groups.add("AH"); //31 U
            groups.add("UH"); //32 U
            groups.add("AO"); //33 O
            groups.add("ER"); //34 Short end U
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
        int i = groups.indexOf(p);
        if(0<=i && i<=5) //If the phoneme is plosive
        {
            return 1;
        }
        if(6<=i && i<=16) //If the phoneme is frictative
        {
            return 2;
        }
        if(6<=i && i<=19) //If the phoneme is nasal
        {
            return 3;
        }
        return 0; //Otherwise, no family
    }
    public Boolean isFamily(Phoneme b)
    {
        if(this.equals(b)) //If it's the same phoneme
        {
            return false; //Not a family
        }
        int i = this.family();
        if(i==0) //If the first phoneme has no family
        {
            return false; //Not a family
        }
        if(i==b.family()) //If the family for Phoneme b is the same as the family for this phoneme.
        {
            return true;
        }
        return false; //If somehow you got this far
    }
    public String vowelFamily()
    {
        int i = groups.indexOf(p);
        String s = new String("");
        if(20<=i && i<=26) //If the phoneme is plosive
        {
            s = s+"L";
        }
        else
        {
            s = s+"S";
        }
        if(i==20||i==27)
        {
            s = s+"A";
        }
        if(i==21||i==28)
        {
            s = s+"E";
        }
        if(i==22||i==29)
        {
            s = s+"I";
        }
        if(i==23||i==25||i==26||i==30||i==33)
        {
            s = s+"O";
        }
        if(i==24||i==31||i==32||i==34)
        {
            s = s+"U";
        }
        return s;
    }
    public Boolean isSimilarVowel(Phoneme d)
    {
        return((this.vowelFamily().charAt(0)==d.vowelFamily().charAt(0))||(this.vowelFamily().charAt(1)==d.vowelFamily().charAt(1)));
    }
}
