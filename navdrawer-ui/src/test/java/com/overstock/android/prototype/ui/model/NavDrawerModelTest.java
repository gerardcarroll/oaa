package com.overstock.android.prototype.ui.model;

import android.content.Context;

import com.overstock.android.prototype.ui.service.JsonFileLoader;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;

import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by itowey on 07/04/16.
 */
@RunWith(RobolectricTestRunner.class)
public class NavDrawerModelTest {

    @Test
    public void testConstructorNoArgAndGetterSetter(){
        NavDrawerModel navDrawerModel = new NavDrawerModel();
        Assert.assertNotNull(navDrawerModel);
        navDrawerModel.setNavDrawerItems(new ArrayList<NavDrawerItem>(){{
            add(new NavDrawerItem(){{
                setTitle("Home");
                setIcon("ic_home");
                setEnabled(true);
            }});
            add(new NavDrawerItem(){{
                setTitle("Settings");
                setIcon("ic_settings");
                setEnabled(false);
            }});
        }});
        Assert.assertNotNull(navDrawerModel.getNavDrawerItems());
        Assert.assertEquals(navDrawerModel.getNavDrawerItems().size(), 2);
        Assert.assertEquals(navDrawerModel.getNavDrawerItems().get(0).getTitle(), "Home");
        Assert.assertEquals(navDrawerModel.getNavDrawerItems().get(1).getTitle(), "Settings");
        Assert.assertEquals(navDrawerModel.getNavDrawerItems().get(0).getIcon(), "ic_home");
        Assert.assertEquals(navDrawerModel.getNavDrawerItems().get(1).getIcon(), "ic_settings");
        Assert.assertTrue(navDrawerModel.getNavDrawerItems().get(0).isEnabled());
        Assert.assertFalse(navDrawerModel.getNavDrawerItems().get(1).isEnabled());
    }


    @Test
    public void testConstructorArgAndGetter(){
        Context context = mock(Context.class);

        JsonFileLoader jsonFileLoader = mock(JsonFileLoader.class);
        when(jsonFileLoader.getFileAsString(any(Context.class), any(Integer.class))).thenReturn(Observable.just(new String("[\n" +
                        "  {\n" +
                        "    \"title\": \"Home\",\n" +
                        "    \"icon\": \"ic_home\",\n" +
                        "    \"enabled\": true\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"title\": \"My Account\",\n" +
                        "    \"icon\": \"ic_fingerprint\",\n" +
                        "    \"enabled\": false\n" +
                        "  }]")));

        NavDrawerModel navDrawerModel = new NavDrawerModel(context, jsonFileLoader);
        Assert.assertNotNull(navDrawerModel);
        Assert.assertNotNull(navDrawerModel.getNavDrawerItems());
        Assert.assertEquals(navDrawerModel.getNavDrawerItems().size(), 2);
        Assert.assertEquals(navDrawerModel.getNavDrawerItems().get(0).getTitle(), "Home");
        Assert.assertEquals(navDrawerModel.getNavDrawerItems().get(1).getTitle(), "My Account");
        Assert.assertEquals(navDrawerModel.getNavDrawerItems().get(0).getIcon(), "ic_home");
        Assert.assertEquals(navDrawerModel.getNavDrawerItems().get(1).getIcon(), "ic_fingerprint");
        Assert.assertTrue(navDrawerModel.getNavDrawerItems().get(0).isEnabled());
        Assert.assertFalse(navDrawerModel.getNavDrawerItems().get(1).isEnabled());

    }
}
