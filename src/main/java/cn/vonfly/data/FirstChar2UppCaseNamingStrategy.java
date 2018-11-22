package cn.vonfly.data;

import org.springframework.stereotype.Component;


@Component
public class FirstChar2UppCaseNamingStrategy implements NamingStrategy{
    @Override
    public String convert(String name) {
        if (null != name && name.length()>0)
        {
            char[] chars = name.toCharArray();
            chars[0]= Character.toUpperCase(chars[0]);
            return new String(chars);
        }
        return null;
    }
}
