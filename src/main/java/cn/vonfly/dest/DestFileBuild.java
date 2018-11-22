package cn.vonfly.dest;

import cn.vonfly.data.obj.TableObj;

import java.util.Map;

public interface DestFileBuild {
    /**
     * 模板文件
     * @return
     */
    String templateFileName();

    /**
     * 目标文件
     * @return
     */
    String destFilePath(TableObj tableObj);

    /**
     * 目标文件名称
     * @param tableObj
     * @return
     */
    String destFileName(TableObj tableObj);
    /**
     * 写入模板的数据
     * @return
     */
    Map<String,Object> data(TableObj tableObj);
}
