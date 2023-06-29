package be.intecbrussel.view;

import be.intecbrussel.model.Word;
import be.intecbrussel.service.WordService;

import java.util.Optional;
import java.util.Scanner;
import java.util.ServiceConfigurationError;

public class Menu {
    private WordService wordService;

    public Menu() {
        wordService = new WordService();
    }

    public void run() {
        app : while (true) {
            System.out.println("Welcome to learning with words.");
            System.out.println("What will we be doing today?");
            System.out.println("1/ add word\n2/ test you knowledge\n3/ delete word \n4/ exit");

            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    addMenu();
                    break;
                case "2":
                    playMenu();
                    break;
                case "3":
                    deleteMenu();
                    break;
                case "4":
                    break app;

            }
        }
    }

    private void addMenu() {
        Scanner scanner = new Scanner(System.in);
        String userWord;
        String userDefinition;
        String userKeywords;

        wordLoop : while (true) {
            System.out.println("Enter a word.");
            userWord = scanner.nextLine();
            boolean isWordValid = wordService.validateWord(userWord);

            if (!isWordValid) {
                System.out.println("Your word is too short! ERROR");
            } else {
                break wordLoop;
            }
        }

        defLoop : while (true) {
            System.out.println("Enter the definition of the word.");
            userDefinition = scanner.nextLine();
            boolean isDefinitionValid = wordService.validateDefinition(userDefinition);

            if (!isDefinitionValid) {
                System.out.println("Definition cannot be empty");
            } else {
                break defLoop;
            }
        }

        keyLoop : while (true) {
            System.out.println("Enter the keywords of the definition. All keywords must be space seperated.");
            userKeywords = scanner.nextLine();
            boolean isKeywordsValid = wordService.validateKeywords(userKeywords);

            if (!isKeywordsValid) {
                System.out.println("Keywords cannot be empty");
            } else {
                break keyLoop;
            }
        }

        boolean wordAdded = wordService.addNewWord(userWord, userDefinition, userKeywords);

        if (wordAdded) {
            System.out.println("Word added succesfully!");
        } else {
            System.out.println("Word already exists!");
        }

    }

    private void playMenu() {
        Optional<Word> randomWord = wordService.getRandomWord();
        if (randomWord.isEmpty()) {
            System.out.println("There are no words. Please add some first!");
            return;
        }

        Word word = randomWord.get();

        System.out.println("Your word is: " + word.getName());

        Scanner scanner = new Scanner(System.in);
        String userDef = scanner.nextLine();
        boolean isCorrect = wordService.validateUserGuess(word, userDef);

        if (isCorrect) {
            System.out.println("You are winner");
        } else {
            System.out.println("!!! LOSER ALERT !!!");
        }

        System.out.println("Definition: " + word.getDefinition());
    }

    private void deleteMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which word do you want to delete?");
        String wordToDelete = scanner.nextLine();
        boolean isDeleted = wordService.deleteWord(wordToDelete);

        if (isDeleted) {
            System.out.println("Word deleted. YEAY");
        } else {
            System.out.println("Word not found! NOOOOOOO");
        }

    }
}
