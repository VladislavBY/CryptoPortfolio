package by.popkov.cryptoportfolio.settings_fragment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import by.popkov.cryptoportfolio.repositories.settings_repository.SettingsRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SettingsFragmentViewModelTest {

    private SettingsRepository settingsRepositoryMock = mock(SettingsRepository.class);
    private SettingsFragmentViewModel settingsFragmentViewModel = new SettingsFragmentViewModel(settingsRepositoryMock);
    private static final String SYMBOL_FOR_TEST = "EUR";
    private static final String SORT_TYPE_FOR_TEST = "SUM";

    @Test
    public void whenGetFiatSettings() {
        when(settingsRepositoryMock.getFiatSetting()).thenReturn(SYMBOL_FOR_TEST);
        assertEquals(SYMBOL_FOR_TEST, settingsFragmentViewModel.getFiatSettings());
        verify(settingsRepositoryMock, times(1)).getFiatSetting();
    }

    @Test
    public void whenSaveFiatSetting() {
        settingsFragmentViewModel.saveFiatSetting(SYMBOL_FOR_TEST);
        verify(settingsRepositoryMock, times(1)).saveFiatSetting(SYMBOL_FOR_TEST);
    }

    @Test
    public void whenGetSortSetting() {
        when(settingsRepositoryMock.getSortSetting()).thenReturn(SORT_TYPE_FOR_TEST);
        assertEquals(SORT_TYPE_FOR_TEST, settingsFragmentViewModel.getSortSettings());
        verify(settingsRepositoryMock, times(1)).getSortSetting();
    }

    @Test
    public void whenSaveSortSetting() {
        settingsFragmentViewModel.saveSortSetting(SORT_TYPE_FOR_TEST);
        verify(settingsRepositoryMock, times(1)).saveSortSetting(SORT_TYPE_FOR_TEST);
    }
}