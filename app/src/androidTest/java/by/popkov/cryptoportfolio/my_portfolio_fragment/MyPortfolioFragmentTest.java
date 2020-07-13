package by.popkov.cryptoportfolio.my_portfolio_fragment;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.navigation.Navigation;
import androidx.navigation.testing.TestNavHostController;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import by.popkov.cryptoportfolio.R;
import by.popkov.cryptoportfolio.utils.EspressoWaitId;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MyPortfolioFragmentTest {
    @Test
    public void navigationToSettingsFragment() {
        TestNavHostController navHostController = new TestNavHostController(ApplicationProvider.getApplicationContext());
        navHostController.setGraph(R.navigation.app_navigation);
        FragmentScenario<MyPortfolioFragment> fragmentScenario = launchFragment();
        fragmentScenario.onFragment(fragment -> Navigation.setViewNavController(fragment.requireView(), navHostController));
        onView(withId(R.id.settingsImageButton)).perform(ViewActions.click());
        Assert.assertEquals(navHostController.getCurrentDestination().getId(), R.id.settingsFragment);
    }

    @Test
    public void navigationToAddNewCoinDialogFragment() {
        TestNavHostController navHostController = new TestNavHostController(ApplicationProvider.getApplicationContext());
        navHostController.setGraph(R.navigation.app_navigation);
        FragmentScenario<MyPortfolioFragment> fragmentScenario = launchFragment();
        fragmentScenario.onFragment(fragment -> Navigation.setViewNavController(fragment.requireView(), navHostController));
        onView(withId(R.id.addCoinFab)).perform(ViewActions.click());
        Assert.assertEquals(navHostController.getCurrentDestination().getId(), R.id.addNewCoinDialogFragment);
    }

    @Test
    public void navigationToCoinInfoFragment() {
        TestNavHostController navHostController = new TestNavHostController(ApplicationProvider.getApplicationContext());
        navHostController.setGraph(R.navigation.app_navigation);
        FragmentScenario<MyPortfolioFragment> fragmentScenario = launchFragment();
        fragmentScenario.onFragment(fragment -> Navigation.setViewNavController(fragment.requireView(), navHostController));
        onView(isRoot()).perform(EspressoWaitId.waitId(R.id.coinSymbol, 3000));
        onView(withId(R.id.coinSymbol)).perform(ViewActions.click());
        Assert.assertEquals(navHostController.getCurrentDestination().getId(), R.id.coinInfoFragment);
    }

    private FragmentScenario<MyPortfolioFragment> launchFragment() {
        return FragmentScenario.launchInContainer(MyPortfolioFragment.class);
    }
}