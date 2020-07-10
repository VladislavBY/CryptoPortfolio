package by.popkov.cryptoportfolio.settings_fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;

import java.util.Optional;

import by.popkov.cryptoportfolio.OnBackClickListener;
import by.popkov.cryptoportfolio.R;

import static by.popkov.cryptoportfolio.repositories.api_repository.ApiRepositoryImp.BTC;
import static by.popkov.cryptoportfolio.repositories.api_repository.ApiRepositoryImp.BYN;
import static by.popkov.cryptoportfolio.repositories.api_repository.ApiRepositoryImp.ETH;
import static by.popkov.cryptoportfolio.repositories.api_repository.ApiRepositoryImp.EUR;
import static by.popkov.cryptoportfolio.repositories.api_repository.ApiRepositoryImp.GBP;
import static by.popkov.cryptoportfolio.repositories.api_repository.ApiRepositoryImp.JPY;
import static by.popkov.cryptoportfolio.repositories.api_repository.ApiRepositoryImp.KRW;
import static by.popkov.cryptoportfolio.repositories.api_repository.ApiRepositoryImp.RUB;
import static by.popkov.cryptoportfolio.repositories.api_repository.ApiRepositoryImp.USD;
import static by.popkov.cryptoportfolio.repositories.settings_repository.SettingsRepositoryImp.ALPHABET_SORT;
import static by.popkov.cryptoportfolio.repositories.settings_repository.SettingsRepositoryImp.SUM_SORT;
import static by.popkov.cryptoportfolio.repositories.settings_repository.SettingsRepositoryImp.TIME_ADD_SORT;

public class SettingsFragment extends Fragment {
    public static final String TAG = "SettingsFragment";
    private Optional<OnBackClickListener> onBackClickListenerOptional = Optional.empty();
    private Optional<OnUpdatePortfolioListener> onUpdatePortfolioListenerOptional = Optional.empty();
    private Context context;
    private Optional<ViewModelProvider.Factory> viewModelFactoryOptional = Optional.empty();
    private SettingsFragmentViewModel settingsFragmentViewModel;

    private ScrollView rootLayout;
    private RadioGroup selectedSymbol;
    private RadioGroup selectedSortType;
    private ImageButton homeBtn;

    public interface OnUpdatePortfolioListener {
        void onUpdatePortfolio();
    }

    @NotNull
    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    /**
     * !onHomeClickListenerOptional.isPresent() and !onUpdatePortfolioListenerOptional.isPresent()
     * for check is testing (with mock) or no
     * {@link #setOnBackClickListenerOptional} {@link #setOnUpdateCoinListListenerOptional}
     *
     * @param context context
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        if (!onBackClickListenerOptional.isPresent() && context instanceof OnBackClickListener) {
            onBackClickListenerOptional = Optional.of((OnBackClickListener) context);
        }
        if (!onUpdatePortfolioListenerOptional.isPresent() && context instanceof OnUpdatePortfolioListener) {
            onUpdatePortfolioListenerOptional = Optional.of((OnUpdatePortfolioListener) context);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
        initViews(view);
    }

    /**
     * !viewModelFactoryOptional.isPresent() for check is testing (with mock) or no
     * {@link #setViewModelFactoryOptional}
     */

    private void initViewModel() {
        if (!viewModelFactoryOptional.isPresent()) {
            viewModelFactoryOptional = Optional.of(new SettingsFragmentViewModelFactory(context));
        }
        viewModelFactoryOptional.ifPresent(viewModelFactory -> settingsFragmentViewModel = new ViewModelProvider(
                getViewModelStore(), viewModelFactory).get(SettingsFragmentViewModel.class));
    }

    private void initViews(@NotNull View view) {
        rootLayout = view.findViewById(R.id.rootLayout);
        setOpenAnimation();
        selectedSortType = view.findViewById(R.id.selectedSortType);
        setCheckedSortType(view);
        setSelectedSortTypeChangeListener();
        selectedSymbol = view.findViewById(R.id.selectedSymbol);
        setCheckedSymbol(view);
        setSelectedSymbolChangeListener();
        homeBtn = view.findViewById(R.id.homeBtn);
        setBackBtnListener();
    }

    private void setOpenAnimation() {
        rootLayout.setAnimation(AnimationUtils.loadAnimation(context, R.anim.screen_change_forward));
    }

