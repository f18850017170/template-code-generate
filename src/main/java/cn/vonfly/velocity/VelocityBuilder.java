package cn.vonfly.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Paths;
import java.util.Map;

/**
 * @author Feng
 */
@Component
public class VelocityBuilder {
    @Autowired
    private Configuration configuration;

    public void generate( String templateFile, String destFilePath,String destFile, Map<String, Object> data) {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init(configuration.build());
        VelocityContext context = new VelocityContext();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            context.put(entry.getKey(),entry.getValue());
        }
        try {
            //加载模板文件
            Template template = velocityEngine.getTemplate("template/" + templateFile, "UTF-8");
            File file = Paths.get(destFilePath).toFile();
            if (!file.exists()){
                file.mkdirs();
            }
            File targetFile = Paths.get(destFilePath).resolve(destFile).toFile();
            if (targetFile.exists()) {
                targetFile.delete();
            }
            targetFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(targetFile);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
            //渲染生成
            template.merge(context, writer);
            writer.flush();
            writer.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
