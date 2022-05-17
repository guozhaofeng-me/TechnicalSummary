package fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * User: Zephyr
 * Date: 2022/5/16
 * Time: 10:05
 */
public class BaseDialogFragment extends DialogFragment {
    @Override
    public int show(@NonNull FragmentTransaction transaction, @Nullable String tag) {
        if (isAdded()) {
            return 0;
        }
        return show(transaction, tag);
    }


}
