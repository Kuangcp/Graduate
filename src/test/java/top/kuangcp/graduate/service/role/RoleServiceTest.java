package top.kuangcp.graduate.service.role;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-4-25  上午9:53
 */
public class RoleServiceTest {

    private RoleService roleService = new RoleService();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoginByStudent() throws Exception {
        boolean result = roleService.loginByStudent("2", "2");
        System.out.println(result);
    }
}