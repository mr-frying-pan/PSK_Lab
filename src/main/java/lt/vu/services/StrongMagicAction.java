package lt.vu.services;

import javax.enterprise.inject.Specializes;

@Specializes
public class StrongMagicAction extends WeakMagicAction {
    @Override
    public int doIt() {
        System.out.println("STRONG MAGIC ACTION");
        return 500;
    }
}
