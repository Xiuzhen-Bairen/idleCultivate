import com.caucho.hessian.client.HessianProxyFactory;
import com.idleCultivate.common.model.CommonResult;
import com.idleCultivate.user.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class HessianTest {
    protected static final Logger logger = LogManager.getLogger(HessianTest.class);

    public static void main(String[] args) {
        String url = "http://localhost:20000/remoting/UserService";
        HessianProxyFactory factory = new HessianProxyFactory();
        try {
            UserService userService = (UserService) factory.create(UserService.class, url);
            CommonResult commonResult = userService.register("testuser666", "123456", "127.0.0.1");
            logger.info(commonResult);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
