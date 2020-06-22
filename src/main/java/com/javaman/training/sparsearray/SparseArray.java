package com.javaman.training.sparsearray;

import lombok.extern.slf4j.Slf4j;

/**
 * @author pengzhe
 * @date 2020/5/12 23:28
 * @description 稀疏数组
 */
@Slf4j
public class SparseArray {
    public static void main(String[] args) {
        /**
         * 创建一个二维数组 11*11
         * 0:表示没有棋子  1:黑子 2:篮子
         */
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;
        //输出原始的二维数组
        log.info("原始二维数组");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        //将二维数组转稀疏数组
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1.length; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        log.info("有效数字::{}", sum);
        //创建对应的稀疏数组
        int[][] sparseArray = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;

        //遍历二维数组,将非零值存放到稀疏数组中
        int count = 0;//count用于记录是第几个非零数据
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1.length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = i;
                    sparseArray[count][2] = chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组
        System.out.println();
        log.info("得到的稀疏数组：：");
        for (int i = 0; i < sparseArray.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
        }
        System.out.println();

        /**
         * 将稀疏数组恢复成原始的二维数组
         * 1:先读取稀疏数组第一行,根据第一行数据,创建原始的二维数组,比如上面的int[][] chessArr1 = new int[11][11];
         * 2:在读取稀疏数组后几行的数据,并赋给原始的二维数组即可
         */

        int[][] chessArray2 = new int[sparseArray[0][0]][sparseArray[0][1]];
        //在读取稀疏数组后几行的数据(从第二行开始)
        for (int i = 1; i < sparseArray.length; i++) {
            chessArray2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        //输出恢复后的二维数组
        System.out.println();
        log.info("恢复后的二维数组");
        for (int[] row : chessArray2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
