package org.pabk.application.emanager.module;

import java.io.IOException;

import ch.qos.logback.classic.Logger;

public interface Module {
	Logger getLogger();
	void todoBusiness();
	void invokeShutdown();
	void initialize();
	void wakeUp() throws IOException;
}
