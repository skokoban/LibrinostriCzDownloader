package tools.config;

public class PropertiesFactory {
/*=================================================================================================
                                                Attributes
=================================================================================================*/
/*=================================================================================================
                                                Constructor
=================================================================================================*/
  private PropertiesFactory() {
    throw new RuntimeException("Utility class");
  }
/*=================================================================================================
                                                Methods
=================================================================================================*/
  public static PropertiesProvider getPropertiesProvider() {
    LocationProvider locationProvider = new LocationProvider();
    java.io.File config = locationProvider.configFile();
    return new PropertiesProvider(config);
  }
}
