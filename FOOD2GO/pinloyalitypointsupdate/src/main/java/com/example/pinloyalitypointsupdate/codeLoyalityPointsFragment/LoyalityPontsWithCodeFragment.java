package com.example.pinloyalitypointsupdate.codeLoyalityPointsFragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.pinloyalitypointsupdate.R;

import hr.foi.air.core.modularFunctionInterface.ILoyalityPointsUpdate;

public class LoyalityPontsWithCodeFragment extends Fragment implements ILoyalityPointsUpdate {

    View view;
    @Override
    public void setData(int korisnikID, String code) {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = container;
       return inflater.inflate(R.layout.fragment_pin_loyalty, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


    }
}
