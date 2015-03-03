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
    public Phoneme getFinalVowel() //returns the last vowel in the word
    {
        for(int i = phonemes.size()-1; i >= 0; i--) //Starts looking from the back of the word
        {
            if(phonemes.get(i).isVowel())
            {
                return phonemes.get(i);  //Returns the first vowel it finds
            }
        }
        Phoneme error = new Phoneme("noVowel"); //If no vowel is found, return a noVowel phoneme which we can use to launch an error message later
        return error;
    }
    public Phoneme getSecondVowel() //Searches for the second to last vowel
    {
        ArrayList<Phoneme> x = new ArrayList(); //creates an empty list of Phonemes
        for(int i = phonemes.size()-1; i >= 0; i--) //Starts looking from the back of the word
        {
            if(phonemes.get(i).isVowel())
            {
                x.add(phonemes.get(i)); //adds any vowels found to the list
                if(x.size()==2) //then, if at least two vowels have been found
                {
                    return x.get(1); //return the second vowel
                }
            }
        }
        Phoneme error = new Phoneme("noVowel"); //If insufficient vowels are found, return a noVowel phoneme which we can use to launch an error message later
        return error; //Ideally, when the amount of rhyming sylabbles to be searched for is set to 2 or 3, first check if the input word has that many vowels, 
                          //if it doesn't, rhyme only the maximum amount of vowels in the word, no need to search for 3+ syllable words if the input word doesn't have that.
                          //When searching through words for multi-syllable rhymes, check their Vowel Count before bothering to check if any vowels rhyme.
                          //Essentially, there should be no way for getSecondVowel to return error
    }
    public Phoneme getThirdVowel() //Searches for the third to last vowel
    {
        ArrayList<Phoneme> x = new ArrayList(); //creates an empty list of Phonemes
        for(int i = phonemes.size()-1; i >= 0; i--) //Starts looking from the back of the word
        {
            if(phonemes.get(i).isVowel())
            {
                x.add(phonemes.get(i)); //adds any vowels found to the list
                if(x.size()==3) //then, if at least three vowels have been found
                {
                    return x.get(2); //return the third vowel
                }
            }
        }
        Phoneme error = new Phoneme("noVowel");
        return error;
    }
    public ArrayList<Phoneme> getLastConsonants() //Returns a list of any consonants after the final vowel. The list is empty if there are none.
    {
        ArrayList<Phoneme> x = new ArrayList(); //creates an empty list of Phonemes
        for(int i = phonemes.size()-1; i >= 0; i--) //Starts looking from the back of the word
        {
            if(phonemes.get(i).isVowel())
            {
                return x; //Returns the list of consonants as soon as a vowel is found
            }
            x.add(phonemes.get(i)); //If no vowel has been found yet, add the consonant to the list
        }
        return x; //Return any consonants that have been found
    }
    public ArrayList<Phoneme> getSecondConsonants() //Returns a list of any consonants between the final vowel and the one before it.
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
                if(vowelCounter==1) //and exactly one vowel has already been found
                {
                    x.add(phonemes.get(i)); //Add the consonant to the list
                }
            }
            if(vowelCounter==2) //if a second vowel has been found, return the list
            {
                return x;
            }
        }
        return x; //if one or fewer vowels are found, the list is still returned.
    }
    public ArrayList<Phoneme> getThirdConsonants() //Returns a list of any consonants between the second to last vowel and the one before it.
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
                if(vowelCounter==2) //and exactly two vowels has already been found
                {
                    x.add(phonemes.get(i)); //Add the consonant to the list
                }
            }
            if(vowelCounter==3) //if a third vowel has been found, return the list
            {
                return x;
            }
        }
        return x;
    }
    public ArrayList<Phoneme> getFourthConsonants() //Returns a list of any consonants between the third to last vowel and the one before it.
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
                if(vowelCounter==3) //and exactly three vowels have already been found
                {
                    x.add(phonemes.get(i)); //Add the consonant to the list
                }
            }
            if(vowelCounter==4) //if a fourth vowel has been found, return the list
            {
                return x;
            }
        }
        return x; //These last two methods should only be called if we know a word has sufficient syllables
    }
}
