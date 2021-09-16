import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class HangMan {
    private String choose;
    private  int  lives = 3;
    private ArrayList<String> word = new ArrayList<>();
    private ArrayList<Character> underScore = new ArrayList<>();
    private ArrayList<Character> secretWords = new ArrayList<>();
    private Scanner scan = new Scanner(System.in);

    public void randomWord(ArrayList<Character> secretWords, ArrayList<Character> underScore){
        word.add("black");
        word.add("strong");
        word.add("smart");
        word.add("brave");
        Random rand = new Random();
        int num = rand.nextInt(word.size());
        choose = word.get(num);
        for(int i = 0; i < choose.length(); i++){
            char w = choose.charAt(i);
            secretWords.add(w);
            underScore.add('_');
        }
        System.out.println(word);
        System.out.println(secretWords);
       // System.out.println(underScore);

    }

    public void guess(){
        boolean victory = false;
        while (!victory && lives >= 0) {
            String input = scan.next();

            if(!Character.isLetter(input.charAt(0)) || input.length() != 1){
                System.out.println("You Should enter only one letter.");
                continue;
            }
            char guessedLetter = input.charAt(0);
            boolean hasGuessedRight = secretWords.contains(guessedLetter);

            if (hasGuessedRight){
                int guessedLetterPosition = secretWords.indexOf(guessedLetter);
                char fetchedGuessedLetter = secretWords.get(guessedLetterPosition);
                underScore.remove(guessedLetterPosition);
                underScore.add(guessedLetterPosition, fetchedGuessedLetter);
                System.out.println("the word contains your letter");
                System.out.println(underScore);
                victory = underScore.equals(secretWords);
            }else{
                System.out.println("you guessed wrong: you have " + lives + " chances left ");
                lives--;
            }
        }
        if(victory){
            System.out.println("YOU WINN");
        }else System.out.println("YOU LOSE ");

    }

    public void printUnderscore(){
        for (Character character : underScore) {
            System.out.print(character + " ");
        }

    }

    public static void main(String[] args){
        System.out.println("Welcome!");
        HangMan hangMan = new HangMan();
        System.out.println("The words chosen for you are " );
        hangMan.randomWord(hangMan.secretWords, hangMan.underScore);
        System.out.println("");
        System.out.println("Enter a letter:");
        hangMan.guess();
        hangMan.printUnderscore();

    }
}