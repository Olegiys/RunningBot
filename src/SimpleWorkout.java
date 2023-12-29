import java.util.ArrayList;

public class SimpleWorkout {
    private long Total_distance = 0;
    private ArrayList<Integer> Time = new ArrayList<>();
    private ArrayList <Integer> Average_TempoMS = new ArrayList<>();

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
}