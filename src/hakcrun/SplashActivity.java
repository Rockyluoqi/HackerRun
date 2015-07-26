package hakcrun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.feiwo.appwall.AppWallManager;

public class SplashActivity extends Activity {
    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        AppWallManager.init(getApplicationContext(), "7tIXUAXre0Nt2aRtXXmxJgX0");
        setContentView(2130903044);
        new Handler().postDelayed(new SplashHandler(), 1500L);
    }

    class SplashHandler
            implements Runnable {
        SplashHandler() {
        }

        public void run() {
            SplashActivity.this.startActivity(new Intent(SplashActivity.this.getApplication(), MainActivity.class));
            SplashActivity.this.finish();
        }
    }
}

/* Location:           D:\豌豆荚\Apps\WDJDownload\Apps\虚拟入侵 汉化版\classes_dex2jar.jar
 * Qualified Name:     com.i273.hackrun.SplashActivity
 * JD-Core Version:    0.6.0
 */