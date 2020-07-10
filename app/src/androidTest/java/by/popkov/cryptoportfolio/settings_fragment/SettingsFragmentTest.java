package by.popkov.cryptoportfolio.settings_fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;
import androidx.test.espresso.action.ViewActions;

import org.junit.Test;

import by.popkov.cryptoportfolio.OnBackClickListener;
import by.popkov.cryptoportfolio.R;
import by.popkov.cryptoportfolio.repositories.api_repository.ApiRepositoryImp;
import by.popkov.cryptoportfolio.repositories.settings_repository.SettingsRepositoryImp;

import static androidx.fragment.app.testing.FragmentScenario.*;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static by.popkov.cryptoportfolio.settings_fragment.SettingsFragment.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SettingsFragmentTest {

    private SettingsFragmentViewModel settingsFragmentViewModel = mock(SettingsFragmentViewModel.class);
    private OnUpdatePortfolioListener onUpdatePortfolioListener = mock(OnUpdatePortfolioListener.class);
    private OnBackClickListener onBackClickListener = mock(OnBackClickListener.class);
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
        verify(onUpdatePortfolioListener, times(1)).onUpdatePortfolio();
    }

    @Test
    public void whenChangeFiatSymbolThenSaveToSettingsAndUpdPortfolio() {
        launchFragment();
        onView(withId(R.id.rub)).perform(ViewActions.click());
        onView(withId(R.id.rub)).check(matches(isChecked()));
        verify(settingsFragmentViewModel, times(1)).saveFiatSetting(SYMBOL_FOR_CHANGE);
        verify(onUpdatePortfolioListener, times(1)).onUpdatePortfolio();
    }

    @Test
    public void whenClickOnHomeBtnThenOnHomeClickListenerPerform() {
        launchFragment();
        onView(withId(R.id.homeBtn)).perform(ViewActions.click());
        verify(onBackClickListener, times(1)).onBackClick();
    }

    private void launchFragment() {
        when(settingsFragmentViewModel.getFiatSettings()).thenReturn(SYMBOL_FOR_LAUNCH);
        when(settingsFragmentViewModel.getSortSettings()).thenReturn(SORT_TYPE_FOR_LAUNCH);
        launchInContainer(SettingsFragment.class, null, new SettingsFragmentFactory());
    }

    private class SettingsFragmentFactory extends FragmentFactory {
        @NonNull
        @Override
        public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String className) {
            SettingsFragment fragment = SettingsFragment.newInstance();
            fragment.setViewModelFactoryOptional(ViewModelUtil.createViewModelFactory(settingsFragmentViewModel));
            fragment.setOnUpdateCoinListListenerOptional(onUpdatePortfolioListener);
            fragment.setOnBackClickListenerOptional(onBackClickListener);
            return fragment;
        }
    }
}