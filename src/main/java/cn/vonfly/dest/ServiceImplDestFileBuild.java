package cn.vonfly.dest;

import cn.vonfly.data.obj.TableObj;
import cn.vonfly.velocity.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
@Component
public class ServiceImplDestFileBuild extends ServiceDestFileBuild implements DestFileBuild{
    @Autowired
    private Configuration configuration;
    @Override
    public String templateFileName() {
        return "ServiceImpl.vel";
    }

    @Override
    public String destFilePath(TableObj tableObj) {
        Path path = configuration.getRootPath().resolve("core").resolve(tableObj.getSubPackage()).resolve("service").resolve("impl");
        return path.toString();
    }

    @Override
    public String destFileName(TableObj tableObj) {
        return tableObj.getU1stTableName()+"ServiceImpl.java";
    }


}
