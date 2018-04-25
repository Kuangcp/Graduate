package top.kuangcp.graduate.util.orm;


import top.kuangcp.graduate.util.orm.base.DBType;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * 实现了一个查询记录，插入记录
 * TODO 需要使用工厂模式进行修改, 只处理一次
 *
 * @author Myth On 2016年9月10日 下午8:20:12
 * 实现了ORM类似操作
 */
public class MythORM {
    private DBType dbType;
    private DBConfig dbConfig;

    private MythORM(DBType dbType) {
        this.dbType = dbType;
    }

    public static MythORM build(DBType dbType) {
        return new MythORM(dbType);
    }

    /**
     * 将输入对象转换成SQL语句
     * 不能有除了属性的get方法之外的get方法，不然这里的SQL拼接会失败
     *
     * @param obj 输入对象, 属性为空就默认为空字符串和0  字段的类型暂时只支持 long int Integer String Date
     * @return boolean 是否成功
     */
    public boolean save(Object obj) throws SQLException {
        Class target = obj.getClass();
        StringBuilder sqlBuilder = new StringBuilder("insert into ");
        StringBuilder valueBuilder = new StringBuilder("values(");

        Method[] methods = target.getMethods();
        String className = target.getName();
        String tableName = classToTableName(className);
        sqlBuilder.append(tableName).append(" (");

        for (Method method : methods) {
            String mName = method.getName();
            if (mName.startsWith("get") && !mName.startsWith("getClass")) {
                String colName = classToTableName(mName.substring(3, mName.length()));
                sqlBuilder.append(colName).append(",");
                Class returnType = method.getReturnType();
                // TODO 优化类型
                try {
                    if (returnType == String.class) {
                        String p = (String) method.invoke(obj);
                        if (p == null) {
                            p = " ";
                        }
                        valueBuilder.append("'").append(p).append("',");
                    } else if (returnType == int.class) {
                        long temp = (int) method.invoke(obj);
                        valueBuilder.append(temp).append(",");
                    } else if (returnType == long.class) {
                        long temp = (long) method.invoke(obj);
                        valueBuilder.append(temp).append(",");
                    } else if (returnType == Integer.class) {
                        Integer temp = (Integer) method.invoke(obj);
                        if (temp != null) {
                            valueBuilder.append(temp).append(",");
                        }
                    }
                    // TODO Date 类型
//                    else if( returnType==Date.class){
//                        Date temp = (Date)method.invoke(obj);
//                        StringBuilder pp = new StringBuilder(new SimpleDateFormat("YYYY-MM-dd HH:MM:SS").format(temp));
//                        valueBuilder.append("'").append(pp.delete(pp.length() - 9, pp.length())).append("',");
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        sqlBuilder.delete(sqlBuilder.length() - 1, sqlBuilder.length());
        valueBuilder.delete(valueBuilder.length() - 1, valueBuilder.length());
        sqlBuilder.append(")");
        valueBuilder.append(")");
        String sql = sqlBuilder.toString() + valueBuilder.toString();
        return new DBAction(dbConfig, dbType).executeUpdateSQL(sql);
    }

    /**
     * 传入对象更新对应不为空的值
     *
     * @param obj 实体
     * @param condition 自定义条件 可以为空
     * @return boolean
     * @throws SQLException
     */
    public boolean update(Object obj, String condition) throws SQLException {
        Class target = obj.getClass();
        StringBuilder sqlBuilder = new StringBuilder("update ");

        Method[] methods = target.getMethods();
        String className = target.getName();
        //通过正则表达式来截取类名，赋值给表名
        String tableName = classToTableName(className);
        sqlBuilder.append(tableName).append(" set ");

        for (Method method : methods) {
            String mName = method.getName();
            if (mName.startsWith("get") && !mName.startsWith("getClass")) {
                String colName = classToTableName(mName.substring(3, mName.length()));
                Class returnType = method.getReturnType();
                // TODO 优化类型
                try {
                    if (returnType == String.class) {
                        String p = (String) method.invoke(obj);
                        if (p != null) {
                            sqlBuilder.append(colName).append(" = ").append("'").append(p).append("',");
                        }
                    } else if (returnType == int.class) {
                        long temp = (int) method.invoke(obj);
                        sqlBuilder.append(colName).append(" = ").append(temp).append(",");
                    } else if (returnType == long.class) {
                        long temp = (long) method.invoke(obj);
                        sqlBuilder.append(colName).append(" = ").append(temp).append(",");
                    } else if (returnType == Integer.class) {
                        Integer temp = (Integer) method.invoke(obj);
                        if (temp != null) {
                            sqlBuilder.append(colName).append(" = ").append(temp).append(",");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        sqlBuilder.delete(sqlBuilder.length() - 1, sqlBuilder.length());
        sqlBuilder.append(" ").append(condition);
        System.out.println(" 更新  " + sqlBuilder.toString());
        return new DBAction(dbConfig, dbType).executeUpdateSQL(sqlBuilder.toString());
    }

    /**
     * 获取指定的class对应的表全部的记录
     *
     * @param target 类对象
     * @return List 对象集合没有泛型
     */
    public <T> List<T> listAll(Class<T> target) {
        String tableName = classToTableName(target.getName());
        return query("select * from " + tableName, target, dbConfig);
    }

    public <T> List<T> query(String sql, Class<T> target, DBConfig DBConfig) {
        List<T> list = new ArrayList<>();
        T obj;
        DBAction db = null;
        try {
            db = new DBAction(DBConfig, dbType);
            ResultSet resultSet = db.queryBySQL(sql);
            Method[] methodArray = target.getMethods();
            while (resultSet.next()) {
                obj = target.newInstance();
                for (Method method : methodArray) {
                    String methodName = method.getName();
                    if (methodName.startsWith("set")) {
                        String colName = classToTableName(methodName.substring(3, methodName.length()));
                        Class[] params = method.getParameterTypes();
//							System.out.print(" : "+resultSet.getString(colName)+"\n");
                        //根据相应的类型来给对象赋值 TODO case 或者设计模式
                        if (params[0] == String.class) {
                            method.invoke(obj, resultSet.getString(colName));
                        } else if (params[0] == int.class) {
                            method.invoke(obj, resultSet.getInt(colName));
                        } else if (params[0] == long.class) {
                            method.invoke(obj, resultSet.getLong(colName));
                        } else if (params[0] == Date.class) {
                            method.invoke(obj, resultSet.getDate(colName));
                        }
                    }
                }
                list.add(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.closeAll();
            }
        }
        return list;
    }

    /**
     * TODO 驼峰转下划线
     * @param className 类名或者属性名
     * @return 下划线方式字符串
     */
    public String classToTableName(String className) {
        String tableName = className.split("\\.")[className.split("\\.").length - 1];
        int count = 0;
        // 如果小写字母开头，属性名
        Pattern pattern = compile("^[a-z]");
        Matcher matcher = pattern.matcher(tableName);
        if (matcher.find()) {
            count++;
        }
        // 大写字母开头 类名
        pattern = compile("[A-Z]");
        matcher = pattern.matcher(tableName);
        while (matcher.find()) {
            count++;
            if (count == 1) {
                continue;
            }
            String w = matcher.group().trim();
            tableName = tableName.replace(w, "_" + w);
        }
        return tableName.toLowerCase();
    }

    public DBType getDbType() {
        return dbType;
    }

    public void setDbType(DBType dbType) {
        this.dbType = dbType;
    }

    public DBConfig getDbConfig() {
        return dbConfig;
    }

    public void setDbConfig(DBConfig dbConfig) {
        this.dbConfig = dbConfig;
    }
}