package project;

import project.controller.Member_HeadController;

public class AppStart {
    public static void main(String[] args) {
        Member_HeadController hc = Member_HeadController.getInstance();
        hc.signUp("test","test","test","010-1234-1234");
    }
}
