package com.yx.demo.config;

//import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
//import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
//import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
//import net.sf.jsqlparser.expression.Expression;
//import net.sf.jsqlparser.expression.StringValue;
//import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import java.time.LocalDateTime;

/**
 * @author yangxi
 * @version 1.0
 */
@Configuration
public class MybatisPlusConfig {

//    /**
//     * 自动填充配置 （不常用）
//     * @return
//     */
//    @Bean
//    public MetaObjectHandler metaObjectHandler() {
//        return new MetaObjectHandler() {
//            @Override
//            public void insertFill(MetaObject metaObject) {
//                this.strictInsertFill(metaObject, "createdTime", LocalDateTime.class, LocalDateTime.now());
//                this.strictInsertFill(metaObject, "updatedTime", LocalDateTime.class, LocalDateTime.now());
//            }
//
//            @Override
//            public void updateFill(MetaObject metaObject) {
//                this.strictUpdateFill(metaObject, "updatedTime", LocalDateTime.class, LocalDateTime.now());
//            }
//        };
//    }

    /**
     * 新版的插件配置
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();

//
//        // 多租户插件（不常用）
//        TenantLineInnerInterceptor tenantLineInnerInterceptor = new TenantLineInnerInterceptor();
//        tenantLineInnerInterceptor.setTenantLineHandler(new TenantLineHandler() {
//            @Override
//            public Expression getTenantId() {
//                // 需要从用户上下文获取当前的租户ID
//                String tenantId = "xxxTenantId";
//                if (tenantId == null || "".equals(tenantId)) {
//                    throw new IllegalArgumentException("tenantId is null");
//                }
//                return new StringValue(tenantId);
//            }
//
//            @Override
//            public String getTenantIdColumn() {
//                return "tenant_id";
//            }
//
//            @Override
//            public boolean ignoreTable(String tableName) {
//                // 配置忽略多租户的表
//                if("xxxTable".equals(tableName)) {
//                    return true;
//                }
//                return false;
//            }
//        });
//        mybatisPlusInterceptor.addInnerInterceptor(tenantLineInnerInterceptor);


        // 分页插件
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());

        // 乐观锁插件
        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return mybatisPlusInterceptor;
    }
}