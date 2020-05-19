package lt.vu.services;

import lt.vu.interceptors.Log;

import javax.enterprise.inject.Alternative;

@Alternative
public class WeakMagicAction implements MagicAction {
    @Log
    @Override
    public int doIt() {
        System.out.println("WEAK MAGIC ACTION");
        return 1000;
    }
}
