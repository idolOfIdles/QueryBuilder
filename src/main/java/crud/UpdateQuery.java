package crud;

import query.util.Util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class UpdateQuery {
    StringBuilder updateBuilder;

    public UpdateQuery(String table) {
        this.updateBuilder = new StringBuilder();
        updateBuilder.append("update ").append(table);
    }

    public SetUpdate set(String field, Object value) {
        updateBuilder.append(" set ");
        return new SetUpdate(updateBuilder).set(field, value);
    }

    public String values(Object value, String primaryKey) throws Exception {
        ArrayList<String> primaryKeys = new ArrayList<String>();
        primaryKeys.add(primaryKey);
        return updateBuilder.append(createSingleRowUpdateSqlString(value, primaryKeys)).toString();
    }

    public String values(Object value, List<String> primaryKeys) throws Exception {
        return updateBuilder.append(createSingleRowUpdateSqlString(value, primaryKeys)).toString();
    }


    private String createSingleRowUpdateSqlString(Object o, List<String> primaryKeys) throws Exception{
        if(primaryKeys.size() == 0) throw new Exception("Primary key/value not found");
        StringBuilder fieldSetBuilder = new StringBuilder();
        Field[] fields = o.getClass().getDeclaredFields();
        fieldSetBuilder.append(" set ");
        for(Field field : fields){
            Method method = o.getClass().getDeclaredMethod("get"+ Util.toTitle(field.getName()));
            fieldSetBuilder.append(field.getName()).append("=").append(Util.toMysqlString(method.invoke(o))).append(",");
        }
        Util.rightStripIfExists(fieldSetBuilder, ',');
        fieldSetBuilder.append(" where 1=1");
        for(String primaryKey : primaryKeys){
            Method method = o.getClass().getDeclaredMethod("get"+ Util.toTitle(primaryKey));
            fieldSetBuilder.append(" and ").append(primaryKey).append("=").append(Util.toMysqlString(method.invoke(o))).append(",");
        }
        Util.rightStripIfExists(fieldSetBuilder, ',');
        return fieldSetBuilder.toString();


    }

}
