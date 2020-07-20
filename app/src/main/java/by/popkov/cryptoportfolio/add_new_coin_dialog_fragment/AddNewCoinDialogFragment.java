package by.popkov.cryptoportfolio.add_new_coin_dialog_fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import by.popkov.cryptoportfolio.MyApplication;
import by.popkov.cryptoportfolio.R;

public class AddNewCoinDialogFragment extends DialogFragment {
    @Inject
    AddNewCoinDialogFragmentViewModel addNewCoinDialogFragmentViewModel;
    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        if (getActivity() != null) {
            ((MyApplication) getActivity().getApplication()).getAppComponent().inject(this);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return makeDialog();
    }

    @NotNull
    private Dialog makeDialog() {
        Dialog customDialog = new Dialog(context);
        customDialog.setContentView(R.layout.dialog_fragment_add_coin);
        EditText coinSymbolEditText = customDialog.findViewById(R.id.coinSymbolEditText);
        EditText coinNumberEditText = customDialog.findViewById(R.id.coinNumberEditText);
        customDialog.findViewById(R.id.positiveBtn).setOnClickListener(v -> {
            saveNewCoin(coinSymbolEditText.getText().toString(), coinNumberEditText.getText().toString());
            customDialog.dismiss();
        });
        customDialog.findViewById(R.id.negativeBtn).setOnClickListener(v -> customDialog.dismiss());
        Window window = customDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        return customDialog;
    }

    private void saveNewCoin(String symbol, String number) {
        if (addNewCoinDialogFragmentViewModel != null) {
            addNewCoinDialogFragmentViewModel.saveCoin(symbol, number);
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }
}
