## About
Start of Just-Gif-It for Pluralsight course Spring Boot: Efficient Development, Configuration, and Deployment

UI: AngularJS + WebPack + ES6 (ES2015)
Backend: Spring Boot + JavaCV + Animated-Gif-Lib

## License
Version 2.0 of the Apache License

## Intellij Community
File - Project Structure
    set sdk 1.8

## Spring Boot: Efficient Development, Configuration, and Deployment

https://app.pluralsight.com/player?course=spring-boot-efficient-development-configuration-deployment&author=dustin-schultz&name=spring-boot-efficient-development-configuration-deployment-m0&clip=0&mode=live

## run start.spring.io from localhost to generate starter project

git clone git@github.com:spring-io/start.spring.io.git

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home/ mvn clean install

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home/ mvn spring-boot:run


http://localhost:8080/

shultz.dustin.io
just-gif-it
web actuator
curl start.spring.io		# curl access to above web spring start project generator

## mp4 downloaded to convert to gif by app

16s -> 29s https://vimeo.com/47201852 saved as FunnyPhobias.mp4

## curl command to POST file

curl -F file=@FunnyPhobias.mp4 -F start=0 -F end=0 -F speed=1 -F repeat=0 localhost:8080/upload
https://github.com/dustinschultz/just-gif-it

## run up app and gui
mvn spring-boot:run
OR run JustGifItApplication in intellij

```http://localhost:8080/```

shows gui

## devtools auto restart

```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional>
        </dependency>
```

Intellij
Edit - macros - start macro recording
File - save all
Build - Build project
Edit - macros - stop macro recording
name - autoBuild
Preferences
save macro as CMD D
then
if mvn spring-boot:run is running
if you change a file and type CMD D
the program will auto restart.
nice.

## disable properties

application.properties
 newer versions of spring-boot use these properties rather than above
 spring.servlet.multipart.max-file-size=128KB
 spring.servlet.multipart.max-request-size=128KB

 this disables the icon only
    spring.mvc.favicon.enabled=false


## disable stuff in application class

@SpringBootApplication(exclude = {JacksonAutoConfiguration.class,
        JmxAutoConfiguration.class, WebSocketAutoConfiguration.class})
public class JustGifItApplication {
. . .
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

## debug
Add --debug as program argument or -Ddebug VM option.

Then when the app starts up you will see the exclusions you have set:
```
Exclusions:
-----------

   org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration

   org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration

   org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration
```
