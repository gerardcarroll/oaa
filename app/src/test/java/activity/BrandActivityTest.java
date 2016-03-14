package activity;

import android.content.Intent;
import android.os.Build;
import android.widget.TextView;

import com.overstock.android.prototype.BuildConfig;
import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.BrandActivity;
import com.overstock.android.prototype.activity.CommunitiesActivity;
import com.overstock.android.prototype.main.OAppPrototypeApplication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by rconnolly on 3/10/2016.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP) // manifest = "src/main/AndroidManifest.xml"
@RunWith(RobolectricGradleTestRunner.class)
public class BrandActivityTest {

    private OAppPrototypeApplication application;

    private CommunitiesActivity communitiesActivity;

    private BrandActivity brandActivity;

    @Before
    public void setUp() throws Exception {
        //super.setUp();

        brandActivity = Robolectric.setupActivity(BrandActivity.class);
        communitiesActivity = Robolectric.setupActivity(CommunitiesActivity.class);
    }

    @Test
    public void testBrandActivity_NotNull() throws Exception {
        assertNotNull(false);
    }

    //@Test
    public void testBrandActivityContent(){
        TextView tvBestSellers = (TextView) brandActivity.findViewById(R.id.tvBestSellers);
        TextView tvNewArrivals = (TextView) brandActivity.findViewById(R.id.tvNewArrivals);

        assertNotNull("TextView could not be found", tvBestSellers);
        assertNotNull("TextView could not be found", tvNewArrivals);
        assertTrue("TextView contains incorrect text", tvBestSellers.getText().toString().equals("Best Sellers"));
        assertTrue("TextView contains incorrect text", tvNewArrivals.getText().toString().equals("New Arrivals"));
    }

    //@Test
    public void testIntentStarted(){

        BrandActivity brandActivity = Robolectric.setupActivity(BrandActivity.class);
        brandActivity.findViewById(R.id.productCard).performClick();

        Intent expectedIntent = new Intent(brandActivity, BrandActivity.class);
        //assertThat(Shadows.shadowOf(brandActivity).getNextStartedActivity()).isEqualTo(expectedIntent);
    }

}