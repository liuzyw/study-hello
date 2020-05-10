package com.study.util.sort.offer;

public class HuiWen {

    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        //int a = in.nextInt();
        //System.out.println(a);

        String src = "12212";

        System.out.println(huiwenStr(src));

        System.out.println("Hello World!");
    }

    private static int huiwenStr(String src) {

        if (src == null || src.length() < 2) {
            return 0;
        }
        if (src.length() == 2) {
            return src.charAt(0) == src.charAt(1) ? 1 : 0;
        }

        int maxHuiWenLen = 0;

        char[] chars = src.toCharArray();

        for (int i = 1; i < chars.length; i++) {

            // 向两边比较奇数情况
            int len = getHuiWenStr(0, i, chars.length, chars);

            maxHuiWenLen = maxHuiWenLen < len ? len : maxHuiWenLen;

            // 偶数情况 
            len = getHuiWenStr2(0, i, chars.length, chars);

            maxHuiWenLen = maxHuiWenLen < len ? len : maxHuiWenLen;

        }

        return maxHuiWenLen;
    }


    /**
     * 奇数的情况
     */
    private static int getHuiWenStr(int begin, int mid, int end, char[] chars) {

        int len = 0;

        int left = mid - 1;
        int right = mid + 1;

        while (left >= begin && right < end) {
            if (chars[left--] == chars[right++]) {
                len++;
            } else {
                break;
            }

        }

        if (len == 0) {
            return 0;
        }

        return len * 2 + 1;

    }


    /**
     * 偶数的情况
     */
    private static int getHuiWenStr2(int begin, int mid, int end, char[] chars) {

        int len = 0;

        int left = mid - 1;
        int right = mid + 2;

        while (left >= begin && right < end) {
            if (chars[left--] == chars[right++]) {
                len++;
            } else {
                break;
            }

        }
        if (len == 0) {
            return 0;
        }
        return len * 2 + 2;

    }


}