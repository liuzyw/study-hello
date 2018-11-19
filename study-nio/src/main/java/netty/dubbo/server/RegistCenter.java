package netty.dubbo.server;

/**
 * Created on 2018-11-17
 *
 * @author liuzhaoyuan
 */
public interface RegistCenter {

    void register(String serviceName, String url);

}
