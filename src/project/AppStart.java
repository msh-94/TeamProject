package project;

import project.controller.Member_HeadController;

public class AppStart {
    public static void main(String[] args) {
        Member_HeadController hc = Member_HeadController.getInstance();
        hc.signUp(0,3,"test6","test2","test3","test4",null);
    }
}
