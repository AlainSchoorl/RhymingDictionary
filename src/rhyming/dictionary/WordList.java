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
public class WordList extends ArrayList {
    private ArrayList<Word> wordList;
    
        public WordList()
        {
            ArrayList<Word> wordList = new ArrayList();
        }
        
    public void addWord(Word w)
    {
        wordList.add(w);
    }
    
    public WordList getPerfectRhymes(Word w, int i)
    {
        WordList rhymes = new WordList();
        for(Word r : wordList)
        {
            if(r.isPerfectRhyme(w, i))
            {
                rhymes.add(r);
            }
        }
        return rhymes;
    }
    public WordList getFamilyRhymes(Word w, int i)
    {
        WordList rhymes = new WordList();
        for(Word r : wordList)
        {
            if(r.isFamilyRhyme(w, i))
            {
                rhymes.add(r);
            }
        }
        return rhymes;
    }
    public WordList getAdditiveRhymes(Word w, int i)
    {
        WordList rhymes = new WordList();
        for(Word r : wordList)
        {
            if(r.isAdditiveRhyme(w, i))
            {
                rhymes.add(r);
            }
        }
        return rhymes;
    }
    public WordList getSubtractiveRhymes(Word w, int i)
    {
        WordList rhymes = new WordList();
        for(Word r : wordList)
        {
            if(r.isSubtractiveRhyme(w, i))
            {
                rhymes.add(r);
            }
        }
        return rhymes;
    }
    public WordList getAssonanceRhymes(Word w, int i)
    {
        WordList rhymes = new WordList();
        for(Word r : wordList)
        {
            if(r.isAssonanceRhyme(w, i))
            {
                rhymes.add(r);
            }
        }
        return rhymes;
    }
    public WordList getConsonanceRhymes(Word w, int i)
    {
        WordList rhymes = new WordList();
        for(Word r : wordList)
        {
            if(r.isConsonanceRhyme(w, i))
            {
                rhymes.add(r);
            }
        }
        return rhymes;
    }
    public WordList getHalfRhymes(Word w, int i)
    {
        WordList rhymes = new WordList();
        for(Word r : wordList)
        {
            if(r.isHalfRhyme(w, i))
            {
                rhymes.add(r);
            }
        }
        return rhymes;
    }
}
