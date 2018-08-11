package com.binlog;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.BinaryLogClient.EventListener;
import com.github.shyiko.mysql.binlog.BinaryLogFileReader;
import com.github.shyiko.mysql.binlog.event.Event;
import com.github.shyiko.mysql.binlog.event.deserialization.ChecksumType;
import com.github.shyiko.mysql.binlog.event.deserialization.EventDeserializer;
import java.io.File;

/**
 * Created on 2018-08-08
 *
 * @author liuzhaoyuan
 */
public class TestReadBinlog {

    public static void main(String[] args) throws Exception {
        read();
    }


    private static void read() throws Exception {

        String filePath = "/usr/local/var/mysql/mysql-bin.000001";
        File binlogFile = new File(filePath);
        EventDeserializer eventDeserializer = new EventDeserializer();
        eventDeserializer.setChecksumType(ChecksumType.CRC32);
        BinaryLogFileReader reader = new BinaryLogFileReader(binlogFile, eventDeserializer);
        try {
            for (Event event; (event = reader.readEvent()) != null; ) {
                System.out.println(event.toString());
            }
        } finally {
            reader.close();
        }

    }

    private static void listener() throws Exception {
        BinaryLogClient client = new BinaryLogClient("localhost", 3306, "root", "123456");
        client.registerEventListener(new EventListener() {

            @Override
            public void onEvent(Event event) {
                System.out.println(event.toString());
            }
        });
        client.connect();
    }
}
