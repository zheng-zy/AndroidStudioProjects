package com.zhengzy.learnbluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * http://wear.techbrood.com/guide/topics/connectivity/bluetooth.html#Profiles
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    //蓝牙适配器
    private BluetoothAdapter mBluetoothAdapter;
    //蓝牙设备打开请求
    private final int REQUEST_ENABLE_BT = 1001;
    //蓝牙设备允许可见请求
    private final int REQUEST_ENABLE_DISCOVERABLE = 1002;
    private TextView mTextView;
    private Button mButton;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private Button mButton5;

    //显示列表
    private ListView mListView;
    private List<String> bluetoothDevices = new ArrayList<String>();
    private ArrayAdapter<String> mArrayAdapter;
    //自定义一个UUID
    private final UUID MY_UUID = UUID.fromString("abcdefgh-abcd-abcd-abcd-abcd-abcdefgh1234");

    private final String BluetoothName = "Bluetooth_Socket";

    private BluetoothDevice mBluetoothDevice;
    private BluetoothSocket mClientSocket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initBluetooth();

    }

    private void initBluetooth() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //判断当前设备是否支持蓝牙
        if (mBluetoothAdapter == null) {
            Toast.makeText(MainActivity.this, "当前设备不支持蓝牙！", Toast.LENGTH_SHORT).show();
        }

        dealPairedDevice();

        //注册广播监听
        //发现设备监听
        IntentFilter mIntentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mBroadcastReceiver, mIntentFilter);
        //搜索结束监听
        mIntentFilter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mBroadcastReceiver, mIntentFilter);

        mListView.setAdapter(mArrayAdapter);
        mListView.setOnClickListener(this);


    }

    /**
     * 处理已经配对的设备
     */
    private void dealPairedDevice() {
        if (mBluetoothAdapter.isEnabled()) {
            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
            if (pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {
                    bluetoothDevices.add(device.getName() + ":" + device.getAddress());
                    mTextView.setText(mTextView.getText() + ";\n" + device.getName() + ":" + device.getAddress());
                }
            } else {
                Toast.makeText(MainActivity.this, "没有已经配对的设备！", Toast.LENGTH_SHORT).show();
            }
            mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bluetoothDevices);
        }
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.textView);
        mButton = (Button) findViewById(R.id.button);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton3 = (Button) findViewById(R.id.button3);
        mButton4 = (Button) findViewById(R.id.button4);
        mButton5 = (Button) findViewById(R.id.button5);
        mListView = (ListView) findViewById(R.id.listView);

        mButton.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mButton5.setOnClickListener(this);
    }

    //打开蓝牙设备
    private void OpenBluetoothDevice() {
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //监听蓝牙设备打开是否成功
        if (REQUEST_ENABLE_BT == requestCode && RESULT_OK == resultCode) {
            Toast.makeText(MainActivity.this, "蓝牙设备已打开！", Toast.LENGTH_SHORT).show();
        }
        if (REQUEST_ENABLE_BT == requestCode && RESULT_CANCELED == resultCode) {
            Toast.makeText(MainActivity.this, "蓝牙设备打开失败！", Toast.LENGTH_SHORT).show();
        }
        //监听蓝牙设备允许可见是否成功
        if (REQUEST_ENABLE_DISCOVERABLE == requestCode && RESULT_OK == resultCode) {
            Toast.makeText(MainActivity.this, "蓝牙设备允许可见！", Toast.LENGTH_SHORT).show();
        }
        if (REQUEST_ENABLE_DISCOVERABLE == requestCode && RESULT_CANCELED == resultCode) {
            Toast.makeText(MainActivity.this, "蓝牙设备允许可见失败！", Toast.LENGTH_SHORT).show();
        }
    }


    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                //判断设备是否已经配对，没配对的添加进去，有配对的已经处理过了
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    mTextView.setText(mTextView.getText() + ";\n" + device.getName() + ":" + device.getAddress());
                }

            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                setProgressBarVisibility(false);
                setTitle("已经搜索完成！");
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                OpenBluetoothDevice();
                break;
            case R.id.button2:
                break;
            case R.id.button3:
                //允许蓝牙设备可见
                EnableDiscovery();
                break;
            case R.id.button4:
//                dealPairedDevice();
                break;
            case R.id.button5:
                //蓝牙搜索
                startDiscovery();

            default:
                break;
        }
    }

    private void startDiscovery() {
        //开启蓝牙搜索,异步搜索
        setProgressBarIndeterminateVisibility(true);
        setTitle("正在搜索...");
        if (mBluetoothAdapter.isDiscovering()) {
            mBluetoothAdapter.cancelDiscovery();
        }
        mBluetoothAdapter.startDiscovery();
    }

    /**
     * 允许蓝牙设备可见
     */
    private void EnableDiscovery() {
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivityForResult(discoverableIntent, REQUEST_ENABLE_DISCOVERABLE);
//                startActivity(discoverableIntent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s = mArrayAdapter.getItem(position);
        String address = s.substring(s.indexOf(":") + 1).trim();
        try {
            if (mBluetoothAdapter.isDiscovering()) {
                mBluetoothAdapter.cancelDiscovery();
            }
            try {
                if (mBluetoothDevice == null) {
                    mBluetoothDevice = mBluetoothAdapter.getRemoteDevice(address);
                }
                if (mClientSocket == null) {
                    mClientSocket = mBluetoothDevice.createRfcommSocketToServiceRecord(MY_UUID);
                    mClientSocket.connect();
                }
            } catch (Exception e) {

            }

        } catch (Exception e) {

        }
    }
}
