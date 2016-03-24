#
#
#	HomeActivity layout test, compares the HomeActivity UI Image against a "baseline" HomeActivity UI Image
#	any deviation should fail test 
#
#		1. loads reference HomeActivity Ui Image
#		2. starts device 
#		3. Loads HomeActivity
#		4. captures HomeActivity image
#		5. compares images

from com.android.monkeyrunner import MonkeyRunner, MonkeyDevice, MonkeyImage
import time
import sys
import random

failure_image_dir = 'screenshots/'
test_supported_resolution = (1440, 2560)

def home_activity_test():
    print('Home activity test no extra ui actions to perform')
    # Presses the Menu button
    # device.press('KEYCODE_MENU', MonkeyDevice.DOWN_AND_UP)
    MonkeyRunner.sleep(1)
    
def communities_activity_test():
    print('Communities activity perform actions')
    MonkeyRunner.sleep(1)
    device.touch(700, 2000, 'DOWN_AND_UP')
    MonkeyRunner.sleep(1)

def feed_activity_test():
    print('Feed activity perform actions')
    communities_activity_test()
    MonkeyRunner.sleep(1)
    device.touch(400, 1000, 'DOWN_AND_UP')
    device.touch(1000, 1700, 'DOWN_AND_UP')
    device.touch(1000, 1000, 'DOWN_AND_UP')
    MonkeyRunner.sleep(0.5)
    device.touch(700, 2400, 'DOWN_AND_UP')
    MonkeyRunner.sleep(1)

def compare_images(current_image, baseline_image):
    x = random.randrange(0, int(device.getProperty('display.width')) - 100) 
    y = random.randrange(0, int(device.getProperty('display.height')) - 100) 
    w = 100
    h = 100 

    # Take the sub-image of current device screen
    current_activity_screenshot_sub_image = current_activity_screenshot.getSubImage((x, y, w, h)) #x,y,w,h
    # Take the sub-image of reference screen
    reference_screenshot_sub_image = reference_screenshot.getSubImage((x, y, w, h)) #x,y,w,h

    #print('*' * 50)
    if not current_activity_screenshot_sub_image.sameAs(reference_screenshot_sub_image,0.9):
        print "Images do not match, subsets compared (%d,%d,%d,%d), saving failing image" % (x,y,w,h)
        # Writes the screenshot to a file
        current_tmstmp = int(time.time())
        current_activity_screenshot_sub_image.writeToFile("%s%s_%d_%d_%d_%d_%d.png" % (failure_image_dir,activity_under_test,current_tmstmp,x,y,w,h),'png')
        reference_screenshot_sub_image.writeToFile("%sref_%s_%d_%d_%d_%d_%d.png" % (failure_image_dir,activity_under_test,current_tmstmp,x,y,w,h),'png')
        return 1 
    else:
        print "Test passed!! image subsets compared (%d,%d,%d,%d)" % (x,y,w,h)
        return 0
    print('*' * 50)

    
if __name__ == "__main__":
    if sys.argv[1] == 'help':
        print ('Usage: param1 : apk path, param2 : activity to test, param3 : activity baseline image path')

    apk_path=sys.argv[1] 
    activity_under_test=sys.argv[2]
    baseline_activity_image_path = sys.argv[3] 
    activity_action_method = sys.argv[4]
    num_samples_to_compare = sys.argv[5]

    # Load the reference image for HomeActivity comparison
    reference_screenshot = MonkeyRunner.loadImageFromFile(baseline_activity_image_path)

    # Connects to the current device, returning a MonkeyDevice object
    device = MonkeyRunner.waitForConnection()
    
    if not (int(device.getProperty('display.width')) == test_supported_resolution[0] and int(device.getProperty('display.height')) == test_supported_resolution[1]):
        print 'error test optimised for devices with resolution %dx%d' % test_supported_resolution
        sys.exit(102)
    else:
        print(device.getProperty('build.manufacturer') + '   ' + device.getProperty('build.model') )

    # Installs the Android package. Notice that this method returns a boolean, so you can test
    # to see if the installation worked.
    if device.installPackage(apk_path):

        print('APK installed on device')
        # sets a variable with the packageimport random's internal name
        package = 'com.overstock.android.prototype'

        # sets a variable with the name of an Activity in the package
        activity = '.activity.HomeActivity' #+ activity_under_test

        # sets the name of the component to start
        runComponent = package + '/' + activity

        print 'Starting activity : %s' % runComponent
        # Runs the componentecho $
        device.startActivity(component=runComponent)

        #invoke specific action for the activity under test
        eval(activity_action_method + "()")

        # Takes a screenshot
        print('Getting screenshot of running activity : %s' % activity_under_test)
        current_activity_screenshot = device.takeSnapshot()

        print('Comparing %s random sample of the current v baseline %s activity image ' % (num_samples_to_compare, activity_under_test))
        num_failures = sum([compare_images(current_activity_screenshot, reference_screenshot) for _ in range(int(num_samples_to_compare))])
        print('Failed : %d out of %s samples' % (num_failures, num_samples_to_compare))
        print 'SUCCESS' if num_failures == 0 else 'FAILURE'
    else:
        print('*' * 50)
        print('error installing apk')
        sys.exit(101) #



