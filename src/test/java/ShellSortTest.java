import com.kpi.parallel.Generator;
import com.kpi.parallel.ShellSortUtil;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ShellSortTest {

    @ParameterizedTest(name = "Test {index}: is sorted a list with {0} size")
    @ValueSource(ints = {50000, 100000, 500000, 1000000, 5000000, 10000000})
    void test_list_sorted_plain(int count) {
        List<Integer> listPlain = Generator.generate(count);

        long start = System.currentTimeMillis();
        ShellSortUtil.shellSort(listPlain);
        long end = System.currentTimeMillis();

        System.out.printf("Size: %d; process took %d milliseconds%n", count, end - start);

        assertTrue(ShellSortUtil.isSortedAscending(listPlain));
    }

    @ParameterizedTest(name = "Test {index}: is sorted a list with {0} size")
    @ValueSource(ints = {50000, 100000, 500000, 1000000, 5000000, 10000000})
    void test_list_sorted_parallel(int count) {
        List<Integer> listPlain = Generator.generate(count);
        List<Integer> listParallel = new ArrayList<>(listPlain);
        int threads = 12;

        ShellSortUtil.shellSort(listPlain);
        ShellSortUtil.shellSortParallel(listParallel, threads);

        assertTrue(ShellSortUtil.isSortedAscending(listParallel));
        assertTrue(ShellSortUtil.compareLists(listPlain, listParallel));
    }

}
