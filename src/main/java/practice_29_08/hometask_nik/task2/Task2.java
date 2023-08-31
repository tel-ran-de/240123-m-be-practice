package practice_29_08.hometask_nik.task2;


import java.io.File;

public class Task2 {
    public static void main(String[] args) {
        FileProcessor smallFileProcessor = new FileProcessor(10);
//        FileProcessor largeFileProcessor = new FileProcessor(100);

        smallFileProcessor.process(null, null);
//        smallFileProcessor.process(new File("C:\\Users\\Slonick\\IdeaProjectsUltimate\\240123-m-be-practice\\src\\main\\java\\practice_29_08\\hometask_nik\\task2\\file.txt"), line -> {
//            try {
//                Thread.sleep(1000);
//                System.out.println(line);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//                throw new RuntimeException(e);
//            }
//        });
//        smallFileProcessor.closeExecutor();
        System.out.println("End");
    }
}
