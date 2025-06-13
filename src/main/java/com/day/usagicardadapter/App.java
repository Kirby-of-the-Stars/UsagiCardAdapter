package com.day.usagicardadapter;

import org.noear.solon.Solon;
import org.noear.solon.annotation.SolonMain;
import org.noear.solon.core.runtime.NativeDetector;

@SolonMain
public class App {
    public static void main(String[] args) {
        Solon.start(App.class, args);
        System.out.println("is Native runtime?:"+NativeDetector.inNativeImage());
        System.out.println("is AOT runtime?:"+NativeDetector.isAotRuntime());

    }
}