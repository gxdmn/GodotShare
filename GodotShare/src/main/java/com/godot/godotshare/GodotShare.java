package com.godot.godotshare;

import androidx.annotation.NonNull;

import org.godotengine.godot.Godot;
import org.godotengine.godot.plugin.GodotPlugin;
import org.godotengine.godot.plugin.SignalInfo;
import org.godotengine.godot.plugin.UsedByGodot;

import java.util.HashSet;
import java.util.Set;

public class GodotShare extends GodotPlugin {

    GodotSharePresenter godotShare;
    public GodotShare(Godot godot) {
        super(godot);
        godotShare = new GodotSharePresenter(getActivity());
    }

    @UsedByGodot
    public int function_demo(int i) {
        return i * 2;
    }

    @UsedByGodot
    public void signal_demo(String s){
        emitSignal("signal_demo_complete", 2342, s);
    }

    @UsedByGodot
    public void share_img(String path, String title, String message) {
        godotShare.share_img(path, title, message);
    }

    @UsedByGodot
    public void share_img_web(String url, String title, String message) {
      godotShare.share_img_web(url, title, message);
    }

    @NonNull
    @Override
    public Set<SignalInfo> getPluginSignals() {
        Set<SignalInfo> signals = new HashSet<>();
        signals.add(new SignalInfo("signal_demo_complete", Integer.class, String.class));
        return signals;
    }

    @NonNull
    @Override
    public String getPluginName() {
        return "GodotShare";
    }
}
