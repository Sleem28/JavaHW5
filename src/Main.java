import Classes.DiscreteWorkField;
import Classes.WavesAlgo;

public class Main {

    public static void main(String[] args) {
        DiscreteWorkField wf = new DiscreteWorkField();
        int[][] workField = wf.getWorkField();
        System.out.println("Поиск кратчайшего маршрута на поле 20 х 15.");
        System.out.println(" * - Старт, / - Финиш\n");

        WavesAlgo wAlgo = new WavesAlgo(workField);

        int[][] result = wAlgo.getWorkField();
        wf.printWorkField(result);
    }

}
