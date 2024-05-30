package com.keyin;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SuggestionEngineTest {

    private SuggestionEngine suggestionEngine;

    @Before
    public void setUp() {
        suggestionEngine = new SuggestionEngine();
        try {
            suggestionEngine.loadDictionaryData(Paths.get("src/test/resources/words.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenCorrectWord_thenNoSuggestions() {
        String suggestions = suggestionEngine.generateSuggestions("correct");
        assertEquals("", suggestions);
    }

    @Test
    public void whenIncorrectWord_thenSuggestionsProvided() {
        String suggestions = suggestionEngine.generateSuggestions("incorect");
        String expectedSuggestions = "correct\nincite\ninsect";
        assertEquals(expectedSuggestions, suggestions);
    }

    @Test
    public void whenNoSuggestionsAvailable_thenEmptyString() {
        String suggestions = suggestionEngine.generateSuggestions("qwertyuiop");
        assertEquals("", suggestions);
    }

    @Test
    public void whenSingleCharacterWord_thenNoSuggestions() {
        String suggestions = suggestionEngine.generateSuggestions("a");
        assertEquals("", suggestions);
    }

    @Test
    public void whenNullInput_thenNoSuggestions() {
        String suggestions = suggestionEngine.generateSuggestions(null);
        assertEquals("", suggestions);
    }

    @Test
    public void whenEmptyInput_thenNoSuggestions() {
        String suggestions = suggestionEngine.generateSuggestions("");
        assertEquals("", suggestions);
    }

    @Test
    public void whenAllUpperCaseInput_thenNoSuggestions() {
        String suggestions = suggestionEngine.generateSuggestions("CORRECT");
        assertEquals("", suggestions);
    }

    @Test
    public void whenMixedCaseInput_thenSuggestionsProvided() {
        String suggestions = suggestionEngine.generateSuggestions("InCorrect");
        String expectedSuggestions = "correct\nincite\ninsect";
        assertEquals(expectedSuggestions, suggestions);
    }

    @Test
    public void whenLongInputWord_thenNoSuggestions() {
        String suggestions = suggestionEngine.generateSuggestions("supercalifragilisticexpialidocious");
        assertEquals("", suggestions);
    }

    @Test
    public void testWordSuggestionDBInitialization() {
        Map<String, Integer> wordMap = suggestionEngine.getWordSuggestionDB();
        assertNotNull(wordMap);
    }
}
