package com.example.qrcodeloyalitypointsupdate.Fragmenti.QRCodeFragmenti;

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


import com.example.qrcodeloyalitypointsupdate.R;

import hr.foi.air.core.modularFunctionInterface.ILoyalityPointsUpdate;

public class QRCodeFragment extends Fragment  implements ILoyalityPointsUpdate
{
    View view;
    public boolean Modul;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = container;
        return inflater.inflate(R.layout.fragment_qrcode, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


    }


    @Override
    public void setData(int korisnikID, String code) {

    }
}
