package cc.likq.config;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;

import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * @author likq
 */
public class MyMetaObjectHandler extends MetaObjectHandler {

    /***
     * 插入拦截
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //初始化更新时间
        Object gmtModified = getFieldValByName("gmtModified", metaObject);
        if (gmtModified == null) {
            setFieldValByName("gmtModified", new Date(), metaObject);
        }
        //初始化创建时间
        Object gmtCreate = getFieldValByName("gmtCreate", metaObject);
        if (gmtCreate == null) {
            setFieldValByName("gmtCreate", new Date(), metaObject);
        }
    }

    /***
     * 更新拦截
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        //更新时间
//        Object gmtModified = getFieldValByName("gmtModified", metaObject);
        setFieldValByName("gmtModified", new Date(), metaObject);
    }
}