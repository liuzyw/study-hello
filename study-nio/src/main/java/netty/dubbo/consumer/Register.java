package netty.dubbo.consumer;

import java.io.IOException;
import netty.dubbo.server.RegistCenter;
import netty.dubbo.server.impl.RegistCenterImpl;

/**
 * Created on 2018-11-17
 *
 * @author liuzhaoyuan
 */
public class Register {

    public static void main(String[] args) {
        RegistCenter registCenter = new RegistCenterImpl();

        registCenter.register("orderService", "127.0.0.1:9999");



        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
