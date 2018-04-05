package com.tangzq.identicon.generator.impl;

import com.google.common.base.Preconditions;
import com.google.common.math.DoubleMath;
import com.tangzq.identicon.generator.IBaseGenartor;
import org.apache.commons.lang.StringUtils;

import java.awt.*;
import java.math.RoundingMode;

/**
 * Author: Bryant Hang
 * Date: 15/1/10
 * Time: 下午2:44
 */
public class DefaultGenerator implements IBaseGenartor {
    private String hash;
    private boolean[][] booleanValueArray;

    @Override
    public boolean[][] getBooleanValueArray(String hash) {
        Preconditions.checkArgument(StringUtils.isNotBlank(hash) && hash.length() >= 16,
                "illegal argument hash:not null and size >= 16");

        this.hash = hash;

        boolean[][] array = new boolean[6][5];

        //初始化字符串
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                array[i][j] = false;
            }
        }

        for (int i = 0; i < hash.length(); i += 2) {
            int s = i / 2; //只取hash字符串偶数编号（从0开始）的字符

            boolean v =
                    DoubleMath.roundToInt(Integer.parseInt(hash.charAt(i) + "", 16) / 10.0, RoundingMode.HALF_UP) > 0 ? true : false;
            if (s % 3 == 0) {
                array[s / 3][0] = v;
                array[s / 3][4] = v;
            } else if (s % 3 == 1) {
                array[s / 3][1] = v;
                array[s / 3][3] = v;
            } else {
                array[s / 3][2] = v;
            }
        }

        this.booleanValueArray = array;

        return this.booleanValueArray;
    }

    @Override
    public Color getBackgroundColor() {
        int r = Integer.parseInt(String.valueOf(this.hash.charAt(0)), 16) * 16;
        int g = Integer.parseInt(String.valueOf(this.hash.charAt(1)), 16) * 16;
        int b = Integer.parseInt(String.valueOf(this.hash.charAt(2)), 16) * 16;

        return new Color(r, g, b);
    }

    @Override
    public Color getForegroundColor() {
        int r = Integer.parseInt(String.valueOf(hash.charAt(hash.length() - 1)), 16) * 16;
        int g = Integer.parseInt(String.valueOf(hash.charAt(hash.length() - 2)), 16) * 16;
        int b = Integer.parseInt(String.valueOf(hash.charAt(hash.length() - 3)), 16) * 16;

        return new Color(r, g, b);
    }
}
