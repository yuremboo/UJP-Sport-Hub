package com.softserve.edu.sporthubujp.validator;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PasswordValidator implements Predicate<String> {
    private final String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
    private final Pattern pattern = Pattern.compile(regex);

    @Override
    public boolean test(String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}