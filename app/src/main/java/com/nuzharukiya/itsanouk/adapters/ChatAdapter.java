package com.nuzharukiya.itsanouk.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nuzharukiya.itsanouk.R;
import com.nuzharukiya.itsanouk.models.ChatModel;

import java.util.List;

/**
 * Created by Nuzha Rukiya on 18/10/01.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private List<ChatModel> chatList;

    public ChatAdapter(List<ChatModel> chatList) {
        this.chatList = chatList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_chat_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ChatModel cm = chatList.get(i);

        viewHolder.tvText.setText(cm.getText());
        viewHolder.tvTime.setText(cm.getTime());
    }

    @Override
    public int getItemCount() {
        return chatList == null ? 0 : chatList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvText, tvTime;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvText = itemView.findViewById(R.id.tvText);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }
}
