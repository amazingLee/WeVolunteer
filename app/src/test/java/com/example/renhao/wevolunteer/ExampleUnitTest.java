package com.example.renhao.wevolunteer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        int[] nums = {0, 0, 2, 5, 7, 8, 0, 0, 0,
                0, 0, 0, 0, 0, 1, 8, 0, 0,
                0, 6, 0, 0, 0, 0, 0, 0, 2,
                1, 5, 0, 2, 0, 6, 0, 0, 9,
                8, 0, 0, 0, 0, 0, 0, 0, 1,
                3, 0, 0, 1, 0, 7, 0, 8, 4,
                2, 0, 0, 0, 0, 0, 0, 7, 0,
                0, 0, 5, 9, 0, 0, 0, 0, 0,
                0, 0, 0, 7, 1, 4, 2, 0, 0};
        //先把空位取出来
        List<Integer> empty = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                empty.add(i);
            }
        }
        //一个个判断空位的唯一值
        for (int i = 0; i < empty.size(); i++) {
            int position = empty.get(i);
            nums[position] = getNum(nums, position);
        }

        for (int i = 0; i < nums.length; i++)//打印
        {
            if (i % 9 == 0)
                System.out.print("\n");
            System.out.print(nums[i] + "  ");
        }
    }

    private int getNum(int[] nums, int position) {
        List<Integer> maybe = new ArrayList<>();
        //判断在第几行第几列
        int h = position == 0 ? 1 : (position / 9) + 1;
        int l = position == 0 ? 1 : (position % 9) + 1;
        for (int i = 1; i < 10; i++) {//九个数依次进行判断
            boolean match = false;
            //横
            for (int j = 0; j < 9; j++) {
                int starPosition = (h - 1) * 9;//开始位置
                if (nums[(starPosition + (j))] == i) {
                    match = true;
                    break;
                }
            }
            if (match)
                break;
            //竖
            for (int j = 0; j < 9; j++) {
                int starPosition = l - 1;//开始位置
                if (nums[(starPosition + (j * 9))] == i) {
                    match = true;
                    break;
                }
            }
            if (match)
                break;
            //方格
            for (int j = 0; j < 3; j++) {//方格第一行
                int starPosition = (h - ((h - 1) % 3)) * 9 + (l - ((l - 1) % 3));//开始位置
                if (nums[(starPosition + (j))] == i) {
                    match = true;
                    break;
                }
            }
            if (match)
                break;
            for (int j = 0; j < 3; j++) {//方格第一行
                int starPosition = (h - ((h - 1) % 3)) * 9 + (l - ((l - 1) % 3)) + 9;//开始位置
                if (nums[(starPosition + (j))] == i) {
                    match = true;
                    break;
                }
            }
            if (match)
                break;
            for (int j = 0; j < 3; j++) {//方格第一行
                int starPosition = (h - ((h - 1) % 3)) * 9 + (l - ((l - 1) % 3)) + 18;//开始位置
                if (nums[(starPosition + (j))] == i) {
                    match = true;
                    break;
                }
            }
            if (match)
                break;
            maybe.add(i);
        }
        return maybe.size() == 1 ? maybe.get(0) : 0;
    }
}