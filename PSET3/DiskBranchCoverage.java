package Week4;

import org.junit.Test;

public class DiskBranchCoverage {

    @Test
    public void t1() {
        Disk disk = new Disk(0,13); // pw
        disk.manipulate();
    }

    @Test
    public void t2() {
        Disk disk = new Disk(1001, -5); // infinite loop pxy
        disk.manipulate();
    }

    @Test
    public void t3() {
        Disk disk = new Disk(4,8); // qrw
        disk.manipulate();
    }

    @Test
    public void t4() {
        Disk disk = new Disk(0, 15); // qsw
        disk.manipulate();
    }
}
