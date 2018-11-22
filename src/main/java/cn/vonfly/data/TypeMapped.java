package cn.vonfly.data;

public interface TypeMapped {
    String mapped(String jdbcType);
    String typeImport(String javaType);
}
