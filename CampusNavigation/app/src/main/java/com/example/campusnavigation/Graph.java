package com.example.campusnavigation;


import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 单例类Graph
 * 储存图的所有信息
 */
public class Graph {

    // 单例类,以防出现二义性
    private static Graph graph;

    private Graph() {
    }

    public static Graph getInstance() {
        if (graph == null) {
            graph = new Graph();
        }
        return graph;
    }

    final private int MAX = 999;
    public List<Node> Nodes = new ArrayList<Node>(); // 地点List

    // 初始化地点数据
    {
        Nodes.add(new Node("体检中心", "0", 115.568463, 38.88999, "体检中心贼棒"));
        Nodes.add(new Node("操场", "1", 115.572838, 38.891099, "操场"));
        Nodes.add(new Node("校门北口", "2", 115.575169, 38.889526, "校门北口"));
        Nodes.add(new Node("银杏景观", "3", 115.577738, 38.889031, "银杏景观"));
        Nodes.add(new Node("邯郸音乐厅", "4", 115.56897, 38.889411, "邯郸音乐厅"));
        Nodes.add(new Node("图书馆", "5", 115.572595, 38.887894, "图书馆"));
        Nodes.add(new Node("餐厅", "6", 115.576009, 38.888122, "餐厅"));
        Nodes.add(new Node("信息学部", "7", 115.571207, 38.887059, "信息学部"));
        Nodes.add(new Node("花园景观", "8", 115.572303, 38.886111, "花园景观"));
        Nodes.add(new Node("校门东口", "9", 115.574818, 38.886585, "校门东口"));
        Nodes.add(new Node("网计学院", "10", 115.569518, 38.885668, "网计学院"));
        Nodes.add(new Node("校门南口", "11", 115.571894, 38.883077, "校门南口"));
        // Nodes.add(new Node("馨宁宿舍楼", "12", 115.580702, 38.889632, "馨宁宿舍楼"));
    }


    public double[][] MAP = new double[MAX][MAX];  // 邻接矩阵
    public double[][] Distance = new double[MAX][MAX]; // 距离矩阵
    public int[][] P;  // P矩阵


    {
        // 初始化邻接矩阵

        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                if (i == j) MAP[i][j] = 0;
                else
                    MAP[i][j] = Double.POSITIVE_INFINITY;
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

        init();
    }


    /**
     * 邻接矩阵的初始化
     */
    public void init() {


// 深拷贝
        // 打印矩阵MAP
        for (int i = 0; i < Nodes.size(); i++) {
            Log.d("x1aolata", "MAP: " + Arrays.toString(MAP[i]));
        }
        for (int i = 0; i < MAX; i++) {
            Distance[i] = MAP[i].clone();
        }
//        Distance = MAP;
        P = new int[Nodes.size()][Nodes.size()];


        // Floyd算法求解最短路径
        for (int i = 0; i < Nodes.size(); i++) {
            for (int j = 0; j < Nodes.size(); j++) {
                P[i][j] = j;
            }
        }

        for (int k = 0; k < Nodes.size(); k++) {
            for (int i = 0; i < Nodes.size(); i++) {
                for (int j = 0; j < Nodes.size(); j++) {
//                    int temp = (Distance[i][k] == -1 || Distance[k][j] == -1) ? Integer.MAX_VALUE : (Distance[i][k] + Distance[k][j]);
                    if (Distance[i][j] > (Distance[i][k] + Distance[k][j])) {
                        Distance[i][j] = Distance[i][k] + Distance[k][j];
                        P[i][j] = P[i][k];
                    }
                }
            }
        }

        // 打印矩阵MAP
        for (int i = 0; i < Nodes.size(); i++) {
            Log.d("x1aolata", "MAP: " + Arrays.toString(MAP[i]));
        }
        // 打印矩阵P
        for (int i = 0; i < Nodes.size(); i++) {
            Log.d("x1aolata", "P: " + Arrays.toString(P[i]));
        }

    }


    /**
     * Floyd算法求解最短路径
     *
     * @param startingPosition
     * @param destination
     * @return Minimum distance between startingPosition and destination
     */
    public double Floyd(int startingPosition, int destination) {
        init();
        return Distance[startingPosition][destination];
    }

    public double Floyd(String startingPosition, String destination) {
        int startingPositionIndex = 0;
        int destinationIndex = 0;
        for (int i = 0; i < Nodes.size(); i++) {
            if (Nodes.get(i).getName().equals(startingPosition))
                startingPositionIndex = i;
            if (Nodes.get(i).getName().equals(destination))
                destinationIndex = i;
        }
        // Log.d("x1aolata", "Floyd: " + destinationIndex + " " + startingPositionIndex);
        return Floyd(startingPositionIndex, destinationIndex);
    }


    /**
     * 最短路径
     *
     * @param startingPosition
     * @param destination
     * @return
     */
    public List<Integer> getPath(int startingPosition, int destination) {
        init();
        List<Integer> route = new ArrayList<Integer>();
        int i, j;
        i = startingPosition;
        j = destination;
        route.add(startingPosition);
        while (i != destination) {
            route.add(P[i][j]);
            i = P[i][j];
        }
        return route;
    }

    public List<Integer> getPath(String startingPosition, String destination) {
        int startingPositionIndex = 0;
        int destinationIndex = 0;
        for (int i = 0; i < Nodes.size(); i++) {
            if (Nodes.get(i).getName().equals(startingPosition))
                startingPositionIndex = i;
            if (Nodes.get(i).getName().equals(destination))
                destinationIndex = i;
        }
        return getPath(startingPositionIndex, destinationIndex);
    }


    public String[] getPlace() {
        String[] place = new String[Nodes.size()];
        for (int i = 0; i < Nodes.size(); i++) {
            place[i] = Nodes.get(i).getName();
        }
        return place;
    }

    public List<Node> getNodes() {
        return Nodes;
    }

    public String getAbout(String name) {
        for (Node node : Nodes
        ) {
            if (node.getName().equals(name))
                return node.getAbout();

        }
        return "啥也没有";
    }

    public Node getNode(String name) {
        for (Node node : Nodes
        ) {
            if (node.getName().equals(name))
                return node;

        }
        return null;
    }


    public int getIndex(String name) {
        for (int i = 0; i < Nodes.size(); i++) {
            if (Nodes.get(i).getName().equals(name))
                return i;
        }
        return -1;
    }

    // 删除地点
    public void DeleteNode(String name) {
        int index = getIndex(name);

        // 删除行
        for (int i = index; i < MAX - 1; i++) {
            for (int j = 0; j < MAX; j++) {
                MAP[i][j] = MAP[i + 1][j];
            }
        }
        for (int i = index; i < MAX - 1; i++) {
            for (int j = 0; j < MAX; j++) {
                MAP[j][i] = MAP[j][i + 1];
            }
        }
        Nodes.remove(index);
    }
}
