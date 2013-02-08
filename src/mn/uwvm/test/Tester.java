package mn.uwvm.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import android.text.TextUtils;
import android.util.Log;

public class Tester {
    private final AtomicInteger mSuccessCount = new AtomicInteger();
    private final AtomicInteger mFailedCount = new AtomicInteger();
    private final long mStartTime = System.currentTimeMillis();
    private final List<String> mReposts = Collections.synchronizedList(new ArrayList<String>());
    
    public boolean equals(String label, Object a, Object b) {
        final boolean isSuccess;
        if (a == null || b == null) {
            isSuccess = a == null && b == null;
        } else {
            isSuccess = a.equals(b);
        }
        
        return setResult(label, isSuccess);
    }
    
    public boolean isNotNull(String label, Object a) {
        return setResult(label + " not null", a != null);
    }
    
    public boolean isNull(String label, Object a) {
        return setResult(label + " is null", a == null);
    }
    
    boolean setResult(String label, boolean isSuccess) {
        String report = String.format("%-60s%s", label, isSuccess ? "OK" : "NG");
        Log.v("parson-test", report);
        mReposts.add(report);
        if (isSuccess) {
            mSuccessCount.incrementAndGet();
        } else {
            mFailedCount.incrementAndGet();
        }
        return isSuccess;
    }
    
    public String getReport() {
        long time = System.currentTimeMillis();
        return String.format("%s\n" +
                             "%d ms elapsed\n" +
                             "Tests failed: %d\n" +
                             "Tests passed: %d\n",
                             TextUtils.join("\n", mReposts),
                             (time - mStartTime),
                             mFailedCount.get(),
                             mSuccessCount.get());
    }

}
