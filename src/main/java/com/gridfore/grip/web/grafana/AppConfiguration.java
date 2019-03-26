package com.gridfore.grip.web.grafana;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;


@Configuration
@EnableWebMvc
public class AppConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldType(Date.class, new StringToDateFormatter());
    }

    class StringToDateFormatter implements Formatter<Date> {

        @Override
        public Date parse(String text, Locale locale) throws ParseException {
            return new Date();
        }

        @Override
        public String print(Date object, Locale locale) {
            return object.toString();
        }
    }
}
