package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtils;

public class AllureListener implements ITestListener {
    //SI EL TEST FALLA ESCUCHA Y SACA SCREENSHOT PARA EL REPORTE DE ALLURE
    @Override
    public void onTestFailure(ITestResult result) {
        ScreenshotUtils.attachScreenshot("Failure");
    }
}