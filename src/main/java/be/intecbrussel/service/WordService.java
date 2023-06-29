package be.intecbrussel.service;

import be.intecbrussel.model.Word;
import be.intecbrussel.repository.WordRepository;
import be.intecbrussel.repository.WordRepositoryListImpl;

import java.util.Optional;

public class WordService {
    private WordRepository wordRepository;

    public WordService() {
        wordRepository = WordRepositoryListImpl.getInstance();
    }

    public boolean validateWord(String word) {
        if (word.trim().length() < 2) {
            return false;
        }

        return true;
    }

    public boolean validateDefinition(String def) {
        if (def.trim().isEmpty()) {
            return false;
        }

        return true;
    }

    public boolean validateKeywords(String keys) {
        return validateDefinition(keys);

    }

    public boolean addNewWord(String word, String def, String keys){
        Word newWord = new Word(word, def, keys);
        boolean wordAdded = wordRepository.createWord(newWord);
        return wordAdded;
    }

    public Optional<Word> getRandomWord(){
        return wordRepository.readRandomWord();

    }

    public boolean validateUserGuess(Word word, String guess) {
        String[] guesses = guess.split(" ");
        String[] keywords = word.getKeywords().split(" ");

        keywordLoop : for (String keyword : keywords) {
            for (String guessWord : guesses) {
                if (keyword.equals(guessWord)) {
                    continue keywordLoop;
                }
            }

            return false;
        }

        return true;
    }

    public boolean deleteWord(String wordName) {
        return wordRepository.deleteWord(wordName);

    }
}
