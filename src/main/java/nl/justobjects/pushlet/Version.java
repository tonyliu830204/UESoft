package nl.justobjects.pushlet;

public class Version
{
  public static final String SOFTWARE_VERSION = Version.class.getPackage().getSpecificationVersion();
  public static final String BUILD_DATE = Version.class.getPackage().getImplementationVersion();
}