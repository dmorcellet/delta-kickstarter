package delta.kickstarter;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import delta.launcher.Launcher;
import delta.launcher.data.LauncherConfiguration;
import delta.launcher.data.io.xml.LauncherConfigurationXmlIO;
import delta.updates.engine.UpdateController;

/**
 * Application launcher.
 * Launches a main class using a classpath specified in a XML file.
 * @author DAM
 */
public class Kickstarter
{
  private static final Logger LOGGER=LoggerFactory.getLogger(Kickstarter.class);

  private static void checkForUpdates()
  {
    UpdateController ctrl=new UpdateController();
    File rootAppDir=new File(".");
    ctrl.doIt(rootAppDir);
  }

  private static void startApp()
  {
    LOGGER.info("Starting app!");
    File configFile=new File("launcher.xml");
    LauncherConfiguration config=LauncherConfigurationXmlIO.parseFile(configFile);
    Launcher.launch(config);
  }

  /**
   * Main method for this test.
   * @param args Not used.
   */
  public static void main(String[] args)
  {
    try
    {
      checkForUpdates();
      startApp();
    }
    catch(Throwable t)
    {
      LOGGER.error("Caught fatal exception!", t);
    }
  }
}
