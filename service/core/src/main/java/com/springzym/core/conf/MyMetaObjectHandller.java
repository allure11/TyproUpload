package com.springzym.core.conf;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自动填充字段
 * @author springzym
 */
@Component
public class MyMetaObjectHandller implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        // 新建时填充创建时间和修改时间
        this.setFieldValByName("gmtCreate", LocalDateTime.now(), metaObject);
        this.setFieldValByName("gmtModified", LocalDateTime.now(), metaObject);
        this.setFieldValByName("isDeleted", 0, metaObject);
        this.setFieldValByName("version", 0L, metaObject);
        this.setFieldValByName("status", "Draft", metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 修改时填充修改时间
        this.setFieldValByName("gmtModified", LocalDateTime.now(), metaObject);
    }
}
