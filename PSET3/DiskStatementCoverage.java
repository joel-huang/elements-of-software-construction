package Week4;

import org.junit.Test;

public class DiskStatementCoverage {

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
    public void pathTest() {
        Disk disk = new Disk(6,993); // 100, pw
        disk.manipulate();
    }

    @Test
    public void pathTest2() {
        Disk disk = new Disk(-50,850); // 100,qsw
        disk.manipulate();
    }
}
