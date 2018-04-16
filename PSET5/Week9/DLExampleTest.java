package Week9;

import org.junit.Test;

public class DLExampleTest {

    @Test
    public void test1() throws InterruptedException {
        Dispatcher disp = new Dispatcher();
        Taxi t1 = new Taxi(disp);
        TaxiThread tt = new TaxiThread();
        tt.setTaxi(t1);
        DispatcherThread dt = new DispatcherThread();
        dt.setDispatcher(disp);
        dt.dispatcher.addTaxi(t1);
        tt.start();
        dt.start();
        tt.join();
        dt.join();
    }

}
