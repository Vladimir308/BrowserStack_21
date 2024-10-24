package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:android.properties")
public interface BrowserstackDriverConfig extends Config {

    @Key("device")
    String getDevice();

    @Key("os_version")
    String getOsVersion();

    @Key("app")
    String getApp();

}
