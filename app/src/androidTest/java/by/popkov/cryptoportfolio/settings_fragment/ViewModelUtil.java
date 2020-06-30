package by.popkov.cryptoportfolio.settings_fragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelUtil {
    public static <T extends ViewModel> ViewModelProvider.Factory createViewModelFactory(T model) {
        return new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) model;
            }
        };
    }
}
