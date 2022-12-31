package tools;

import org.junit.jupiter.api.Test;

public class TaskExecutorTest {
    @Test
    public void test() {
        String[] args = {"help"};
        TaskExecutor executor = new TaskExecutor();
        executor.runTask(args);
    }
}
