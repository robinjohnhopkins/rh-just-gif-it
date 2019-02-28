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
and if you change a file and type CMD D
the program will auto restart.
nice.
```


## disable properties
```
application.properties
 #newer versions of spring-boot use these properties rather than above
 spring.servlet.multipart.max-file-size=128KB
 spring.servlet.multipart.max-request-size=128KB

 #this disables the icon only
 spring.mvc.favicon.enabled=false
```

## intellij command completion
It is important to turn off spotlight search
```
Settings - Spotlight - shortcuts untick Show spotlight search
```

Then install Spring Assistant

You will need to add
```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>
```
to pom.


## yaml properties
```
\# configure auto-configured MultipartConfigElement
multipart:
  max-file-size: 50MB
  max-request-size: 50MB
  location: ${java.io.tmpdir}

\# newer versions of spring-boot use these properties rather than above
\# spring.servlet.multipart.max-file-size=128KB
\# spring.servlet.multipart.max-request-size=128KB

logging:
  level.: DEBUG

\## this disables the icon only
spring:
  mvc:
    favicon:
      enabled: false
```


## disable stuff in application class

```
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
```

## debug
Add ```--debug``` as program argument or ```-Ddebug``` VM option.

OR
```
application.properties
    logging.level.=DEBUG
```
OR

application.yml
```
logging:
  level.:DEBUG
```

Then when the app starts up you will see the exclusions you have set:
```
Exclusions:
-----------

   org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration

   org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration

   org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration
```

## Presence or Absence of Class on Classpath
```
@ConditionalOnClass

Attributes

[]
name = { "a.b.c.Foo" }

[]
value = { Foo.class }

@ConditionalOnMissingClass
Attributes

[]
name = { "a.b.c.Foo" }

```


## Presence or Absence of Defined Bean
```
@ConditionalOnBean

Attributes

[]
name = { “dataSource” }

[]
value = { Foo.class }

[]
type = { “a.b.c.Foo” }

[]
annotation = { Foo.class }

enum
search = { ALL }
```

```
@ConditionalOnMissingBean

Attributes

[]
name = { “dataSource” }
 []
value = { Foo.class }

enum
type = { “a.b.c.Foo” }
 []
annotation = { Foo.class }

[]
search = { ALL }
 []
ignored = { “a.b.c.Foo” }

[]
ignoredType = { Foo.class }
```

## Presence or Absence of a Property Having Value
```
@ConditionalOnProperty
Attributes

[]
name = { “my-property” }

[]
value = { “my-property” }

String
havingValue = “foo”

[]
prefix = { “some.prefix” }

boolean
matchIfMissing = false

boolean
relaxedNames = true

```

## Additional Conditions
```
• @ConditionalOnJava
• @ConditionalOnJndi
• @ConditionalOnResource
• @ConditionlOnExpression
• @ConditionlOnWebApplication
• @ConditionalOnNotWebApplication
```

## .properties
```
# A list
numbers[0]=one 
numbers[1]=two
# Inline list
numbers: [one,two]
```
## .yml
```
# A list
numbers:
  - one
  - two
# Inline list
numbers=one,two
```

## @ConfigurationProperties

a. Annotate with @ConfigurationProperties
b. Define getters & setters (JavaBean Spec)
c. Annotate with @Component
    i. Can also use @EnableConfigurationProperties


```
my.feature-enabled=true

my:
 feature-enabled: true
```

```
#@Component
#@ConfigurationProperties(prefix = "my")
#public class MyConfig {
#
#    @NotNull
#    @Pattern( regexp = "my.{5}zip", message = "regex check should match my?{5}zip")
#    private String myString;
#    private long myLong;
#    private boolean myBoolean;

my:
  my-string: my12345zip
  myLong: 1234.5
  my-boolean: true
```

## Pimp your Charts with the fancy XKCD Style
https://devops.datenkollektiv.de/pimp-your-charts-with-the-fancy-xkcd-style.html


## Spring Boot – Custom Banner

To add a custom banner in Spring Boot application, create a banner.txt file and put it into the resources folder.

The following can be used to generate a header:

https://github.com/acanda/spring-banner-plugin


#### Usage
```
<build>
    <plugins>
        <plugin>
            <groupId>ch.acanda.maven</groupId>
            <artifactId>spring-banner-plugin</artifactId>
            <version>1.0</version>
            <executions>
                <execution>
                    <id>generate-spring-banner</id>
                    <phase>generate-resources</phase> (1)
                    <goals>
                        <goal>generate</goal>
                    </goals>
                </execution>
            </executions>
            <configuration> (2)
                <text>${project.name}</text>
                <outputDirectory>${project.build.outputDirectory}</outputDirectory>
                <filename>banner.txt</filename>
                <includeInfo>true</includeInfo>
                <info>Version: ${application.version}, Server: ${server.address}:${server.port}, Active Profiles: ${spring.active.profiles}</info>
                <version>${project.version}</version>
                <color>default</color>
            </configuration>
        </plugin>
    </plugins>
</build>
```

```
 _  __  ___            ____                                        _         _
| |/ / ( _ )  ___     / ___|  __ _  _ __    __ _   ___  _ __ ___  (_) _ __  (_)
| ' /  / _ \ / __|   | |     / _` || '_ \  / _` | / _ \| '_ ` _ \ | || '_ \ | |
| . \ | (_) |\__ \ _ | |___ | (_| || |_) || (_| ||  __/| | | | | || || | | || |
|_|\_\ \___/ |___/(_) \____| \__,_|| .__/  \__, | \___||_| |_| |_||_||_| |_||_|
                                   |_|     |___/
Version: 1.1, Server: localhost:1234.
```

