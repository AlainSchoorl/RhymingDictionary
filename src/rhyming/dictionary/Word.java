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
    public Boolean isPerfectRhyme(Word w, int syl) // Returns true if the base word and given word meet the conditions for perfect rhyme
    {
        for(int i = 1; i <= syl;i++) // runs through the number of syllable groups as specified by the input.
        {
            if (i==syl) // if the final vowel group is being checked, the consonant group before it must not match, hence the seperate condition.
            {
                if((!this.getConsonants(i).equals(w.getConsonants(i)))||(!this.getVowel(i).isEqual(w.getVowel(i)))||(this.getConsonants(i+1).equals(w.getConsonants(i+1))))
                {
                    return false;
                }
            }
            else // in all other cases the vowels and the consonants after it are checked to ensure to are the same.
            {
                if((!this.getConsonants(i).equals(w.getConsonants(i)))||(!this.getVowel(i).isEqual(w.getVowel(i))))
                {
                    return false;
                }
            }
        }
        return true; // If all checks are passed, the words are perfect rhymes.
    }
    public Boolean isFamilyRhyme(Word w, int syl) // Returns true if the base word and given word meet the conditions for family rhyme
    {   
        for(int i = 1; i <= syl;i++) // runs through the number of syllable groups as specified by the input
        {
            if (i==syl) // if the final vowel group is being checked, the consonant group before it be family.
            {
                if(!this.isFamily(w.getConsonants(i), i)||(!this.getVowel(i).isEqual(w.getVowel(i)))||(this.getConsonants(i+1).equals(w.getConsonants(i+1))))
                {
                    return false;
                }
            }
            else // in all other cases the vowels and the consonants after it are checked to ensure to are the same.
            {
                if(!this.isFamily(w.getConsonants(i), i)||(!this.getVowel(i).isEqual(w.getVowel(i))))
                {
                    return false;
                }
            }
        }
        return true; // If all checks are passed, the words are family rhymes.
    }
    public Boolean isFamily(ArrayList<Phoneme> c, int n) // Returns true if two phenome list meet the conditions for family.
    {
        ArrayList<Phoneme> p = this.getConsonants(n); // Grab the correct consonant group from the local word and store it in p.
                
        if(c.size() != p.size()) // Check to make sure the two consonant groups have the same number of elements.
        {
            return false;
        }
        int family = 0; // Iniate a counter to see how many elements are not exact matches, but in the same family.
        for(int i=0;i<c.size();i++) // run through all the elements in the consonant groups.
        {
            if(!c.get(i).isEqual(p.get(i))) // First check if the consonants in the same position are not the same.
            {
                if(c.get(i).isFamily(p.get(i))) // If they are not the same, but they belong to the same family, increase the family counter.
                {
                    family++;
                }
                else // If elements in the same position are neither family nor exact matches, the word cannot be a family rhyme.
                {
                    return false;
                }
            }
        }
        return (family==1||family==0); // If the consonants match exactly or have exactly one consonant in the same family as another
                                       // consonant in the same position, then the consonant groups are families
    }
    public Boolean isAssonanceRhyme(Word w, int syl) //
    {
        for(int i = 1; i <= syl;i++) // runs through the number of syllable groups as specified by the input.
        {
            if(!this.getVowel(i).isEqual(w.getVowel(i))) //Check to see if the vowels are the same.
            {
                return false;
            }
        }
        return true; // If all the vowels are identical, the words are assonance rhymes. This also returns perfect rhymes.
    }
    public Boolean isHalfRhyme(Word w, int syl) //
    {
        for(int i = 1; i <= syl;i++) // runs through the number of syllable groups as specified by the input.
        {
            if((!this.getConsonants(i).equals(w.getConsonants(i)))||(!this.getVowel(i).isSimilarVowel(w.getVowel(i)))) // If the consonants differ or the vowels aren't similar at any point, returns false.
            {
                return false;
            }
        }
        return true; // If all the consonants are identical and all the vowels similar, returns true. At the moment doesn't care for consonants beyond the last vowel.
    }
    public Boolean isConsonanceRhyme(Word w, int syl) //
    {
        for(int i = 1; i <= syl;i++) // runs through the number of syllable groups as specified by the input.
        {
            if (i==syl) // if the final vowel group is being checked, the consonant group before it be family.
            {
                if((!this.getConsonants(i).equals(w.getConsonants(i)))||(!this.getConsonants(i+1).get(0).equals(w.getConsonants(i+1).get(0)))) // Checks the sylth consonant and a single one beyond that.
                {
                    return false;
                }
            }
            else
            {
                if((!this.getConsonants(i).equals(w.getConsonants(i)))) // If the consonants differ, return false
                {
                    return false;
                }
            }
        }
        return true; // If all the consonants are identical and all the vowels similar, returns true. At the moment doesn't care for consonants beyond the last vowel.
    }
    /**public Boolean isAdditiveRhyme(Word w, int syl) // Here the local word is meant to be the additive rhyme and hence should be the longer group
    {
        if(this.getConsonants(1).size()<=w.getConsonants(1).size()) // Check that returns false if the base word does not end with a longer consonance group
        {
            return false;
        }
        if(this.getConsonants(1).indexOf(w.getConsonants(1).get(0))==-1) // Makes sure the first element of the shorter group exists in the longer group
        {
            return false;
        }
        if(w.getConsonants(1).size()!=1) // Knowing that the first element exists, only runs the remaining checks if there are more elements in the shorter group
        {
            for(int i = 1; i < w.getConsonants(1).size(); i++) // runs through the remaining elements in the shorter group
            {
                if(this.getConsonants(1).indexOf(w.getConsonants(1).get(i))==-1) // Checks if the next element of the shorter group also exist
                {
                    return false;
                }
                if((this.getConsonants(1).indexOf(w.getConsonants(1).get(i))!=(this.getConsonants(1).indexOf(w.getConsonants(1).get(i-1))+1))) // Check if the next element appears right after the previous element.
                {
                    return false;
                }
            }
        }
        for(int i = 1; i <= syl;i++) // Check the remaining consonants and their adjacent vowels
        {
            if(i==syl) // If the final syllable has been reached different conditions on the consonants apply
            {
                if((this.getConsonants(i+1)==w.getConsonants(i+1))||(this.getVowel(i)!=w.getVowel(i))) // Check that all conditions for the rhyme apply
                {
                    return false;
                }
            }
            else if((this.getConsonants(i+1)!=w.getConsonants(i+1))||(this.getVowel(i)!=w.getVowel(i))) // Check that all conditions for the rhyme apply
            {
                return false;
            }
        }
        return true; // If all checks pass the base word is an additive rhyme for the given word.
    }*/
    public Boolean isSubtractiveRhyme(Word w, int syl) // Slightly different implementation. The local word is the shorter one.
    {
        ArrayList<Phoneme> wCons = w.getConsonants(1); //These get called a few times
        ArrayList<Phoneme> tCons = this.getConsonants(1);
        if((tCons.size() >= wCons.size())) //Check to see if the local word is shorter
        {
            return false;
        }
        if(!wCons.isEmpty()) //If the other word is empty, then the following checks are already satisified and should be skipped to avoid a NullPointerException
        {
            if(tCons.get(0).equals(wCons.get(0))) //If the first consonants match, start looking from the front
            {
                for(int c = 1; c < wCons.size(); c++) //Run through the last consononants group starting from the back
                {
                    if(!tCons.get(c).equals(wCons.get(c))) //Check every element to see if they match. If they do, they start the same but w has extra consonants at the end
                    {
                        return false;
                    }
                }
            }
            else //Otherwise start looking from the back
            {
                for(int c = (tCons.size()-1); c >= 0; c--) //Run through the last consononants group starting from the back
                {
                    if(!tCons.get(c).equals(wCons.get(c))) //Check every element to see if they match. If they do, they end the same but w has extra consonants at the end
                    {
                        return false;
                    }
                }
            }
        }
        for(int i = 1; i <= syl;i++) // Check the remaining consonants and their adjacent vowels
        {
            if(i==syl) // If the final syllable has been reached different conditions on the consonants apply
            {
                if((this.getConsonants(i+1).equals(w.getConsonants(i+1)))||(!this.getVowel(i).isEqual(w.getVowel(i)))) // Check that all conditions for the rhyme apply
                {
                    return false;
                }
            }
            else if((this.getConsonants(i+1).equals(w.getConsonants(i+1)))||(!this.getVowel(i).isEqual(w.getVowel(i)))) // Check that all conditions for the rhyme apply
            {
                return false;
            }
        }
        return true; // If all checks are passed, w is an additive rhyme to the local word.
    }
    public Boolean isAdditiveRhyme(Word w, int syl) // Subtractive Rhyme but w's and this' have been switched.
    {
        ArrayList<Phoneme> wCons = w.getConsonants(1); //These get called a few times
        ArrayList<Phoneme> tCons = this.getConsonants(1);
        if((wCons.size() >= tCons.size())) //Check to see if the local word is shorter
        {
            return false;
        }
        if(!tCons.isEmpty()) //If the local word is empty, the following checks are already satisified and should be skipped to avoid a NullPointerException
        {
            if(wCons.get(0).equals(tCons.get(0))) //If the first consonants match, start looking from the front
            {
                for(int c = 1; c < tCons.size(); c++) //Run through the last consononants group starting from the back
                {
                    if(!wCons.get(c).equals(tCons.get(c))) //Check every element to see if they match. If they do, they start the same but w has extra consonants at the end
                    {
                        return false;
                    }
                }
            }
            else //Otherwise start looking from the back
            {
                for(int c = (wCons.size()-1); c >= 0; c--) //Run through the last consononants group starting from the back
                {
                    if(!wCons.get(c).equals(tCons.get(c))) //Check every element to see if they match. If they do, they end the same but w has extra consonants at the end
                    {
                        return false;
                    }
                }
            }
        }
        for(int i = 1; i <= syl;i++) // Check the remaining consonants and their adjacent vowels
        {
            if(i==syl) // If the final syllable has been reached different conditions on the consonants apply
            {
                if((w.getConsonants(i+1).equals(this.getConsonants(i+1)))||(!w.getVowel(i).isEqual(this.getVowel(i)))) // Check that all conditions for the rhyme apply
                {
                    return false;
                }
            }
            else if((w.getConsonants(i+1).equals(this.getConsonants(i+1)))||(!w.getVowel(i).isEqual(this.getVowel(i)))) // Check that all conditions for the rhyme apply
            {
                return false;
            }
        }
        return true; // If all checks are passed, w is an additive rhyme to the local word.
    }
    /** public Boolean isSubtractiveRhyme(Word w, int syl) // Here the local word is meant to be the subtractive rhyme and hence should be the shorter group
    {   // This first section is the same as the isAdditiveRhyme function except all instances of "w" and "this" have been swapped.
        if(w.getConsonants(1).size()<=this.getConsonants(1).size()) // Check that returns false if the base word does not end with a shorter consonance group
            return false;
        if(w.getConsonants(1).indexOf(this.getConsonants(1).get(0))==-1) // Makes sure the first element of the shorter group exists in the longer group
        {
            return false;
        }
        if(this.getConsonants(1).size()!=1) // Knowing that the first element exists, only runs the remaining checks if there are more elements in the shorter group
        {
            for(int i = 1; i < this.getConsonants(1).size(); i++) // runs through the remaining elements in the shorter group
            {
                if(w.getConsonants(1).indexOf(this.getConsonants(1).get(i))==-1) // Checks if the next element of the shorter group also exist
                {
                    return false;
                }
                if((w.getConsonants(1).indexOf(this.getConsonants(1).get(i))!=(w.getConsonants(1).indexOf(this.getConsonants(1).get(i-1))+1))) // Check if the next element appears right after the previous element.
                {
                    return false;
                }
            }
        }
        for(int i = 1; i <= syl;i++) // Check the remaining consonants and their adjacent vowels
        {
            if(i==syl) // If the final syllable has been reached different conditions on the consonants apply
            {
                if((this.getConsonants(i+1)==w.getConsonants(i+1))||(this.getVowel(i)!=w.getVowel(i))) // Check that all conditions for the rhyme apply
                {
                    return false;
                }
            }
            else if((this.getConsonants(i+1)!=w.getConsonants(i+1))||(this.getVowel(i)!=w.getVowel(i))) // Check that all conditions for the rhyme apply
            {
                return false;
            }
        }
        return true; // If all checks pass the base word is a subtractive rhyme for the given word.
    }*/
}