    private void setCheckedSortType(View view) {
        switch (settingsFragmentViewModel.getSortSettings()) {
            case TIME_ADD_SORT:
                ((RadioButton) view.findViewById(R.id.sortByAddTime)).setChecked(true);
                break;
            case ALPHABET_SORT:
                ((RadioButton) view.findViewById(R.id.sortByAlphabet)).setChecked(true);
                break;
            case SUM_SORT:
                ((RadioButton) view.findViewById(R.id.sortBySum)).setChecked(true);
                break;
        }
    }

    private void setSelectedSortTypeChangeListener() {
        selectedSortType.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.sortByAddTime:
                    settingsFragmentViewModel.saveSortSetting(TIME_ADD_SORT);
                    break;
                case R.id.sortByAlphabet:
                    settingsFragmentViewModel.saveSortSetting(ALPHABET_SORT);
                    break;
                case R.id.sortBySum:
                    settingsFragmentViewModel.saveSortSetting(SUM_SORT);
                    break;
            }
            onUpdatePortfolioListenerOptional.ifPresent(OnUpdatePortfolioListener::onUpdatePortfolio);
        });
    }

    private void setCheckedSymbol(View view) {
        switch (settingsFragmentViewModel.getFiatSettings()) {
            case USD:
                ((RadioButton) view.findViewById(R.id.usd)).setChecked(true);
                break;
            case EUR:
                ((RadioButton) view.findViewById(R.id.eur)).setChecked(true);
                break;
            case RUB:
                ((RadioButton) view.findViewById(R.id.rub)).setChecked(true);
                break;
            case GBP:
                ((RadioButton) view.findViewById(R.id.gbp)).setChecked(true);
                break;
            case JPY:
                ((RadioButton) view.findViewById(R.id.jpy)).setChecked(true);
                break;
            case KRW:
                ((RadioButton) view.findViewById(R.id.krw)).setChecked(true);
                break;
            case BYN:
                ((RadioButton) view.findViewById(R.id.byn)).setChecked(true);
                break;
            case BTC:
                ((RadioButton) view.findViewById(R.id.btc)).setChecked(true);
                break;
            case ETH:
                ((RadioButton) view.findViewById(R.id.eth)).setChecked(true);
                break;
        }
    }

    private void setSelectedSymbolChangeListener() {
        selectedSymbol.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.usd:
                    settingsFragmentViewModel.saveFiatSetting(USD);
                    break;
                case R.id.eur:
                    settingsFragmentViewModel.saveFiatSetting(EUR);
                    break;
                case R.id.rub:
                    settingsFragmentViewModel.saveFiatSetting(RUB);
                    break;
                case R.id.gbp:
                    settingsFragmentViewModel.saveFiatSetting(GBP);
                    break;
                case R.id.jpy:
                    settingsFragmentViewModel.saveFiatSetting(JPY);
                    break;
                case R.id.krw:
                    settingsFragmentViewModel.saveFiatSetting(KRW);
                    break;
                case R.id.byn:
                    settingsFragmentViewModel.saveFiatSetting(BYN);
                    break;
                case R.id.btc:
                    settingsFragmentViewModel.saveFiatSetting(BTC);
                    break;
                case R.id.eth:
                    settingsFragmentViewModel.saveFiatSetting(ETH);
                    break;
            }
            onUpdatePortfolioListenerOptional.ifPresent(OnUpdatePortfolioListener::onUpdatePortfolio);
        });
    }


    private void setBackBtnListener() {
        onBackClickListenerOptional.ifPresent(onBackClickListener ->
                homeBtn.setOnClickListener(v -> backWithAnimation(onBackClickListener)));
    }

    private void backWithAnimation(OnBackClickListener onBackClickListener) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.screen_change_backward);
        rootLayout.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                onBackClickListener.onBackClick();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onBackClickListenerOptional = Optional.empty();
        onUpdatePortfolioListenerOptional = Optional.empty();
        context = null;
    }

    @TestOnly
    void setViewModelFactoryOptional(ViewModelProvider.Factory viewModelFactory) {
        viewModelFactoryOptional = Optional.of(viewModelFactory);
    }

    @TestOnly
    void setOnBackClickListenerOptional(OnBackClickListener onBackClickListener) {
        onBackClickListenerOptional = Optional.of(onBackClickListener);
    }

    @TestOnly
    void setOnUpdateCoinListListenerOptional(OnUpdatePortfolioListener onUpdatePortfolioListener) {
        onUpdatePortfolioListenerOptional = Optional.of(onUpdatePortfolioListener);
    }
}
