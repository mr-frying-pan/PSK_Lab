package lt.vu.services;

import lt.vu.interceptors.Log;

import javax.enterprise.inject.Specializes;

@Specializes
public class StrongMagicAction extends WeakMagicAction {
    @Log
    @Override
    public int doIt() {
        System.out.println("STRONG MAGIC ACTION");
        return 500;
    }
}
