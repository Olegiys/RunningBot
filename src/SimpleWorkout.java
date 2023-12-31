import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SimpleWorkout {
    private long Total_distance = 0;
    private ArrayList<Integer> Time = new ArrayList<>();
    private ArrayList <Integer> Average_TempoMS = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);
    static String choice2;
    protected String ASK_SIMPLE_PARAMETERS = "Что Вы знаете о своей будущей тренировке?\n" +
            "1. Я знаю расстояние и время за которое я хочу пробежать, но не знаю необходимую скорость для этого\n" +
            "2. Я знаю расстояние и скорость с которой я хочу бежать, но не знаю сколько времени это займёт\n" +
            "3. Я знаю сколько времени и с какой скоростью я хочу бежать, но не знаю какое расстояние я пробегу\n";

    public SimpleWorkout () {}
    public void setTotal_distance(long total_distance) {
        Total_distance = total_distance;
    }

    public void setTime(ArrayList<Integer> time) {
        Time = time;
    }

    public void setAverage_TempoMS(ArrayList<Integer> average_TempoMS) {
        Average_TempoMS = average_TempoMS;
    }

    public long getTotal_distance() {
        return Total_distance;
    }

    public ArrayList<Integer> getTime() {
        return Time;
    }

    public ArrayList<Integer> getAverage_TempoMS() {
        return Average_TempoMS;
    }

    public long DistanceComputing(ArrayList<Integer> HMSNumeric, ArrayList<Integer> TempoMS){
        double minutes = (HMSNumeric.get(0) * 60) + HMSNumeric.get(1) + ((double) HMSNumeric.get(2) / 60);
        double timeFor1km = TempoMS.get(0) + (double) TempoMS.get(1) / 60;
        long distance = (int) ((minutes/timeFor1km)*1000);
        System.out.println("Вы пробежите " + distance + " метров если будете бежать " + HMSNumeric.get(0)+ " часов "+ HMSNumeric.get(1)+
                " минут и "+ HMSNumeric.get(2) + " секунд в темпе " + TempoMS.get(0)+"'"+TempoMS.get(1)+"''" );
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
            System.out.println("Вам потребуется " + Hours +" часов " + NettoMinutes + " минут и " + Nettosec
                    +" секунд для того чтобы пробежать " + distance + " метров в темпе " + TempoMS.get(0)+"'"+TempoMS.get(1)+"''");
        }
        else {
            NettoMinutes= (int) BruttoTime;
            double BruttoSec=BruttoTime-NettoMinutes;
            Nettosec= (int) (0.6*(BruttoSec*100));
            System.out.println("Вам потребуется "+ NettoMinutes + " минут и " + Nettosec
                    +" секунд для того чтобы пробежать " + distance + " метров в темпе " + TempoMS.get(0)+"'"+TempoMS.get(1)+"''");
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
        System.out.println("Для того что бы пробежать " + distance + " м. за " + HMSNumeric.get(0) + " ч. "
                + HMSNumeric.get(1) + " мин. " + HMSNumeric.get(2) + " сек. , Вам нужно бежать в темпе " + tempTempo + "'" + sec + "'' на киллометр");
        ArrayList <Integer> TempoMS = new ArrayList<>();
        TempoMS.add(tempTempo);
        TempoMS.add(sec);
        return TempoMS;
    }
    public void AskKnownParameters(String ASK_SIMPLE_PARAMETERS) {
        boolean KnownParameters = false;
        do {
            System.out.println(ASK_SIMPLE_PARAMETERS);
            try {
                choice2 = scan.nextLine();
            } catch (Exception e) {}
            if (RunningBot.StopWord(choice2)){
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
}