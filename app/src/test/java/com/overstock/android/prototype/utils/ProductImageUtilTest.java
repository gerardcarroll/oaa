package com.overstock.android.prototype.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.overstock.android.prototype.model.ImageSizes;
import com.overstock.android.prototype.model.OViewerImage;

/**
 * @author LeeMeehan
 * @since Created on 27-Apr-16.
 */
@RunWith(RobolectricTestRunner.class)
public class ProductImageUtilTest {

  private static final String ORIGINAL_IMAGE_URL = "8554452/NFL-Denver-Broncos-Wrap-Sunglasses-58fff857-a40e-4770-9d04-921f68b51a71.jpg";

  private static final String SMALL_IMAGE_URL = "8554452/NFL-Denver-Broncos-Wrap-Sunglasses-58fff857-a40e-4770-9d04-921f68b51a71_320.jpg";

  private static final String MEDIUM_IMAGE_URL = "8554452/NFL-Denver-Broncos-Wrap-Sunglasses-58fff857-a40e-4770-9d04-921f68b51a71_600.jpg";

  private static final String LARGE_IMAGE_URL = "8554452/NFL-Denver-Broncos-Wrap-Sunglasses-58fff857-a40e-4770-9d04-921f68b51a71_1000.jpg";

  private ProductImageUtil productImageUtil;

  private List<OViewerImage> oViewerImages;

  @Mock
  private Application applicationContext;

  @Mock
  private Resources resources;

  @Mock
  private Configuration configuration;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    productImageUtil = new ProductImageUtil(applicationContext);
    oViewerImages = new ArrayList<>();
    final List<ImageSizes> imageSizes = new ArrayList<>();
    imageSizes.add(new ImageSizes(SMALL_IMAGE_URL, 320, 320));
    imageSizes.add(new ImageSizes(MEDIUM_IMAGE_URL, 600, 600));
    imageSizes.add(new ImageSizes(LARGE_IMAGE_URL, 1000, 1000));
    oViewerImages.add(new OViewerImage(ORIGINAL_IMAGE_URL, 1260, 1260, imageSizes));
  }

  @Test
  public void testGetOptimizedImages_NORMAL_DEVICE() {
    assertNotNull(productImageUtil);
    configuration.screenLayout = 2;
    when(resources.getConfiguration()).thenReturn(configuration);
    when(applicationContext.getResources()).thenReturn(resources);
    List<String> optimizedImageUrls = productImageUtil.getOptimizedImages(oViewerImages);
    assertNotNull(optimizedImageUrls);
    assertEquals(MEDIUM_IMAGE_URL, optimizedImageUrls.get(0));
  }

  @Test
  public void testGetOptimizedImages_SMALL_DEVICE() {
    assertNotNull(productImageUtil);
    configuration.screenLayout = 1;
    when(resources.getConfiguration()).thenReturn(configuration);
    when(applicationContext.getResources()).thenReturn(resources);
    List<String> optimizedImageUrls = productImageUtil.getOptimizedImages(oViewerImages);
    assertNotNull(optimizedImageUrls);
    assertEquals(SMALL_IMAGE_URL, optimizedImageUrls.get(0));
  }

  @Test
  public void testGetOptimizedImages_LARGE_DEVICE() {
    assertNotNull(productImageUtil);
    configuration.screenLayout = 3;
    when(resources.getConfiguration()).thenReturn(configuration);
    when(applicationContext.getResources()).thenReturn(resources);
    List<String> optimizedImageUrls = productImageUtil.getOptimizedImages(oViewerImages);
    assertNotNull(optimizedImageUrls);
    assertEquals(LARGE_IMAGE_URL, optimizedImageUrls.get(0));
  }

  @Test
  public void testGetOptimizedImages_XLARGE_DEVICE() {
    assertNotNull(productImageUtil);
    configuration.screenLayout = 4;
    when(resources.getConfiguration()).thenReturn(configuration);
    when(applicationContext.getResources()).thenReturn(resources);
    List<String> optimizedImageUrls = productImageUtil.getOptimizedImages(oViewerImages);
    assertNotNull(optimizedImageUrls);
    assertEquals(LARGE_IMAGE_URL, optimizedImageUrls.get(0));
  }

  @Test
  public void testGetOptimizedImages_OTHER_DEVICE() {
    assertNotNull(productImageUtil);
    when(resources.getConfiguration()).thenReturn(configuration);
    when(applicationContext.getResources()).thenReturn(resources);
    List<String> optimizedImageUrls = productImageUtil.getOptimizedImages(oViewerImages);
    assertNotNull(optimizedImageUrls);
    assertEquals(MEDIUM_IMAGE_URL, optimizedImageUrls.get(0));
  }

  @Test
  public void testGetOptimizedImages_NO_SIZES_LOWER_RES() {
    assertNotNull(productImageUtil);
    configuration.screenLayout = 2;
    when(resources.getConfiguration()).thenReturn(configuration);
    when(applicationContext.getResources()).thenReturn(resources);
    oViewerImages = new ArrayList<>();
    oViewerImages.add(new OViewerImage(ORIGINAL_IMAGE_URL, 1260, 1260, new ArrayList<ImageSizes>()));
    List<String> optimizedImageUrls = productImageUtil.getOptimizedImages(oViewerImages);
    assertEquals(ORIGINAL_IMAGE_URL, optimizedImageUrls.get(0));
  }

  @Test
  public void testGetOptimizedImages_ONE_SIZE_ENTRY() {
    assertNotNull(productImageUtil);
    configuration.screenLayout = 2;
    when(resources.getConfiguration()).thenReturn(configuration);
    when(applicationContext.getResources()).thenReturn(resources);
    oViewerImages = new ArrayList<>();
    final List<ImageSizes> imageSizes = new ArrayList<>();
    imageSizes.add(new ImageSizes(SMALL_IMAGE_URL, 320, 320));
    oViewerImages.add(new OViewerImage(ORIGINAL_IMAGE_URL, 1260, 1260, imageSizes));
    List<String> optimizedImageUrls = productImageUtil.getOptimizedImages(oViewerImages);
    assertEquals(0, optimizedImageUrls.size());
  }

  @Test(expected = NullPointerException.class)
  public void testGetOptimizedImages_NULL_PARAM() {
    productImageUtil.getOptimizedImages(null);
  }

}
