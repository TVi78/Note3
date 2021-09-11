package com.zadania.note3.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.zadania.note3.R;
import com.zadania.note3.data.CardData;
import com.zadania.note3.data.PictureIndexConverter;
import com.zadania.note3.observe.Publisher;

import java.util.Calendar;
import java.util.Date;

public class NoteUpdateFragment extends Fragment {
    private static final String ARG_CARD_DATA = "Param_CardData";

    private CardData cardData;      // Данные по карточке
    private Publisher publisher;    // Паблишер, с его помощью обмениваемся данными

    private TextInputEditText title;
    private TextInputEditText description;
    private TextInputEditText description2;
    private TextInputEditText data;
    private DatePicker datePicker;

    // Для редактирования данных
    public static NoteUpdateFragment newInstance(CardData cardData) {
        NoteUpdateFragment fragment = new NoteUpdateFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CARD_DATA, cardData);
        fragment.setArguments(args);
        return fragment;
    }

    // Для добавления новых данных
    public static NoteUpdateFragment newInstance() {
        Log.d("myLogs", "CardFragment");
        NoteUpdateFragment fragment = new NoteUpdateFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("myLogs", "onCreate");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cardData = getArguments().getParcelable(ARG_CARD_DATA);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        Log.d("myLogs", "onAttach");
        super.onAttach(context);
        MainActivity activity = (MainActivity) context;
        publisher = activity.getPublisher();
    }

    @Override
    public void onDetach() {
        Log.d("myLogs", "onDetach");
        publisher = null;
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_card, container, false);
        initView(view);
        // если cardData пустая, то это добавление
        if (cardData != null) {
            populateView();
        }
        return view;
    }

    // Здесь соберём данные из views
    @Override
    public void onStop() {
        Log.d("myLogs", "onStop");
        super.onStop();
        cardData = collectCardData();
        publisher.notifySingle(cardData);  //при удалении карточки появляется новая снизу
    }

    // Здесь передадим данные в паблишер
    @Override
    public void onDestroy() {
        Log.d("myLogs", "onDestroy");
        super.onDestroy();
        //      publisher = null;
        //       publisher.notifySingle(cardData);
    }

    private CardData collectCardData() {
        String title = this.title.getText().toString();
        String description = this.description.getText().toString();
        String description2 = this.description2.getText().toString();
        String data = this.data.getText().toString();
        Date date = getDateFromDatePicker();
        //
        //     int picture;
        //     boolean like;
        if (cardData != null) {
            CardData answer;
            answer = new CardData(title, description, description2, data, cardData.getPicture(), cardData.isLike(), date);
            answer.setId(cardData.getId());
            return answer;
            //         cardData.setTitle(title);
            //       cardData.setDescription(description);
            //        cardData.setDescription2(description2);
            //        cardData.setDdata(data);
            //       cardData.setDate(date);
            //        return cardData;
        } else {
            int picture = PictureIndexConverter.getPictureByIndex(PictureIndexConverter.randomPictureIndex());
            return new CardData(title, description, description2, data, picture, false, date);
        }
    }

    private void populateView() {
        title.setText(cardData.getTitle());
        description.setText(cardData.getDescription());
        description2.setText(cardData.getDescription2());
        data.setText(cardData.getDdata());
        initDatePicker(cardData.getDate());
    }

    // Установка даты в DatePicker
    private void initDatePicker(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        this.datePicker.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                null);
    }

    // Получение даты из DatePicker
    private Date getDateFromDatePicker() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, this.datePicker.getYear());
        cal.set(Calendar.MONTH, this.datePicker.getMonth());
        cal.set(Calendar.DAY_OF_MONTH, this.datePicker.getDayOfMonth());
        return cal.getTime();
    }

    private void initView(View view) {
        title = view.findViewById(R.id.inputTitle);
        description = view.findViewById(R.id.inputDescription);
        description2 = view.findViewById(R.id.inputDescription2);
        data = view.findViewById(R.id.inputData);
        datePicker = view.findViewById(R.id.inputDate);
    }
}
