package lt.vu.services;

import javax.enterprise.inject.Alternative;

@Alternative
public class WeakMagicAction implements MagicAction {
    @Override
    public int doIt() {
        System.out.println("WEAK MAGIC ACTION");
        return 1000;
    }
}
