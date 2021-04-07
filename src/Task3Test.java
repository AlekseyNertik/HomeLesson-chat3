import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class Task3Test {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {new int[]{1,11,4,4,1,4,1}, false},
                {new int[]{1,1,1,1,1,4,4,1,1,1}, true},
                {new int[]{4,4,1,1,4,4,1,44}, false}
        });
    }
    private int[] in;
    private boolean out;

    public Task3Test(int[] in, boolean out) {
        this.in = in;
        this.out = out;
    }
    private Task3 test1;

    @Before
    public void startTest() {
        test1 = new Task3();
    }

    @Test
    public void Task3Test() {
        Assert.assertEquals(Task3.task31(in), out);
//        Assert.assertArrayEquals(out, Task3.task31(in));
    }
}
