import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class IntervalWorkout extends SimpleWorkout {
    private int intervals_quantity = 0;
    private long Rest_distance = 0;
    private ArrayList<Integer> Rest_Time = new ArrayList<>();
    private ArrayList <Integer> Rest_TempoMS = new ArrayList<>();

    protected String ASK_HARD_INTERVAL_DISTANCE = "Какое расстояние каждого быстрого интервала? Введите расстояние в метрах";
    protected String ASK_REST_INTERVAL_DISTANCE = "Какое расстояние каждого отрезка отдыха? Введите расстояние в метрах";

    public int getIntervals_quantity() {
        return intervals_quantity;
    }

    public long getRest_distance() {
        return Rest_distance;
    }

    public ArrayList<Integer> getRest_Time() {
        return Rest_Time;
    }

    public ArrayList<Integer> getRest_TempoMS() {
        return Rest_TempoMS;
    }

    protected void setIntervals_quantity(int intervals_quantity) {
        this.intervals_quantity = intervals_quantity;
    }

    private void setRest_distance(long rest_distance) {
        Rest_distance = rest_distance;
    }

    private void setRest_Time(ArrayList<Integer> rest_Time) {
        Rest_Time = rest_Time;
    }

    private void setRest_TempoMS(ArrayList<Integer> rest_TempoMS) {
        Rest_TempoMS = rest_TempoMS;
    }

    public Integer AskIntervalQuantity() {
        boolean CorrectIntervalsQuantity=false;
        int quantity = 0;
        do {
            System.out.println("Сколько итервалов будет в Вашей тренировке?");
            Scanner scan = new Scanner(System.in);
            try {
                quantity = scan.nextInt();
                if (quantity <= 0){
                    System.out.println("Должен быть хотя бы 1 интервал");
                } else { CorrectIntervalsQuantity = true;}
            } catch (Exception e) {
                System.out.println("Введите число без запятых, точек и других разделителей.");
            }
        } while (!CorrectIntervalsQuantity);
        return quantity;
    }
    @Override
    public void AskKnownParameters_Training() {
        boolean KnownParameters = false;
        do {
            System.out.println("Что Вы знаете о каждом быстром интервале?\n" +
                    "1. Я знаю расстояние и время\n" +
                    "2. Я знаю расстояние и темп\n" +
                    "3. Я знаю время и темп\n");
            String choice2 = scan.nextLine();
            if (RunningBot.StopWord(choice2)){
                break;
            }
            switch (choice2) {
                case "1" -> {
                    setHard_Training_distance(AskDistance(ASK_HARD_INTERVAL_DISTANCE));
                    setHard_Training_Time(Ask_Hard_Training_Time(getHard_Training_distance()));
                    setHard_Training_TempoMS(PaceComputing(getHard_Training_distance(), getHard_Training_Time()));
                    KnownParameters = true;
                }
                case "2" -> {

                    setHard_Training_distance(AskDistance(ASK_HARD_INTERVAL_DISTANCE));
                    setHard_Training_TempoMS(Ask_Hard_Training_Tempo(getHard_Training_distance()));
                    setHard_Training_Time(TimeComputing(getHard_Training_distance(), getHard_Training_TempoMS()));
                    KnownParameters = true;
                }
                case "3" -> {
                    setHard_Training_Time(Ask_Hard_Training_Time(getHard_Training_distance()));
                    setHard_Training_TempoMS(Ask_Hard_Training_Tempo(getHard_Training_distance()));
                    setHard_Training_distance(DistanceComputing(getHard_Training_Time(), getHard_Training_TempoMS()));
                    KnownParameters = true;
                }
                default -> System.out.println("Выберите из предложенных вариантов. 1 для расчета скорости, 2 для расчета времени," +
                        " 3 для расчета расстояния\n");
            }
        } while(!KnownParameters);
    }
    public void AskKnownParameters_Rest() {
        boolean KnownParameters = false;
        do {
            System.out.println("Что Вы знаете об отдыхе после каждого быстрого интервала?\n" +
                    "1. Я знаю расстояние и время\n" +
                    "2. Я знаю расстояние и темп\n" +
                    "3. Я знаю время и темп\n" +
                    "4. Я знаю только время (отдых без даижения)\n");
            String choice2 = scan.nextLine();
            if (RunningBot.StopWord(choice2)){
                break;
            }
            switch (choice2) {
                case "1" -> {
                    setRest_distance(AskDistance(ASK_REST_INTERVAL_DISTANCE));
                    setRest_Time(Ask_Rest_Time(getRest_distance()));
                    setRest_TempoMS(PaceComputing(getRest_distance(), getRest_Time()));
                    KnownParameters = true;
                }
                case "2" -> {

                    setRest_distance(AskDistance(ASK_REST_INTERVAL_DISTANCE));
                    setRest_TempoMS(Ask_Rest_Tempo(getRest_distance()));
                    setRest_Time(TimeComputing(getRest_distance(), getRest_TempoMS()));
                    KnownParameters = true;
                }
                case "3" -> {
                    setRest_Time(Ask_Rest_Time(getRest_distance()));
                    setRest_TempoMS(Ask_Rest_Tempo(getRest_distance()));
                    setRest_distance(DistanceComputing(getRest_Time(), getRest_TempoMS()));
                    KnownParameters = true;
                }
                case "4" -> {
                    setRest_Time(Ask_Rest_Time(getRest_distance()));
                    setRest_distance(0);
                    setRest_TempoMS(new ArrayList<>(Arrays.asList(0,0)));
                    KnownParameters = true;
                }
                default -> System.out.println("Выберите из предложенных вариантов. 1 для расчета скорости, 2 для расчета времени," +
                        " 3 для расчета расстояния, 4 для отдыха без бега\n");
            }
            setTotal_distance((getWarmUp_CoolDown_distance() * 2) + getHard_Training_distance() * getIntervals_quantity() + getRest_distance() * getIntervals_quantity());
            set_Total_Time(TotalTimeComputing(getWarmUp_CoolDown_Time(),getHard_Training_Time(),getRest_Time()));
            setAverage_TempoMS(PaceComputing(getTotal_distance(), get_Total_Time()));
        } while(!KnownParameters);
    }
    public ArrayList Ask_Hard_Training_Time(Long distance){
        boolean CorrectTime=false;
        ArrayList <Integer> HMSNumeric= new ArrayList<>();
        ArrayList <String> HMSinMassive = new ArrayList<>();
        do{
            try {
                Scanner scan = new Scanner(System.in);
                if (distance==0)
                    System.out.println("Какое время у каждого быстрого интервала? Введите 3 числа через пробел.\n" +
                            "Напиример 0 часов 2 минуты 10 секунд нужно написать как 0 2 10");
                else
                    System.out.println("Какое время у каждого быстрого интервала в " + distance + " метров? Введите 3 числа через пробел.\n" +
                            "Напиример 0 часов 2 минуты 10 секунд нужно написать как 0 2 10");
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
    public ArrayList Ask_Rest_Time(Long distance){
        boolean CorrectTime=false;
        ArrayList <Integer> HMSNumeric= new ArrayList<>();
        ArrayList <String> HMSinMassive = new ArrayList<>();
        do{
            try {
                Scanner scan = new Scanner(System.in);
                if (distance==0)
                    System.out.println("Сколько времени на каждый медленный интервал? Введите 3 числа через пробел.\n" +
                            "Напиример 0 часов 2 минуты 10 секунд нужно написать как 0 2 10");
                else
                    System.out.println("Сколько времени на каждый медленный интервал в " + distance + " метров? Введите 3 числа через пробел.\n" +
                            "Напиример 0 часов 2 минуты 10 секунд нужно написать как 0 2 10");
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
    public ArrayList Ask_Hard_Training_Tempo(Long distance){
        boolean CorrectTempo=false;
        ArrayList <Integer> TempoMS = new ArrayList<>();
        ArrayList<String> TempoinMassive = new ArrayList<>();
        do{
            try {
                Scanner scan = new Scanner(System.in);
                if (distance==0)
                    System.out.println("Какой темп на быстрые интервалы? Введите 2 числа через пробел.\n" +
                            "Где первое число это минуты а второе секунды\n"+ " Напиример темп 4'30'' нужно написать как 4 30");
                else
                    System.out.println("Какой темп на быстрые интервалы в " + distance + " метров каждый? Введите 2 числа через пробел.\n" +
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
    public ArrayList Ask_Rest_Tempo(Long distance){
        boolean CorrectTempo=false;
        ArrayList <Integer> TempoMS = new ArrayList<>();
        ArrayList<String> TempoinMassive = new ArrayList<>();
        do{
            try {
                Scanner scan = new Scanner(System.in);
                if (distance==0)
                    System.out.println("Какой темп на медленные интервалы? Введите 2 числа через пробел.\n" +
                            "Где первое число это минуты а второе секунды\n"+ " Напиример темп 4'30'' нужно написать как 4 30");
                else
                    System.out.println("Какой темп на медленные интервалы в " + distance + " метров каждый? Введите 2 числа через пробел.\n" +
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
    public ArrayList<Integer> TotalTimeComputing(ArrayList<Integer> warmUp_CoolDown_Time, ArrayList<Integer> hard_Training_Time, ArrayList<Integer> rest_Time) {
        int total_hour = 0;
        int total_min = 0;
        int total_sec;
        total_sec = ((warmUp_CoolDown_Time.get(2) * 2) + hard_Training_Time.get(2) * getIntervals_quantity() + rest_Time.get(2) * getIntervals_quantity()) % 60;
        total_min = ((((warmUp_CoolDown_Time.get(2) * 2) + hard_Training_Time.get(2) * getIntervals_quantity() + rest_Time.get(2) * getIntervals_quantity())/60) + (warmUp_CoolDown_Time.get(1) * 2) + hard_Training_Time.get(1) * getIntervals_quantity() + rest_Time.get(1) * getIntervals_quantity()) % 60;
        total_hour = (warmUp_CoolDown_Time.get(0) * 2) + hard_Training_Time.get(0) * getIntervals_quantity()+ rest_Time.get(0) * getIntervals_quantity() + ((((warmUp_CoolDown_Time.get(2) * 2) + hard_Training_Time.get(2) * getIntervals_quantity() + rest_Time.get(2) * getIntervals_quantity())/60) + (warmUp_CoolDown_Time.get(1) * 2) + hard_Training_Time.get(1) * getIntervals_quantity() + rest_Time.get(1) * getIntervals_quantity())/60;
        ArrayList <Integer> Total_HMS = new ArrayList<>(Arrays.asList(total_hour,total_min,total_sec));
        return Total_HMS;
    }
}
