package cn.vonfly.dest;

import cn.vonfly.data.obj.TableObj;
import cn.vonfly.velocity.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SqlDestFileBuild implements DestFileBuild {
    @Autowired
    private Configuration configuration;

    @Override
    public String templateFileName() {
        return "sql.vel";
    }

    @Override
    public String destFilePath(TableObj tableObj) {
        Path path = configuration.getRootPathResource().resolve(tableObj.getSubPackage());
        return path.toString();
    }

    @Override
    public String destFileName(TableObj tableObj) {
        return tableObj.getTableName() + "_sql.xml";
    }

    @Override
    public Map<String, Object> data(TableObj tableObj) {
        Map<String, Object> result = new HashMap<>();
        result.put("subPackage",tableObj.getSubPackage());
        result.put("typeImported",tableObj.getTypeImports());
        result.put("tableComment",tableObj.getComment());
        result.put("nativeTable",tableObj.getNativeTableName());
        result.put("tableName",tableObj.getTableName());
        List<TableObj.TableColumn> allColumns=new ArrayList<>();
        allColumns.addAll(tableObj.getColumns());
        allColumns.addAll(tableObj.getIgnoreColumns());
        result.put("columns",allColumns);
        result.put("u1stTableName",tableObj.getU1stTableName());
        return result;
    }
}
