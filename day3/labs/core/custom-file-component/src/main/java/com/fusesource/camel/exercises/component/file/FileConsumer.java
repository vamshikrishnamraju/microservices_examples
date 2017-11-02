package com.fusesource.camel.exercises.component.file;

import org.apache.camel.Consumer;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.ScheduledPollConsumer;

import java.io.File;

public class FileConsumer
        extends ScheduledPollConsumer
        implements Consumer {
    private FileEndpoint endpoint;

    public FileConsumer(FileEndpoint fileEndpoint, Processor processor) {
        super(fileEndpoint, processor);
        this.endpoint = fileEndpoint;
    }

    @Override
    protected synchronized int poll() throws Exception {
        // should be true the first time as its the top directory
        return pollFileOrDirectory(endpoint.getFile());
    }

    private int pollFileOrDirectory(File fileOrDirectory) {
        if (fileOrDirectory.isFile()) {
            pollFile(fileOrDirectory);
            return 1;
        } else {
            if (!fileOrDirectory.exists()) {
                fileOrDirectory.mkdirs();
            }
            // Only descend one level down into directory
            File[] files = fileOrDirectory.listFiles();
            if (files == null) {
                // No files available. Do nothing
                return 0;
            } else {
                int numFilesPolled = 0;
                for (File file : files) {
                    if (!file.isDirectory()) {
                        pollFile(file);
                        ++numFilesPolled;
                    }
                }
                return numFilesPolled;
            }
        }
    }

    private void pollFile(File file) {
        if (isValidFile(file)) {
            Exchange exchange = endpoint.createExchange();
            exchange.setIn(new FileMessage(file));
            try {
                getProcessor().process(exchange);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Must wait until *after* processing is finished before renaming the file
            File newFile = new File(file.getParentFile(), FileEndpoint.RENAME_PREFIX + file.getName());
            file.renameTo(newFile);
        }
    }

    private boolean isValidFile(File file) {
        return file.exists() && !file.getName().startsWith(FileEndpoint.RENAME_PREFIX);
    }
}
