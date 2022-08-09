package com.softserve.edu.sporthubujp.email;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailValidator implements Predicate<String> {


    private final String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+" +
            "/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    private final Pattern pattern = Pattern.compile(regex);
    
    @Override
    public boolean test(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
