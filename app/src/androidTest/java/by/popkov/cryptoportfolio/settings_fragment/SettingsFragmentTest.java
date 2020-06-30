package by.popkov.cryptoportfolio.settings_fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import org.junit.Test;

import by.popkov.cryptoportfolio.OnHomeClickListener;
import by.popkov.cryptoportfolio.R;
import by.popkov.cryptoportfolio.repositories.settings_repository.SettingsRepositoryImp;

import static androidx.fragment.app.testing.FragmentScenario.*;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static by.popkov.cryptoportfolio.settings_fragment.SettingsFragment.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SettingsFragmentTest {

    private SettingsFragmentViewModel settingsFragmentViewModel = mock(SettingsFragmentViewModel.class);
    private OnUpdatePortfolioListener onUpdatePortfolioListener = mock(OnUpdatePortfolioListener.class);
    private OnHomeClickListener onHomeClickListener = mock(OnHomeClickListener.class);
    private static final String SYMBOL_FOR_TEST = "EUR";
    private static final String SORT_TYPE_FOR_TEST = SettingsRepositoryImp.SUM_SORT;

    @Test
    public void whenStartFragmentThenSortTypeSetToRadioBtn() {
        when(settingsFragmentViewModel.getSortSettings()).thenReturn(SORT_TYPE_FOR_TEST);
        when(settingsFragmentViewModel.getFiatSettings()).thenReturn(SYMBOL_FOR_TEST);
        launchInContainer(SettingsFragment.class, null, new SettingsFragmentFactory());
        onView(withId(R.id.sortBySum)).check(matches(isChecked()));
    }

    private class SettingsFragmentFactory extends FragmentFactory {
        @NonNull
        @Override
        public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String className) {
            SettingsFragment fragment = SettingsFragment.getInstance();
            fragment.setViewModelFactoryOptional(ViewModelUtil.createViewModelFactory(settingsFragmentViewModel));
            fragment.setOnUpdateCoinListListenerOptional(onUpdatePortfolioListener);
            fragment.setOnHomeClickListenerOptional(onHomeClickListener);
            return fragment;
        }
    }
}