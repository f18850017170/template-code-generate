package cn.vonfly.execute;

import cn.vonfly.data.TableDataBuild;
import cn.vonfly.data.obj.TableObj;
import cn.vonfly.dest.DestFileBuild;
import cn.vonfly.velocity.VelocityBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Feng
 */
@Component
public class CodeGenerateExecutor implements InitializingBean {
    @Autowired
    private TableDataBuild tableDataBuild;
    @Autowired
    private List<DestFileBuild> destFileBuildList;
    @Autowired
    private VelocityBuilder velocityBuilder;
    @Value("${ignoreTables}")
    private String ignoreTables;
    private List<String> ignoreTableList;

    public void exec() {
        List<TableObj> tableObjList = tableDataBuild.build();
        for (TableObj tableObj : tableObjList) {
            if (!ignoreTableList.contains(tableObj.getNativeTableName())) {
                for (DestFileBuild destFileBuild : destFileBuildList) {
                    Map<String, Object> data = destFileBuild.data(tableObj);
                    velocityBuilder.generate(destFileBuild.templateFileName(), destFileBuild.destFilePath(tableObj),destFileBuild.destFileName(tableObj), data);
                }
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (null != ignoreTables) {
            ignoreTableList = Arrays.asList(ignoreTables.split(","));
        }
    }
}
