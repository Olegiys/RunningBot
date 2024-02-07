import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
// трам парам
public class RunningBot {
    static String choice;
    static Scanner scan = new Scanner(System.in);
    public static void AskRunningType() {
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
                    simpleWorkout.AskKnownParameters_Training();
                    if (simpleWorkout.getHasWarmUpAndCoolDown()) {
                        System.out.println("Ваша тренировка:\n" +
                                "Разминка:\n" +
                                "  Расстояние - " + simpleWorkout.getWarmUp_CoolDown_distance() + " метров\n" +
                                "  Время - " + simpleWorkout.getWarmUp_CoolDown_Time().get(0) + " ч." +
                                simpleWorkout.getWarmUp_CoolDown_Time().get(1) + " мин." +
                                simpleWorkout.getWarmUp_CoolDown_Time().get(2) + " сек.\n" +
                                "  Темп - " + simpleWorkout.getWarmUp_CoolDown_TempoMS().get(0) + "'" + simpleWorkout.getWarmUp_CoolDown_TempoMS().get(1) + "'' на киллометр\n");
                    }
                        System.out.println(
                            "Тренировка:\n" +
                            "  Расстояние - " + simpleWorkout.getHard_Training_distance() + " метров\n" +
                            "  Время - " + simpleWorkout.getHard_Training_Time().get(0) + " ч." +
                            simpleWorkout.getHard_Training_Time().get(1) + " мин." +
                            simpleWorkout.getHard_Training_Time().get(2) + " сек.\n" +
                            "  Темп - " +  simpleWorkout.getHard_Training_TempoMS().get(0) + "'" +simpleWorkout.getHard_Training_TempoMS().get(1) + "'' на киллометр\n");
                    if (simpleWorkout.getHasWarmUpAndCoolDown()) {
                        System.out.println(
                                "Заминка:\n" +
                                   "  Расстояние - " + simpleWorkout.getWarmUp_CoolDown_distance() + " метров\n" +
                                   "  Время - " + simpleWorkout.getWarmUp_CoolDown_Time().get(0) + " ч." +
                                                  simpleWorkout.getWarmUp_CoolDown_Time().get(1) + " мин." +
                                                  simpleWorkout.getWarmUp_CoolDown_Time().get(2) + " сек.\n" +
                                   "  Темп - " + simpleWorkout.getWarmUp_CoolDown_TempoMS().get(0) + "'" + simpleWorkout.getWarmUp_CoolDown_TempoMS().get(1) + "'' на киллометр\n" +
                                "Общее время трениовки: " + simpleWorkout.get_Total_Time().get(0) + " ч. " + simpleWorkout.get_Total_Time().get(1) + " мин." + simpleWorkout.get_Total_Time().get(2) + " сек.\n" +
                                "Общяя дистанция трениовки: " + simpleWorkout.getTotal_distance() + " метров\n" +
                                "Средний Темп тренировки: " + simpleWorkout.getAverage_TempoMS().get(0) + "'" + simpleWorkout.getAverage_TempoMS().get(1) + "'' на киллометр");
                    }
                    RunningTypeIsChosen = true;
                }
                case "2" -> {
                    IntervalWorkout intervalWorkout = new IntervalWorkout();
                    intervalWorkout.setHasWarmUpAndCoolDown(intervalWorkout.AskWarmUpAndCoolDown());
                    if (intervalWorkout.getHasWarmUpAndCoolDown())
                        intervalWorkout.AskKnownParameters_WarmUp_CoolDown();
                    intervalWorkout.setIntervals_quantity(intervalWorkout.AskIntervalQuantity());
                    intervalWorkout.AskKnownParameters_Training();
                    intervalWorkout.AskKnownParameters_Rest();
                    if (intervalWorkout.getHasWarmUpAndCoolDown()) {
                        System.out.println("Ваша тренировка:\n" +
                                "Разминка:\n" +
                                "  Расстояние : " + intervalWorkout.getWarmUp_CoolDown_distance() + " метров\n" +
                                "  Время : " + intervalWorkout.getWarmUp_CoolDown_Time().get(0) + " ч." +
                                intervalWorkout.getWarmUp_CoolDown_Time().get(1) + " мин." +
                                intervalWorkout.getWarmUp_CoolDown_Time().get(2) + " сек.\n" +
                                "  Темп : " + intervalWorkout.getWarmUp_CoolDown_TempoMS().get(0) + "'" + intervalWorkout.getWarmUp_CoolDown_TempoMS().get(1) + "'' на киллометр\n");
                    }
                    System.out.println(
                                "Тренировка:\n" +
                                    "  Кол-во интервалов : " + intervalWorkout.getIntervals_quantity()  + "\n" +
                                    "  Быстрые интервалы : \n"  +
                                    "      Расстояние : " + intervalWorkout.getHard_Training_distance() + " метров\n" +
                                    "      Время : " + intervalWorkout.getHard_Training_Time().get(0) + " ч." +
                                           intervalWorkout.getHard_Training_Time().get(1) + " мин." +
                                           intervalWorkout.getHard_Training_Time().get(2) + " сек.\n" +
                                    "      Темп : " +  intervalWorkout.getHard_Training_TempoMS().get(0) + "'" +intervalWorkout.getHard_Training_TempoMS().get(1) + "'' на киллометр\n");
                    if (intervalWorkout.getRest_distance() != 0) {
                        System.out.println(
                                        "  Медленные интервалы : \n"  +
                                        "      Расстояние : " + intervalWorkout.getRest_distance() + " метров\n" +
                                        "      Время : " + intervalWorkout.getRest_Time().get(0) + " ч." +
                                               intervalWorkout.getRest_Time().get(1) + " мин." +
                                               intervalWorkout.getRest_Time().get(2) + " сек.\n" +
                                        "      Темп : " +  intervalWorkout.getRest_TempoMS().get(0) + "'" +intervalWorkout.getRest_TempoMS().get(1) + "'' на киллометр\n");
                    }
                    else { System.out.println(
                            "  Отдых между интервалами : \n"  +
                                    "      Время : " + intervalWorkout.getRest_Time().get(0) + " ч." +
                                           intervalWorkout.getRest_Time().get(1) + " мин." +
                                           intervalWorkout.getRest_Time().get(2) + " сек.\n");
                    }
//                            System.out.println(
//                                "Общее время интервальной трениовки : " + intervalWorkout.get_Total_Time().get(0) + " ч. " + intervalWorkout.get_Total_Time().get(1) + " мин." + intervalWorkout.get_Total_Time().get(2) + " сек.\n" +
//                                "Общяя дистанция интервальной трениовки : " + simpleWorkout.getTotal_distance() + " метров\n" +
//                                "Средний Темп интервальной тренировки : " + simpleWorkout.getAverage_TempoMS().get(0) + "'" + simpleWorkout.getAverage_TempoMS().get(1) + "'' на киллометр");
                    if (intervalWorkout.getHasWarmUpAndCoolDown()) {
                        System.out.println(
                                "Заминка:\n" +
                                        "  Расстояние - " + intervalWorkout.getWarmUp_CoolDown_distance() + " метров\n" +
                                        "  Время - " + intervalWorkout.getWarmUp_CoolDown_Time().get(0) + " ч." +
                                        intervalWorkout.getWarmUp_CoolDown_Time().get(1) + " мин." +
                                        intervalWorkout.getWarmUp_CoolDown_Time().get(2) + " сек.\n" +
                                        "  Темп - " + intervalWorkout.getWarmUp_CoolDown_TempoMS().get(0) + "'" + intervalWorkout.getWarmUp_CoolDown_TempoMS().get(1) + "'' на киллометр\n");
                    }
                        System.out.println(
                                "Общее время трениовки: " + intervalWorkout.get_Total_Time().get(0) + " ч. " + intervalWorkout.get_Total_Time().get(1) + " мин." + intervalWorkout.get_Total_Time().get(2) + " сек.\n" +
                                "Общяя дистанция трениовки: " + intervalWorkout.getTotal_distance() + " метров\n" +
                                "Средний Темп тренировки: " + intervalWorkout.getAverage_TempoMS().get(0) + "'" + intervalWorkout.getAverage_TempoMS().get(1) + "'' на киллометр");
                    RunningTypeIsChosen = true;
                }
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
        AskRunningType();
    }
}