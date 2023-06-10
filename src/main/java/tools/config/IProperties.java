package tools.config;

public interface IProperties {

  String getProperty(String key);

  void setProperty(String key, String value);
}
