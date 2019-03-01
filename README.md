## About
Notes added to Just-Gif-It for Pluralsight course Spring Boot: Efficient Development, Configuration, and Deployment

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

## SPRING_APPLICATION_JSON env var
This environment var will override any properties found.

```
SPRING_APPLICATION_JSON {"my":{"my-long":"9876"}}
```

## Commandline props override

prefix any property with a double dash
```
--server.port=9000
--spring.config.name=config
--debug

System.getenv("SPRING_APPLICATION_JSON")
```

## config third party Beans
```
@Configuration
public class MyConfig 3{
 @Bean
 @ConfigurationProperties(
 prefix = "config.some-bean")
 public SomeBean someBean()
 {
  // Has getters & setters
  return new SomeBean()
 }
}
```
application.properties
```
 # someBean has setFirstName method
 config.some-bean.first-name=Dustin
 # someBean has setLastName method
```

## Relaxed Configuration Names

Camel Case

featureEnabled

Dash Notation

feature-enabled

Underscore

PREFIX_FEATURE_ENABLED


## StandardServletEnvironment

A hierarchy within itself
```
a) ServletConfig init parameters
b) ServletContext init parameters
c) JNDI attributes
d) System.getProperties()
e) OS environment vars
```

## RandomValuePropertySource
```
  ${random.*} replacements •“ * ” can be one of
  A. value
  B. int
  C. long
  D. int(<number>)
  E. int[<num1>,<num2>] 
```

## application.properties / YAML + Variants
```
•Look for profile-specific configuration 1st
• application-{profile}.properties
• application-{profile}.yml
•Look for generic configuration 2nd
•application.properties / application.yml
•Check these locations
•$CWD/config AND $CWD •classpath:/config AND classpath:
```

## @PropertySource
```
1 @SpringBootApplication
2 @PropertySource("/some/path/foo.properties")
3 public class MyApplication {
4 ...
5}
```

## Default Properties
```
1 @SpringBootApplication
2 public class MyApplication {
3 public static void main(String args[])
4{
5 SpringApplication.setDefaultProperties(...)
6}
7}
```

## Introducing Spring Boot Actuator
```
•Production ready monitoring and   management features out of the box
•Health, autoconfig report, beans, etc •HTTP or JMX
•Feed into Nagios / Zabbix / New Relic •Easy to add your own
```
```
<dependency> 
    <groupId>org.springframework.boot</groupId> 
    <artifactId>spring-boot-starter-actuator</artifactId> 
 </dependency>
 ```

 ## Builtin Production Ready Endpoints
 ```
 /autoconfig for report
 /dump for memory dump
 /beans for all beans
 /health to check application

 {"status":"UP","diskSpace":{"status":"UP","total":250790436864,"free":124369686528,"threshold":10485760}}

 /configprops  for all config

 Many more ...
 http://docs.spring.io/ spring-boot/docs/current/ reference/htmlsingle/ #production-ready
 ```

## Autoconfigured HealthIndicator’s RedisHealthIndicator

* DiskSpaceHealthIndicator
* DataSourceHealthIndicator
* ElasticsearchHealthIndicator

http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/ #_auto_configured_healthindicators


## Custom HealthIndicator’s CassandraHealthIndicator
* DiskSpaceHealthIndicator
* DataSourceHealthIndicator
* ElasticsearchHealthIndicator
* MyCustomHealthIndicator

## Custom health
```
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import schultz.dustin.io.services.MyConfig;

import javax.inject.Inject;

@Component
public class JustGifItHealthIndicator implements HealthIndicator {

@Inject
private MyConfig properties;

    @Override
    public Health health() {
        if (!properties.getGifLocation().canWrite()) {
            return Health.down().build();
        }

        return Health.up().build();
    }
}
```

`curl -u user:a1c56301-47bd-aaaa-aaaaaaaaaaaa http://localhost:8080/health`

where `password` is found in logs


```
{"status":"UP","justGifIt":{"status":"UP"},"diskSpace":{"status":"UP","total":250790436864,"free":124032151552,"threshold":10485760}}
```

https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-security.html

If Spring Security is on the classpath, then web applications are secured by default. Spring Boot relies on Spring Security’s content-negotiation strategy to determine whether to use httpBasic or formLogin. To add method-level security to a web application, you can also add @EnableGlobalMethodSecurity with your desired settings. Additional information can be found in the Spring Security Reference Guide.


