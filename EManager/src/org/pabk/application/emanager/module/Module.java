package org.pabk.application.emanager.module;

import ch.qos.logback.classic.Logger;

public interface Module {
	Logger getLogger();
	void todoBusiness();
	void invokeShutdown();
	void initialize();
}
