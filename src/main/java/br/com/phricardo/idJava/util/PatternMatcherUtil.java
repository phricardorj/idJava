package br.com.phricardo.idJava.util;


import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class PatternMatcherUtil {

    public boolean matcher(String expression, String str) {
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(str).matches();
    }

}
