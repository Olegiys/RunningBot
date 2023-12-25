import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RunningBot {
    String choice;
    int choice2;
    //Бла-бла
    public void ChooseRunningType() throws IOException {
        boolean RunningTypeIsChosen;
        do {
            System.out.println("Какой вид бега вы планируете использовать?");
            System.out.println("Выберите из предложенных вариантов. Введите цифрцу 1 для Равномерного бега" +
                    " и цифру 2 для Интервального\n");
            System.out.println(" 1. Равномерный");
            System.out.println(" 2. Интервальный\n");
            System.out.print("Bыбepитe (q - выход): ");
            Scanner scan = new Scanner(System.in);
                choice = scan.nextLine();
                if (StopWord(choice)==true){
                    break;
                }
                else if (choice.equals("1")) {
                    KnownParameters();
                    break;
                } else if (choice.equals("2")) {
                    System.out.println("В данный момент, к сожалению, данная функция не доступна\n");
                    RunningTypeIsChosen = true;
                }
                else {
                    System.out.println("Выберите из предложенных вариантов. Введите цифрцу 1 для Равномерного бега" +
                            " и цифру 2 для Интервального\n");
                    RunningTypeIsChosen = true;
                }
        } while (RunningTypeIsChosen == true);
    }
    public void KnownParameters() throws IOException {
        boolean KnownParameters = false;
        do {
                System.out.println("Что Вы знаете о своей будущей тренировке?");
                System.out.println(" 1. Я знаю расстояние и время за которое я хочу пробежать," +
                        " но не знаю необходимую скорость для этого");
                System.out.println(" 2. Я знаю расстояние и скорость с которой я хочу бежать," +
                        " но не знаю сколько времени это займёт");
                System.out.println(" 3. Я знаю сколько времени и с какой скоростью я хочу бежать, " +
                        "но не знаю какое расстояние я пробегу\n");
                Scanner scan2 = new Scanner(System.in);
                try {
                    choice2 = scan2.nextInt();
                    KnownParameters = true;
                } catch (Exception e) {}
            switch (choice2) {
                case 1:
                    long distance= Distance();
                    ArrayList <Integer> TimeHMS;
                    TimeHMS=Time(distance);
                    PaceComputing(distance,TimeHMS.get(0),TimeHMS.get(1),TimeHMS.get(2));
                    KnownParameters = true;
                    break;
                case 2:
                    long distance1= Distance();
                    ArrayList <Integer> TempoHM;
                    TempoHM=Tempo(distance1);
                    TempoComputing(distance1,TempoHM.get(0), TempoHM.get(1));
                    KnownParameters = true;
                    break;
                case 3:
                    ArrayList <Integer> TimeHMS1;
                    TimeHMS1=Time((long) 0);
                    ArrayList <Integer> TempoHM1;
                    TempoHM1=Tempo((long) 0);
                    DistanceComputing(TimeHMS1.get(0),TimeHMS1.get(1),TimeHMS1.get(2),TempoHM1.get(0),TempoHM1.get(1));
                    KnownParameters = true;
                    break;
                default:
                    System.out.println("Выберите из предложенных вариантов. 1 для расчета скорости, 2 для расчета времени," +
                            " 3 для расчета расстояния\n");
                    KnownParameters = false;
                    break;
            }
        } while(KnownParameters == false);
    }
    public long Distance(){
        boolean WrongDistance=false;
        long distance = 0;
        do {
            System.out.println("Какое расстояние Вы хотите пробежать? Введите расстояние в метрах");
            Scanner scan3 = new Scanner(System.in);
            try {
                distance = scan3.nextLong();
                System.out.println(distance);
                WrongDistance=false;
            } catch (Exception e) {
                System.out.println("Введите число без запятых, точек и других разделителей.");
                WrongDistance = true;
            }
        } while (WrongDistance == true);
        return distance;
    }
    public ArrayList Time(Long distance){
        boolean WrongTime=false;
        ArrayList <Integer> HMSNumeric = new ArrayList<>();
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

                ArrayList<String> HMSinMassive = new ArrayList<>(Arrays.asList(HMSwithBlanks.split(" ")));
                HMSNumeric = new ArrayList<>(HMSinMassive.size()) ;
                for (String myInt : HMSinMassive)
                {
                    HMSNumeric.add(Integer.valueOf(myInt));
                }
                if (HMSNumeric.size()<3) {
                    System.out.println("Вы ввели недостаточное количество параметров. Вы ввели только:" + HMSNumeric);
                    HMSNumeric.clear();
                    WrongTime=true;
                }
                else if (HMSNumeric.size()>3){
                    System.out.println("Вы ввели чрезмерное количество параметров. Вы ввели:" + HMSNumeric);
                    HMSNumeric.clear();
                    WrongTime=true;
                }
                else {
                    System.out.println("Вы ввели все корректно");
                    WrongTime=false;
                }
            } catch (Exception t) {
                //t.printStackTrace();
                System.out.println("Каждое вводимое число должно быть натуральным и ввод должен соответствовать шаблону");
                HMSNumeric.clear();
                WrongTime=true;
            }
        } while (WrongTime==true);
        return HMSNumeric;
    }
    public ArrayList Tempo(Long distance){
        boolean WrongTempo=false;
        ArrayList <Integer> TempoMS = new ArrayList<>();
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

                ArrayList<String> TempoinMassive = new ArrayList<>(Arrays.asList(TempoMSwithBlanks.split(" ")));
                TempoMS = new ArrayList<>(TempoinMassive.size()) ;
                for (String myInt : TempoinMassive)
                {
                    TempoMS.add(Integer.valueOf(myInt));
                }
                if (TempoMS.size()<2) {
                    System.out.println("Вы ввели недостаточное количество параметров. Вы ввели только:" + TempoMS);
                    TempoMS.clear();
                    WrongTempo=true;
                }
                else if (TempoMS.size()>2){
                    System.out.println("Вы ввели чрезмерное количество параметров. Вы ввели:" + TempoMS);
                    TempoMS.clear();
                    WrongTempo=true;
                }
                else {
                    System.out.println("Вы ввели все корректно");
                    WrongTempo=false;
                }
            } catch (NumberFormatException t) {
                t.printStackTrace();
                System.out.println("Каждое вводимое число должно быть натуральным и ввод должен соответствовать шаблону");
                TempoMS.clear();
                WrongTempo=true;
            }
        } while (WrongTempo==true);
        return TempoMS;
    }
    public void PaceComputing(long distance, int Hours, int Minutes, int Seconds ){

        double minutes = (Hours * 60) + Minutes + ((double) Seconds / 60);
        double tempo = (minutes / (double) distance) * 1000;
        int tempTempo = (int) tempo;
        double tempo2 = tempo - tempTempo;
        int sec = (int) (0.6 * (tempo2 * 100));
        System.out.println("Для того что бы пробежать " + distance + " м. за " + Hours + " ч. "
                + Minutes + " мин. " + Seconds + " сек. , Вам нужно бежать в темпе " + tempTempo + "'" + sec + "'' на киллометр");
    }
    public void TempoComputing(long distance, int Minutes, int Seconds ) {
        double timeFor1km = Minutes + (double) Seconds / 60;
        double BruttoTime = (distance * timeFor1km) / 1000;
        int Hours = (int) BruttoTime / 60;
        if (BruttoTime >= 60) {
            double Bruttominutes= BruttoTime%60;
            int NettoMinutes= (int) Bruttominutes;
            double BruttoSec=Bruttominutes-NettoMinutes;
            int Nettosec= (int) (0.6*(BruttoSec*100));
            System.out.println("Вам потребуется " + Hours +" часов " + NettoMinutes + " минут и " + Nettosec
                    +" секунд для того чтобы пробежать " + distance + " метров в темпе " + Minutes+"'"+Seconds+"''");
        }
        else {
            int NettoMinutes= (int) BruttoTime;
            double BruttoSec=BruttoTime-NettoMinutes;
            int sec= (int) (0.6*(BruttoSec*100));
            int Nettosec= (int) (0.6*(BruttoSec*100));
            System.out.println("Вам потребуется "+ NettoMinutes + " минут и " + sec
                    +" секунд для того чтобы пробежать " + distance + " метров в темпе " + Minutes+"'"+Seconds+"''");
        }

    }
    public void DistanceComputing(int TimeHours, int TimeMinutes, int TimeSeconds, int TempoMinute, int TempoSeconds){
        double minutes = (TimeHours * 60) + TimeMinutes + ((double) TimeSeconds / 60);
        double timeFor1km = TempoMinute + (double) TempoSeconds / 60;
        int distance = (int) ((minutes/timeFor1km)*1000);
        System.out.println("Вы пробежите " + distance + " метров если будете бежать " + TimeHours+ " часов "+ TimeMinutes+
                " минут и "+ TimeSeconds + " секунд в темпе " + TempoMinute+"'"+TempoSeconds+"''" );
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
    public static void main(String[] args) throws IOException {
        RunningBot NewRequest = new RunningBot();
        NewRequest.ChooseRunningType();
    }
}