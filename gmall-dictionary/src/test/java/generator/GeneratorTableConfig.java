package generator;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description:
 * @author: yunzhang.du
 * @date: 2019年09月17日
 * @version: v1.0
 * @since: JDK 1.8
 */
public class GeneratorTableConfig {

    @SuppressWarnings("unchecked")
    public static void generator(String projectName, GenerateConstantCompareType compareType, String filePlace, String packagePlace) {
        Connection conn = null;
        FileWriter fw = null;
        try {
            //读取黑名单
            BufferedReader bufferedReader = new BufferedReader(new FileReader(GeneratorTableConfig.class.getResource("/").toString().replaceFirst("file:/", "") + "whitelist.json"));
            String s;
            StringBuilder json = new StringBuilder();
            JSONObject infoJsonObject;
            while ((s = bufferedReader.readLine()) != null) {
                json.append(s);
            }
            bufferedReader.close();
            infoJsonObject = JSONObject.fromObject(json.toString().replaceAll("\t", ""));
            if (infoJsonObject == null) {
                System.err.println("白名单文件内容为空");
                return;
            }
            List<String> infoJsonList = (List<String>) infoJsonObject.get("whitelist");
            //连接数据库
            String driverClassName = infoJsonObject.getString("driverClassName");
            String jdbcUrl = infoJsonObject.getString("jdbc_url");
            String jdbcUsername = infoJsonObject.getString("jdbc_username");
            String jdbcPassword = infoJsonObject.getString("jdbc_password");
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
            Statement statement = conn.createStatement();
            DatabaseMetaData dmd = conn.getMetaData();
            //获取数据库中的所有表
            ResultSet tableNameSet = dmd.getTables(null, null, null, new String[]{"TABLE"});
            List<String> tableNameList = new ArrayList<>();
            //排除掉所有以_bak结尾的备份表
            while (tableNameSet.next()) {
                tableNameList.add(tableNameSet.getString("TABLE_NAME"));
            }
            //创建文件生成目录
            String url = filePlace + projectName.replaceAll("\\.", "\\/") + "/dictionary/";
            File file = new File(url);
            if (!file.exists()) {
                file.mkdirs();
            }
            //生成表名对应的配置文件
            StringBuilder sb = new StringBuilder();
            sb.append("package ").append(packagePlace).append(projectName).append(".dictionary;\n\n");
            sb.append("public class TableNameDictionary {\n\n");
            fw = new FileWriter(url + "/TableNameDictionary.java");
            switch (compareType) {
                case UNMODIFIED:
                    for (String key : tableNameList) {
                        if (infoJsonList.contains(key)) {
                            if (key.startsWith("t_")) {
                                sb.append("\tpublic final static String ").append(key.toUpperCase()).append(" = \"").append(key.substring(1)).append("\";\n\n");
                            } else {
                                sb.append("\tpublic final static String ").append(key.toUpperCase()).append(" = \"").append(key).append("\";\n\n");
                            }
                        }
                    }
                    break;
                case CAMEL:
                    for (String key : tableNameList) {
                        if (infoJsonList.contains(key)) {
                            if (key.startsWith("t_")) {
                                sb.append("\tpublic final static String ").append(key.toUpperCase()).append(" = \"").append(underline2camel(key).substring(1)).append("\";\n\n");
                            } else {
                                sb.append("\tpublic final static String ").append(key.toUpperCase()).append(" = \"").append(underline2camel(key)).append("\";\n\n");
                            }
                        }
                    }
                    break;
                case ALIAS:
                    for (String key : tableNameList) {
                        if (infoJsonList.contains(key)) {
                            if (key.startsWith("t_")) {
                                sb.append("\tpublic final static String ").append(key.toUpperCase()).append(" = \"").append(underline2camel(key).substring(1)).append("\";\n\n");
                            } else {
                                sb.append("\tpublic final static String ").append(key.toUpperCase()).append(" = \"").append(underline2camel(key)).append("\";\n\n");
                            }
                        }
                    }
                    break;
                default:
                    break;
            }

            sb.deleteCharAt(sb.length() - 1).append("}");
            fw.write(sb.toString());
            fw.flush();
            fw.close();
            //生成字段名对应配置文件
            for (String key : tableNameList) {
                if (infoJsonList.contains(key)) {
                    sb.setLength(0);
                    sb.append("package ").append(packagePlace).append(projectName).append(".dictionary;\n\n");
                    if (key.startsWith("t_")) {
                        sb.append("public class ").append(underline2camel(key).substring(1)).append("Field {\n\n");
                        fw = new FileWriter(url + underline2camel(key).substring(1) + "Field.java");
                    } else {
                        key = "_" + key;
                        sb.append("public class ").append(underline2camel(key)).append("Field {\n\n");
                        fw = new FileWriter(url + underline2camel(key) + "Field.java");
                        key = key.substring(1);
                    }
                    ResultSet columnSet = dmd.getColumns(null, "%", key, "%");
                    switch (compareType) {
                        case UNMODIFIED:
                            while (columnSet.next()) {
                                String columnName = columnSet.getString("COLUMN_NAME");
                                sb.append("\t" + "/**\n\t * ").append(columnSet.getString("REMARKS")).append("\n\t */").append("\n");
                                sb.append("\tpublic final static String ").append(columnName.toUpperCase()).append(" = \"").append(columnName).append("\";\n\n");
                            }
                            sb.deleteCharAt(sb.length() - 1).append("}");
                            break;
                        case CAMEL:
                            while (columnSet.next()) {
                                String columnName = columnSet.getString("COLUMN_NAME");
                                sb.append("\t" + "/**\n\t * ").append(columnSet.getString("REMARKS")).append("\n\t */").append("\n");
                                sb.append("\tpublic final static String ").append(columnName.toUpperCase()).append(" = \"").append(underline2camel(columnName)).append("\";\n\n");
                            }
                            sb.deleteCharAt(sb.length() - 1).append("}");
                            break;
                        case ALIAS:
                            while (columnSet.next()) {
                                sb.append("\t" + "/**\n\t * ").append(columnSet.getString("REMARKS")).append("\n\t */").append("\n");
                                String columnName = columnSet.getString("COLUMN_NAME");

                                String sqlStr = "select alias from t_alias_dictionary where `word` = '" + columnName + "';";
                                ResultSet dictSet = statement.executeQuery(sqlStr);
                                if (!dictSet.next()) {
                                    System.err.println("该字段全名无法在数据库中获取,请检查或自行添加→" + columnName + "表名为→" + key);
                                } else {
                                    sb.append("\tpublic final static String ").append(columnName.toUpperCase()).append(" = \"").append(dictSet.getString("alias")).append("\";\n\n");
                                }
                            }
                            sb.deleteCharAt(sb.length() - 1).append("}");
                            break;
                        default:
                            break;
                    }
                    fw.write(sb.toString());
                    fw.flush();
                    fw.close();
                }
            }
            System.out.println("生成结束");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * <p>Title:underline2camel</p>
     * <p>Description: 将下划线显示转换成驼峰命名，例如member_id转成memberId</p>
     *
     * @return String
     * @param: param
     */
    private static String underline2camel(String param) {
        if (isNullOrEmpty(param)) {
            return "";
        }
        StringBuilder sb = new StringBuilder(param);
        Matcher mc = Pattern.compile("_").matcher(param);
        int i = 0;
        while (mc.find()) {
            int position = mc.end() - (i++);
            sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
        }
        return sb.toString();
    }

    private static boolean isNullOrEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static void main(String[] args) {
        generator("gmall", GenerateConstantCompareType.UNMODIFIED, "gmall-dictionary/src/main/java/com/balance/", "com.balance.");
    }
}
