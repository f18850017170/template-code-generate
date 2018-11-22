package cn.vonfly.data;

import org.springframework.stereotype.Component;

/**
 * @author Feng
 */
@Component
public class FirstWordNamingStrategy implements NamingStrategy {
    @Override
    public String convert(String name) {
        if (null != name) {
            StringBuilder result = new StringBuilder();
            for (char c : name.toCharArray()) {
                if (Character.isUpperCase(c)){
                    break;
                }
                result.append(c);
            }
            return result.toString();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new FirstWordNamingStrategy().convert("deptDutyReceived"));
    }
}
