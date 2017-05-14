package com.example.farrukh.labs;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.annotation.IntegerRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.nio.charset.StandardCharsets;

public class lab2 extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2);

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

        public PlaceholderFragment() {
        }

        int type = R.id.radio_vernam;
        public char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' '};
        public int[] alphabet_index = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27};


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
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_lab2, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getArguments().getString(ARG_SECTION_NUMBER));

            final EditText msg = (EditText) rootView.findViewById(R.id.msg);
            final TextView msgLen = (TextView) rootView.findViewById(R.id.msgLen);
            final EditText key = (EditText) rootView.findViewById(R.id.key);
            final TextView keyLen = (TextView) rootView.findViewById(R.id.keyLen);
            RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.radio_group);
            Button btn = (Button) rootView.findViewById(R.id.btn);
            final TextView resultTxt = (TextView) rootView.findViewById(R.id.result_txt);
            final Context context = getContext();

            resultTxt.setMovementMethod(new ScrollingMovementMethod());


            type = radioGroup.getCheckedRadioButtonId();


            msg.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    msgLen.setText(String.valueOf(s.length()));
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            key.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    keyLen.setText(String.valueOf(s.length()));
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    type = checkedId;
                    resultTxt.setText("");
                }
            });

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (TextUtils.isEmpty(msg.getText())) {
                        msg.setError("Fill in this field first!");
                        return;
                    }
                    if (TextUtils.isEmpty(key.getText())) {
                        key.setError("Fill in this field first!");
                        return;
                    }

                    String resultMsg = "";
                    resultTxt.setText("");

                    switch (type) {
                        case R.id.radio_vernam:
                            if (msg.getText().toString().matches("[01]+")) {
                                msg.setText(fullBinary(msg.getText().toString()));
                            }
                            resultMsg = vernam_func(msg.getText().toString(), key.getText().toString(), resultTxt, getArguments().getString(ARG_SECTION_NUMBER));
                            break;
                        case R.id.radio_visioner:
                            resultMsg = visioner_func(msg.getText().toString(), key.getText().toString(), resultTxt, getArguments().getString(ARG_SECTION_NUMBER));
                            break;
                        default:
                            break;
                    }

                    resultTxt.setText(resultTxt.getText() + "\nRESULT: " + resultMsg + "\n\n\n");

                    if (getArguments().getString(ARG_SECTION_NUMBER).equals("Encryption")) {
                        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("result", resultMsg);
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(getContext(), "Result has been copied to clipboard!", Toast.LENGTH_SHORT).show();
                    }

                }

            });


            return rootView;
        }

        String fullBinary(String string) {
            String result = "";
            int len;
            if (string.length() % 8 == 0) {
                len = (string.length() / 8) * 8;
            } else {
                len = ((string.length() / 8) + 1) * 8;
            }
            Log.d("TAG", "length = " + len);
            char[] tmp = new char[len];
            char[] strArray = string.toCharArray();
            int j = strArray.length - 1;
            for (int i = len - 1; i >= 0; i--) {
                if (j < 0) tmp[i] = '0';
                else tmp[i] = strArray[j--];
            }
            result = new String(tmp);
            return result;
        }

        String strToBin(String text) {
            String binCode = "";
            String tmp;
            byte[] bytes = null;
            bytes = text.getBytes(StandardCharsets.UTF_8);
            for (byte b : bytes) {
                tmp = Integer.toBinaryString(b);
                if (tmp.length() == 6) tmp = "00" + tmp;
                if (tmp.length() == 7) tmp = "0" + tmp;
                binCode += tmp;
            }

            return binCode;
        }

        String binToStr(String input) {
            String output = "";
            String sub;
            for (int i = 0; i <= input.length() - 8; i += 8) {
                sub = input.substring(i, i + 8);
                Log.d("TAG", "bincode1  " + sub);
                int k = Integer.parseInt(sub, 2);
                output += (char) k;

                Log.d("TAG", "char:   " + (char) k + "   " + k);
            }

            return output;
        }

        String vernam_func(String inputStr, String inputKey, TextView textView, String encFlag) {
            String result = "";

            int i, j;
            char c, c1, c2;
            char[] inputStrArray;
            char[] inputKeyArray;

            if (inputStr.matches("[01]+")) inputStrArray = inputStr.toCharArray();
            else inputStrArray = strToBin(inputStr).toCharArray();
            if (inputKey.matches("[01]+")) inputKeyArray = inputKey.toCharArray();
            else inputKeyArray = strToBin(inputKey).toCharArray();

            char[] resultArray = new char[inputStrArray.length];
            textView.setText(textView.getText() + "Message in binary:\n" + new String(inputStrArray) + "\nKey in binary:\n" + new String(inputKeyArray) + "\n\n");

            j = 0;
            for (i = 0; i < inputStrArray.length; i++) {
                if (j >= inputKeyArray.length) j = 0;
                c1 = inputStrArray[i];
                c2 = inputKeyArray[j];
                if (c1 == c2) c = '0';
                else c = '1';
                resultArray[i] = c;
                textView.setText(textView.getText() + Character.toString(c1) + "   xor   " + Character.toString(c2) + "   =   " + Character.toString(c) + "\n");
                j++;
            }

            result = new String(resultArray);

            if (getArguments().getString(ARG_SECTION_NUMBER).equals("Decryption")) {
                result = binToStr(result);
            }

            return result;
        }

        String visioner_func(String inputStr, String inputKey, TextView textView, String encFlag) {
            String result = "";
            int i, j, c, c1, c2;
            char[] inputStrArray = inputStr.toCharArray();
            char[] inputKeyArray = inputKey.toCharArray();
            char[] resultArray = new char[inputStr.length()];

            j = 0;
            for (i = 0; i < inputStr.length(); i++) {
                if (j >= inputKey.length()) j = 0;
                c1 = symbolIndex(inputStrArray[i]);
                c2 = symbolIndex(inputKeyArray[j]);
                switch (encFlag) {
                    case "Encryption":
                        c = (c1 + c2) % 27;
                        break;
                    case "Decryption":
                        c = (c1 - c2 + 27) % 27;
                        break;
                    default:
                        c = (c1 + c2) % 27;
                        break;
                }
                resultArray[i] = alphabet[c];
                Log.d("TAG", Character.toString(inputStrArray[i]) + "(" + c1 + ")" + getString(R.string.tab) + " + " + Character.toString(inputKeyArray[j]) + "(" + c2 + ")" + " = " + Character.toString(resultArray[i]) + "(" + c + ")\n");
                textView.setText(textView.getText() + Character.toString(inputStrArray[i]) + "(" + c1 + ")" + "   +   " + Character.toString(inputKeyArray[j]) + "(" + c2 + ")" + "   =   " + Character.toString(resultArray[i]) + "(" + c + ")\n");
                j++;
            }


            for (Character ch : resultArray) {
                result += ch.toString();
            }
            return result;
        }

        int symbolIndex(char c) {
            for (int i = 0; i < 27; i++) {
                if (c == alphabet[i]) return alphabet_index[i];
            }
            return -1;
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
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
