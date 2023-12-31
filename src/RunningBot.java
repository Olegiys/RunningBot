import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
// трам парам
public class RunningBot {
    static String choice;
    static Scanner scan = new Scanner(System.in);
    public void AskRunningType() {
        boolean RunningTypeIsChosen = false;
        do {
            System.out.println("Какой вид бега вы планируете использовать?\n" +
                    "Выберите из предложенных вариантов. Введите цифрцу 1 для Равномерного бега и цифру 2 для Интервального\n " +
                    "1. Равномерный\n " +
                    "2. Интервальный\n " +
                    "Bыбepитe (q - выход): \n");
            try {
                choice = scan.nextLine();
            } catch (Exception e) {}

            if (StopWord(choice)){
                break;
            }
            switch (choice) {
                case "1" -> {
                    SimpleWorkout simpleWorkout = new SimpleWorkout();
                    simpleWorkout.AskKnownParameters(simpleWorkout.ASK_SIMPLE_PARAMETERS);
                    RunningTypeIsChosen = true;
                }
                case "2" -> {
                    IntervalWorkout intervalWorkout = new IntervalWorkout();
                    intervalWorkout.AskKnownParameters(intervalWorkout.ASK_HARD_INTERVAL_PARAMETERS);
                    System.out.println("В данный момент, к сожалению, данная функция не доступна\n");}
                default -> System.out.println("Выберите из предложенных вариантов. Введите цифрцу 1 для Равномерного бега" +
                        " и цифру 2 для Интервального\n");
            }
        } while (!RunningTypeIsChosen);
    }
    public static boolean StopWord(String word){
        String[] stopwords = new String[]{"хватит", "стоп","выход","q"};
        String lowercaseWord = word.toLowerCase();
        for (String findword: stopwords) {
            if (findword.equals(lowercaseWord)){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        RunningBot NewRequest = new RunningBot();
        NewRequest.AskRunningType();
    }
}