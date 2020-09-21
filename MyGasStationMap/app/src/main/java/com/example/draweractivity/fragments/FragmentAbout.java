package com.example.draweractivity.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.draweractivity.R;
import com.example.draweractivity.activity_infoText;


public class FragmentAbout extends Fragment {
    Button buttonSendEmail;
    Button buttonInfo;
    String [] addresses = new String[]{"andrey_undricov@mail.ru"};
    String subject = "Сообщение из приложения";
    Uri attachment;
    String sendEmail;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        buttonSendEmail = view.findViewById(R.id.button_Otziv);
        buttonInfo = view.findViewById(R.id.button_info);
        buttonSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
        buttonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), activity_infoText.class));

            }
        });

        return view;
    }

    private void sendEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL,addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, sendEmail);
//        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


