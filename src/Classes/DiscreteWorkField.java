package Classes;

import java.util.Arrays;

public class DiscreteWorkField {
    private final int[][] workField;

    public DiscreteWorkField(){
        int fieldHeight = 15;
        int fieldWith = 20;
        this.workField = new int[fieldHeight][fieldWith];
        addFreeCell(this.workField);
        addWalls(this.workField);
        addStartFinish(this.workField);
    }

    public int[][] getWorkField() {
        return workField;
    }

    public void printWorkField(int[][] workField){
        for (int[] i: workField){
            for(int j: i){
                System.out.print(getSymbol(j));
            }
            System.out.println();
        }
    }

    void addFreeCell(int[][] workField){
        for(int i = 0; i < workField.length; i++){
            Arrays.fill(workField[i], -2);
        }
    }
    static String getSymbol(int number){
        String sNumber = String.valueOf(number);
        String tail = sNumber.length() == 1? " ": "";
        switch (number){
            case (-4) -> {return " X ";}
            case (-3) -> {return "WWW";}
            case (0) -> {return " * ";}
            case (-1) -> {return " / ";}
            default -> {return "   ";}
        }
    }

    static void addStartFinish(int[][] workField){
        workField[14][7] = -2; // Финиш
        workField[0][8] = 0; // Старт
    }
    static void addWalls(int[][] workField){ // Добавляем стенки на поле
        for (int row = 0; row < workField.length; row++){
            if(row == 2){
                for (int j = 3; j < 12; j++) {
                    workField[row][j] = -3;
                }
            }

            if(row == 6){
                for (int j = 7; j < 20; j++) {
                    workField[row][j] = -3;
                }
            }

            if(row == 5){
                for (int j = 0; j < 6; j++) {
                    workField[row][j] = -3;
                }
            }

            if(row == 4){
                for (int j = 7; j < 15; j++) {
                    workField[row][j] = -3;
                }
            }

            if(row == 9){
                for (int j = 0; j < 7; j++) {
                    workField[row][j] = -3;
                }
            }

            if(row == 12){
                for (int j = 4; j < 10; j++) {
                    workField[row][j] = -3;
                }
            }
        }
    }
}
