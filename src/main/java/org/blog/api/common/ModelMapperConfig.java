package org.blog.api.common;

import org.blog.api.domain.Post;
import org.blog.api.payload.PostDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by melchor
 * Date: 2020/08/31
 * Time: 10:35 PM
 */
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addMappings(new PropertyMap<Post, PostDto.ResponseList>() {
            @Override
            protected void configure() {
                map().setWriter(source.getWriter().getUsername());
            }
        });

        return modelMapper;
    }

}
