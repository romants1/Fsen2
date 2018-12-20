package acptTests.bridge;

import showSystem.ShowSystem;

public abstract class Driver {

	public static Bridge getBridge() {
		ProxyBridge bridge = new ProxyBridge();
        ShowSystem show_sys = new ShowSystem();
		RealBridge rl_bridge = new RealBridge(show_sys);
		bridge.setRealBridge(rl_bridge); // TODO
		return bridge;
	}
}
