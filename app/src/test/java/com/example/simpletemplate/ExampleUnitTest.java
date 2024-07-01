package com.example.simpletemplate;

import org.junit.Test;

import static org.junit.Assert.*;

import android.util.Log;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void longest() {
        String value = "dfdaaa";
//        assertThat(longestPalindrome(value),);
        longestPalindrome(value);

        String result = longestPalindrome(value);

        System.out.print("longestPalindrome = " + result);
//        return longestPalindrome(value);
    }

    public String longestPalindrome(String s) {
        char[] cs = s.toCharArray();
        int l = cs.length;

        int mi = l / 2;
        mi = mi + (l % 2);

        int cm = 0;
        int st = 0, en = 0;

        for (int i = mi; i >= 0; i--) {
            int le = i - 1;
            int ri = i;
            while (le >= 0 && ri < l && cs[le] == cs[ri]) {
                le--;
                ri++;
            }
            st = Math.max(le, 0);
            en = ri;
        }

        return s.substring(st, en);
    }
}