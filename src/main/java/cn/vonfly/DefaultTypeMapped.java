package cn.vonfly;

import cn.vonfly.data.TypeMapped;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Feng
 */
@Component
public class DefaultTypeMapped implements TypeMapped, InitializingBean {
    private Map<String, String> typeMap = new HashMap<>();
    private Map<String, String> typeImportMap = new HashMap<>();

    @Override
    public String mapped(String jdbcType) {
        return typeMap.get(jdbcType);
    }

    @Override
    public String typeImport(String javaType) {
        return typeImportMap.get(javaType);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Properties properties = new Properties();
        properties.load(Files.newInputStream(Paths.get(getClass().getResource("/typeMapped.properties").toURI()), StandardOpenOption.READ));
        for ( Enumeration<Object> keys = properties.keys();keys.hasMoreElements();) {
            String key = (String)keys.nextElement();
            typeMap.put(key, (String) properties.get(key));
        }
        properties.clear();
        properties.load(Files.newInputStream(Paths.get(getClass().getResource("/typeImportMapped.properties").toURI()), StandardOpenOption.READ));
        for ( Enumeration<Object> keys = properties.keys();keys.hasMoreElements();) {
            String key = (String)keys.nextElement();
            typeImportMap.put(key, (String) properties.get(key));
        }
    }
}
