package com.stealthyalda.gui.views;

public class RegWeiter extends Register {
    /**
     * Extract street name and number from the input.
     *
     * @param eingabe the user inputted data
     * @return array of the street and street number
     */
    public String[] extractHausnr(String eingabe) {
        StringBuilder hausnummer = new StringBuilder();
        StringBuilder userstrasse = new StringBuilder();
        if (eingabe != null && !eingabe.isEmpty()) {
            for (char c : eingabe.toCharArray()) {
                if (Character.isDigit(c)) {

                    hausnummer.append(c);
                } else {
                    userstrasse.append(c);
                }
            }
        }
        return new String[]{userstrasse.toString(), hausnummer.toString()};
    }
}
