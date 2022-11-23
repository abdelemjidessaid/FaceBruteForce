package com.mejidprojects.facebruteforce;

public class GetStarted {

    public static void main (String[] args) {
        FaceBruteForce faceBruteForce = new FaceBruteForce(
                "hanaa.essaid.9",
                "D:\\MY_JAVA_PROJECTS\\FacebookBruteForce\\src\\com\\mejidprojects\\facebruteforce\\hanaa.txt",
                "C://webdrivers//chromedriver.exe"
        );
        faceBruteForce.startAttack();

        // check again and continue
        // error of username occurred when -> reloading the page
    }
}
