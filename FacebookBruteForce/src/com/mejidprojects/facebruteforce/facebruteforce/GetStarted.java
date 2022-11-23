package com.mejidprojects.facebruteforce;

public class GetStarted {

    public static void main (String[] args) {
        FaceBruteForce faceBruteForce = new FaceBruteForce(
                "user.example.15",
                "D://MyFiles//WordLists//user_15.txt",
                "C://webdrivers//chromedriver.exe"
        );
        faceBruteForce.startAttack();
    }
}
