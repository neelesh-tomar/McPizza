package org.qa.tests;

import org.qa.base.TestBase;
import org.qa.pages.CommonPage;
import org.qa.pages.LoginPage;
import org.qa.pages.WelcomePage;
import org.qa.util.Utility;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageTest extends TestBase {

    LoginPage loginPage;
    CommonPage commonPage;
    WelcomePage welcomePage;

    public LoginPageTest() {
        super();
    }

    @BeforeMethod
    public void setUp() throws Exception {
        initialize();

        commonPage = new CommonPage();
        welcomePage = new WelcomePage();
        loginPage = new LoginPage();
    }

    @Test(priority = 1)
    public void verifyStartChat() {
        commonPage.startChat();

        Assert.assertTrue(commonPage.isChatOpen());
    }

    @Test(priority = 2)
    public void verifyWelcomeMessage() {
        commonPage.startChat();
        String msg = welcomePage.getWelcomeMessage();

        Assert.assertEquals(msg, "Welcome to Pizza Shoppe");
    }

    @Test(priority = 3)
    public void clickGetStarted() {
        commonPage.startChat();
        String btnText = welcomePage.getStartedBtnText();
        System.out.println(btnText);

        Assert.assertEquals(btnText, "Get Started");

        welcomePage.clickGetStarted();
    }

    @Test(priority = 4)
    public void verifyLoginScreenText() throws Exception {
        commonPage.startChat();
        welcomePage.clickGetStarted();

        String loginScreenText = loginPage.getLoginPageMsg();
        System.out.println(loginScreenText);

        Assert.assertEquals(loginScreenText, "Please enter your details to proceed");
    }

    @DataProvider(name = "LoginTestData")
    public Object[][] getLoginTestData() {
        Object[][] data = Utility.getTestData("LoginData");
        return data;
    }

    @Test(priority = 5, dataProvider = "LoginTestData")
    public void verifyLogin(String fName, String email, String passFail) throws Exception {

        commonPage.startChat();
        welcomePage.clickGetStarted();

        loginPage.login(fName, email);

        if (passFail.equalsIgnoreCase("fail")) {
            Assert.assertTrue(loginPage.firstNameError() || loginPage.emailError());
        }
    }

    @AfterMethod
    public void tearUp() {
        driver.quit();
    }
}