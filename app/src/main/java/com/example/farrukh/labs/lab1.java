package com.example.farrukh.labs;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
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
import android.util.Log;
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

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.Arrays;

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
            final Context context = getContext();

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
                                result.setText(result.getText() + " " + " ");
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
                    if (TextUtils.isEmpty(dimen_ver.getText()) || Integer.parseInt(dimen_ver.getText().toString()) > 10) {
                        dimen_ver.setError("Fill in field first!");
                        return;
                    }
                    if (TextUtils.isEmpty(dimen_hor.getText()) || Integer.parseInt(dimen_hor.getText().toString()) > 10) {
                        dimen_hor.setError("Fill in field first!");
                        return;
                    }

                    if (msg.getText().length() > 100) {
                        msg.setError("Too long!");
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

                    //check key
                    if (key_hor.getVisibility() == View.VISIBLE) {
                        if (keyGenerator(key_hor.getText().toString()) == null) {
                            key_hor.setError("Key is incorrect !");
                            return;
                        }
                        if (!checkKey(key_hor.getText().toString(), Integer.parseInt(dimen_hor.getText().toString()))) {
                            key_hor.setError("Key is incorrect !");
                            return;
                        }
                    }

                    if (key_ver.getVisibility() == View.VISIBLE) {
                        if (keyGenerator(key_ver.getText().toString()) == null) {
                            key_ver.setError("Key is incorrect !");
                            return;
                        }
                        if (!checkKey(key_ver.getText().toString(), Integer.parseInt(dimen_ver.getText().toString()))) {
                            key_ver.setError("Key is incorrect !");
                            return;
                        }
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

                    if (getArguments().getString(ARG_SECTION_NUMBER).equals("Encryption")) {
                        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("result", result_string);
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(getContext(), "Result has been copied to clipboard!", Toast.LENGTH_SHORT).show();
                    }

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
            char[][] result_matrix = new char[a][b];
            for (int j = 0; j < b; j++) {
                k = 0;
                while (k < a) {
                    if (g >= msg_len) {
                        matrix[k][j] = ' ';
                        result_matrix[k++][j] = ' ';
                    } else {
                        matrix[k][j] = message[g];
                        result_matrix[k++][j] = message[g++];
                    }
                }
            }

            showMatrix(resultTxt, matrix, a, b);

            int[] key1, key2;

            switch (type) {
                case R.id.radio_no_key:
                    k = 0;
                    for (int i = 0; i < a; i++) {
                        for (int j = 0; j < b; j++) {
                            result_msg[k++] = matrix[i][j];
                        }
                    }
                    break;
                case R.id.radio_one_key:
                    k = 0;
                    key1 = keyGenerator(first_key);

                    for (int i = 0; i < a; i++) {
                        for (int j = 0; j < b; j++) {
                            result_matrix[i][key1[j]] = matrix[i][j];
                        }
                    }
                    resultTxt.setText(resultTxt.getText() + "\n*****\n");
                    showMatrix(resultTxt, result_matrix, a, b);


                    break;
                case R.id.radio_two_key:

                    key1 = keyGenerator(first_key);
                    key2 = keyGenerator(second_key);
                    char[][] tmp_matrix = new char[a][b];

                    for (int i = 0; i < key2.length; i++) {
                        Log.d("TAG", "key 2: " + key2[i]);
                    }
                    for (int i = 0; i < a; i++) {
                        for (int j = 0; j < b; j++) {
                            tmp_matrix[i][key1[j]] = matrix[i][j];
                        }
                    }
                    resultTxt.setText(resultTxt.getText() + "\n*****\n");
                    showMatrix(resultTxt, tmp_matrix, a, b);

                    for (int i = 0; i < a; i++) {
                        for (int j = 0; j < b; j++) {
                            result_matrix[key2[i]][j] = tmp_matrix[i][j];
                            Log.d("TAG", "matrix: " + key2[i] + "  " + j + "   index: " + i);
                        }
                    }

                    resultTxt.setText(resultTxt.getText() + "\n*****\n");
                    showMatrix(resultTxt, result_matrix, a, b);

                    break;
                default:
                    break;
            }

            g = 0;
            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++) {
                    result_msg[g++] = result_matrix[i][j];
                }
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
            char[][] result_matrix = new char[a][b];

            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++) {
                    if (k >= msg_len) {
                        matrix[i][j] = ' ';
                        result_matrix[i][j] = ' ';
                        resultTxt.setText(resultTxt.getText() + " " + " ");
                    } else {
                        matrix[i][j] = message[k];
                        result_matrix[i][j] = message[k++];
                        resultTxt.setText(resultTxt.getText() + " " + matrix[i][j]);
                    }
                }
                resultTxt.setText(resultTxt.getText() + "\n");
            }

            // for switch
            int[] key1, key2;

            switch (type) {
                case R.id.radio_one_key:
                    k = 0;
                    key1 = keyGenerator(first_key);

                    for (int i = 0; i < a; i++) {
                        for (int j = 0; j < b; j++) {
                            result_matrix[i][j] = matrix[i][key1[j]];
                        }
                    }
                    resultTxt.setText(resultTxt.getText() + "\n*****\n");
                    showMatrix(resultTxt, result_matrix, a, b);
                    break;
                case R.id.radio_two_key:

                    key1 = keyGenerator(first_key);
                    key2 = keyGenerator(second_key);

                    for (int i = 0; i < a; i++) {
                        for (int j = 0; j < b; j++) {
                            result_matrix[i][j] = matrix[key2[i]][key1[j]];
                        }
                    }

                    resultTxt.setText(resultTxt.getText() + "\n*****\n");
                    showMatrix(resultTxt, result_matrix, a, b);

                    break;
                default:
                    break;
            }


            /// converting matrix to simple string
            g = 0;
            for (int j = 0; j < b; j++) {
                k = 0;
                while (k < a) {
                    result_msg[g++] = result_matrix[k++][j];
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
            int[] result;
            int i, j = 0;
            key = new int[strKey.length()];
            result = new int[strKey.length()];
            for (i = 0; i < result.length; i++) {
                result[i] = i;
            }
            Log.d("TAG", "Key " + strKey);
            for (i = 0; i < strKey.length(); i++) {
                Log.d("TAG", "Key " + strKey.charAt(i) + "  length " + strKey.length());
                if (!Character.isDigit(strKey.charAt(i))) return null;
                key[j++] = Integer.parseInt(String.valueOf(strKey.charAt(i)));
            }

            int tmp;
            for (i = 0; i < key.length - 1; i++) {
                for (j = 1; j < key.length; j++) {
                    if (key[j - 1] >= key[j]) {
                        tmp = key[j - 1];
                        key[j - 1] = key[j];
                        key[j] = tmp;
                        tmp = result[j - 1];
                        result[j - 1] = result[j];
                        result[j] = tmp;
                    }
                }
            }
            for (int ind : result) {

                Log.d("TAG", "key result: " + ind);

            }
            return result;
        }

        boolean checkKey(String strKey, int dimen) {
            int i = 0, j = 0;
            int[] key = new int[strKey.length()];
            for (i = 0; i < strKey.length(); i++) {
                if (!Character.isDigit(strKey.charAt(i))) return false;
                key[j++] = Integer.parseInt(String.valueOf(strKey.charAt(i)));
            }

            if (key.length != dimen) return false;

            int[] tmp = key;
            Arrays.sort(tmp);
            for (i = 0; i < tmp.length - 1; i++) {
                if (tmp[i] == tmp[i + 1]) return false;
            }

            for (int aKey : key) {
                if (aKey < 0 || aKey > 9) return false;
            }
            return true;
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
