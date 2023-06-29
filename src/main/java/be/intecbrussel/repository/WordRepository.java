package be.intecbrussel.repository;

import be.intecbrussel.model.Word;

import java.util.Optional;

public interface WordRepository {
    boolean createWord(Word word);
    Optional<Word> readRandomWord();
    boolean deleteWord(String wordName);
}
