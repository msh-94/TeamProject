package project; // 패키지명

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ClassScanner { //  class start
    public static List<Class<?>> scan(String basePackage) throws Exception {
        // 패키지명을 파일 경로형식으로 변환
        String path = basePackage.replace('.', '/');
        // 클래스 로더를 이용해서 해당 경로의 URL 가져오기
        URL url = Thread.currentThread().getContextClassLoader().getResource(path);
        if (url == null){
            throw new RuntimeException("패키지를 찾을 수 없습니다 : " + basePackage);
        }// if end
        // URL을 File 객체로 변환 (디렉토리 탐색용)
        File dir = new File(url.toURI());
        List<Class<?>> classes = new ArrayList<>();
        // 실제 디렉토리 탐색 (하위 디렉토리 포함)
        scanDirectory( dir , basePackage , classes );
        return classes;
    }// func end

    private static void scanDirectory(File dir, String basePackage, List<Class<?>> classes) throws Exception {
        // 디렉토리 안의 모든 파일 탐색
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                // 하위 디렉토리일 경우 재귀호출
                scanDirectory(file, basePackage + "." + file.getName(), classes);
            } else if (file.getName().endsWith(".class")) {
                // .class 파일일 경우 클래스명으로 변환
                String className = basePackage + "." + file.getName().replace(".class", "");
                classes.add(Class.forName(className));
            }// if end
        }// for end
    }// func end
}// class end
