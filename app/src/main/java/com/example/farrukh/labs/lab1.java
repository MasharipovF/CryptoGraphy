package com.example.farrukh.labs;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class lab1 extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    static int msg_len;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        int matrix_dimen_ver, matrix_dimen_hor;
        int type;


        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            if (sectionNumber == 1) args.putString(ARG_SECTION_NUMBER, "Encryption");
            else args.putString(ARG_SECTION_NUMBER, "Decryption");
            fragment.setArguments(args);
            return fragment;
        }


        @Override
        public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                                 final Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_lab1, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getArguments().getString(ARG_SECTION_NUMBER));

            Button go_btn = (Button) rootView.findViewById(R.id.btn);
            Button enc_btn = (Button) rootView.findViewById(R.id.enc_btn);
            final TextView result = (TextView) rootView.findViewById(R.id.result_txt);
            final EditText msg = (EditText) rootView.findViewById(R.id.msg);
            final EditText dimen_ver = (EditText) rootView.findViewById(R.id.dimen_hor);
            final EditText dimen_hor = (EditText) rootView.findViewById(R.id.dimen_ver);
            final TextView msg_length = (TextView) rootView.findViewById(R.id.msgLen);
            final EditText key_ver = (EditText) rootView.findViewById(R.id.second_key);
            final EditText key_hor = (EditText) rootView.findViewById(R.id.first_key);
            RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.radio_group);

            if (getArguments().getString(ARG_SECTION_NUMBER).equals("Decryption"))
                go_btn.setVisibility(View.GONE);
            else go_btn.setVisibility(View.VISIBLE);
            key_hor.setVisibility(View.GONE);
            key_ver.setVisibility(View.GONE);

            type = radioGroup.getCheckedRadioButtonId();

            result.setMovementMethod(new ScrollingMovementMethod());

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    type = checkedId;
                    switch (checkedId) {
                        case R.id.radio_no_key:
                            key_hor.setVisibility(View.GONE);
                            key_ver.setVisibility(View.GONE);
                            break;
                        case R.id.radio_one_key:
                            key_hor.setVisibility(View.VISIBLE);
                            key_ver.setVisibility(View.GONE);
                            break;
                        case R.id.radio_two_key:
                            key_hor.setVisibility(View.VISIBLE);
                            key_ver.setVisibility(View.VISIBLE);
                            break;
                        default:
                            key_hor.setVisibility(View.GONE);
                            key_ver.setVisibility(View.GONE);
                            break;
                    }
                }
            });

            msg.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    msg_length.setText(String.valueOf(s.length()));
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            go_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (TextUtils.isEmpty(msg.getText())) {
                        msg.setError("Fill in this field first!");
                        return;
                    }
                    if (TextUtils.isEmpty(dimen_ver.getText())) {
                        dimen_ver.setError("Fill in field first!");
                        return;
                    }
                    if (TextUtils.isEmpty(dimen_hor.getText())) {
                        dimen_hor.setError("Fill in field first!");
                        return;
                    }

                    result.setText("");
                    String tmpStr = msg.getText().toString();
                    msg_len = tmpStr.length();
                    matrix_dimen_ver = Integer.parseInt(dimen_ver.getText().toString());
                    matrix_dimen_hor = Integer.parseInt(dimen_hor.getText().toString());

                    if (matrix_dimen_hor * matrix_dimen_ver >= msg_len) {
                        if (matrix_dimen_hor * matrix_dimen_ver - msg_len >= matrix_dimen_ver) {
                            dimen_ver.setError("Matrix is too big");
                            dimen_hor.setError("Matrix is too big");
                            return;
                        }
                    } else {
                        dimen_ver.setError("Matrix is too small");
                        dimen_hor.setError("Matrix is too small");
                        return;
                    }

                    String result_string = "";
                    int k = 0;

                    for (int i = 0; i < matrix_dimen_ver; i++) {
                        for (int j = 0; j < matrix_dimen_hor; j++) {
                            if (k >= msg_len) {
                                result.setText(result.getText() + " " + "_");
                            } else {
                                result.setText(result.getText() + " " + tmpStr.toCharArray()[k++]);
                            }
                        }
                        result.setText(result.getText() + "\n");
                    }
                }
            });

            enc_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (TextUtils.isEmpty(msg.getText())) {
                        msg.setError("Fill in this field first!");
                        return;
                    }
                    if (TextUtils.isEmpty(dimen_ver.getText())) {
                        dimen_ver.setError("Fill in field first!");
                        return;
                    }
                    if (TextUtils.isEmpty(dimen_hor.getText())) {
                        dimen_hor.setError("Fill in field first!");
                        return;
                    }
                    if (key_hor.getVisibility() == View.VISIBLE && TextUtils.isEmpty(key_hor.getText())) {
                        key_hor.setError("Fill in field first!");
                        return;
                    }
                    if (key_ver.getVisibility() == View.VISIBLE && TextUtils.isEmpty(key_ver.getText())) {
                        key_ver.setError("Fill in field first!");
                        return;
                    }

                    result.setText("");
                    String tmpStr = msg.getText().toString();
                    msg_len = tmpStr.length();
                    matrix_dimen_ver = Integer.parseInt(dimen_ver.getText().toString());
                    matrix_dimen_hor = Integer.parseInt(dimen_hor.getText().toString());

                    if (matrix_dimen_hor * matrix_dimen_ver >= msg_len) {
                        if (matrix_dimen_hor * matrix_dimen_ver - msg_len >= matrix_dimen_ver) {
                            dimen_ver.setError("Matrix is too big");
                            dimen_hor.setError("Matrix is too big");
                            return;
                        }
                    } else {
                        dimen_ver.setError("Matrix is too small");
                        dimen_hor.setError("Matrix is too small");
                        return;
                    }

                    String result_string = "";

                    switch (getArguments().getString(ARG_SECTION_NUMBER)) {
                        case "Encryption":
                            result_string = encrypt(matrix_dimen_ver, matrix_dimen_hor, tmpStr.toCharArray(), result, key_hor.getText().toString(), key_ver.getText().toString());
                            break;
                        case "Decryption":
                            result_string = decrypt(matrix_dimen_ver, matrix_dimen_hor, tmpStr.toCharArray(), result, key_hor.getText().toString(), key_ver.getText().toString());
                            break;
                        default:
                            result_string = "";
                            break;
                    }
                    result.setText(result.getText() + "\nRESULT: " + result_string + "\n\n\n");

                }
            });

            return rootView;
        }

        String decrypt(int a, int b, char[] message, TextView resultTxt, String first_key, String second_key) {
            String result = "";
            int k, g = 0;
            int msg_len = message.length;
            char[] result_msg = new char[a * b];
            char[][] matrix = new char[a][b];
            for (int j = 0; j < b; j++) {
                k = 0;
                while (k < a) {
                    if (g >= msg_len) matrix[k++][j] = '_';
                    else matrix[k++][j] = message[g++];
                }
            }

            int[] key1, key2;

            switch (type) {
                case R.id.radio_no_key:
                    k = 0;
                    for (int i = 0; i < a; i++) {
                        for (int j = 0; j < b; j++) {
                            result_msg[k++] = matrix[i][j];
                            resultTxt.setText(resultTxt.getText() + " " + matrix[i][j]);
                        }
                        resultTxt.setText(resultTxt.getText() + "\n");
                    }
                    break;
                case R.id.radio_one_key:
                    k = 0;
                    key1 = keyGenerator(first_key);

                    for (int i = 0; i < a; i++) {
                        for (int j = 0; j < b; j++) {
                            result_msg[k++] = matrix[i][key1[j] - 1];
                            resultTxt.setText(resultTxt.getText() + " " + matrix[i][j]);
                        }
                        resultTxt.setText(resultTxt.getText() + "\n");
                    }
                    break;
                case R.id.radio_two_key:

                    key1 = keyGenerator(first_key);
                    key2 = keyGenerator(second_key);

                    showMatrix(resultTxt, matrix, a, b);

                    char[][] tmpmatrix = new char[a][b];
                    for (int j = 0; j < b; j++) {
                        for (g = 0; g < a; g++) {
                            tmpmatrix[g][j] = matrix[g][key1[j] - 1];
                        }
                    }

                    resultTxt.setText(resultTxt.getText() + "\n*****\n");
                    showMatrix(resultTxt, tmpmatrix, a, b);

                    for (int i = 0; i < a; i++) {
                        for (g = 0; g < b; g++) {
                            matrix[i][g] = tmpmatrix[key2[i] - 1][g];
                        }
                    }

                    resultTxt.setText(resultTxt.getText() + "\n*****\n");
                    showMatrix(resultTxt, matrix, a, b);

                    k = 0;
                    for (int i = 0; i < a; i++) {
                        for (int j = 0; j < b; j++) {
                            result_msg[k++] = matrix[i][j];
                        }
                    }

                    break;
                default:
                    break;
            }

            for (Character c : result_msg) {
                result += c.toString();
            }
            return result;
        }

        String encrypt(int a, int b, char[] message, TextView resultTxt, String first_key, String second_key) {
            String result = "";
            int k = 0, g = 0;
            char[] result_msg = new char[a * b];
            char[][] matrix = new char[a][b];

            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++) {
                    if (k >= msg_len) {
                        matrix[i][j] = '_';
                        resultTxt.setText(resultTxt.getText() + " " + "_");
                    } else {
                        matrix[i][j] = message[k++];
                        resultTxt.setText(resultTxt.getText() + " " + matrix[i][j]);
                    }
                }
                resultTxt.setText(resultTxt.getText() + "\n");
            }

            // for switch
            int cur_key;
            int[] key1, key2;
            char swapChar;

            switch (type) {
                case R.id.radio_one_key:
                    cur_key = 1;
                    k = 0;
                    key1 = keyGenerator(first_key);
                    while (cur_key < b) {
                        if (key1[k] == cur_key) {
                            for (g = 0; g < a; g++) {
                                swapChar = matrix[g][k];
                                matrix[g][k] = matrix[g][cur_key - 1];
                                matrix[g][cur_key - 1] = swapChar;
                            }
                            cur_key++;
                            k = 0;
                        }
                        k++;
                    }

                    resultTxt.setText(resultTxt.getText() + "\n*****\n");
                    showMatrix(resultTxt, matrix, a, b);
                    break;
                case R.id.radio_two_key:

                    key1 = keyGenerator(first_key);
                    key2 = keyGenerator(second_key);

                    ////////////////////////////// 1 step
                    cur_key = 1;
                    k = 0;
                    while (cur_key < b) {
                        if (key1[k] == cur_key) {
                            for (g = 0; g < a; g++) {
                                swapChar = matrix[g][k];
                                matrix[g][k] = matrix[g][cur_key - 1];
                                matrix[g][cur_key - 1] = swapChar;
                            }
                            cur_key++;
                            k = 0;
                        }
                        k++;
                    }
                    resultTxt.setText(resultTxt.getText() + "\n*****\n");
                    showMatrix(resultTxt, matrix, a, b);

                    ///////////////////////////// 2 step
                    cur_key = 1;
                    k = 0;
                    while (cur_key < a) {
                        if (key2[k] == cur_key) {
                            for (g = 0; g < b; g++) {
                                swapChar = matrix[k][g];
                                matrix[k][g] = matrix[cur_key - 1][g];
                                matrix[cur_key - 1][g] = swapChar;
                            }
                            cur_key++;
                            k = 0;
                        }
                        k++;
                    }
                    resultTxt.setText(resultTxt.getText() + "\n*****\n");
                    showMatrix(resultTxt, matrix, a, b);

                    break;
                default:
                    break;
            }


            /// converting matrix to simple string
            g = 0;
            for (int j = 0; j < b; j++) {
                k = 0;
                while (k < a) {
                    result_msg[g++] = matrix[k++][j];
                }
            }

            for (Character c : result_msg) {
                result += c.toString();
            }

            return result;
        }

        void showMatrix(TextView view, char[][] matrix, int a, int b) {
            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++) {
                    view.setText(view.getText() + " " + matrix[i][j]);
                }
                view.setText(view.getText() + "\n");
            }
        }

        int[] keyGenerator(String strKey) {
            int[] key;
            int i = 0;
            String[] tmpKey = strKey.split(" ", -1);
            key = new int[tmpKey.length];
            for (String str : tmpKey) {
                key[i++] = Integer.parseInt(str.trim());
            }
            return key;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
            }
            return null;
        }
    }

}
