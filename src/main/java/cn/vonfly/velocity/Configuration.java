package cn.vonfly.velocity;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author Feng
 */
@Component
public class Configuration implements InitializingBean {
    private String templateBasePath = "template";
    private Properties properties;
    private static final Path ROOT_PATH = Paths.get(System.getProperty("user.dir")).resolveSibling("CARBalance/carbalance-os/src/main/java/com/zuche/balance");
    private static final Path ROOT_PATH_RESOURCE=Paths.get(System.getProperty("user.dir")).resolveSibling("CARBalance/carbalance-os/src/main/resources/com/zuche/sql/core");
    @Value("${ignoreColumnNames}")
    private String ignoreColumnNames;
    private List<String> ignoreColumnNameList;

    public Properties build() {
        return properties;
    }

    public Path getRootPath() {
        return ROOT_PATH;
    }

    public Path getRootPathResource() {
        return ROOT_PATH_RESOURCE;
    }

    public List<String> getMethodIgnoreColumnNameList() {
        return ignoreColumnNameList;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        properties = new Properties();
        properties.setProperty("resource.loader", "class");
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        properties.setProperty("file.resource.loader.description", "Velocity File Resource Loader");
        properties.setProperty("file.resource.loader.path", templateBasePath);
        properties.setProperty("file.resource.loader.cache", "true");
        properties.setProperty("file.resource.loader.modificationCheckInterval", "30");
        properties.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.Log4JLogChute");
        properties.setProperty("runtime.log.logsystem.log4j.logger", "org.apache.velocity");
        properties.setProperty("directive.set.null.allowed", "true");
        if (null != ignoreColumnNames) {
            ignoreColumnNameList = Arrays.asList(ignoreColumnNames.split(","));
        }
    }
}
