package by.popkov.cryptoportfolio.coin_info_fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import by.popkov.cryptoportfolio.MyApplication;
import by.popkov.cryptoportfolio.R;
import by.popkov.cryptoportfolio.data_classes.CoinForView;

public class CoinInfoFragment extends Fragment {
    @Inject
    CoinInfoFragmentViewModel coinInfoFragmentViewModel;
    private Context context;
    private ImageView coinIcon;
    private TextView coinSymbol;
    private TextView coinNumberData;
    private TextView coinSumData;
    private TextView coinChangeSum24HourData;
    private TextView coinPriseData;
    private TextView coinChangePercent24HourData;
    private TextView coinChange24HourData;
    private TextView coinMarketCapData;
    private TextView coinMarket24VolumeData;
    private TextView coinGlobalSupplyData;
    private Button editBtn;
    private Button deleteBtn;
    private ImageButton homeBtn;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        if (getActivity() != null) {
            ((MyApplication) getActivity().getApplication()).getAppComponent().inject(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_coin_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initViewModel();
    }

    private void initViews(@NonNull View view) {
        coinIcon = view.findViewById(R.id.coinIcon);
        coinSymbol = view.findViewById(R.id.coinSymbol);
        coinNumberData = view.findViewById(R.id.coinNumberData);
        coinSumData = view.findViewById(R.id.coinSumData);
        coinChangeSum24HourData = view.findViewById(R.id.coinChangeSum24HourData);
        coinPriseData = view.findViewById(R.id.coinPriseData);
        coinChangePercent24HourData = view.findViewById(R.id.coinChangePercent24HourData);
        coinChange24HourData = view.findViewById(R.id.coinChange24HourData);
        coinMarketCapData = view.findViewById(R.id.coinMarketCapData);
        coinMarket24VolumeData = view.findViewById(R.id.coinMarket24VolumeData);
        coinGlobalSupplyData = view.findViewById(R.id.coinGlobalSupplyData);
        editBtn = view.findViewById(R.id.editBtn);
        deleteBtn = view.findViewById(R.id.deleteBtn);
        homeBtn = view.findViewById(R.id.homeBtn);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        progressBar = view.findViewById(R.id.progressBar);
        setBtnListeners();
        setSwipeRefreshLayout();
    }

    private void setBtnListeners() {
        deleteBtn.setOnClickListener(v -> showDeleteDialog());
        editBtn.setOnClickListener(v -> showEditDialog());
        homeBtn.setOnClickListener(v -> Navigation.findNavController(v).popBackStack());
    }

    private void showDeleteDialog() {
        Dialog customDeleteDialog = new Dialog(context);
        customDeleteDialog.setContentView(R.layout.dialog_delete_coin);
        customDeleteDialog.findViewById(R.id.positiveBtn).setOnClickListener(v -> {
            coinInfoFragmentViewModel.deleteCoin();
            customDeleteDialog.dismiss();
            NavHostFragment.findNavController(this).popBackStack();
        });
        customDeleteDialog.findViewById(R.id.negativeBtn).setOnClickListener(v -> customDeleteDialog.dismiss());
        Window window = customDeleteDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        customDeleteDialog.show();
    }

    private void showEditDialog() {
        Dialog customEditDialog = new Dialog(context);
        customEditDialog.setContentView(R.layout.dialog_edit_coin);
        EditText coinNumberEditText = customEditDialog.findViewById(R.id.coinNumberEditText);
        customEditDialog.findViewById(R.id.positiveBtn).setOnClickListener(v -> {
            coinInfoFragmentViewModel.updateCoin(Double.valueOf(coinNumberEditText.getText().toString()));
            customEditDialog.dismiss();
        });
        customEditDialog.findViewById(R.id.negativeBtn).setOnClickListener(v -> customEditDialog.dismiss());
        Window window = customEditDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        customEditDialog.show();
    }

    private void setSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            coinInfoFragmentViewModel.refreshCoinData();
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    private void initViewModel() {
        if (coinInfoFragmentViewModel != null) {
            coinInfoFragmentViewModel.setCoinForView(extractCoinForView());
            coinInfoFragmentViewModel.getCoinForViewLiveData().observe(getViewLifecycleOwner(), this::setViewsData);
            coinInfoFragmentViewModel.getIsLoadingLiveData().observe(getViewLifecycleOwner(), this::loadSwitcher);
        }

    }

    private CoinForView extractCoinForView() {
        if (getArguments() != null) {
            return CoinInfoFragmentArgs.fromBundle(getArguments()).getCoinForVIew();
        }
        throw new IllegalArgumentException("Not found CoinForView in Arguments");
    }

    private void setViewsData(@NotNull CoinForView coinForView) {
        Glide.with(this)
                .load(coinForView.getLogoUrl())
                .into(coinIcon);
        coinSymbol.setText(coinForView.getSymbol());
        coinNumberData.setText(coinForView.getNumber());
        coinSumData.setText(coinForView.getSum());
        coinChangeSum24HourData.setText(coinForView.getChangeSum24Hour());
        coinChangeSum24HourData.setTextColor(coinForView.getChange24Color());
        coinPriseData.setText(coinForView.getPrise());
        coinChangePercent24HourData.setText(coinForView.getChangePercent24Hour());
        coinChangePercent24HourData.setTextColor(coinForView.getChange24Color());
        coinChange24HourData.setText(coinForView.getChange24Hour());
        coinChange24HourData.setTextColor(coinForView.getChange24Color());
        coinMarketCapData.setText(coinForView.getMarketCap());
        coinMarket24VolumeData.setText(coinForView.getMarket24Volume());
        coinGlobalSupplyData.setText(coinForView.getGlobalSupply());
    }

    private void loadSwitcher(boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
        } else progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }
}
