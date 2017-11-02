package com.fusesource.camel.exercises.component.file;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.apache.camel.util.ExchangeHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileProducer extends DefaultProducer {
    private FileEndpoint endpoint;
    private int fileCounter = 0;

    public FileProducer(FileEndpoint fileEndpoint) {
        super(fileEndpoint);
        this.endpoint = fileEndpoint;
        if (!fileEndpoint.getFile().exists()) {
            fileEndpoint.getFile().mkdirs();
        }
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        File target = createFileName();
        InputStream in = ExchangeHelper.getMandatoryInBody(exchange, InputStream.class);
        writeFileByStream(in, target);
    }

    protected File createFileName() {
        fileCounter++;
        File fileOrDirectory = endpoint.getFile();
        if (fileOrDirectory.isDirectory()) {
            String name = "_out_" + (new Integer(fileCounter)).toString();
            return new File(fileOrDirectory, name);
        } else {
            String name = fileOrDirectory.getName() + "_" + (new Integer(fileCounter)).toString();
            return new File(fileOrDirectory.getParentFile(), name);
        }
    }

    private void writeFileByStream(InputStream in, File target) throws IOException {
        FileChannel out = null;
        try {
            out = new FileOutputStream(target).getChannel();

            int size = endpoint.getBufferSize();
            byte[] buffer = new byte[size];
            ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
            while (true) {
                int count = in.read(buffer);
                if (count <= 0) {
                    break;
                } else if (count < size) {
                    byteBuffer = ByteBuffer.wrap(buffer, 0, count);
                    out.write(byteBuffer);
                    break;
                } else {
                    out.write(byteBuffer);
                    byteBuffer.clear();
                }
            }
        } finally {
            in.close();
            out.close();
        }
    }
}
