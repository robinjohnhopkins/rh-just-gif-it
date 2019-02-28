package schultz.dustin.io.health;

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
