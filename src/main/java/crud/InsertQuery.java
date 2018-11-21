package crud;

import interfaces.CrudInterface;
import interfaces.CrudSet;
import query.util.Util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class InsertQuery{
    StringBuilder insertBuilder;

    public InsertQuery(String table) {
        this.insertBuilder = new StringBuilder();
        insertBuilder.append("insert into ").append(table);
    }

    public SetInsert set(String field, Object value) {
        return new SetInsert(insertBuilder).set(field, value);
    }

    public String values(Object value) {
        return insertBuilder.append(createInsertValuesString(value)).toString();
    }


    private String createInsertValuesString(Object o){
        Field[] fields = o.getClass().getDeclaredFields();
        List<String> variableNames = new ArrayList<String>();
        StringBuilder stringBuilder = new StringBuilder("(");
        for(Field field : fields){
            variableNames.add(field.getName());
            stringBuilder.append(field.getName()).append(",");

        }
        stringBuilder.setCharAt(stringBuilder.length()-1, ')');
        stringBuilder.append("values(");
        for(Field field : fields){
            try {
                Method method = o.getClass().getDeclaredMethod("get"+ Util.toTitle(field.getName()));
                stringBuilder.append(Util.toMysqlString(method.invoke(o))).append(",");
            } catch (Exception e) {
                stringBuilder.append("NULL");
            }
        }
        stringBuilder.setCharAt(stringBuilder.length()-1, ')');
        stringBuilder.append(")");
        return stringBuilder.toString();


    }

}
