package com.example.farrukh.labs;

import android.support.annotation.IntegerRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class lab4 extends AppCompatActivity {

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
        setContentView(R.layout.activity_lab4);

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

        public static final int[][] sbox = {
                {0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5, 0x30, 0x01, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76},
                {0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0, 0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0},
                {0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc, 0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15},
                {0x04, 0xc7, 0x23, 0xc3, 0x18, 0x96, 0x05, 0x9a, 0x07, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75},
                {0x09, 0x83, 0x2c, 0x1a, 0x1b, 0x6e, 0x5a, 0xa0, 0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84},
                {0x53, 0xd1, 0x00, 0xed, 0x20, 0xfc, 0xb1, 0x5b, 0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf},
                {0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33, 0x85, 0x45, 0xf9, 0x02, 0x7f, 0x50, 0x3c, 0x9f, 0xa8},
                {0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5, 0xbc, 0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2},
                {0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17, 0xc4, 0xa7, 0x7e, 0x3d, 0x64, 0x5d, 0x19, 0x73},
                {0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88, 0x46, 0xee, 0xb8, 0x14, 0xde, 0x5e, 0x0b, 0xdb},
                {0xe0, 0x32, 0x3a, 0x0a, 0x49, 0x06, 0x24, 0x5c, 0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4, 0x79},
                {0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9, 0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08},
                {0xba, 0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6, 0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a},
                {0x70, 0x3e, 0xb5, 0x66, 0x48, 0x03, 0xf6, 0x0e, 0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e},
                {0xe1, 0xf8, 0x98, 0x11, 0x69, 0xd9, 0x8e, 0x94, 0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf},
                {0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68, 0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16}
        };
        /**
         * Inverse SBOX table used for invSubBytes
         */
        public static final int[][] invsbox = {
                {0x52, 0x09, 0x6a, 0xd5, 0x30, 0x36, 0xa5, 0x38, 0xbf, 0x40, 0xa3, 0x9e, 0x81, 0xf3, 0xd7, 0xfb},
                {0x7c, 0xe3, 0x39, 0x82, 0x9b, 0x2f, 0xff, 0x87, 0x34, 0x8e, 0x43, 0x44, 0xc4, 0xde, 0xe9, 0xcb},
                {0x54, 0x7b, 0x94, 0x32, 0xa6, 0xc2, 0x23, 0x3d, 0xee, 0x4c, 0x95, 0x0b, 0x42, 0xfa, 0xc3, 0x4e},
                {0x08, 0x2e, 0xa1, 0x66, 0x28, 0xd9, 0x24, 0xb2, 0x76, 0x5b, 0xa2, 0x49, 0x6d, 0x8b, 0xd1, 0x25},
                {0x72, 0xf8, 0xf6, 0x64, 0x86, 0x68, 0x98, 0x16, 0xd4, 0xa4, 0x5c, 0xcc, 0x5d, 0x65, 0xb6, 0x92},
                {0x6c, 0x70, 0x48, 0x50, 0xfd, 0xed, 0xb9, 0xda, 0x5e, 0x15, 0x46, 0x57, 0xa7, 0x8d, 0x9d, 0x84},
                {0x90, 0xd8, 0xab, 0x00, 0x8c, 0xbc, 0xd3, 0x0a, 0xf7, 0xe4, 0x58, 0x05, 0xb8, 0xb3, 0x45, 0x06},
                {0xd0, 0x2c, 0x1e, 0x8f, 0xca, 0x3f, 0x0f, 0x02, 0xc1, 0xaf, 0xbd, 0x03, 0x01, 0x13, 0x8a, 0x6b},
                {0x3a, 0x91, 0x11, 0x41, 0x4f, 0x67, 0xdc, 0xea, 0x97, 0xf2, 0xcf, 0xce, 0xf0, 0xb4, 0xe6, 0x73},
                {0x96, 0xac, 0x74, 0x22, 0xe7, 0xad, 0x35, 0x85, 0xe2, 0xf9, 0x37, 0xe8, 0x1c, 0x75, 0xdf, 0x6e},
                {0x47, 0xf1, 0x1a, 0x71, 0x1d, 0x29, 0xc5, 0x89, 0x6f, 0xb7, 0x62, 0x0e, 0xaa, 0x18, 0xbe, 0x1b},
                {0xfc, 0x56, 0x3e, 0x4b, 0xc6, 0xd2, 0x79, 0x20, 0x9a, 0xdb, 0xc0, 0xfe, 0x78, 0xcd, 0x5a, 0xf4},
                {0x1f, 0xdd, 0xa8, 0x33, 0x88, 0x07, 0xc7, 0x31, 0xb1, 0x12, 0x10, 0x59, 0x27, 0x80, 0xec, 0x5f},
                {0x60, 0x51, 0x7f, 0xa9, 0x19, 0xb5, 0x4a, 0x0d, 0x2d, 0xe5, 0x7a, 0x9f, 0x93, 0xc9, 0x9c, 0xef},
                {0xa0, 0xe0, 0x3b, 0x4d, 0xae, 0x2a, 0xf5, 0xb0, 0xc8, 0xeb, 0xbb, 0x3c, 0x83, 0x53, 0x99, 0x61},
                {0x17, 0x2b, 0x04, 0x7e, 0xba, 0x77, 0xd6, 0x26, 0xe1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0c, 0x7d}
        };

        /**
         * Galois table used for mixColumns
         */
        public static final int[][] mixColArray = {
                {0x02, 0x03, 0x01, 0x01},
                {0x01, 0x02, 0x03, 0x01},
                {0x01, 0x01, 0x02, 0x03},
                {0x03, 0x01, 0x01, 0x02}};

        /**
         * Inverse Galois table used for invMixColumns
         */
        public static final int[][] invMixColArray = {
                {0x0e, 0x0b, 0x0d, 0x09},
                {0x09, 0x0e, 0x0b, 0x0d},
                {0x0d, 0x09, 0x0e, 0x0b},
                {0x0b, 0x0d, 0x09, 0x0e}};

        /**
         * RCon array used for Key Expansion
         */
        public static final int[][] rcon = {
                {0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36},
                {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00},
                {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00},
                {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}
        };

        public HashMap<Integer, int[][]> roundKeyMap = new HashMap<>();
        public HashMap<Integer, int[][]> subBytesMap = new HashMap<>();
        public HashMap<Integer, int[][]> shiftRowsMap = new HashMap<>();
        public HashMap<Integer, int[][]> mixColmunsMap = new HashMap<>();
        public HashMap<Integer, int[][]> addRoundKeyMap = new HashMap<>();
        public HashMap<Integer, int[][]> messageMap = new HashMap<>();
        public HashMap<Integer, int[][]> keyMap = new HashMap<>();

        int[][] initKey = {
                {0x2b, 0x28, 0xab, 0x09},
                {0x7e, 0xae, 0xf7, 0xcf},
                {0x15, 0xd2, 0x15, 0x4f},
                {0x16, 0xa6, 0x88, 0x3c}
        };

        int[][] initState = {
                {0x32, 0x88, 0x31, 0xe0},
                {0x43, 0x5a, 0x31, 0x37},
                {0xf6, 0x30, 0x98, 0x07},
                {0xa8, 0x8d, 0xa2, 0x34}
        };
        int[][] currentState;

        String fullMessage = "";

        int[][] cypStat = {
                {0x39, 0x02, 0xdc, 0x19},
                {0x25, 0xdc, 0x11, 0x6a},
                {0x84, 0x09, 0x85, 0x0b},
                {0x1d, 0xfb, 0x97, 0x32}
        };

        int[][] cypherTextArray;
        String cypherText = "";
        int blockMultiplier = 10;

        boolean flag = false;

       TextView roundKeyText;
       TextView roundKeyView ;
       TextView subButyesText;
       TextView subButyesView;
       TextView shiftRowsText;
       TextView shiftRowsView;
       TextView mixColText ;
       TextView mixColView ;
       TextView addRoundKeyText;
       TextView addRoundKeyView;
       TextView messageText ;
       TextView messageView ;
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
            View rootView;
            switch (getArguments().getString(ARG_SECTION_NUMBER)) {
                case "Encryption":
                    rootView = inflater.inflate(R.layout.fragment_lab4, container, false);
                    break;
                case "Decryption":
                    rootView = inflater.inflate(R.layout.fragment_lab41, container, false);
                    break;
                default:
                    rootView = inflater.inflate(R.layout.fragment_lab4, container, false);
                    break;
            }
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getArguments().getString(ARG_SECTION_NUMBER));

            final LinearLayout roundkeyLayout = (LinearLayout) rootView.findViewById(R.id.roundKeyLayout);
            final LinearLayout messageLayout = (LinearLayout) rootView.findViewById(R.id.messageLayout);
            LinearLayout subbytesLayout = (LinearLayout) rootView.findViewById(R.id.subbytesLayout);
            LinearLayout shiftrowsLayout = (LinearLayout) rootView.findViewById(R.id.shiftrowsLayout);
            LinearLayout mixcolLayout = (LinearLayout) rootView.findViewById(R.id.miccolLayout);
            LinearLayout addroundkeyLayout = (LinearLayout) rootView.findViewById(R.id.addroundkeyLayout);

            final EditText messageEdit = (EditText) rootView.findViewById(R.id.message);
            final EditText initialKeyEdit = (EditText) rootView.findViewById(R.id.key);
            final EditText cypherEdit = (EditText) rootView.findViewById(R.id.cypher);

            final Button button = (Button) rootView.findViewById(R.id.btn);
            final Button clearbtn = (Button) rootView.findViewById(R.id.clearbtn);
            final Spinner roundSpinner = (Spinner) rootView.findViewById(R.id.spinner);

            roundKeyText = (TextView) rootView.findViewById(R.id.roundKeyText);
            roundKeyView = (TextView) rootView.findViewById(R.id.roundKey);
            subButyesText = (TextView) rootView.findViewById(R.id.subKeyText);
            subButyesView = (TextView) rootView.findViewById(R.id.subKey);
            shiftRowsText = (TextView) rootView.findViewById(R.id.shiftRowText);
            shiftRowsView = (TextView) rootView.findViewById(R.id.shiftRow);
            mixColText = (TextView) rootView.findViewById(R.id.mixColText);
            mixColView = (TextView) rootView.findViewById(R.id.mixCol);
            addRoundKeyText = (TextView) rootView.findViewById(R.id.addRoundKeyText);
            addRoundKeyView = (TextView) rootView.findViewById(R.id.addRoundKey);
            messageText = (TextView) rootView.findViewById(R.id.msgText);
            messageView = (TextView) rootView.findViewById(R.id.msg);

            final TextView msgLen = (TextView) rootView.findViewById(R.id.msgLen);
            final TextView keyLen = (TextView) rootView.findViewById(R.id.keyLen);

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                    R.array.rounds, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            roundSpinner.setAdapter(adapter);
            roundSpinner.setEnabled(false);

            messageMap.put(0, initState);
            keyMap.put(0, initKey);


            messageEdit.addTextChangedListener(new TextWatcher() {
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

            initialKeyEdit.addTextChangedListener(new TextWatcher() {
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


            clearbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    roundSpinner.setEnabled(false);
                    button.setEnabled(true);
                    clearbtn.setEnabled(false);
                    subButyesView.setText("");
                    shiftRowsView.setText("");
                    mixColView.setText("");
                    addRoundKeyView.setText("");
                    messageView.setText("");
                    roundKeyView.setText("");

                    clearMaps();
                    roundKeyMap.clear();


                }
            });

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(messageEdit.getText())) {
                        messageEdit.setError("The message is emplty!!");
                        return;
                    }

                    if (TextUtils.isEmpty(initialKeyEdit.getText()) || initialKeyEdit.getText().toString().length() < 16) {
                        initialKeyEdit.setError("Key length is not appropriate, please enter 16 character key!");
                        return;
                    }

                    if (messageEdit.getText().toString().matches(".*\\p{InCyrillic}.*")) {
                        messageEdit.setError("The message must contain only Latin symbols!!");
                        return;
                    }

                    if (initialKeyEdit.getText().toString().matches(".*\\p{InCyrillic}.*")) {
                        initialKeyEdit.setError("The key must contain only Latin symbols!!");
                        return;
                    }

                    clearbtn.setEnabled(true);
                    button.setEnabled(false);
                    cypherText = "";

                    // generating keys
                    keyMap.put(0, initKey);
                    roundSpinner.setSelection(0);


                    showMaps(keyMap, 0, roundKeyView);
                    for (int i = 0; i < 10; i++) {
                        if (i == 0)
                            roundKeyMap.put(i, roundKeyGenerator(keyMap.get(0), i));
                        else
                            roundKeyMap.put(i, roundKeyGenerator(roundKeyMap.get(i - 1), i));
                    }


                    // cuttin message in blocks by 16 symbols
                    fullMessage = messageEdit.getText().toString();
                    char addC = '*';
                    while (fullMessage.length() % 16 != 0) {
                        fullMessage = fullMessage + addC;
                    }

                    int len;
                    if (fullMessage.length() <= 16) {
                        len = 1;
                    } else {
                        len = fullMessage.length() / 16;
                    }

                    String messageBlocksString;
                    Log.d("KEY", "LENGHT = " + len);
                    switch (getArguments().getString(ARG_SECTION_NUMBER)) {
                        case "Encryption":
                            clearMaps();
                            for (int i = 0; i < len; i++) {
                                messageBlocksString = fullMessage.substring(16 * i, 16 * (i + 1));
                                Log.d("KEY", "blocks = " + messageBlocksString);
                                currentState = generateInitData(messageBlocksString);
                                encrypt(i);
                                if (i == 0) {
                                    initState = currentState;
                                    showMaps(messageMap, 0, messageView);
                                }
                            }
                            break;
                        case "Decryption":
                            clearMaps();
                            for (int i = 0; i < len; i++) {
                                messageBlocksString = fullMessage.substring(16 * i, 16 * (i + 1));
                                Log.d("KEY", "blocks = " + messageBlocksString);
                                currentState = generateInitData(messageBlocksString);
                                decrypt(i);
                                if (i == 0) {
                                    initState = currentState;
                                    showMaps(messageMap, 0, messageView);
                                }
                            }
                            break;
                    }

                    cypherEdit.setText("");
                    cypherEdit.setText(cypherText);
                    roundSpinner.setEnabled(true);
                }
            });

            roundSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    if (!flag) {
                        flag = true;
                        return;
                    }

                    switch (position) {
                        case 0:
                            roundKeyView.setText("");
                            showMaps(keyMap, position, roundKeyView);

                            messageView.setText("");
                            showMaps(messageMap, position, messageView);

                            addRoundKeyView.setText("");
                            showMaps(addRoundKeyMap, position, addRoundKeyView);

                            subButyesView.setText("");
                            shiftRowsView.setText("");
                            mixColView.setText("");
                            break;
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                            roundKeyView.setText("");
                            showMaps(roundKeyMap, position - 1, roundKeyView);

                            messageView.setText("");
                            showMaps(messageMap, position, messageView);

                            subButyesView.setText("");
                            showMaps(subBytesMap, position - 1, subButyesView);

                            shiftRowsView.setText("");
                            showMaps(shiftRowsMap, position - 1, shiftRowsView);

                            mixColView.setText("");
                            showMaps(mixColmunsMap, position - 1, mixColView);

                            addRoundKeyView.setText("");
                            showMaps(addRoundKeyMap, position, addRoundKeyView);
                            break;
                        case 10:
                            roundKeyView.setText("");
                            showMaps(roundKeyMap, position - 1, roundKeyView);

                            messageView.setText("");
                            showMaps(messageMap, position, messageView);

                            subButyesView.setText("");
                            showMaps(subBytesMap, position - 1, subButyesView);

                            shiftRowsView.setText("");
                            showMaps(shiftRowsMap, position - 1, shiftRowsView);

                            addRoundKeyView.setText("");
                            showMaps(addRoundKeyMap, position, addRoundKeyView);

                            mixColView.setText("");

                            break;
                        case 11:
                            messageView.setText("");
                            for (int i = 0; i < 4; i++) {
                                for (int j = 0; j < 4; j++) {
                                    messageView.setText(messageView.getText() + "  " + Integer.toHexString(cypherTextArray[i][j]));
                                }
                                messageView.setText(messageView.getText() + "\n");
                            }

                            subButyesView.setText("");
                            shiftRowsView.setText("");
                            mixColView.setText("");
                            addRoundKeyView.setText("");
                            roundKeyView.setText("");
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            return rootView;
        }

        public void encrypt(int blockNumber) {

            messageMap.put(blockNumber * blockMultiplier, currentState);

            addRoundKey(messageMap.get(messageMap.size() - 1), initKey);

            addRoundKeyView.setText("");
            showMaps(addRoundKeyMap, 0, addRoundKeyView);

            for (int round = 0; round < 10; round++) {

                subBytes(addRoundKeyMap.get(addRoundKeyMap.size() - 1));

                shiftRows(subBytesMap.get(subBytesMap.size() - 1));

                if (round != 9) {
                    mixColumns(shiftRowsMap.get(shiftRowsMap.size() - 1));
                    addRoundKey(mixColmunsMap.get(mixColmunsMap.size() - 1), roundKeyMap.get(round));
                    continue;
                }
                addRoundKey(shiftRowsMap.get(shiftRowsMap.size() - 1), roundKeyMap.get(round));
            }
            cypherTextArray = addRoundKeyMap.get(addRoundKeyMap.size() - 1);
            cypherText += generateCyphertext(cypherTextArray);

        }


        public void decrypt(int blockNumber) {

            messageMap.put(blockNumber * blockMultiplier, currentState);

            addRoundKey(messageMap.get(messageMap.size() - 1), roundKeyMap.get(roundKeyMap.size() - 1));

            addRoundKeyView.setText("");
            showMaps(addRoundKeyMap, 0, addRoundKeyView);

            for (int round = 0; round < 10; round++) {

                if (round == 9) {
                    invshiftRows(mixColmunsMap.get(mixColmunsMap.size() - 1));

                    invsubBytes(shiftRowsMap.get(shiftRowsMap.size() - 1));

                    addRoundKey(subBytesMap.get(subBytesMap.size() - 1), initKey);

                    continue;
                }

                if (round == 0) {
                    invshiftRows(addRoundKeyMap.get(addRoundKeyMap.size() - 1));

                } else {
                    invshiftRows(mixColmunsMap.get(mixColmunsMap.size() - 1));
                }

                invsubBytes(shiftRowsMap.get(shiftRowsMap.size() - 1));

                addRoundKey(subBytesMap.get(subBytesMap.size() - 1), roundKeyMap.get(8 - round));

                invmixColumns(addRoundKeyMap.get(addRoundKeyMap.size() - 1));


            }
            cypherTextArray = addRoundKeyMap.get(addRoundKeyMap.size() - 1);
            cypherText += generateCyphertext(cypherTextArray);

        }

        public void clearMaps() {
            messageMap.clear();
            subBytesMap.clear();
            shiftRowsMap.clear();
            mixColmunsMap.clear();
            addRoundKeyMap.clear();
        }

        public void showMaps(HashMap<Integer, int[][]> map, int roundNo, TextView textView) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    textView.setText(textView.getText() + "  " + Integer.toHexString(map.get(roundNo)[i][j]));
                }
                textView.setText(textView.getText() + "\n");
            }
        }

        public int[][] roundKeyGenerator(int[][] arr, int roundNo) {
            int i, j, tmpVal;
            int[] tmpArr = new int[4];
            int[][] resultArr = new int[4][4];
            // RotWord
            tmpVal = arr[0][3];
            for (i = 1; i < 4; i++) {
                tmpArr[i - 1] = arr[i][3];
            }
            tmpArr[3] = tmpVal;

            // Subbytes and xor
            for (i = 0; i < 4; i++) {
                int hex = tmpArr[i];
                tmpArr[i] = sbox[hex / 16][hex % 16] ^ arr[i][0] ^ rcon[i][roundNo];
                Log.d("KEY", "sbox = " + Integer.toHexString(sbox[hex / 16][hex % 16]) + " + message = " + Integer.toHexString(arr[i][0]) + "  + rcon = " + Integer.toHexString(rcon[i][roundNo]) + " , answer = " + Integer.toHexString(tmpArr[i]));
                resultArr[i][0] = tmpArr[i];
            }

            for (i = 1; i < 4; i++) {
                for (j = 0; j < 4; j++) {
                    resultArr[j][i] = arr[j][i] ^ resultArr[j][i - 1];
                    Log.d("KEY", "message = " + Integer.toHexString(arr[j][i]) + "  + roundkey  = " + Integer.toHexString(resultArr[j][i - 1]) + " , answer = " + Integer.toHexString(resultArr[j][i]));
                }
            }


            return resultArr;
        }

        public void subBytes(int[][] arr) {
            int[][] resultArray = new int[4][4];
            for (int i = 0; i < arr.length; i++) //Sub-Byte subroutine
            {
                for (int j = 0; j < arr[0].length; j++) {
                    int hex = arr[j][i];
                    resultArray[j][i] = sbox[hex / 16][hex % 16];
                }
            }

            subBytesMap.put(subBytesMap.size(), resultArray);
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    Log.d("KEY", "subbytes result= " + subBytesMap.get(subBytesMap.size() - 1)[i][j]);
                }
            }
        }

        public void invsubBytes(int[][] arr) {
            int[][] resultArray = new int[4][4];
            for (int i = 0; i < arr.length; i++) //Sub-Byte subroutine
            {
                for (int j = 0; j < arr[0].length; j++) {
                    int hex = arr[j][i];
                    resultArray[j][i] = invsbox[hex / 16][hex % 16];
                }
            }

            subBytesMap.put(subBytesMap.size(), resultArray);
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    Log.d("KEY", "invsubbytes result= " + subBytesMap.get(subBytesMap.size() - 1)[i][j]);
                }
            }
        }

        public void shiftRows(int[][] arr) {
            int[][] shiftArray = {
                    {0, 1, 2, 3},
                    {1, 2, 3, 0},
                    {2, 3, 0, 1},
                    {3, 0, 1, 2}
            };
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    Log.d("KEY", "shiftrows = " + arr[i][j]);
                }
            }
            int[][] resultArray = new int[4][4];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    resultArray[i][j] = arr[i][shiftArray[i][j]];
                }
            }
            shiftRowsMap.put(shiftRowsMap.size(), resultArray);
        }

        public void invshiftRows(int[][] arr) {
            int[][] shiftArray = {
                    {0, 1, 2, 3},
                    {3, 0, 1, 2},
                    {2, 3, 0, 1},
                    {1, 2, 3, 0},
            };
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    Log.d("KEY", "shiftrows = " + arr[i][j]);
                }
            }
            int[][] resultArray = new int[4][4];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    resultArray[i][j] = arr[i][shiftArray[i][j]];
                }
            }
            shiftRowsMap.put(shiftRowsMap.size(), resultArray);
        }


        public void mixColumns(int[][] arr) {
            int[][] resultArray = new int[4][4];
            int tmp = 0x00;
            int someval;
            //Log.d("KEY", "shift = " + arr[0][0]);
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < 4; k++) {
                        someval = mixColHelper(arr[k][i], mixColArray[j][k]);
                        tmp ^= someval;
                        Log.d("KEY", "mixcols| val=" + Integer.toHexString(arr[k][i]) + " multby=" + Integer.toHexString(mixColArray[j][k]) + " result = " + Integer.toHexString(someval));
                    }
                    Log.d("KEY", " mixcols| result = " + Integer.toHexString(tmp) + "*********************");
                    resultArray[j][i] = tmp;
                    tmp = 0x00;
                }
            }
            mixColmunsMap.put(mixColmunsMap.size(), resultArray);
        }


        public void invmixColumns(int[][] arr) {
            int[][] resultArray = new int[4][4];
            int tmp = 0x00;
            int someval;
            //Log.d("KEY", "shift = " + arr[0][0]);
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < 4; k++) {
                        someval = mixColHelper(arr[k][i], invMixColArray[j][k]);
                        tmp ^= someval;
                        Log.d("KEY", "invmixcols| val=" + Integer.toHexString(arr[k][i]) + " multby=" + Integer.toHexString(invMixColArray[j][k]) + " result = " + Integer.toHexString(someval));
                    }
                    Log.d("KEY", "invmixcols| result = " + Integer.toHexString(tmp) + "*********************");
                    resultArray[j][i] = tmp;
                    tmp = 0x00;
                }
            }
            mixColmunsMap.put(mixColmunsMap.size(), resultArray);
        }


        public int mixColHelper(int val, int multBy) {
            int shiftedval, result = 0x00;
            switch (multBy) {
                case 0x01:
                    result = val;
                    break;
                case 0x02:
                    result = MCTables.mc2[val / 16][val % 16];
                    break;
                case 0x03:
                    result = MCTables.mc3[val / 16][val % 16];
                    break;
                case 0x09:
                    result = MCTables.mc9[val / 16][val % 16];
                    break;
                case 0x0b:
                    result = MCTables.mc11[val / 16][val % 16];
                    break;
                case 0x0d:
                    result = MCTables.mc13[val / 16][val % 16];
                    break;
                case 0x0e:
                    result = MCTables.mc14[val / 16][val % 16];
                    break;
            }

            return result;
        }

        public void addRoundKey(int[][] arr, int[][] roundKey) {
            int[][] resultArray = new int[4][4];

            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    resultArray[i][j] = arr[i][j] ^ roundKey[i][j];
                    Log.d("KEY", "addroundkey| message = " + Integer.toHexString(arr[i][j]) + " + roundkey = " + Integer.toHexString(roundKey[i][j]) + " == " + Integer.toHexString(resultArray[i][j]));
                }
            }
            addRoundKeyMap.put(addRoundKeyMap.size(), resultArray);
            messageMap.put(messageMap.size(), resultArray);

        }

        public int[][] generateInitData(String initStr) {
            int[][] resultArray = new int[4][4];
            int k = 0;

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    char c = initStr.charAt(k);
                    //Log.d("KEY", "CHAR = " + c + " INDEX" + Integer.toString(k));
                    resultArray[i][j] = (int) c;
                    k++;
                }
            }
            return resultArray;
        }

        public String generateCyphertext(int[][] arr) {
            String result = "";
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    result += (char) arr[i][j];
                }
            }
            return result;
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
