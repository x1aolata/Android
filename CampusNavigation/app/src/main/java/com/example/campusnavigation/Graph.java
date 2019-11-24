package com.example.campusnavigation;


import android.util.Log;

public class Graph {
    // 最大支持节点数量
    public static final int MAX = 100;
    public String[] Place = {"体检中心", "操场", "校门北口", "银杏景观", "邯郸音乐厅", "图书馆", "餐厅", "信息学部", "花园景观", "校门东口", "网计学院", "校门南口"};

    public int numberOfNodes;

    public int[][] MAP = new int[MAX][MAX];


    /**
     * 邻接矩阵的初始化
     */
    public void init() {

        numberOfNodes = Place.length;

        // 初始化邻接矩阵
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                if (i == j) MAP[i][j] = 0;
                else
                    MAP[i][j] = Integer.MAX_VALUE/3;
            }
        }
        // 边赋值
        {

            MAP[0][1] = 350;
            MAP[0][4] = 200;

            MAP[1][0] = 350;
            MAP[1][2] = 200;
            MAP[1][4] = 480;
            MAP[1][5] = 280;

            MAP[2][1] = 200;
            MAP[2][3] = 100;
            MAP[2][6] = 100;

            MAP[3][2] = 100;
            MAP[3][6] = 100;

            MAP[4][0] = 200;
            MAP[4][1] = 480;
            MAP[4][5] = 400;
            MAP[4][7] = 500;
            MAP[4][10] = 500;

            MAP[5][1] = 280;
            MAP[5][4] = 400;
            MAP[5][8] = 160;
            MAP[5][9] = 300;

            MAP[6][2] = 100;
            MAP[6][3] = 100;
            MAP[6][9] = 100;

            MAP[7][4] = 500;
            MAP[7][8] = 150;
            MAP[7][11] = 400;

            MAP[8][5] = 160;
            MAP[8][7] = 150;
            MAP[8][9] = 200;
            MAP[8][11] = 500;

            MAP[9][5] = 300;
            MAP[9][6] = 100;
            MAP[9][8] = 200;
            MAP[9][11] = 600;

            MAP[10][4] = 500;
            MAP[10][11] = 400;

            MAP[11][8] = 500;
            MAP[11][7] = 400;
            MAP[11][10] = 400;
            MAP[11][9] = 600;
        }

    }

    /**
     * Floyd算法求解最短路径
     *
     * @param startingPosition
     * @param destination
     * @return Minimum distance between startingPosition and destination
     */
    public int Floyd(int startingPosition, int destination) {
        init();
        int[][] Distance = MAP.clone();
        int[][] P = new int[numberOfNodes][numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                P[i][j] = j;
            }
        }
        for (int k = 0; k < numberOfNodes; k++) {
            for (int i = 0; i < numberOfNodes; i++) {
                for (int j = 0; j < numberOfNodes; j++) {
//                    int temp = (Distance[i][k] == -1 || Distance[k][j] == -1) ? Integer.MAX_VALUE : (Distance[i][k] + Distance[k][j]);
                    if (Distance[i][j] > (Distance[i][k] + Distance[k][j])) {
                        Distance[i][j] = Distance[i][k] + Distance[k][j];
                        P[i][j] = k;
                    }
                }
            }
        }
        return Distance[startingPosition][destination];
    }

    public int Floyd(String startingPosition, String destination) {
        int startingPositionIndex = 0;
        int destinationIndex = 0;

        for (int i = 0; i < numberOfNodes; i++) {
            if (Place[i].equals(startingPosition))
                startingPositionIndex = i;
            if (Place[i].equals(destination))
                destinationIndex = i;
        }
        Log.d("x1aolata", "Floyd: "+destinationIndex+" " +startingPositionIndex);
        return Floyd(startingPositionIndex, destinationIndex);
    }

    public String[] getPlace() {
        return Place;
    }
}
