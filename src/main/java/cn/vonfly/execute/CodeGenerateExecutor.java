package cn.vonfly.execute;

import cn.vonfly.data.TableDataBuild;
import cn.vonfly.data.obj.TableObj;
import cn.vonfly.dest.DestFileBuild;
import cn.vonfly.velocity.VelocityBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(CodeGenerateExecutor.class);
    @Autowired
    private TableDataBuild tableDataBuild;
    @Autowired
    private List<DestFileBuild> destFileBuildList;
    @Autowired
    private VelocityBuilder velocityBuilder;
    @Value("${ignoreTables}")
    private String ignoreTables;
    @Value("${ignoreDestFileBuilds}")
    private String ignoreDestFileBuilds;
    private List<String> ignoreTableList;

    private List<String> ignoreDestFileBuildList;
    public void exec() {
        List<TableObj> tableObjList = tableDataBuild.build();
        for (TableObj tableObj : tableObjList) {
            if (!ignoreTableList.contains(tableObj.getNativeTableName())) {
                for (DestFileBuild destFileBuild : destFileBuildList) {
                    if (!ignoreDestFileBuildList.contains(destFileBuild.getClass().getSimpleName())) {
                        log.info("执行【{}-{}】文件构建",tableObj.getNativeTableName(),destFileBuild.getClass().getSimpleName());
                        Map<String, Object> data = destFileBuild.data(tableObj);
                        velocityBuilder.generate(destFileBuild.templateFileName(), destFileBuild.destFilePath(tableObj), destFileBuild.destFileName(tableObj), data);
                    }
                }
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (null != ignoreTables) {
            ignoreTableList = Arrays.asList(ignoreTables.split(","));
        }
        if (null != ignoreDestFileBuilds){
            ignoreDestFileBuildList = Arrays.asList(ignoreDestFileBuilds.split(","));
        }
    }
}
