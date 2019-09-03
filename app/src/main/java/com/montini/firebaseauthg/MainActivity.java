package com.montini.firebaseauthg;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    // constants

    private static final String TAG = "MainActivity";

    // private static final Class[] CLASSES = new Class[]{
    //         GoogleSignInActivity.class,
    //         FacebookLoginActivity.class,
    // };
    //
    // private static final int[] DESCRIPTION_IDS = new int[]{
    //         R.string.desc_google_sign_in,
    //         R.string.desc_facebook_login,
    // };

    // vars

    private FirebaseAuth mAuth;

    private TextView mStatusTextView;
    private TextView mDetailTextView;

    // Facebook callback manager
    private CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Views
        mStatusTextView = findViewById(R.id.status); // TODO: amend activity_main.xml
        mDetailTextView = findViewById(R.id.detail); // ---"---

        mAuth = FirebaseAuth.getInstance();
    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    // [END on_start_check_user]

    private void updateUI(FirebaseUser user) {
        // hideProgressDialog();
        if (user != null) {
            Snackbar.make(findViewById(R.id.main_layout), user.getDisplayName(),Snackbar.LENGTH_LONG).show();
            mStatusTextView.setText(user.getDisplayName());
            mDetailTextView.setText(user.getUid());

            // findViewById(R.id.signInButton).setVisibility(View.VISIBLE);
            // findViewById(R.id.buttonFacebookLogin).setVisibility(View.VISIBLE);
        } else {
            Snackbar.make(findViewById(R.id.main_layout), "Please sign in", Snackbar.LENGTH_LONG).show();
            mStatusTextView.setText(R.string.signed_out);
            mDetailTextView.setText(null);

            // findViewById(R.id.signInButton).setVisibility(View.VISIBLE);
            // findViewById(R.id.buttonFacebookLogin).setVisibility(View.VISIBLE);
        }
    }

}
