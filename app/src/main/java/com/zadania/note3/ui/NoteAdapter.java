package com.zadania.note3.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.zadania.note3.R;
import com.zadania.note3.data.CardData;
import com.zadania.note3.data.CardsSource;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private final static String TAG = "NoteAdapter";
    private CardsSource dataSource;
    private Fragment fragment;
    private OnItemClickListener itemClickListener;  // Слушатель будет устанавливаться извне
    private int menuContextClickPosition;

    public int getMenuContextClickPosition() {
        return menuContextClickPosition;
    }

    private int menuPosition;

    // Передаём в конструктор источник данных
    // В нашем случае это массив, но может быть и запрос к БД
    public NoteAdapter(CardsSource dataSource, Fragment fragment) {
        this.dataSource = dataSource;
        this.fragment = fragment;
    }

    // Создать новый элемент пользовательского интерфейса
    // Запускается менеджером
    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Создаём новый элемент пользовательского интерфейса
        // Через Inflater
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);
        // Здесь можно установить всякие параметры
        return new ViewHolder(v);
    }

    // Заменить данные в пользовательском интерфейсе
    // Вызывается менеджером
    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder viewHolder, int i) {
        // Получить элемент из источника данных (БД, интернет...)
        // Вынести на экран, используя ViewHolder
        viewHolder.setData(dataSource.getCardData(i));
    }

    // Вернуть размер данных, вызывается менеджером
    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    // Сеттер слушателя нажатий
    public void SetOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public int getMenuPosition() {
        return menuPosition;
    }

    // Интерфейс для обработки нажатий как в ListView
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    // Этот класс хранит связь между данными и элементами View
    // Сложные данные могут потребовать несколько View на один пункт списка
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView description;
        private TextView description2;
        private TextView data;
        private AppCompatImageView image;
        private CheckBox like;
        private TextView date;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            description2 = itemView.findViewById(R.id.description2);
            image = itemView.findViewById(R.id.imageView);
            like = itemView.findViewById(R.id.like);
            data = itemView.findViewById(R.id.data);
            date = itemView.findViewById(R.id.date);

            registerContextMenu(itemView);
            // Обработчик нажатий на картинке
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
            // Обработчик нажатий на картинке
            image.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    menuContextClickPosition = getAdapterPosition();
                    image.showContextMenu(0, 0); // FIXME
                    return true;
                }
            });
        }

        private void registerContextMenu(@NonNull View itemView) {
            if (fragment != null) {
                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        menuPosition = getLayoutPosition();
                        return false;
                    }
                });
                fragment.registerForContextMenu(itemView);
            }
        }

        public void setData(CardData cardData) {
            title.setText(cardData.getTitle());
            description.setText(cardData.getDescription());
            description2.setText(cardData.getDescription2());
            like.setChecked(cardData.isLike());
            image.setImageResource(cardData.getPicture());
            data.setText(cardData.getData());
            date.setText(cardData.getDate().toString());
            //      date.setText(new SimpleDateFormat("dd-MM-yy").format(cardData.getDate()));
        }
    }
}

