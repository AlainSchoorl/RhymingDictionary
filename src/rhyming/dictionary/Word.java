/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rhyming.dictionary;
import java.util.*;
/**
 *
 * @author Alain
 * @editor Vincent
 */
public class Word {
    private String word;
    private ArrayList<Phoneme> phonemes;
        
        public Word(String w, ArrayList<Phoneme> p)
        {
            word = w;
            phonemes = p;
        }
        
    public String getString()
    {
        return word;
    }
    public ArrayList<Phoneme> getPhonemes()
    {
        return phonemes;
    }
    public Phoneme getPhoneme(int i)
    {
        return phonemes.get(i); //0 is the first in the list
    }
    public int getWordLength()
    {
        return phonemes.size(); // returns amount of phonemes
    }
    public int getVowelCount() //This is also the amount of syllables in the word
    {
        int length = 0; //the eventual number of vowels, starts as 0
        for(int i=0; i<phonemes.size(); i++) //start looking at position 0, continue until one less than size
        {
            if(phonemes.get(i).isVowel())
            {
                length++; //if the phoneme in that position is a vowel, add 1 to the vowel count
            }
        }
        return length; //return the vowel count
    }
    public Phoneme getVowel(int n) //returns the last vowel in the word
    {
        ArrayList<Phoneme> x = new ArrayList(); //creates an empty list of Phonemes
        for(int i = phonemes.size()-1; i >= 0; i--) //Starts looking from the back of the word
        {
            if(phonemes.get(i).isVowel())
            {
                x.add(phonemes.get(i)); //adds any vowels found to the list
                if(x.size()==n) //then, if the right number of vowels have been found
                {
                    return x.get(n-1); //return the request vowel
                }
            }
        }
        Phoneme error = new Phoneme("noVowel"); //If insufficient vowels are found, return a noVowel phoneme which we can use to launch an error message later
        return error; //Ideally, when the amount of rhyming sylabbles to be searched for is set to 2 or 3, first check if the input word has that many vowels, 
                          //if it doesn't, rhyme only the maximum amount of vowels in the word, no need to search for 3+ syllable words if the input word doesn't have that.
                          //When searching through words for multi-syllable rhymes, check their Vowel Count before bothering to check if any vowels rhyme.
                          //Essentially, there should be no way for getSecondVowel to return error
    }
    public ArrayList<Phoneme> getConsonants(int n) //Returns a list of any consonants between the n to last vowel and the one after it, if any.
    {
        ArrayList<Phoneme> x = new ArrayList(); //creates an empty list of Phonemes for the consonants
        int vowelCounter = 0; //And a counter for the vowels
        for(int i = phonemes.size()-1; i >= 0; i--) //Starts looking from the back of the word
        {
            if(phonemes.get(i).isVowel()) //If it finds a vowel
            {
                vowelCounter++; //It adds 1 to the counter
            }
            else //If the phoneme is a consonant
            {
                if(vowelCounter==n-1) //and exactly one vowel has already been found
                {
                    x.add(phonemes.get(i)); //Add the consonant to the list
                }
            }
            if(vowelCounter==n) //if a second vowel has been found, return the list
            {
                return x;
            }
        }
        return x; //if one or fewer vowels are found, the list is still returned.
    }
    public Boolean isPerfectRhyme(Word w, int syl) //
    {
        for(int i = 1; i <= syl;i++)
        {
            if (i==syl)
            {
                if((this.getConsonants(i)!=w.getConsonants(i))||(this.getVowel(i)!=w.getVowel(i))||(this.getConsonants(i+1)==w.getConsonants(i+1)))
                {
                    return false;
                }
            }
            else
            {
                if((this.getConsonants(i)!=w.getConsonants(i))||(this.getVowel(i)!=w.getVowel(i)))
                {
                    return false;
                }
            }
        }
        return true;
    }
    public Boolean isFamilyRhyme(Word w, int syl) //
    {
        for(int i = 1; i <= syl;i++)
        {
            if (i==syl)
            {
                if(!isFamily(w.getConsonants(i), i)||(this.getVowel(i)!=w.getVowel(i))||(this.getConsonants(i+1)==w.getConsonants(i+1)))
                {
                    return false;
                }
            }
            else
            {
                if(!isFamily(w.getConsonants(i), i)||(this.getVowel(i)!=w.getVowel(i)))
                {
                    return false;
                }
            }
        }
        return true;
    }
    public Boolean isFamily(ArrayList<Phoneme> c, int n)
    {
        ArrayList<Phoneme> p = this.getConsonants(n);
                
        if(c.size() != p.size())
        {
            return false;
        }
        int family = 0;
        for(int i=0;i<n;i++)
        {
            if(!c.get(i).isEqual(p.get(i)))
            {
                if(c.get(i).isFamily(p.get(i)))
                {
                    family++;
                }
                else
                {
                    return false;
                }
            }
        }
        return (family==1);
    }
    public Boolean isAssonanceRhyme(Word w, int syl) //
    {
        return true;
    }
    public Boolean isConsanceRhyme(Word w, int syl) //
    {
        return true;
    }
    public Boolean isAdditiveRhyme(Word w, int syl) //
    {
        if(this.getConsonants(1).size()<=w.getConsonants(1).size())
            return false;
        if((w.getConsonants(1).size()==1)&&(this.getConsonants(1).indexOf(w.getConsonants(1).get(0))==-1))
        {
            return false;
        }
        if(w.getConsonants(1).size()!=1)
        {
            for(int i = 1; i < w.getConsonants(1).size(); i++)
            {
                if((this.getConsonants(1).indexOf(w.getConsonants(1).get(i-1))==-1)||(this.getConsonants(1).indexOf(w.getConsonants(1).get(i))==-1))
                {
                    return false;
                }
                if((this.getConsonants(1).indexOf(w.getConsonants(1).get(i))!=(this.getConsonants(1).indexOf(w.getConsonants(1).get(i-1))+1)))
                {
                    return false;
                }
            }
        }
        for(int i = 1; i <= syl;i++)
        {
            if((this.getConsonants(i+1)!=w.getConsonants(i+1))||(this.getVowel(i)!=w.getVowel(i)))
            {
                return false;
            }
        }
        return true;
    }
    public Boolean isSubtractiveRhyme(Word w, int syl) //
    {
        if(w.getConsonants(1).size()<=this.getConsonants(1).size())
            return false;
        if((this.getConsonants(1).size()==1)&&(w.getConsonants(1).indexOf(this.getConsonants(1).get(0))==-1))
        {
            return false;
        }
        if(this.getConsonants(1).size()!=1)
        {
            for(int i = 1; i < this.getConsonants(1).size(); i++)
            {
                if((w.getConsonants(1).indexOf(this.getConsonants(1).get(i-1))==-1)||(w.getConsonants(1).indexOf(this.getConsonants(1).get(i))==-1))
                {
                    return false;
                }
                if((w.getConsonants(1).indexOf(this.getConsonants(1).get(i))!=(w.getConsonants(1).indexOf(this.getConsonants(1).get(i-1))+1)))
                {
                    return false;
                }
            }
        }
        for(int i = 1; i <= syl;i++)
        {
            if((this.getConsonants(i+1)!=w.getConsonants(i+1))||(this.getVowel(i)!=w.getVowel(i)))
            {
                return false;
            }
        }
        return true;
    }
}
