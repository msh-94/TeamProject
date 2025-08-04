package project;

import project.controller.Member_HeadController;
import project.view.TotalView;

import java.util.ArrayList;
import java.util.Map;

public class AppStart {
    public static void main(String[] args) {

        //TotalView.getInstance().index(); // 본사 > 사용자단 > 메인공통화면(view)
        ArrayList<Map<String,Object>> result = Member_HeadController.getInstance().planUserList();
        System.out.println(result);

    } // 푸시용
}//class end