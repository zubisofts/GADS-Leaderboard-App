package com.zubisofts.gadsleaderboard.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.zubisofts.gadsleaderboard.R;
import com.zubisofts.gadsleaderboard.viewmodel.LeaderBoardViewModel;

public class ProjectSubmissionActivity extends AppCompatActivity {

    private EditText edtFirstName;
    private EditText edtLastName;
    private EditText edtEmail;
    private EditText edtLink;
    private LeaderBoardViewModel leaderBoardViewModel;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_submission);

        leaderBoardViewModel = new ViewModelProvider.NewInstanceFactory().create(LeaderBoardViewModel.class);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Submitting Project...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);

        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtEmail = findViewById(R.id.edtEmail);
        edtLink = findViewById(R.id.edtLink);

        findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitProject(view);
            }
        });

        leaderBoardViewModel.getProjectSubmitionResult().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean successful) {
                if (successful) {
                    showSuccessDialog();
                } else {
                    showFailureDialog();
                }

                hideLoadingDialog();
            }
        });
    }

    private void showFailureDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.custom_dialog_view, null, false);
        ImageView icon = view.findViewById(R.id.icon);
        TextView textView = view.findViewById(R.id.text);
        icon.setImageResource(R.drawable.ic_warning);
        textView.setText("Submission not Successful");
        new AlertDialog.Builder(this)
                .setView(view)
                .create().show();
    }

    private void showSuccessDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.custom_dialog_view, null, false);
        ImageView icon = view.findViewById(R.id.icon);
        TextView textView = view.findViewById(R.id.text);
        icon.setImageResource(R.drawable.ic_check_circle);
        textView.setText("Submission Successful");
        new AlertDialog.Builder(this)
                .setView(view)
                .create().show();
    }

    private void submitProject(View view) {

        if (TextUtils.isEmpty(edtFirstName.getText())) {
            Snackbar.make(view, "Please enter your First Name.", Snackbar.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(edtLastName.getText())) {
            Snackbar.make(view, "Please enter your Last Name.", Snackbar.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(edtEmail.getText())) {
            Snackbar.make(view, "Please enter your Email Address.", Snackbar.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(edtLink.getText())) {
            Snackbar.make(view, "Please enter your project GitHub link", Snackbar.LENGTH_SHORT).show();
            return;
        }

        View view2 = LayoutInflater.from(this).inflate(R.layout.custom_confirm_dialog_view, null, false);
        ImageView icon = view.findViewById(R.id.btnClose);
        ImageView confirm = view.findViewById(R.id.btnConfirm);

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view2)
                .create();

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showLoadingDialog();
                leaderBoardViewModel.submitProject(
                        edtEmail.getText().toString(),
                        edtLastName.getText().toString(),
                        edtFirstName.getText().toString(),
                        edtLink.getText().toString()
                );

                alertDialog.dismiss();
            }
        });
        alertDialog.show();


    }

    private void showLoadingDialog() {

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    private void hideLoadingDialog() {

        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}