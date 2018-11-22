package cn.vonfly.data;

import org.springframework.stereotype.Component;

/**
 * @author Feng
 */
@Component
public class ColumnNamingStrategy implements NamingStrategy {
    @Override
    public String convert(String name) {
        if (null != name) {
            char[] chars = name.toCharArray();
            boolean toUpperCase = false;
            StringBuilder result = new StringBuilder();
            for (char temp : chars) {
                if (temp=='_'){
                    toUpperCase = true;
                    continue;
                }
                if (toUpperCase){
                    result.append(Character.toUpperCase(temp));
                    toUpperCase=false;
                }else {
                    result.append(temp);
                }
            }
            return result.toString();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new ColumnNamingStrategy().convert("dept_duty_received"));
    }
}
