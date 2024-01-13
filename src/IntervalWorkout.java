import java.util.Scanner;

public class IntervalWorkout extends SimpleWorkout {
    protected String ASK_HARD_INTERVAL_PARAMETERS = "На чем будут основаны ваши быстрые отрезки?\n" +
            "Выберите минимум 2 параметра\n" +
            "Если для бысторого бега вы хотите взять за основу Рсстояние и Время, то введите: 23 или 32 \n" +
            "1. Темп\n" +
            "2. Время\n" +
            "3. Расстояние\n";
    protected String ASK_HARD_INTERVAL_DISTANCE = "Какое расстояние каждого быстрого интервала? Введите расстояние в метрах";
    @Override
    public void AskKnownParameters_Training() {
        boolean KnownParameters = false;
        do {
            System.out.println("Что Вы знаете о каждом быстром интервале?\n" +
                    "1. Я знаю расстояние и время\n" +
                    "2. Я знаю расстояние и темп\n" +
                    "3. Я знаю время и темп\n");
            try {
                choice2 = scan.nextLine();
            } catch (Exception e) {}
            if (RunningBot.StopWord(choice2)){
                break;
            }
            switch (choice2) {
                case "1" -> {
                    setTotal_distance(AskDistance(ASK_HARD_INTERVAL_DISTANCE));
                    set_Total_Time(AskTime(getTotal_distance()));
                    setAverage_TempoMS(PaceComputing(getTotal_distance(), get_Total_Time()));
                    KnownParameters = true;
                }
                case "2" -> {

                    setTotal_distance(AskDistance(ASK_HARD_INTERVAL_DISTANCE));
                    setAverage_TempoMS(AskTempo(getTotal_distance()));
                    set_Total_Time(TimeComputing(getTotal_distance(), getAverage_TempoMS()));
                    KnownParameters = true;
                }
                case "3" -> {
                    set_Total_Time(AskTime(getTotal_distance()));
                    setAverage_TempoMS(AskTempo(getTotal_distance()));
                    setTotal_distance(DistanceComputing(get_Total_Time(), getAverage_TempoMS()));
                    KnownParameters = true;
                }
                default -> System.out.println("Выберите из предложенных вариантов. 1 для расчета скорости, 2 для расчета времени," +
                        " 3 для расчета расстояния\n");
            }
        } while(!KnownParameters);
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
}
