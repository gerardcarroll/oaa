#
#   Layout tests for Samsung S6
#   
#       orientation : portrait

echo "**********************************************************************"
echo Layout tests on home activity
echo "**********************************************************************"
$ANDROID_HOME/tools/monkeyrunner app/src/androidTest/python/activity_layout_test.py ./app/build/outputs/apk/OApp-debug.apk HomeActivity screenshots/baseline/HomeActivity_baseline.png home_activity_test 3
echo "**********************************************************************"
echo
echo
echo "**********************************************************************"
echo Layout tests on communities activity
echo "**********************************************************************"
$ANDROID_HOME/tools/monkeyrunner app/src/androidTest/python/activity_layout_test.py ./app/build/outputs/apk/OApp-debug.apk CommunitiesActivity screenshots/baseline/CommunitiesActivity_baseline.png communities_activity_test 3
echo "**********************************************************************"
echo
echo
echo "**********************************************************************"
echo Layout tests on feed activity, feed fragment
echo "**********************************************************************"
$ANDROID_HOME/tools/monkeyrunner app/src/androidTest/python/activity_layout_test.py ./app/build/outputs/apk/OApp-debug.apk FeedActivity screenshots/baseline/FeedActivity_baseline.png feed_activity_test 3
echo "**********************************************************************"

