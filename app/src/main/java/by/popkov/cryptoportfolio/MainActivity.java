package by.popkov.cryptoportfolio;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import by.popkov.cryptoportfolio.coin_info_fragment.CoinInfoFragment;
import by.popkov.cryptoportfolio.data_classes.CoinForView;
import by.popkov.cryptoportfolio.my_portfolio_fragment.CoinListAdapter;
import by.popkov.cryptoportfolio.my_portfolio_fragment.MyPortfolioFragment;
import by.popkov.cryptoportfolio.settings_fragment.SettingsFragment;

/**
 * Apps base activity.
 */
public class MainActivity extends AppCompatActivity implements CoinListAdapter.OnCoinListClickListener,
        OnBackClickListener, MyPortfolioFragment.OnSettingsBtnClickListener, SettingsFragment.OnUpdatePortfolioListener {

    /**
     * Implements {@link OnBackClickListener} for back navigational btn.
     */
    @Override
    public void onBackClick() {
        getSupportFragmentManager().popBackStack();
    }

    /**
     * Implements {@link MyPortfolioFragment.OnSettingsBtnClickListener} for settings navigational btn.
     */
    @Override
    public void onClickSettings() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, SettingsFragment.newInstance(), SettingsFragment.TAG)
                .addToBackStack(null)
                .commit();
    }

    /**
     * Implements {@link CoinListAdapter.OnCoinListClickListener}
     * for click on {@link CoinListAdapter} items navigational btn.
     */
    @Override
    public void onItemClick(CoinForView coinForView) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, CoinInfoFragment.newInstance(coinForView), CoinInfoFragment.TAG)
                .addToBackStack(null)
                .commit();
    }

    /**
     * Implements {@link SettingsFragment.OnUpdatePortfolioListener} for update data in {@link MyPortfolioFragment}.
     */
    @Override
    public void onUpdatePortfolio() {
        Fragment myPortfolioFragment = getSupportFragmentManager().findFragmentByTag(MyPortfolioFragment.TAG);
        if (myPortfolioFragment instanceof MyPortfolioFragment) {
            ((MyPortfolioFragment) myPortfolioFragment).updatePortfolioData();
        }
    }

    /**
     * At first Create call {@link #showMyPortfolioFragment()}
     *
     * @param savedInstanceState will be null on app start
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            showMyPortfolioFragment();
        }
    }

    /**
     * Shows main fragment {@link MyPortfolioFragment} with summary portfolio info
     */
    private void showMyPortfolioFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, MyPortfolioFragment.newInstance(), MyPortfolioFragment.TAG)
                .commit();
    }
}
