package by.popkov.cryptoportfolio.settings_fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import by.popkov.cryptoportfolio.R;
import by.popkov.cryptoportfolio.repositories.api_repository.ApiRepositoryImp;
import by.popkov.cryptoportfolio.repositories.settings_repository.SettingsRepositoryImp;

import static androidx.fragment.app.testing.FragmentScenario.launchInContainer;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class SettingsFragmentTest {

    private SettingsFragmentViewModel settingsFragmentViewModel = mock(SettingsFragmentViewModel.class);
    private static final String SYMBOL_FOR_LAUNCH = ApiRepositoryImp.EUR;
    private static final String SORT_TYPE_FOR_LAUNCH = SettingsRepositoryImp.SUM_SORT;
    private static final String SYMBOL_FOR_CHANGE = ApiRepositoryImp.RUB;
    private static final String SORT_TYPE_FOR_CHANGE = SettingsRepositoryImp.ALPHABET_SORT;

    @Test
    public void whenStartFragmentThenSortTypeSetToRadioBtn() {
        launchFragment();
        onView(withId(R.id.sortBySum)).check(matches(isChecked()));
    }

    @Test
    public void whenStartFragmentThenFiatSymbolSetToRadioBtn() {
        launchFragment();
        onView(withId(R.id.eur)).check(matches(isChecked()));
    }

    @Test
    public void whenChangeSortTypeThenSaveToSettingsAndUpdPortfolio() {
        launchFragment();
        onView(withId(R.id.sortByAlphabet)).perform(ViewActions.click());
        onView(withId(R.id.sortByAlphabet)).check(matches(isChecked()));
        verify(settingsFragmentViewModel, times(1)).saveSortSetting(SORT_TYPE_FOR_CHANGE);
    }

    @Test
    public void whenChangeFiatSymbolThenSaveToSettingsAndUpdPortfolio() {
        launchFragment();
        onView(withId(R.id.rub)).perform(ViewActions.click());
        onView(withId(R.id.rub)).check(matches(isChecked()));
        verify(settingsFragmentViewModel, times(1)).saveFiatSetting(SYMBOL_FOR_CHANGE);
    }

    private FragmentScenario<SettingsFragment> launchFragment() {
        when(settingsFragmentViewModel.getFiatSettings()).thenReturn(SYMBOL_FOR_LAUNCH);
        when(settingsFragmentViewModel.getSortSettings()).thenReturn(SORT_TYPE_FOR_LAUNCH);
        return launchInContainer(SettingsFragment.class, null, new SettingsFragmentFactory());
    }

    private class SettingsFragmentFactory extends FragmentFactory {
        @NonNull
        @Override
        public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String className) {
            SettingsFragment fragment = new SettingsFragment();
            fragment.setViewModelFactoryOptional(ViewModelUtil.createViewModelFactory(settingsFragmentViewModel));
            return fragment;
        }
    }
}