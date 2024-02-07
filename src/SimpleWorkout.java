import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SimpleWorkout {
    private long WarmUp_CoolDown_distance = 0;
    private ArrayList<Integer> WarmUp_CoolDown_Time = new ArrayList<>();
    private ArrayList <Integer> WarmUp_CoolDown_TempoMS = new ArrayList<>();
    private long Hard_Training_distance = 0;
    private ArrayList<Integer> Hard_Training_Time = new ArrayList<>();
    private ArrayList <Integer> Hard_Training_TempoMS = new ArrayList<>();
    private long Total_distance = 0;
    private ArrayList<Integer> Total_Time = new ArrayList<>();
    private ArrayList <Integer> Average_TempoMS = new ArrayList<>();
    private boolean HasWarmUpAndCoolDown;
    static Scanner scan = new Scanner(System.in);

    protected String ASK_SIMPLE_DISTANCE = "Какое расстояние Вы хотите пробежать? Введите расстояние в метрах";
    protected String ASK_WARMUP_COOLDOWN_DISTANCE =
            "Какое расстояние Вы хотите пробежать на разминке " +
                    "(такое же расстояние будет установлено и для заминки)" +
                    "? Введите расстояние в метрах";
    protected String ASK_TRAINING_DISTANCE =
            "Какое расстояние Вы хотите пробежать на тренировке? Укажите расстояние в метрах";

    public SimpleWorkout () {}

    public void setHasWarmUpAndCoolDown(boolean hasWarmUpAndCoolDown) {
        HasWarmUpAndCoolDown = hasWarmUpAndCoolDown;
    }
    public boolean getHasWarmUpAndCoolDown() {
        return HasWarmUpAndCoolDown;
    }

    protected void setTotal_distance(long total_distance) {
        Total_distance = total_distance;
    }
    private void setWarmUp_CoolDown_distance(long warmUp_CoolDown_distance) {
        WarmUp_CoolDown_distance = warmUp_CoolDown_distance;
    }
    protected void setHard_Training_distance(long hard_Training_distance) {
        Hard_Training_distance = hard_Training_distance;
    }

    protected void set_Total_Time(ArrayList<Integer> time) {
        Total_Time = time;
    }
    private void setWarmUp_CoolDown_Time(ArrayList<Integer> warmUp_CoolDown_Time) {
        WarmUp_CoolDown_Time = warmUp_CoolDown_Time;
    }
    protected void setHard_Training_Time(ArrayList<Integer> hard_Training_Time) {
        Hard_Training_Time = hard_Training_Time;
    }

    protected void setAverage_TempoMS(ArrayList<Integer> average_TempoMS) {
        Average_TempoMS = average_TempoMS;
    }
    private void setWarmUp_CoolDown_TempoMS(ArrayList<Integer> warmUp_CoolDown_TempoMS) {
        WarmUp_CoolDown_TempoMS = warmUp_CoolDown_TempoMS;
    }
    protected void setHard_Training_TempoMS(ArrayList<Integer> hard_Training_TempoMS) {
        Hard_Training_TempoMS = hard_Training_TempoMS;
    }

    public long getTotal_distance() {
        return Total_distance;
    }
    public long getWarmUp_CoolDown_distance() {
        return WarmUp_CoolDown_distance;
    }
    public long getHard_Training_distance() {
        return Hard_Training_distance;
    }

    public ArrayList<Integer> get_Total_Time() {
        return Total_Time;
    }
    public ArrayList<Integer> getWarmUp_CoolDown_Time() {
        return WarmUp_CoolDown_Time;
    }
    public ArrayList<Integer> getHard_Training_Time() {
        return Hard_Training_Time;
    }

    public ArrayList<Integer> getAverage_TempoMS() {
        return Average_TempoMS;
    }
    public ArrayList<Integer> getWarmUp_CoolDown_TempoMS() {
        return WarmUp_CoolDown_TempoMS;
    }
    public ArrayList<Integer> getHard_Training_TempoMS() {
        return Hard_Training_TempoMS;
    }

    public long DistanceComputing(ArrayList<Integer> HMSNumeric, ArrayList<Integer> TempoMS){
        double minutes = (HMSNumeric.get(0) * 60) + HMSNumeric.get(1) + ((double) HMSNumeric.get(2) / 60);
        double timeFor1km = TempoMS.get(0) + (double) TempoMS.get(1) / 60;
        long distance = (int) ((minutes/timeFor1km)*1000);
        return distance;
    }
    public ArrayList<Integer> TimeComputing(long distance, ArrayList<Integer> TempoMS ) {
        double timeFor1km = TempoMS.get(0) + (double) TempoMS.get(1) / 60;
        double BruttoTime = (distance * timeFor1km) / 1000;
        int Hours= (int) BruttoTime / 60;
        int NettoMinutes;
        int Nettosec;
        if (BruttoTime >= 60) {
            double Bruttominutes= BruttoTime%60;
            NettoMinutes= (int) Bruttominutes;
            double BruttoSec=Bruttominutes-NettoMinutes;
            Nettosec= (int) (0.6*(BruttoSec*100));
        }
        else {
            NettoMinutes= (int) BruttoTime;
            double BruttoSec=BruttoTime-NettoMinutes;
            Nettosec= (int) (0.6*(BruttoSec*100));
        }
        ArrayList <Integer> TimeHMS = new ArrayList<>();
        TimeHMS.add(Hours);
        TimeHMS.add(NettoMinutes);
        TimeHMS.add(Nettosec);
        return TimeHMS;
    }
    public ArrayList<Integer> PaceComputing(long distance, ArrayList<Integer> HMSNumeric ){
        double minutes = (HMSNumeric.get(0) * 60) + HMSNumeric.get(1) + ((double) HMSNumeric.get(2) / 60);
        double tempo = (minutes / (double) distance) * 1000;
        int tempTempo = (int) tempo;
        double tempo2 = tempo - tempTempo;
        int sec = (int) (0.6 * (tempo2 * 100));
        ArrayList <Integer> TempoMS = new ArrayList<>();
        TempoMS.add(tempTempo);
        TempoMS.add(sec);
        return TempoMS;
    }
    public ArrayList<Integer> TotalTimeComputing(ArrayList<Integer> warmUp_CoolDown_Time, ArrayList<Integer> hard_Training_Time) {
        int total_hour = 0;
        int total_min = 0;
        int total_sec;
        total_sec = ((warmUp_CoolDown_Time.get(2) * 2) + hard_Training_Time.get(2)) % 60;
        total_min = ((((warmUp_CoolDown_Time.get(2) * 2) + hard_Training_Time.get(2))/60) + (warmUp_CoolDown_Time.get(1) * 2) + hard_Training_Time.get(1)) % 60;
        total_hour = (warmUp_CoolDown_Time.get(0) * 2) + hard_Training_Time.get(0) + ((((warmUp_CoolDown_Time.get(2) * 2) + hard_Training_Time.get(2))/60) + (warmUp_CoolDown_Time.get(1) * 2) + hard_Training_Time.get(1))/60;
        ArrayList <Integer> Total_HMS = new ArrayList<>(Arrays.asList(total_hour,total_min,total_sec));
        return Total_HMS;
    }
    public boolean AskWarmUpAndCoolDown () {
        boolean CorrectAnswer = false;
        do {
            System.out.println("В тренировке будет разминка и заминка? Введите 1 или 2.\n" +
                    "1. Да\n" +
                    "2. Нет\n");
            String answer = scan.nextLine();
            switch (answer){
                case "1" -> {
                    CorrectAnswer = true;
                    return true;
                }
                case "2" -> {
                    setWarmUp_CoolDown_distance(0);
                    setWarmUp_CoolDown_TempoMS(new ArrayList<>(Arrays.asList(0,0)));
                    setWarmUp_CoolDown_Time(new ArrayList<>(Arrays.asList(0,0,0)));
                    return false;
                }
                default -> System.out.println("Выберите из предложенных вариантов. 1 Если есть разминка и заминка 2 если разминки и заминки нет\n");
            }
        } while (CorrectAnswer == false);
        return true;
    }
    public void AskKnownParameters_WarmUp_CoolDown() {
        boolean KnownParameters = false;
        do {
            System.out.println("Что Вы знаете о своей разминке/заминке?\n" +
                    "1. Я знаю расстояние и время\n" +
                    "2. Я знаю расстояние и темп\n" +
                    "3. Я знаю время и скорость\n");
            String choice2 = scan.nextLine();
            if (RunningBot.StopWord(choice2)){
                break;
            }
            switch (choice2) {
                case "1" -> {
                    setWarmUp_CoolDown_distance(AskDistance(ASK_WARMUP_COOLDOWN_DISTANCE));
                    setWarmUp_CoolDown_Time(AskTime_WarmUp_CoolDown(getWarmUp_CoolDown_distance()));
                    setWarmUp_CoolDown_TempoMS(PaceComputing(getWarmUp_CoolDown_distance(), getWarmUp_CoolDown_Time()));
                    KnownParameters = true;
                }
                case "2" -> {
                    setWarmUp_CoolDown_distance(AskDistance(ASK_WARMUP_COOLDOWN_DISTANCE));
                    setWarmUp_CoolDown_TempoMS(AskTempo_WarmUp_CoolDown(getWarmUp_CoolDown_distance()));
                    setWarmUp_CoolDown_Time(TimeComputing(getWarmUp_CoolDown_distance(), getWarmUp_CoolDown_TempoMS()));
                    KnownParameters = true;
                }
                case "3" -> {
                    setWarmUp_CoolDown_Time(AskTime_WarmUp_CoolDown(getWarmUp_CoolDown_distance()));
                    setWarmUp_CoolDown_TempoMS(AskTempo_WarmUp_CoolDown(getWarmUp_CoolDown_distance()));
                    setWarmUp_CoolDown_distance(DistanceComputing(getWarmUp_CoolDown_Time(), getWarmUp_CoolDown_TempoMS()));
                    KnownParameters = true;
                }
                default -> System.out.println("Выберите из предложенных вариантов. Если хотите выйти - введите q\n");
            }
        } while(!KnownParameters);
    }
    protected void AskKnownParameters_Training() {
        boolean KnownParameters = false;
        do {
            System.out.println("Что Вы знаете о своей будущей тренировке?\n" +
                    "1. Я знаю расстояние и время за которое я хочу пробежать, но не знаю необходимую скорость для этого\n" +
                    "2. Я знаю расстояние и скорость с которой я хочу бежать, но не знаю сколько времени это займёт\n" +
                    "3. Я знаю сколько времени и с какой скоростью я хочу бежать, но не знаю какое расстояние я пробегу\n");
            String choice2 = scan.nextLine();
            if (RunningBot.StopWord(choice2)){
                break;
            }
            switch (choice2) {
                case "1" -> {
                    setHard_Training_distance(AskDistance(ASK_TRAINING_DISTANCE));
                    setHard_Training_Time(AskTime_Simple_Training(getHard_Training_distance()));
                    setHard_Training_TempoMS(PaceComputing(getHard_Training_distance(), getHard_Training_Time()));
                    KnownParameters = true;
                }
                case "2" -> {

                    setHard_Training_distance(AskDistance(ASK_TRAINING_DISTANCE));
                    setHard_Training_TempoMS(AskTempo_Simple_Training(getHard_Training_distance()));
                    setHard_Training_Time(TimeComputing(getHard_Training_distance(), getHard_Training_TempoMS()));
                    KnownParameters = true;
                }
                case "3" -> {
                    setHard_Training_Time(AskTime_Simple_Training(getHard_Training_distance()));
                    setHard_Training_TempoMS(AskTempo_Simple_Training(getHard_Training_distance()));
                    setHard_Training_distance(DistanceComputing(getHard_Training_Time(), getHard_Training_TempoMS()));
                    KnownParameters = true;
                }
                default -> System.out.println("Выберите из предложенных вариантов. 1 для расчета скорости, 2 для расчета времени," +
                        " 3 для расчета расстояния\n");
            }
            setTotal_distance((getWarmUp_CoolDown_distance() * 2) + getHard_Training_distance());
            set_Total_Time(TotalTimeComputing(getWarmUp_CoolDown_Time(),getHard_Training_Time()));
            setAverage_TempoMS(PaceComputing(getTotal_distance(), get_Total_Time()));
        } while(!KnownParameters);
    }
    public long AskDistance(String TypeOfDistanceQuestion){
        boolean CorrectDistance=false;
        long distance = 0;
        do {
            System.out.println(TypeOfDistanceQuestion);
            if (getHasWarmUpAndCoolDown() & TypeOfDistanceQuestion == ASK_TRAINING_DISTANCE)
                System.out.println("(Без учета разминки и заминки)");
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
    public ArrayList AskTime_Simple_Training(Long distance){
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
    public ArrayList AskTime_WarmUp_CoolDown(Long distance){
        boolean CorrectTime=false;
        ArrayList <Integer> HMSNumeric= new ArrayList<>();
        ArrayList <String> HMSinMassive = new ArrayList<>();
        do{
            try {
                Scanner scan = new Scanner(System.in);
                if (distance==0)
                    System.out.println("Сколько времени Вы хотите уделить разминке?\n " +
                            "(Такое же время будет установлено и для заминки)\n" +
                            "Введите 3 числа через пробел.\n" +
                            "Напиример 0 часов 35 минут 50 секунд нужно написать как 0 35 50");
                else
                    System.out.println("За какое время Вы хотите пробежать разминочные " + distance + " метров?\n" +
                            "(Такое же время будет установлено и для заминки)\n" +
                            "Введите 3 числа через пробел.\n" +
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
    public ArrayList AskTempo_Simple_Training(Long distance){
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
    public ArrayList AskTempo_WarmUp_CoolDown(Long distance){
        boolean CorrectTempo=false;
        ArrayList <Integer> TempoMS = new ArrayList<>();
        ArrayList<String> TempoinMassive = new ArrayList<>();
        do{
            try {
                Scanner scan = new Scanner(System.in);
                if (distance==0)
                    System.out.println("В каком темпе Вы хотите бежать на разминке?\n" +
                            "(Такой же темп будет установлен и для заминки)\n" +
                            "Введите 2 числа через пробел.\n" +
                            "Где первое число это минуты а второе секунды\n"+ " Напиример темп 4'30'' нужно написать как 4 30");
                else
                    System.out.println("В каком темпе Вы хотите бежать на разминке в " + distance + " метров?\n" +
                            "(Такой же темп будет установлен и для заминки)\n" +
                            "Введите 2 числа через пробел.\n" +
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
}