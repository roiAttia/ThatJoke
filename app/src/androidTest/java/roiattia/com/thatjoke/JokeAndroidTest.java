package roiattia.com.thatjoke;

import android.test.AndroidTestCase;
import android.util.Log;

public class JokeAndroidTest extends AndroidTestCase{

    public void testVerifyAsyncTask(){
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(null);
        String result = null;
        try {
            result = endpointsAsyncTask.get();
        } catch (Exception e) {
            Log.i("Test", e.getMessage());
            e.printStackTrace();
        }
        assertNotNull(result);
    }
}
