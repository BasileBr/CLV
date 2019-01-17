package fr.essant.basilebyvanu.clv;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.web.assertion.WebAssertion;
import androidx.test.espresso.web.assertion.WebViewAssertions;
import androidx.test.espresso.web.model.Atoms;

import static androidx.test.espresso.web.model.Atoms.getCurrentUrl;
import static androidx.test.espresso.web.sugar.Web.onWebView;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("fr.essant.basilebyvanu.clv", appContext.getPackageName());
    }

    @Test
    public void webViewDisplayProperContent(){
        onWebView()
                .check(WebViewAssertions.webMatches(getCurrentUrl(),containsString("wiki")));
    }
}
