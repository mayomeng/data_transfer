package pers.mxy.data.transfer.common;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2017/10/20.
 */
@Configuration
public class MapperConfiguration {
    @Bean
    public Mapper getMapper() {
        return new DozerBeanMapper();
    }
}
