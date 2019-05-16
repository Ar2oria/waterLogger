package edu.hrbust.common.enums;

/**
 * 为了统一枚举的 json序列化,展现,数据库存储 而抽象出的行为
 * <p>
 * 需要实现静态方法 codeOf
 *
 * @author zhengweichao
 * @since 2018/03/13
 */
public interface EnumTrait {

    int getCode();

    String getText();

}
