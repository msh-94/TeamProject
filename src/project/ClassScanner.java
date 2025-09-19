package project; // 패키지명

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ClassScanner { //  class start
    public static List<Class<?>> scan(String basePackage) throws Exception {
        // 패키지명을 파일 경로로 변환
        String path = basePackage.replace('.', '/');
        // 클래스 로더를 이용해서 해당 경로의 URL 가져오기
        URL url = Thread.currentThread().getContextClassLoader().getResource(path);
        if (url == null){
            throw new RuntimeException("패키지를 찾을 수 없습니다 : " + basePackage);
        }// if end
        // URL을 File 객체로 변환 (디렉토리 탐색용)
        File dir = new File(url.toURI());
        List<Class<?>> classes = new ArrayList<>();
        // 디렉토리 안의 모든 파일 탐색
        for (File file : dir.listFiles()) {
            // .class 파일만 처리
            if (file.getName().endsWith(".class")) {
                // 파일 이름에서 ".class" 제거 후 패키지명 붙이기
                String className = basePackage + "." + file.getName().replace(".class", "");
                // Class 객체로 로딩
                classes.add(Class.forName(className));
            } // if end
        }// for end
        return classes;
    }// func end
}// class end
