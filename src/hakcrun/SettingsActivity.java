package hakcrun;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;
import com.i273.hackrun.Classes.Utils;

public class SettingsActivity extends Activity {
    public void clickContactUs(View paramView) {
        new AlertDialog.Builder(this).setTitle("Contacting Us").setMessage("Thank you for taking the time to contact us.\n\nPlease use the 'hint' and 'answer' commands for questions about puzzle levels.\n\nFeel free to contact us directly with any comments, suggestions or technical questions.").setIcon(17301543).setPositiveButton(17039379, new OnClickListener() {
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                Intent localIntent = new Intent("android.intent.action.SEND");
                localIntent.setType("message/rfc822");
                localIntent.putExtra("android.intent.extra.EMAIL", new String[]{"ContactHackRUN@i273.com"});
                if (Utils.isAppFree(SettingsActivity.this.getApplicationContext())) {
                    localIntent.putExtra("android.intent.extra.SUBJECT", "My Hack RUN free Comment");
                    localIntent.putExtra("android.intent.extra.TEXT", "My Hack RUN free comment is...");
                }
                try {
                    while (true) {
                        SettingsActivity.this.startActivity(Intent.createChooser(localIntent, "Send mail..."));
                        return;
                        localIntent.putExtra("android.intent.extra.SUBJECT", "My Hack RUN Comment");
                        localIntent.putExtra("android.intent.extra.TEXT", "My Hack RUN comment is...");
                    }
                } catch (ActivityNotFoundException localActivityNotFoundException) {
                    Toast.makeText(SettingsActivity.this.getApplicationContext(), "There are no email clients installed.", 0).show();
                }
            }
        }).setNegativeButton(17039369, null).show();
    }

    public void clickReset(View paramView) {
        new AlertDialog.Builder(this).setTitle("Confirm Reset").setMessage("你确定要重置你的游戏吗？你将丢失所有的游戏进度并重新开始游戏。").setIcon(17301543).setPositiveButton(17039379, new OnClickListener() {
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                Utils.resetGame(SettingsActivity.this.getApplicationContext(), 0, -1, false);
                Intent localIntent = new Intent();
                localIntent.putExtra("intent_parm_id1", "intent_parm_id2");
                SettingsActivity.this.setResult(-1, localIntent);
                SettingsActivity.this.finish();
            }
        }).setNegativeButton(17039369, null).show();
    }

    public void clickShareEmail(View paramView) {
        Intent localIntent = new Intent("android.intent.action.SEND");
        localIntent.setType("text/html");
        localIntent.putExtra("android.intent.extra.SUBJECT", "Check out Hack RUN!");
        localIntent.putExtra("android.intent.extra.TEXT", Html.fromHtml("<html><head></head><body cellpadding='0' cellspacing='0' style='font-family:Trebuchet MS; background-color:White; text-align:left;'>Check out this app I've been playing. It's called Hack RUN by i273 llc. You play a computer hacker who has been hired by a mysterious employer to uncover secrets from a strange organization. You use 'old school' command line prompts to navigate through the organization's systems. The deeper you go, the more dirt you uncover about the organization and the people who work there.<br/><br/>You can download Hack RUN here:<br/><br/><a href='http://www.i273.com/app/default.aspx'>Get the iPhone/iPad Version</a><br/><br/><a href='http://www.i273.com/app/default_android.aspx'>Get the Android Version</a><br/><br/>View the <a href='http://www.i273.com'>i273 llc</a> website for more details.<br/><br/></body></html>"));
        try {
            startActivity(Intent.createChooser(localIntent, "Send mail..."));
            return;
        } catch (ActivityNotFoundException localActivityNotFoundException) {
            Toast.makeText(this, "There are no email clients installed.", 0).show();
        }
    }

    public void clickShareFBTW(View paramView) {
        Intent localIntent = new Intent(this, WebTemplateActivity.class);
        localIntent.putExtra("com.i273.hackrun.MESSAGE_PAGE_TO_OPEN", "http://www.i273.com/apps/hackrun/mshare_global.html");
        startActivity(localIntent);
    }

    public void clickTips(View paramView) {
        Intent localIntent = new Intent(this, WebTemplateActivity.class);
        localIntent.putExtra("com.i273.hackrun.MESSAGE_PAGE_TO_OPEN", "http://php.pujiahh.com/hackrun/mtips.html");
        startActivity(localIntent);
    }

    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(2130903043);
    }

    public boolean onCreateOptionsMenu(Menu paramMenu) {
        getMenuInflater().inflate(2131230723, paramMenu);
        return true;
    }
}

/* Location:           D:\豌豆荚\Apps\WDJDownload\Apps\虚拟入侵 汉化版\classes_dex2jar.jar
 * Qualified Name:     com.i273.hackrun.SettingsActivity
 * JD-Core Version:    0.6.0
 */