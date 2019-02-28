package schultz.dustin.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import schultz.dustin.io.services.ASCIIArtService;
import schultz.dustin.io.services.MyConfig;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

@SpringBootApplication(exclude = {JacksonAutoConfiguration.class,
        JmxAutoConfiguration.class, WebSocketAutoConfiguration.class})
public class JustGifItApplication {

    @Inject
    private MyConfig myConfig;

    @Inject
    private ASCIIArtService asciiArtService;

    private final static Logger log = LoggerFactory.getLogger(JustGifItApplication.class);

    @Value("${multipart.location}/gif/")
    private String gifLocation;

    public static void main(String[] args) {
        SpringApplication.run(JustGifItApplication.class, args);
    }

    @PostConstruct
    private void init() {
        File gifFolder = new File(gifLocation);
        if (!gifFolder.exists()) {
            gifFolder.mkdir();
        }
    }
    @PostConstruct
    private void initAlt(){
        log.info("JustGifItApplication MyConfig xyz debug " + myConfig.getMyString() + myConfig.getMyLong() + myConfig.isMyBoolean());
        log.info("SPRING_APPLICATION_JSON " + System.getenv("SPRING_APPLICATION_JSON"));
        try {
            asciiArtService.process();
        } catch (IOException e) {
        }
    }

    @Bean
    public FilterRegistrationBean deRegisterHiddenHttpMethodFilter
            (HiddenHttpMethodFilter filter) {
        FilterRegistrationBean bean = new FilterRegistrationBean(filter);
        bean.setEnabled(false);
        return bean;
    }
    @Bean
    public FilterRegistrationBean deRegisterHttpPutFormContentFilter
            (HttpPutFormContentFilter filter) {
        FilterRegistrationBean bean = new FilterRegistrationBean(filter);
        bean.setEnabled(false);
        return bean;
    }
    @Bean
    public FilterRegistrationBean deRegisterRequestContextFilter
            (RequestContextFilter filter) {
        FilterRegistrationBean bean = new FilterRegistrationBean(filter);
        bean.setEnabled(false);
        return bean;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/gif/**")
                        .addResourceLocations("file:" + gifLocation);
                super.addResourceHandlers(registry);
            }
        };
    }
}
