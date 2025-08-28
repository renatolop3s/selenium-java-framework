package br.com.renatolop3s.sjf.config;

import br.com.renatolop3s.sjf.enums.Target;
import org.aeonbits.owner.Accessible;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.ConfigCache;
import org.aeonbits.owner.Converter;
import org.aeonbits.owner.Mutable;

import java.lang.reflect.Method;

import static org.aeonbits.owner.Config.LoadType.MERGE;

@LoadPolicy(MERGE)
@Sources({
        "system:properties",
        "file:src/test/resources/local.properties",
        "file:src/test/resources/env.properties",
        "file:src/main/resources/reportportal.properties",
        "system:env"
})
public interface Configuration extends Mutable, Accessible {

    @Key("target")
    @DefaultValue("local")
    @ConverterClass(TargetToUpperCase.class)
    Target target();

    @Key("env")
    @DefaultValue("qa")
    String env();

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("browser.size")
    @DefaultValue("1280x720")
    String browserSize();

    @Key("headless")
    @DefaultValue("false")
    boolean headless();

    @Key("timeout")
    @DefaultValue("4")
    int timeout();

    @Key("remote.host")
    @DefaultValue("localhost")
    String remoteHost();

    @Key("remote.port")
    @DefaultValue("4444")
    String remotePort();

    @DefaultValue("http://${remote.host}:${remote.port}/wd/hub")
    String remoteUrl();

    @Key("remote.session.timeout")
    @DefaultValue("1m")
    String remoteSessionTimeout();

    @Key("remote.enable.vnc")
    @DefaultValue("true")
    boolean remoteEnableVnc();

    @Key("remote.enable.video")
    @DefaultValue("false")
    boolean remoteEnableVideo();

    @Key("rp.endpoint")
    String rpEndpoint();

    @Key("rp.api.key")
    String rpApiKey();

    @Key("rp.project")
    String rpProject();

    @Key("rp.launch")
    String rpLaunch();

    @Key("rp.enable")
    @DefaultValue("false")
    boolean rpEnable();

    @Key("${env}.base.url")
    String baseUrl();

    class TargetToUpperCase implements Converter<Target> {
        @Override public Target convert(Method method, String s) { return Target.valueOf(s.toUpperCase()); }
    }

    static Configuration cfg() {
        return ConfigCache.getOrCreate(Configuration.class);
    }
}
