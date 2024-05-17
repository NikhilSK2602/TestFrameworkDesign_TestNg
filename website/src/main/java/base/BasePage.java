
package base;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.
ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

 

public class BasePage {
    //public static WebDriver driver;
    private String url;
    private Properties prop;
    public static String screenShotDestinationPath;
    public BasePage() throws IOException {
        prop = new Properties();
        FileInputStream data = new FileInputStream(
                System.getProperty("user.dir") + "\\src\\main\\java\\resources\\config.properties");
        prop.load(data);

    }

	public static WebDriver getDriver() throws IOException {
		return WebDriverInstance.getDriver();
        /*if (prop.getProperty("browser").equals("chrome")) {
           System.setProperty("webdriver.chrome.driver",
                    System.getProperty("user.dir") + "\\src\\main\\java\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (prop.getProperty("browser").equals("firefox")) {
            System.setProperty("webdriver.gecko.driver",
                    System.getProperty("user.dir") + "\\src\\main\\java\\drivers\\geckodriver.exe");
            driver = new FirefoxDriver();
            
        } else {
            System.setProperty("webdriver.edge.driver",
                    System.getProperty("user.dir") + "\\src\\main\\java\\drivers\\msedgedriver.exe");
            driver = new EdgeDriver();

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Thread.sleep(5000);

        return driver;*/

    }

 

    public String getUrl() throws IOException {

        url = prop.getProperty("url");
        return url;

    }

 

    public static String takeSnapShot(String name) throws IOException {

        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String destFile = System.getProperty("user.dir") + "\\target\\screenshots\\"
                + timestamp() + ".png";
screenShotDestinationPath = destFile;
		
		try {
			FileUtils.copyFile(srcFile, new File(destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return name;
		

    }

    public static String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());

    }
    public static String getScreenshotDestinationPath() {
		return screenShotDestinationPath;
	}
    
    public static void waitForElementInvisible(WebElement element,Duration timer) throws IOException {
    	WebDriverWait wait = new WebDriverWait(getDriver(),timer);
    	wait.until(ExpectedConditions.invisibilityOf(element));
    }

}