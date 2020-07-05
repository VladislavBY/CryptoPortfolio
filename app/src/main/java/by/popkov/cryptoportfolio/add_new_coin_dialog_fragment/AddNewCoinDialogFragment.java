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
import androidx.lifecycle.ViewModelProvider;

import org.jetbrains.annotations.NotNull;

import by.popkov.cryptoportfolio.R;

public class AddNewCoinDialogFragment extends DialogFragment {
    public static final String TAG = "AddNewCoinDialogFragment";

    private AddNewCoinDialogFragmentViewModel addNewCoinDialogFragmentViewModel;
    private Context context;

    @NotNull
    public static AddNewCoinDialogFragment newInstance() {
        return new AddNewCoinDialogFragment();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        initViewModel();
        return makeDialog();
    }

    private void initViewModel() {
        if (getActivity() != null) {
            addNewCoinDialogFragmentViewModel = new ViewModelProvider(
                    getViewModelStore(), new AddNewCoinDialogFragmentViewModelFactory(getActivity().getApplication(), context)
            ).get(AddNewCoinDialogFragmentViewModel.class);
        }
    }

    @NotNull
    private Dialog makeDialog() {
        Dialog customDialog = new Dialog(context);
        customDialog.setContentView(R.layout.dialog_fragment_custom_add_coin);
        EditText coinSymbolEditText = customDialog.findViewById(R.id.coinSymbolEditText);
        EditText coinNumberEditText = customDialog.findViewById(R.id.coinNumberEditText);
        customDialog.findViewById(R.id.addBtn).setOnClickListener(v -> {
            saveNewCoin(coinSymbolEditText.getText().toString(), coinNumberEditText.getText().toString());
            customDialog.dismiss();
        });
        customDialog.findViewById(R.id.cancelBtn).setOnClickListener(v -> customDialog.dismiss());
        Window window = customDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        return customDialog;
    }

    private void saveNewCoin(String symbol, String number) {
        addNewCoinDialogFragmentViewModel.saveCoin(symbol, number);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }
}
