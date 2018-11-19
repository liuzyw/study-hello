package netty.dubbo.consumer;

import java.io.Serializable;

/**
 * Created on 2018-11-17
 *
 * @author liuzhaoyuan
 */
public class Order implements Serializable {

    private static final long serialVersionUID = 21321312L;

    private int id;

    private int type;

    private String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
