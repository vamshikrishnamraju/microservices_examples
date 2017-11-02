package com.fusesource.camel.exercises.component.file;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.ScheduledPollEndpoint;

import java.io.File;

public class FileEndpoint extends ScheduledPollEndpoint {
	public static final String RENAME_PREFIX = "_";

	private File file;
	private int bufferSize = 128 * 1024;

	public FileEndpoint(File file, String uri, FileComponent fileComponent) {
		super(uri, fileComponent);
		this.file = file;
	}

	@Override
	public Consumer createConsumer(Processor processor) throws Exception {
		Consumer result = new FileConsumer(this, processor);
		configureConsumer(result);
		return result;
	}

	@Override
	public Producer createProducer() throws Exception {
		Producer result = new FileProducer(this);
		return result;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public int getBufferSize() {
		return bufferSize;
	}

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}
}
