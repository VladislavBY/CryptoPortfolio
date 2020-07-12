package by.popkov.cryptoportfolio;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import by.popkov.cryptoportfolio.my_portfolio_fragment.MyPortfolioFragment;

import static by.popkov.cryptoportfolio.settings_fragment.SettingsFragment.*;

/**
 * Apps base activity.
 */
public class MainActivity extends AppCompatActivity implements OnUpdatePortfolioListener {

    /**
     * Implements {@link OnUpdatePortfolioListener} for update data in {@link MyPortfolioFragment}.
     */
    @Override
    public void onUpdatePortfolio() {
        Fragment myPortfolioFragment = getSupportFragmentManager().findFragmentByTag(MyPortfolioFragment.TAG);
        if (myPortfolioFragment instanceof MyPortfolioFragment) {
//            ((MyPortfolioFragment) myPortfolioFragment).updatePortfolioData();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
