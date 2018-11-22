package cn.vonfly.data;

import cn.vonfly.data.obj.TableObj;
import cn.vonfly.velocity.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Feng
 */
@Component
public class TableDataBuild {
    private static final Logger log = LoggerFactory.getLogger(TableDataBuild.class);
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Value("${schema}")
    private String schema;
    @Autowired
    private TypeMapped typeMapped;
    @Autowired
    private TableNamingStrategy tableNamingStrategy;
    @Autowired
    private ColumnNamingStrategy columnNamingStrategy;
    @Autowired
    private FirstChar2UppCaseNamingStrategy firstChar2UppCaseNamingStrategy;
    @Autowired
    private FirstWordNamingStrategy firstWordNamingStrategy;
    @Autowired
    private Configuration configuration;
    public List<TableObj> build(){
        List<TableObj> result = new ArrayList<>();
        log.info("query tables");

        List<Map<String, Object>> tables = jdbcTemplate.queryForList("select TABLE_NAME,TABLE_COMMENT from information_schema.TABLES WHERE table_schema =?", new Object[]{schema});

        for (Map<String, Object> table : tables) {
            String nativeTableName = (String) table.get("TABLE_NAME");
            List<Map<String, Object>> columns = jdbcTemplate.queryForList("select  * from information_schema.`COLUMNS` where TABLE_NAME=? and TABLE_SCHEMA=?", new Object[]{nativeTableName, schema});
            Set<String> typeImports=new HashSet<>();
            List<TableObj.TableColumn> tableColumns = new ArrayList<>();
            List<TableObj.TableColumn> ignoreTableColumns = new ArrayList<>();
            for (Map<String, Object> column : columns) {
                TableObj.TableColumn tableColumn = new TableObj.TableColumn();
                tableColumn.setNativeColumnName(getColumnProValue(column.get("COLUMN_NAME")));
                tableColumn.setNativeType(getColumnProValue(column.get("DATA_TYPE")));
                tableColumn.setColumnName(columnNamingStrategy.convert(tableColumn.getNativeColumnName()));
                tableColumn.setJavaType(typeMapped.mapped(tableColumn.getNativeType()));
                tableColumn.setMethodColumnName(firstChar2UppCaseNamingStrategy.convert(tableColumn.getColumnName()));
                String isNullable = getColumnProValue(column.get("IS_NULLABLE"));
                tableColumn.setComment(getColumnProValue(column.get("COLUMN_COMMENT"))+" "+(isNullable=="YES"?"必填":""));

                String typeImport = typeMapped.typeImport(tableColumn.getJavaType());
                if (null !=typeImport){
                    typeImports.add(typeImport);
                }
                if (!configuration.getMethodIgnoreColumnNameList().contains(tableColumn.getColumnName())){
                    tableColumns.add(tableColumn);
                }else {
                    ignoreTableColumns.add(tableColumn);
                }
            }
            TableObj tableObj = new TableObj();
            tableObj.setNativeTableName(nativeTableName);
            tableObj.setTableName(tableNamingStrategy.convert(nativeTableName));
            tableObj.setSubPackage(firstWordNamingStrategy.convert(tableObj.getTableName()));
            tableObj.setTypeImports(typeImports);
            tableObj.setComment((String) table.get("TABLE_COMMENT"));
            tableObj.setColumns(tableColumns);
            tableObj.setIgnoreColumns(ignoreTableColumns);
            tableObj.setU1stTableName(firstChar2UppCaseNamingStrategy.convert(tableObj.getTableName()));
            result.add(tableObj);

        }
       return result;
    }
    /**
     * 获取column属性
     *
     * @param value
     * @return
     */
    private String getColumnProValue(Object value) {
        return null == value ? " " : ((String) value).length() > 0 ? ((String) value) : " ";
    }
}
