package cn.vonfly.data.obj;

import java.util.List;
import java.util.Set;

/**
 * @author Feng
 */
public class TableObj {
    private String subPackage;
    private String tableName;
    private String nativeTableName;
    private String comment;
    private Set<String> typeImports;
    private List<TableColumn> columns;
    private List<TableColumn> ignoreColumns;


    public List<TableColumn> getIgnoreColumns() {
        return ignoreColumns;
    }

    public void setIgnoreColumns(List<TableColumn> ignoreColumns) {
        this.ignoreColumns = ignoreColumns;
    }

    private String u1stTableName;

    public String getU1stTableName() {
        return u1stTableName;
    }

    public void setU1stTableName(String u1stTableName) {
        this.u1stTableName = u1stTableName;
    }

    public String getSubPackage() {
        return subPackage;
    }


    public void setSubPackage(String subPackage) {
        this.subPackage = subPackage;
    }

    public Set<String> getTypeImports() {
        return typeImports;
    }

    public void setTypeImports(Set<String> typeImports) {
        this.typeImports = typeImports;
    }



    public String getNativeTableName() {
        return nativeTableName;
    }

    public void setNativeTableName(String nativeTableName) {
        this.nativeTableName = nativeTableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<TableColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<TableColumn> columns) {
        this.columns = columns;
    }



    public static class TableColumn{
        private String columnName;
        private String nativeColumnName;
        private String comment;
        private String nativeType;
        private String javaType;
        private String methodColumnName;

        public String getMethodColumnName() {
            return methodColumnName;
        }

        public void setMethodColumnName(String methodColumnName) {
            this.methodColumnName = methodColumnName;
        }

        public String getNativeColumnName() {
            return nativeColumnName;
        }

        public void setNativeColumnName(String nativeColumnName) {
            this.nativeColumnName = nativeColumnName;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getNativeType() {
            return nativeType;
        }

        public void setNativeType(String nativeType) {
            this.nativeType = nativeType;
        }

        public String getJavaType() {
            return javaType;
        }

        public void setJavaType(String javaType) {
            this.javaType = javaType;
        }

        @Override
        public String toString() {
            return "TableColumn{" +
                    "columnName='" + columnName + '\'' +
                    ", comment='" + comment + '\'' +
                    ", nativeType='" + nativeType + '\'' +
                    ", javaType='" + javaType + '\'' +
                    '}';
        }
    }
}
