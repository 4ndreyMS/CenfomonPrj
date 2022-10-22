package com.cenfotec.cenfomon.core.tools;

public class TextUtils {
    public static String getWrappedText(String p_text, int p_lineCharsLimit) {
        String[] wordsArr = p_text.split(" ");
        int cont = 0;
        String result = "";

        for (int i = 0; i < wordsArr.length; i++) {
            result += wordsArr[i] + " ";
            cont += wordsArr[i].length() + 1;

            if (cont >= p_lineCharsLimit) {
                cont = 0;
                result += "\n";
            }
        }

        return result;
    }
}
