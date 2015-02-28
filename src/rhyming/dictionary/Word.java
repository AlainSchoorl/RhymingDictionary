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
}
