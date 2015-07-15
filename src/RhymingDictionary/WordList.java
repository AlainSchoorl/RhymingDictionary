/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RhymingDictionary;
import java.util.HashSet;
/**
 *
 * @author Alain
 */
public class WordList extends HashSet<Word> {

    public Word getWord(String s)
    {
        for(Word w : this) //For every word in the list
        {
            if(w.isWord(s)) //If the strings match, return that word
            {
                return w;
            }
        }
        return null; // Need to find something to return if the word isn't in our dictionary.
    }
    
    public WordList getCustomRhymes(Word w, int i)
    {
        WordList rhymes = new WordList(); //Creates empty wordlist (I hope)
        for(Word r : this) //For every word in the list
        {
            if(r.getVowelCount() >= i)
            {
                if(r.isCustomRhyme(w, i)) //If it is a perfect rhyme, add it to the rhyme list
                {
                    rhymes.add(r);
                }
            }
        }
        return rhymes;
    }
    public WordList getPerfectRhymes(Word w, int i)
    {
        WordList rhymes = new WordList(); //Creates empty wordlist (I hope)
        for(Word r : this) //For every word in the list
        {
            if(r.getVowelCount() >= i)
            {
                if(r.isPerfectRhyme(w, i)) //If it is a perfect rhyme, add it to the rhyme list
                {
                    rhymes.add(r);
                }
            }
        }
        return rhymes; //Return the rhyme list
    }
    public WordList getFamilyRhymes(Word w, int i)
    {
        WordList rhymes = new WordList();
        for(Word r : this)
        {
            if(r.getVowelCount() >= i)
            {
                if(r.isFamilyRhyme(w, i)) //If it is a perfect rhyme, add it to the rhyme list
                {
                    rhymes.add(r);
                }
            }
        }
        return rhymes;
    }
    public WordList getAdditiveRhymes(Word w, int i)
    {
        WordList rhymes = new WordList();
        for(Word r : this)
        {
            if(r.getVowelCount() >= i)
            {
                if(r.isAdditiveRhyme(w, i)) //If it is a perfect rhyme, add it to the rhyme list
                {
                    rhymes.add(r);
                }
            }
        }
        return rhymes;
    }
    public WordList getSubtractiveRhymes(Word w, int i)
    {
        WordList rhymes = new WordList();
        for(Word r : this)
        {
            if(r.getVowelCount() >= i)
            {
                if(r.isSubtractiveRhyme(w, i)) //If it is a perfect rhyme, add it to the rhyme list
                {
                    rhymes.add(r);
                }
            }
        }
        return rhymes;
    }
    public WordList getAssonanceRhymes(Word w, int i)
    {
        WordList rhymes = new WordList();
        for(Word r : this)
        {
            if(r.getVowelCount() >= i)
            {
                if(r.isAssonanceRhyme(w, i)) //If it is a perfect rhyme, add it to the rhyme list
                {
                    rhymes.add(r);
                }
            }
        }
        return rhymes;
    }
    public WordList getConsonanceRhymes(Word w, int i)
    {
        WordList rhymes = new WordList();
        for(Word r : this)
        {
            if(r.getVowelCount() >= i)
            {
                if(r.isConsonanceRhyme(w, i)) //If it is a perfect rhyme, add it to the rhyme list
                {
                    rhymes.add(r);
                }
            }
        }
        return rhymes;
    }
    public WordList getHalfRhymes(Word w, int i)
    {
        WordList rhymes = new WordList();
        for(Word r : this)
        {
            if(r.getVowelCount() >= i)
            {
                if(r.isHalfRhyme(w, i)) //If it is a perfect rhyme, add it to the rhyme list
                {
                    rhymes.add(r);
                }
            }
        }
        return rhymes;
    }
}
