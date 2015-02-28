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
    
    public void addWord(Word w)
    {
        wordList.add(w);
    }
    
    public WordList getFamilyRhymes(Word w, int i)
    {
        return null;
    }
    public WordList getAdditiveRhymes(Word w, int i)
    {
        return null;
    }
    public WordList getSubtractiveRhymes(Word w, int i)
    {
        return null;
    }
    public WordList getAssonanceRhymes(Word w, int i)
    {
        return null;
    }
    public WordList getConsonanceRhymes(Word w, int i)
    {
        return null;
    }
}
