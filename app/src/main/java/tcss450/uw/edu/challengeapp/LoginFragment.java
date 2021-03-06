package tcss450.uw.edu.challengeapp;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import tcss450.uw.edu.challengeapp.model.Credentials;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;

    EditText name;

    EditText pass;

    Credentials.Builder creds;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        Button b = (Button) (v.findViewById(R.id.loginButton));
        b.setOnClickListener(this);

        b = (Button) (v.findViewById(R.id.registerButton));
        b.setOnClickListener(this);

        name = v.findViewById(R.id.usernameText);
        pass = v.findViewById(R.id.passwordText);

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        if (mListener != null) {
            switch (view.getId()) {
                case R.id.loginButton:
                    checkCredentials();
                    break;
                case R.id.registerButton:
                    mListener.onRegisterClicked();
                    break;
                default:
                    Log.wtf("", "Didn't expect to see me...");
            }
        }
    }

    private void checkCredentials() {
        if (name.getText().toString().length() > 1 && pass.getText().toString().length() > 1) {
            Editable password = new SpannableStringBuilder(pass.getText());
            creds = new Credentials.Builder(name.getText().toString(), password);
            mListener.onLoginAttempt(creds.build());
        } else {
            setError("ERROR");
            if (name.getText().toString().length() < 1) {
                name.setError("Username cannot be empty");
            }
            if (pass.getText().toString().length() < 1) {
                pass.setError("Password cannot be empty");
            }
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onLoginAttempt(Credentials credentials);
        void onRegisterClicked();
    }
    /**
     * Allows an external source to set an error message on this fragment. This may
     * be needed if an Activity includes processing that could cause login to fail.
     * @param err the error message to display.
     */
    public void setError(String err) {
//Log in unsuccessful for reason: err. Try again.
//you may want to add error stuffs for the user here.
        ((TextView) getView().findViewById(R.id.usernameText))
                .setError("Login Unsuccessful");
    }

}
