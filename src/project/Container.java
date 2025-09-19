package project; // 패키지명

import project.controller.*;
import project.model.dao.*;


import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;


/**
 *
 */
public class Container { // class start
    /**
     * 모든 객체를 저장하는 map
     * key: 클래스타입
     * value: 실제 객체
     */
    private static final Map< Class<?> , Object > beans = new HashMap<>();
    public static void find(String base){
        try{ // 클래스 스캔
            List<Class<?>> classes = ClassScanner.scan(base);
            for (Class<?> cl : classes){
                // 클래스 이름 소문자로 바꾸기
                String name = cl.getSimpleName().toLowerCase();
                // 포함된 이름 찾기
                if ( name.endsWith("dao") || name.endsWith("controller") || name.endsWith("view") ){
                    // 찾으면 기본생성자 호출해서 인스턴스 생성
                    Object instance = cl.getDeclaredConstructor().newInstance();
                    beans.put(cl,instance); // 저장
                }// if end
            }// for end
            // 의존성 주입
            for (Object bean : beans.values()){
                // 해당 객체 모든 변수 가져오기
                for (Field field : bean.getClass().getDeclaredFields()){
                    Class<?> type = field.getType();
                    // 같은 타입 존재하면 인스턴스 주입
                    if (beans.containsKey(type)){
                        field.setAccessible(true);
                        field.set(bean,beans.get(type));
                    }// if end
                }
            }
        } catch (Exception e) { System.out.println("e = " + e); }
    }// func end

    /**
     * @param type 가져올 클래스 타입
     * @param <T> 제네릭 타입
     * @return 해당 클래스의 bean 인스턴스
     */
    public static <T> T getBean(Class<T> type){
        return type.cast(beans.get(type)); // T 타입으로 캐스팅
    }// func end


}// class end
