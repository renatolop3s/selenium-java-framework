package br.com.renatolop3s.sjf.driver;

public interface DriverArguments {
    String START_MAXIMIZED = "--start-maximized";
    String HEADLESS_CHROMIUM = "--headless=new";
    String HEADLESS = "--headless";
    String DISABLE_INFOBARS = "--disable-infobars";
    String DISABLE_EXTENSIONS = "--disable-extensions";
    String DISABLE_NOTIFICATIONS = "--disable-notifications";
    String REMOTE_ALLOW_ORIGINS = "--remote-allow-origins=*";
    String NO_SANDBOX = "--no-sandbox";
    String DISABLE_GPU = "--disable-gpu";
    String DISABLE_DEV_SHM_USAGE = "--disable-dev-shm-usage";
    String IGNORE_CERTIFICATE_ERRORS = "--ignore-certificate-errors";
}
