import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class RunningBot {
    static String choice;
    static String choice2;
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
                    AskKnownParameters();
                    RunningTypeIsChosen = true;
                }
                case "2" -> System.out.println("В данный момент, к сожалению, данная функция не доступна\n");
                default -> System.out.println("Выберите из предложенных вариантов. Введите цифрцу 1 для Равномерного бега" +
                        " и цифру 2 для Интервального\n");
            }
        } while (!RunningTypeIsChosen);
    }
    public void AskKnownParameters() {
        boolean KnownParameters = false;
        do {
            System.out.println("Что Вы знаете о своей будущей тренировке?\n" +
                    "1. Я знаю расстояние и время за которое я хочу пробежать, но не знаю необходимую скорость для этого\n" +
                    "2. Я знаю расстояние и скорость с которой я хочу бежать, но не знаю сколько времени это займёт\n" +
                    "3. Я знаю сколько времени и с какой скоростью я хочу бежать, но не знаю какое расстояние я пробегу\n");
            try {
                choice2 = scan.nextLine();
            } catch (Exception e) {}
            if (StopWord(choice2)){
                break;
            }
            switch (choice2) {
                case "1" -> {
                    SimpleWorkout simpleWorkout = new SimpleWorkout();
                    simpleWorkout.setTotal_distance(AskDistance());
                    simpleWorkout.setTime(AskTime(simpleWorkout.getTotal_distance()));
                    simpleWorkout.setAverage_TempoMS(simpleWorkout.PaceComputing(simpleWorkout.getTotal_distance(), simpleWorkout.getTime()));
                    KnownParameters = true;
                }
                case "2" -> {
                    SimpleWorkout simpleWorkout = new SimpleWorkout();
                    simpleWorkout.setTotal_distance(AskDistance());
                    simpleWorkout.setAverage_TempoMS(AskTempo(simpleWorkout.getTotal_distance()));
                    simpleWorkout.setTime(simpleWorkout.TimeComputing(simpleWorkout.getTotal_distance(), simpleWorkout.getAverage_TempoMS()));
                    KnownParameters = true;
                }
                case "3" -> {
                    SimpleWorkout simpleWorkout = new SimpleWorkout();
                    simpleWorkout.setTime(AskTime(simpleWorkout.getTotal_distance()));
                    simpleWorkout.setAverage_TempoMS(AskTempo(simpleWorkout.getTotal_distance()));
                    simpleWorkout.setTotal_distance(simpleWorkout.DistanceComputing(simpleWorkout.getTime(), simpleWorkout.getAverage_TempoMS()));
                    KnownParameters = true;
                }
                default -> System.out.println("Выберите из предложенных вариантов. 1 для расчета скорости, 2 для расчета времени," +
                        " 3 для расчета расстояния\n");
            }
        } while(!KnownParameters);
    }
    public long AskDistance(){
        boolean CorrectDistance=false;
        long distance = 0;
        do {
            System.out.println("Какое расстояние Вы хотите пробежать? Введите расстояние в метрах");
            Scanner scan = new Scanner(System.in);
            try {
                distance = scan.nextLong();
                if (distance <= 0){
                    System.out.println("Расстояние должно быть больше 0");
                } else { CorrectDistance = true;}
            } catch (Exception e) {
                System.out.println("Введите число без запятых, точек и других разделителей.");
            }
        } while (!CorrectDistance);
        return distance;
    }
    public ArrayList AskTime(Long distance){
        boolean CorrectTime=false;
        ArrayList <Integer> HMSNumeric= new ArrayList<>();
        ArrayList <String> HMSinMassive = new ArrayList<>();
        do{
            try {
                Scanner scan = new Scanner(System.in);
                if (distance==0)
                    System.out.println("Сколько времени Вы хотите потратить на забег? Введите 3 числа через пробел.\n" +
                            "Напиример 0 часов 35 минут 50 секунд нужно написать как 0 35 50");
                else
                    System.out.println("За какое время Вы хотите пробежать " + distance + " метров? Введите 3 числа через пробел.\n" +
                            "Напиример 0 часов 35 минут 50 секунд нужно написать как 0 35 50");
                String HMSwithBlanks = scan.nextLine();

                HMSinMassive = new ArrayList<>(Arrays.asList(HMSwithBlanks.split(" ")));
                for (String myInt : HMSinMassive)
                {
                    HMSNumeric.add(Integer.valueOf(myInt));
                }
                if (HMSNumeric.size()<3) {
                    System.out.println("Вы ввели недостаточное количество параметров. Вы ввели только:" + HMSNumeric);
                    HMSNumeric.clear();
                }
                else if (HMSNumeric.size()>3){
                    System.out.println("Вы ввели чрезмерное количество параметров. Вы ввели:" + HMSNumeric);
                    HMSNumeric.clear();
                }
                else {
                    System.out.println("Вы ввели все корректно");
                    CorrectTime=true;
                }
            } catch (Exception t) {
//                t.printStackTrace();
                System.out.println("Каждое вводимое число должно быть натуральным и ввод должен соответствовать шаблону. Вы ввели: " + HMSinMassive);
                HMSNumeric.clear();
            }
        } while (!CorrectTime);
        return HMSNumeric;
    }
    public ArrayList AskTempo(Long distance){
        boolean CorrectTempo=false;
        ArrayList <Integer> TempoMS = new ArrayList<>();
        ArrayList<String> TempoinMassive = new ArrayList<>();
        do{
            try {
                Scanner scan = new Scanner(System.in);
                if (distance==0)
                    System.out.println("В каком темпе Вы хотите бежать? Введите 2 числа через пробел.\n" +
                            "Где первое число это минуты а второе секунды\n"+ " Напиример темп 4'30'' нужно написать как 4 30");
                else
                    System.out.println("В каком темпе Вы хотите пробежать " + distance + " метров? Введите 2 числа через пробел.\n" +
                            "Где первое число это минуты а второе секунды\n"+ " Напиример темп 4'30'' нужно написать как 4 30");
                String TempoMSwithBlanks = scan.nextLine();

                TempoinMassive = new ArrayList<>(Arrays.asList(TempoMSwithBlanks.split(" ")));
                for (String myInt : TempoinMassive)
                {
                    TempoMS.add(Integer.valueOf(myInt));
                }
                if (TempoMS.size()<2) {
                    System.out.println("Вы ввели недостаточное количество параметров. Вы ввели только:" + TempoMS);
                    TempoMS.clear();
                }
                else if (TempoMS.size()>2){
                    System.out.println("Вы ввели чрезмерное количество параметров. Вы ввели:" + TempoMS);
                    TempoMS.clear();
                }
                else {
                    System.out.println("Вы ввели все корректно");
                    CorrectTempo=true;
                }
            } catch (NumberFormatException t) {
                t.printStackTrace();
                System.out.println("Каждое вводимое число должно быть натуральным и ввод должен соответствовать шаблону. Вы ввели: " + TempoinMassive);
                TempoMS.clear();
            }
        } while (!CorrectTempo);
        return TempoMS;
    }
    public boolean StopWord(String word){
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