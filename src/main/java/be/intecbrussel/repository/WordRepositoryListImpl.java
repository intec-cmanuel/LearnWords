package be.intecbrussel.repository;

import be.intecbrussel.model.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class WordRepositoryListImpl implements WordRepository{
    private static WordRepositoryListImpl instance;

    private WordRepositoryListImpl() {
        words = new ArrayList<>();
    }

    public static WordRepositoryListImpl getInstance() {
        if (instance == null) {
            instance = new WordRepositoryListImpl();
        }

        return instance;
    }


    private List<Word> words;

    @Override
    public boolean createWord(Word word) {
        if (words.stream().anyMatch(listword -> listword.getName().equals(word.getName()))){
            return false;
        }

        words.add(word);
        return true;
    }

    @Override
    public Optional<Word> readRandomWord() {
        if (words.isEmpty()) {
            return Optional.empty();
        }

        int listSize = words.size();
        Random random = new Random();
        int randomIndex = random.nextInt(listSize);
        return Optional.of(words.get(randomIndex));
    }

    @Override
    public boolean deleteWord(String wordName) {
        return words.removeIf(w -> w.getName().equals(wordName));
    }
}
