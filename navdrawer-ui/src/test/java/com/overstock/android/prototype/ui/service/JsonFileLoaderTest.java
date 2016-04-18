package com.overstock.android.prototype.ui.service;

import android.content.Context;
import android.content.res.Resources;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.ByteArrayInputStream;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by itowey on 07/04/16.
 */
@RunWith(RobolectricTestRunner.class)
public class JsonFileLoaderTest {

    @Test
    public void testConstructorNoArg(){
        JsonFileLoader jsonFileLoader = new JsonFileLoader();
        Assert.assertNotNull(jsonFileLoader);
    }

    @Test
    public void testMethos_getFileAsString(){

        String mockString = new String("[\n" +
                "  {\n" +
                "    \"title\": \"Home\",\n" +
                "    \"icon\": \"ic_home\",\n" +
                "    \"enabled\": true\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"My Account\",\n" +
                "    \"icon\": \"ic_fingerprint\",\n" +
                "    \"enabled\": false\n" +
                "  }]");

        Resources resources = mock(Resources.class);
        Context context = mock(Context.class);
        when(context.getResources()).thenReturn(resources);
        when(resources.openRawResource(any(Integer.class))).thenReturn(new ByteArrayInputStream(mockString.getBytes()));

        JsonFileLoader jsonFileLoader = new JsonFileLoader();

        String str = jsonFileLoader.getFileAsString(context,0).toBlocking().first();
        Assert.assertEquals(str, mockString);
    }

}
