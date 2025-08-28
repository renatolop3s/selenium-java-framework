package br.com.renatolop3s.sjf.listeners;

import br.com.renatolop3s.sjf.enums.Target;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.ITestListener;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static br.com.renatolop3s.sjf.config.Configuration.cfg;

@Slf4j
public class AllureEnvironmentListener implements ITestListener {

    @Override
    public void onFinish(ITestContext context) {
        log.info("Creating environment.properties");
        try (OutputStream output = Files.newOutputStream(Paths.get("target/allure-results/environment.properties"))) {
            final Properties properties = new Properties();
            properties.setProperty("url", cfg().baseUrl());
            properties.setProperty("env", cfg().env().toUpperCase());
            properties.setProperty("target", cfg().target().name());
            if (cfg().target() == Target.REMOTE) {
                properties.setProperty("grid.url", cfg().remoteUrl());
            }
            properties.store(output, "Environment properties for Allure Report");
        } catch (IOException e) {
            log.error("Failed to store environment properties", e);
        }
    }
}
