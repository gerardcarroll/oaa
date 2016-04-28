
package com.overstock.android.prototype.perftesting.testrules;

import android.os.Build;
import android.os.Trace;
import android.util.Log;

import com.google.android.perftesting.common.PerfTestingUtils;

import org.junit.rules.ExternalResource;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * This rule executes clears logcat prior to a test then records the output at the end of a test.
 *
 * <pre>
 * @Rule
 * public EnableLogcatDump mEnableLogcatDump = new EnableLogcatDump();
 * </pre>
 */
public class EnableLogcatDump extends ExternalResource {

    private static final String LOG_TAG = "EnableLogcatDump";

    private String mTestName;

    private String mTestClass;

    @Override
    public Statement apply(Statement base, Description description) {
        mTestName = description.getMethodName();
        mTestClass = description.getClassName();
        return super.apply(base, description);
    }

    /**
     * Clear logcat buffer prior to test run.
     */
    public void before() throws Throwable {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("logcat", "-c");
            processBuilder.redirectErrorStream();
            Process process = processBuilder.start();
            process.waitFor();
            if (process.exitValue() != 0) {
                Log.e(LOG_TAG, "Error while clearing logcat, exitValue=" + process.exitValue());
            }
        }
    }

    /**
     * Extract logcat buffer to a file ater test run.
     */
    public void after() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                Trace.beginSection("Taking logcat");
            }
            ProcessBuilder processBuilder = new ProcessBuilder();

            processBuilder.command("logcat", "-d",
                    "-f", PerfTestingUtils.getTestFile(mTestClass, mTestName, "logcat.log")
                            .getAbsolutePath());
            processBuilder.redirectErrorStream();
            Process process = processBuilder.start();
            process.waitFor();
            if (process.exitValue() != 0) {
                Log.e(LOG_TAG, "Error exit value while extracting logcat, exitValue=" +
                        process.exitValue());
            }
        } catch (Exception ignored) {
            Log.e(LOG_TAG, "Error while extracting logcat", ignored);
        } finally {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                Trace.endSection();
            }
        }
    }
}