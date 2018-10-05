package com.nuzharukiya.itsanouk;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.nuzharukiya.hzaun_app_base.BaseActivity;
import com.nuzharukiya.hzaun_app_base.BaseUtils;
import com.nuzharukiya.hzaun_app_base.UIComponents;
import com.nuzharukiya.hzaun_volley_helper.Connector;
import com.nuzharukiya.hzaun_volley_helper.VolleyListener;
import com.nuzharukiya.itsanouk.adapters.ChatAdapter;
import com.nuzharukiya.itsanouk.models.ChatModel;
import com.nuzharukiya.itsanouk.utils.ANOUK_URLS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

import static com.nuzharukiya.hzaun_volley_helper.Connector.MethodType.POST;

public class StartingPoint extends BaseActivity implements
        View.OnClickListener,
        VolleyListener {

    @BindView(R.id.rvChat)
    RecyclerView rvChat;
    @BindView(R.id.etMessage)
    EditText etMessage;
    @BindView(R.id.ivSend)
    ImageView ivSend;

    private ChatAdapter chatAdapter;
    private static final List<ChatModel> chatList = new ArrayList<>();

    private Connector connector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_starting_point);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initApp() {
        super.initApp();

        context = this;

        connector = new Connector();
        uiComponents = new UIComponents(context, true);
        uiComponents.setToolbarItems(R.drawable.ic_menu, R.string.activity_main);

        baseUtils = new BaseUtils(context);
    }

    @Override
    public void initViews() {
        super.initViews();

        ivSend.setOnClickListener(this);

        initChat();
    }

    @Override
    public void initData() {
        super.initData();

        chatList.clear();
        // Add data
        chatAdapter.notifyDataSetChanged();
    }

    @Override
    public void runFactory() {
        super.runFactory();
    }

    public void fetchData() {
        if (baseUtils.isOnline()) {
            baseUtils.getLoader();

            // call API
        }
    }

    public void parseData() {
        baseUtils.dismissLoader();
    }

    private void initChat() {
        chatAdapter = new ChatAdapter(chatList);
        rvChat.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        rvChat.setLayoutManager(layoutManager);
        rvChat.setAdapter(chatAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivSend: {
                // Post Message
                String message = etMessage.getText().toString();
                addMessageToList(message);
                fetchBotResponse(message);
            }
            break;
        }
    }

    private void fetchBotResponse(String message) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Token e9a48df834477fcc8fc03d1ed1607ef3");
        connector.connect(ANOUK_URLS.GET_ANOUK, POST, ChatModel.getJSONBody(message), this, headers);
    }

    private void addMessageToList(String message) {
        chatList.add(new ChatModel.ChatBuilder(message, "").build());
        chatAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResponseReceived(String URL, Object obj) {

    }
}
