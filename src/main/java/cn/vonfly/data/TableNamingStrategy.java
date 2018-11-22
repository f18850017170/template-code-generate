package cn.vonfly.data;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author Feng
 */
@Component
public class TableNamingStrategy implements NamingStrategy, InitializingBean {
    @Value("${ignoreTablePrefix}")
    private String ignorePrefixes;
    private List<String> ignorePrefixList;
    @Autowired
    private ColumnNamingStrategy columnNamingStrategy;
    @Override
    public String convert(String name) {
        if (null !=name){
            for (String s : ignorePrefixList) {
                name = name.replace(s,"");
            }
            return columnNamingStrategy.convert(name);
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ignorePrefixList = Arrays.asList(ignorePrefixes.split(","));
    }
}
