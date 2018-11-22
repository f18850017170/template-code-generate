package cn.vonfly.data;

public interface NamingStrategy {
    /**
     * 名称转换
     * @param name
     * @return
     */
    String convert(String name);
}