## maven docker plugin

https://github.com/spotify/docker-maven-plugin

This led to the creation of a second Maven plugin for building docker images, dockerfile-maven, which we think offers a simpler mental model of working with Docker from Maven, for all of the reasons outlined in dockerfile-maven's README.

https://github.com/spotify/dockerfile-maven

```
            <plugin>
                <groupId>com.spotify</groupId>
                <artifactId>dockerfile-maven-plugin</artifactId>
                <version>${dockerfile-maven-version}</version>
                <executions>
                    <execution>
                        <id>default</id>
                        <goals>
                            <goal>build</goal>
                            <goal>push</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <repository>robinjohnhopkins/justgifit</repository>
                    <tag>latest</tag>
                    <!--<tag>${project.version}</tag>-->
                    <buildArgs>
                        <JAR_FILE>${project.build.finalName}.jar</JAR_FILE>
                    </buildArgs>
                </configuration>
            </plugin>
```

This configures the actual plugin to build your image with mvn package and push it with mvn deploy. Of course you can also say mvn dockerfile:build explicitly.


A corresponding Dockerfile could look like:

```
FROM openjdk:8-jre
MAINTAINER David Flemström <dflemstr@spotify.com>

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/myservice/myservice.jar"]

# Add Maven dependencies (not shaded into the artifact; Docker-cached)
ADD target/lib           /usr/share/myservice/lib
# Add the service itself
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/myservice/myservice.jar
```

Important note

The most Maven-ish way to reference the build artifact would probably be to use the project.build.directory variable for referencing the 'target'-directory. However, this results in an absolute path, which is not supported by the ADD command in the Dockerfile. Any such source must be inside the context of the Docker build and therefor must be referenced by a relative path. See https://github.com/spotify/dockerfile-maven/issues/101

Do not use ${project.build.directory} as a way to reference your build directory.


```
docker run -it -p80:8080 robinjohnhopkins/justgifit:latest
```

http://localhost

Nice

## deploy to aws

login to aws cli
$(aws ecr get-login --no-include-email --region us-east-2)

docker build -t robinjohnhopkins/justgifit .
docker tag robinjohnhopkins/justgifit:latest 433651427572.dkr.ecr.us-east-2.amazonaws.com/robinjohnhopkins/justgifit:latest
docker push 433651427572.dkr.ecr.us-east-2.amazonaws.com/robinjohnhopkins/justgifit:latest

deploy using ECS Clusters

https://us-east-2.console.aws.amazon.com/ecs/home?region=us-east-2#/clusters

click Get Started rather than Create Cluster and follow through steps.

## kubectl k8s

docker push robinjohnhopkins/justgifit:latest

kubectl get all

kubectl delete <stuff from above>

kubectl create -f svc.yml

kubectl create -f deploy.yml

kubectl get all

kubectl logs pod/justgifit-deploy-7786c6c568-4kz2l
 _  __  ___            ____                                        _         _
| |/ / ( _ )  ___     / ___|  __ _  _ __    __ _   ___  _ __ ___  (_) _ __  (_)
| ' /  / _ \ / __|   | |     / _` || '_ \  / _` | / _ \| '_ ` _ \ | || '_ \ | |
| . \ | (_) |\__ \ _ | |___ | (_| || |_) || (_| ||  __/| | | | | || || | | || |
|_|\_\ \___/ |___/(_) \____| \__,_|| .__/  \__, | \___||_| |_| |_||_||_| |_||_|
                                   |_|     |___/

. . .

## check a GET rest end point in browser
```
minikube service list
|-------------|----------------------|---------------------------|
|  NAMESPACE  |         NAME         |            URL            |
|-------------|----------------------|---------------------------|
| default     | justgifit-svc        | http://192.168.64.2:30001 |
| default     | kubernetes           | No node port              |
| kube-system | kube-dns             | No node port              |
| kube-system | kubernetes-dashboard | No node port              |
|-------------|----------------------|---------------------------|
```

Here the justgifit-svc  has exposed the internal port 8080 to external port 30001.
Thus you can try the following endpont in a browser.

http://192.168.x.x:30001

