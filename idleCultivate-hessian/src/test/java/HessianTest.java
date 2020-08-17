import com.caucho.hessian.client.HessianProxyFactory;
import com.idleCultivate.common.model.CommonResult;
import com.idleCultivate.user.model.UserAccount;
import com.idleCultivate.user.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class HessianTest {
    protected static final Logger logger = LogManager.getLogger(HessianTest.class);

    public static void main(String[] args) {
        String url = "http://106.52.31.169:8080/idleCultivate-hessian-1.0-SNAPSHOT/remoting/UserService";
        HessianProxyFactory factory = new HessianProxyFactory();
        try {
            UserService userService = (UserService) factory.create(UserService.class, url);
            UserAccount userAccount = userService.register("testuser666", "123456", "127.0.0.1");
            logger.info(userAccount.toString());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
