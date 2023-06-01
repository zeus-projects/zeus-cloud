package tech.alexchen.zeus.starter.data.mybatis.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.alexchen.zeus.starter.data.mybatis.handler.DefaultMetaObjectHandler;

import javax.sql.DataSource;

/**
 * @author alexchen
 */
@Configuration
@ConditionalOnBean(DataSource.class)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class MybatisPlusConfiguration {

    /**
     * 自定义的元数据填充处理器，用来给创建人、创建时间等通用属性设置值，无需代码显式地赋值
     */
    @Bean
    public MetaObjectHandler defaultMetaObjectHandler() {
        return new DefaultMetaObjectHandler();
    }

    /**
     * MybatisPlus 插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        // 添加分页插件
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }

}
