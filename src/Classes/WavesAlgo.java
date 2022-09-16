package Classes;

public class WavesAlgo {
    private final int[][] workField;
    private final int fieldHeight;
    private final int fieldWidth;
    private final int xStart;
    private final int yStart;
    private final int xFinish;
    private final int yFinish;
    private int[] xSlice;
    private int[] ySlice;
    private boolean finishIsFound;
    private int counter;
    private int emptyCell;
    private int wall;


    public WavesAlgo(int[][] workField) {
        this.workField = workField;
        this.fieldHeight = workField.length;
        this.fieldWidth = workField[0].length;
        this.xStart = 8;
        this.yStart = 0;
        this.xFinish = 8;
        this.yFinish = 14;
        this.xSlice = new int[]{1, 0, -1, 0};
        this.ySlice = new int[]{0, 1, 0, -1};

        this.counter = 0;
        this.emptyCell = -2;
        this.wall = -3;
        this.finishIsFound = false;
        wavePropagation(yStart, xStart);
    }

    public int[][] getWorkField() {
        return workField;
    }

    void wavePropagation(int y, int x) {
        workField[y][x] = 0;
        do {
            finishIsFound = true;
            for (int i = 0; i < fieldHeight; i++) {
                for (int j = 0; j < fieldWidth; j++) {
                    if(workField[i][j] == counter){ // Старт найден
                        for (int k = 0; k < xSlice.length; k++) {
                            int yBias = i + ySlice[k], xBias = j + xSlice[k];
                            if(yBias >= 0 && yBias < fieldHeight &&
                                    xBias >= 0 && xBias < fieldWidth &&
                                    workField[yBias][xBias] == emptyCell){
                                finishIsFound = false;
                                workField[yBias][xBias] = counter+1;
                            }
                        }
                    }
                }
            }
            counter++;
        }while (!finishIsFound && workField[yFinish][xFinish] == emptyCell);

        if(workField[yFinish][xFinish] != emptyCell){
            findRoute();
        }else System.out.println("Маршрут не может быть построен!!!");
    }

    void findRoute(){
        int[] xCoordArray = new int[(fieldHeight + fieldWidth)*2];
        int[] yCoordArray = new int[(fieldHeight + fieldWidth)*2];
        int finish = workField[yFinish][xFinish];
        int xCoord = xFinish;
        int yCoord = yFinish;
        int curNum = finish;

        while (curNum > 0){
            xCoordArray[curNum] = xCoord;
            yCoordArray[curNum] = yCoord;
            curNum--;

            for (int k = 0; k < xSlice.length; k++) {
                int yBias = yCoord + ySlice[k], xBias = xCoord + xSlice[k];
                if(yBias >= 0 && yBias < fieldHeight &&
                        xBias >= 0 && xBias < fieldWidth &&
                        workField[yBias][xBias] == curNum){
                    xCoord += xSlice[k];
                    yCoord += ySlice[k];
                    break;
                }
            }
        }

        for (int i = 0; i < xCoordArray.length; i++) {
            if(yCoordArray[i] != 0 && xCoordArray[i] != 0)
                workField[yCoordArray[i]][xCoordArray[i]] = -4;
        }

        workField[yFinish][xFinish] = -1;
    }
}
