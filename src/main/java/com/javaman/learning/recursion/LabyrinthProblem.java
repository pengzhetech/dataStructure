package com.javaman.learning.recursion;

import lombok.extern.slf4j.Slf4j;

/**
 * @author pengzhe
 * @date 2020/5/23 18:23
 * @description 迷宫问题
 */
@Slf4j
public class LabyrinthProblem {
    public static void main(String[] args) {
        //创建一个二维数组,模拟迷宫
        //地图
        int[][] map = new int[8][7];
        //使用[1][1]表示墙
        //上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        //设挡板 1表示
        map[3][1] = 1;
        map[3][2] = 1;
        // map[1][2] = 1;
        // map[2][2] = 1;

        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                // log.info("map[i][j]:{}", map[i][j]);
                //
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        setWay2(map, 1, 1);
        //输出新的地图,小球走过,并标识过的地图
        //输出地图
        log.info("小球走过,并标识的地图");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                // log.info("map[i][j]:{}", map[i][j]);
                //
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }

    //使用递归回溯来给小球找路

    /**
     * @param map 地图
     * @param i   从哪个位置开始找
     * @param j
     * @return 是否找到
     * 说明:map表示地图
     * i,j表示从地图哪个位置开始找(1,1)
     * 如果小球能到达map[6][5]位置,则说明通路找到
     * 约定:当地图的i,j为0时,表示该点没有走过,当为1时表示墙,不能走,为2时表示是一个通路,可以走
     * 3表示已该位置已探测过,但是走不通
     * 在走迷宫时,需要确定一个策略(方法),先走下面,--》右边---》上---》左,如果该点走不通,在回溯
     * <p>
     * 最短路径问题??
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            //说明通路已经找到
            return true;
        } else {
            //如果当前这个点还没有走
            if (map[i][j] == 0) {
                //按照策略走
                map[i][j] = 2;//假定该点是可以走通的
                if (setWay(map, i + 1, j)) {//向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {//向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {//向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {//向左走
                    return true;
                } else {
                    map[i][j] = 3;//说明该点是走不通的,是死路
                }
            } else {//如果map[i][j]不等于0;可能是1,2,3
                return false;
            }
        }
        return false;
    }


    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            //说明通路已经找到
            return true;
        } else {
            //如果当前这个点还没有走
            if (map[i][j] == 0) {
                //按照策略走
                map[i][j] = 2;//假定该点是可以走通的
                if (setWay2(map, i - 1, j)) {//向上走
                    return true;
                } else if (setWay2(map, i, j + 1)) {//向右走
                    return true;
                } else if (setWay2(map, i + 1, j)) {//向下走
                    return true;
                } else if (setWay2(map, i, j - 1)) {//向左走
                    return true;
                } else {
                    map[i][j] = 3;//说明该点是走不通的,是死路
                }
            } else {//如果map[i][j]不等于0;可能是1,2,3
                return false;
            }
        }
        return false;
    }
}
