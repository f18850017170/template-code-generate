package cn.vonfly.dest;

import cn.vonfly.data.obj.TableObj;
import cn.vonfly.velocity.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
public class DaoDestFileBuild implements DestFileBuild{
    @Autowired
    private Configuration configuration;

    @Override
    public String templateFileName() {
        return "Dao.vel";
    }

    @Override
    public String destFilePath(TableObj tableObj) {
        Path path = configuration.getRootPath().resolve("core").resolve(tableObj.getSubPackage()).resolve("dao");
        return path.toString();
    }

    @Override
    public String destFileName(TableObj tableObj) {
        return tableObj.getU1stTableName()+"Dao.java";
    }

    @Override
    public Map<String, Object> data(TableObj tableObj) {
        Map<String, Object> result = new HashMap<>();
        result.put("subPackage",tableObj.getSubPackage());
        result.put("tableComment",tableObj.getComment());
        result.put("nativeTable",tableObj.getNativeTableName());
        result.put("tableName",tableObj.getTableName());
        result.put("u1stTableName",tableObj.getU1stTableName());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        result.put("time",now.format(formatter) );
        result.put("author","冯江龙 (jianglong.feng@ucarinc.com)");
        return result;
    }
}
