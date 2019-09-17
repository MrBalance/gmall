package generator;

/**
 * @description:
 * @author: yunzhang.du
 * @date: 2019年09月17日
 * @version: v1.0
 * @since: JDK 1.8
 */
public enum GenerateConstantCompareType {
    /**
     * 常量名为真实字段,无修改
     */
    UNMODIFIED,

    /**
     * 常量名为真实字段的驼峰式
     */
    CAMEL,

    /**
     * 常量名为真实字段名的别名
     */
    ALIAS;
}
