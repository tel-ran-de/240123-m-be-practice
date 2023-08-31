package practice_29_08.hometask_nik.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

class FileProcessorTest {

    @Test
    void processNormalWork() {

        FileProcessor fileProcessor = new FileProcessor(10);

        File myFile = new File("C:\\Users\\Slonick\\IdeaProjectsUltimate\\240123-m-be-practice\\src\\test\\java\\practice_29_08\\hometask_nik\\task2\\file.txt");
        Processor processor = Mockito.mock(Processor.class);

        fileProcessor.process(myFile, processor);

        verify(processor, times(1)).process("11111");
        verify(processor, times(1)).process("22222");
        verify(processor, times(1)).process("33333");
        verify(processor, times(1)).process("44444");
        verify(processor, times(1)).process("55555");
        verify(processor, times(1)).process("66666");
        fileProcessor.closeExecutor();
    }

    @Test
    void processEmptyFileCosesToZeroCall() {
        FileProcessor fileProcessor = new FileProcessor(10);
        //empty file
        File myFile = new File("C:\\Users\\Slonick\\IdeaProjectsUltimate\\240123-m-be-practice\\src\\test\\java\\practice_29_08\\hometask_nik\\task2\\file2.txt");
        Processor processor = Mockito.mock(Processor.class);

        fileProcessor.process(myFile, processor);

        verify(processor, times(0)).process(anyString());
        fileProcessor.closeExecutor();
    }

    @Test
    void procesMustCallByDifferentThreads() {

        FileProcessor fileProcessor = new FileProcessor(10);

        File myFile = new File("C:\\Users\\Slonick\\IdeaProjectsUltimate\\240123-m-be-practice\\src\\test\\java\\practice_29_08\\hometask_nik\\task2\\file.txt");
        Processor processor = Mockito.mock(Processor.class);
        Set<String> setOfThreadNames = new HashSet<>();

        doAnswer(gogo -> {
            setOfThreadNames.add(Thread.currentThread().getName());
            return null;
        }).when(processor).process(anyString());

        fileProcessor.process(myFile, processor);
        int numberOfLinesInFile = 6;
        Assertions.assertEquals(numberOfLinesInFile, setOfThreadNames.size());
        fileProcessor.closeExecutor();
    }

    @Test
    void noActionAfterNullArguments() {
        FileProcessor fileProcessor = new FileProcessor(10);

        Processor processor = Mockito.mock(Processor.class);

        fileProcessor.process(null, processor);
        verify(processor, times(0)).process(anyString());
        fileProcessor.closeExecutor();
    }
}