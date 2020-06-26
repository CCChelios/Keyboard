package com.example.keyboard;

import android.inputmethodservice.Keyboard;
import android.util.Log;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class WordCheck {
    private ArrayList words;
    private StringBuffer text;
    private InputConnection ic;

    WordCheck(){
        words = new ArrayList();
        words.add("one");
        words.add("two");
        words.add("three");
    }

    public void check_input(InputConnection ic_in){
        ic = ic_in;
        ExtractedText extracted = ic.getExtractedText(new ExtractedTextRequest(), 0);
        StringBuffer text;

        String big_word = words.get(0).toString();
        for (int i = 1; i < words.size(); i++) {
            if (big_word.length() < words.get(i).toString().length())
                big_word = words.get(i).toString();
        }
        text = new StringBuffer(ic.getTextBeforeCursor(big_word.length(),0).toString().toLowerCase());
        for (int i = 0; i < words.size(); i++) {
            if (text.lastIndexOf(words.get(i).toString()) != -1){
                //Log.i("ic ++++++ ", "There is a coincidence");
                ic.deleteSurroundingText(words.get(i).toString().length(), 0);
             }
        }
    }
    private void remove_word(String word){
        //ic.deleteSurroundingText(1, 0);
        //ic.getTextBeforeCursor();
        ic.deleteSurroundingText(1, 0);
        int  len_word = word.length();
        int  len_text = text.length();
        //text.delete(len_text - len_word, len_text);
    }
}
