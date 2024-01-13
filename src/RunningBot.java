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
                    "Bыбepитe (q - выход):");
            try {
                choice = scan.nextLine();
            } catch (Exception e) {}

            if (StopWord(choice)){
                break;
            }
            switch (choice) {
                case "1" -> {
                    SimpleWorkout simpleWorkout = new SimpleWorkout();
                    simpleWorkout.setHasWarmUpAndCoolDown(simpleWorkout.AskWarmUpAndCoolDown());
                    if (simpleWorkout.getHasWarmUpAndCoolDown())
                        simpleWorkout.AskKnownParameters_WarmUp_CoolDown();
                    System.out.println(simpleWorkout.getHasWarmUpAndCoolDown());
                    simpleWorkout.AskKnownParameters_Training();
                    System.out.println("Ваша тренировка:\n" +
                            "Разминка:\n" +
                            "  Расстояние - " + simpleWorkout.getWarmUp_CoolDown_distance() + " метров\n" +
                            "  Время - " + simpleWorkout.getWarmUp_CoolDown_Time().get(0) + " ч." +
                                           simpleWorkout.getWarmUp_CoolDown_Time().get(1) + " мин." +
                                           simpleWorkout.getWarmUp_CoolDown_Time().get(2) + " сек.\n" +
                            "  Темп - " +  simpleWorkout.getWarmUp_CoolDown_Average_TempoMS().get(0) + "'" +simpleWorkout.getWarmUp_CoolDown_Average_TempoMS().get(1) + "'' на киллометр\n" +
                            "Тренировка:\n" +
                            "  Расстояние - " + simpleWorkout.getHard_Training_distance() + " метров\n" +
                            "  Время - " + simpleWorkout.getHard_Training_Time().get(0) + " ч." +
                            simpleWorkout.getHard_Training_Time().get(1) + " мин." +
                            simpleWorkout.getHard_Training_Time().get(2) + " сек.\n" +
                            "  Темп - " +  simpleWorkout.getHard_Training_TempoMS().get(0) + "'" +simpleWorkout.getHard_Training_TempoMS().get(1) + "'' на киллометр\n" +
                            "Заминка:\n" +
                            "  Расстояние - " + simpleWorkout.getWarmUp_CoolDown_distance() + " метров\n" +
                            "  Время - " + simpleWorkout.getWarmUp_CoolDown_Time().get(0) + " ч." +
                            simpleWorkout.getWarmUp_CoolDown_Time().get(1) + " мин." +
                            simpleWorkout.getWarmUp_CoolDown_Time().get(2) + " сек.\n" +
                            "  Темп - " +  simpleWorkout.getWarmUp_CoolDown_Average_TempoMS().get(0) + "'" +simpleWorkout.getWarmUp_CoolDown_Average_TempoMS().get(1) + "'' на киллометр");
                    System.out.println();
                    RunningTypeIsChosen = true;
                }
                case "2" -> {
                    IntervalWorkout intervalWorkout = new IntervalWorkout();
                    intervalWorkout.setHasWarmUpAndCoolDown(intervalWorkout.AskWarmUpAndCoolDown());
                    if (intervalWorkout.getHasWarmUpAndCoolDown())
                        intervalWorkout.AskKnownParameters_WarmUp_CoolDown();
                    intervalWorkout.AskIntervalQuantity();
                    intervalWorkout.AskKnownParameters_Training();
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