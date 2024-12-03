package br.com.renatolop3s.sjf.config;

import org.aeonbits.owner.Accessible;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.ConfigCache;
import org.aeonbits.owner.Mutable;

import static org.aeonbits.owner.Config.LoadType.MERGE;

@LoadPolicy(MERGE)
@Sources({
        "system:properties",
        "file:src/test/resources/config.properties",
        "file:src/test/resources/env/dev.properties",
        "file:src/test/resources/env/qa.properties",
        "file:src/test/resources/env/stage.properties",
        "file:src/test/resources/env/prod.properties",
        "file:src/main/resources/reportportal.properties",
        "system:env"
})
public interface Configuration extends Mutable, Accessible {

    @DefaultValue("local")
    @Key("target")
    String target();

    @DefaultValue("qa")
    @Key("env")
    String env();

    @DefaultValue("chrome")
    @Key("browser")
    String browser();

    @DefaultValue("false")
    @Key("headless")
    boolean headless();

    @DefaultValue("localhost")
    @Key("grid.host")
    String gridHost();

    @DefaultValue("4444")
    @Key("grid.port")
    String gridPort();

    @DefaultValue("http://${grid.host}:${grid.port}/wd/hub")
    String gridUrl();

    @DefaultValue("1m")
    @Key("grid.session.timeout")
    String gridSessionTimeout();

    @DefaultValue("true")
    @Key("grid.enable.vnc")
    boolean gridEnableVnc();

    @DefaultValue("false")
    @Key("grid.enable.video")
    boolean gridEnableVideo();

    @Key("rp.endpoint")
    String rpEndpoint();

    @Key("rp.api.key")
    String rpApiKey();

    @Key("rp.project")
    String rpProject();

    @Key("rp.launch")
    String rpLaunch();

    @Key("rp.enable")
    boolean rpEnable();

    @Key("${env}.url")
    String url();

    static Configuration cfg() {
        return ConfigCache.getOrCreate(Configuration.class);
    }
}
