package com.wipro.cloud.api.helloworld.config;

import java.io.IOException;
import java.util.Map;

import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlPropertySourceFactory  implements PropertySourceFactory {
    

	@Override
   public PropertySource<?> createPropertySource(
     String name, EncodedResource resource)
         throws IOException {
       Map<String, Object> readValue = 
    		   new XmlMapper()
         .readValue(resource.getInputStream(), new TypeReference<Map<String, Object>>() {
         });


       return new MapPropertySource(name, readValue);
   }

}
