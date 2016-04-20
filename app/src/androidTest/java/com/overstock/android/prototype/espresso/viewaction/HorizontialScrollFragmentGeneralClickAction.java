package com.overstock.android.prototype.espresso.viewaction;

import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.CoordinatesProvider;
import android.support.test.espresso.action.GeneralLocation;
import android.support.test.espresso.action.PrecisionDescriber;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Tap;
import android.support.test.espresso.action.Tapper;
import android.support.test.espresso.core.deps.guava.base.Optional;
import android.support.test.espresso.util.HumanReadables;
import android.view.View;
import android.view.ViewConfiguration;
import android.webkit.WebView;

import org.hamcrest.Matcher;

import static android.support.test.espresso.action.ViewActions.actionWithAssertions;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by itowey on 20/04/16.
 */
public final class HorizontialScrollFragmentGeneralClickAction implements ViewAction {

    private final CoordinatesProvider coordinatesProvider;
    private final Tapper tapper;
    private final PrecisionDescriber precisionDescriber;
    private final Optional<ViewAction> rollbackAction;

    public HorizontialScrollFragmentGeneralClickAction(Tapper tapper, CoordinatesProvider coordinatesProvider,
                                                       PrecisionDescriber precisionDescriber) {
        this(tapper, coordinatesProvider, precisionDescriber, null);
    }

    public HorizontialScrollFragmentGeneralClickAction(Tapper tapper, CoordinatesProvider coordinatesProvider,
                              PrecisionDescriber precisionDescriber, ViewAction rollbackAction) {
        this.coordinatesProvider = coordinatesProvider;
        this.tapper = tapper;
        this.precisionDescriber = precisionDescriber;
        this.rollbackAction = Optional.fromNullable(rollbackAction);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Matcher<View> getConstraints() {
        Matcher<View> standardConstraint = isEnabled();
        if (rollbackAction.isPresent()) {
            return allOf(standardConstraint, rollbackAction.get().getConstraints());
        } else {
            return standardConstraint;
        }
    }

    @Override
    public void perform(UiController uiController, View view) {
        float[] coordinates = coordinatesProvider.calculateCoordinates(view);
        float[] precision = precisionDescriber.describePrecision();

        Tapper.Status status = Tapper.Status.FAILURE;
        int loopCount = 0;

        while (status != Tapper.Status.SUCCESS && loopCount < 3) {
            try {
                status = tapper.sendTap(uiController, coordinates, precision);
            } catch (RuntimeException re) {
                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(re)
                        .build();
            }

            int duration = ViewConfiguration.getPressedStateDuration();
            // ensures that all work enqueued to process the tap has been run.
            if (duration > 0) {
                uiController.loopMainThreadForAtLeast(duration);
            }
            if (status == Tapper.Status.WARNING) {
                if (rollbackAction.isPresent()) {
                    rollbackAction.get().perform(uiController, view);
                } else {
                    break;
                }
            }
            loopCount++;
        }
        if (status == Tapper.Status.FAILURE) {
            throw new PerformException.Builder()
                    .withActionDescription(this.getDescription())
                    .withViewDescription(HumanReadables.describe(view))
                    .withCause(new RuntimeException(String.format("Couldn't "
                                    + "click at: %s,%s precision: %s, %s . Tapper: %s coordinate provider: %s precision " +
                                    "describer: %s. Tried %s times. With Rollback? %s", coordinates[0], coordinates[1],
                            precision[0], precision[1], tapper, coordinatesProvider, precisionDescriber, loopCount,
                            rollbackAction.isPresent())))
                    .build();
        }

        if (tapper == Tap.SINGLE && view instanceof WebView) {
            // WebViews will not process click events until double tap
            // timeout. Not the best place for this - but good for now.
            uiController.loopMainThreadForAtLeast(ViewConfiguration.getDoubleTapTimeout());
        }
    }

    @Override
    public String getDescription() {
        return tapper.toString().toLowerCase() + " click";
    }

    public static ViewAction click() {
        return actionWithAssertions(
                new HorizontialScrollFragmentGeneralClickAction(Tap.SINGLE, GeneralLocation.VISIBLE_CENTER, Press.FINGER));
    }


}
