# FaceBruteForce
Facebook brute force attack on users accounts with ***Java***.

1- Install an ***IDE*** for example (Intellij Idea).
2- Install ***JDK*** (Java Development Kit).
3- Download *FaceBruteForce* project & open it in the ***IDE***.
4- Import Selenium Modules to your project with Clicking on **File > Project Structure > Modules > Add > JARs or Directories** & select all files from next Path **FacebookBruteForce\selenium_jar** & press **OK**.
5- Download **chromedriver.exe** and its version required to be the same version number of your installed Google Chrome browser [chromedriver](https://chromedriver.chromium.org/downloads).
6- Move the **chromedriver.exe** to this Directory Path **C:\webdrivers\chromedriver.exe** or another place but you need to modify the code of **GetStarted.java**.
> ### For Example
> You move the **chromedriver.exe** the next path **D:\MyFiles\WebDrivers\chromedriver.exe**.
> **In Code**.
> 'FaceBruteForce faceBruteForce = new FaceBruteForce("username.example.15", "D:\MyFiles\WordLists\target_1.txt","D:\MyFiles\WebDrivers\chromedriver.exe");'
