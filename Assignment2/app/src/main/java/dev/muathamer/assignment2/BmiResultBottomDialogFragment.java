package dev.muathamer.assignment2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BmiResultBottomDialogFragment extends BottomSheetDialogFragment {

    public static BmiResultBottomDialogFragment newInstance() {
        return new BmiResultBottomDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bottom_sheet_bmi_result, container,
                false);

        double bmiValue = getArguments().getDouble("bmiValue");
        String bmiStatus = getArguments().getString("bmiStatus");

        TextView valueTextView = view.findViewById(R.id.bmiValueTextView);
        TextView statusTextView = view.findViewById(R.id.bmiStatusTextView);

        valueTextView.setText(String.format("%s", bmiValue));
        statusTextView.setText(bmiStatus);

        return view;

    }
}
