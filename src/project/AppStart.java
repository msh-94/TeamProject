package project;

import project.controller.Member_HeadController;
import project.controller.PlanController;
import project.model.dto.PlanDto;
import project.view.TotalView;

public class AppStart {
    public static void main(String[] args) {

        TotalView.getInstance().index(); // 본사 > 사용자단 > 메인공통화면(view)
        //PlanController.getInstance().planEdit(new PlanDto(1,"test",2,2));


    }
}//class end