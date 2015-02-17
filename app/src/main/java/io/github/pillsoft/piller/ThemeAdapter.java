/*
This file is part of Piller.

Piller is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Piller is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Piller. If not, see <http://www.gnu.org/licenses/>.

Thanks to ishida for android-easy-lvl library
https://github.com/ishida/android-easy-lvl

Copyright 2015, Giulio Fagioli, Lorenzo Salani
*/
package io.github.pillsoft.piller;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder>{

    private List<Theme> action;
    private int rowLayout;
    private Context mContext;

    public ThemeAdapter(List<Theme> listTheme, int rowLayout, Context context) {
        this.action = listTheme;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Theme theme = action.get(i);
        viewHolder.cardThemeName.setText(theme.theme_name);
        viewHolder.card.setTag(theme.theme_name);
        viewHolder.motto.setText(theme.theme_motto);
        viewHolder.card.setCardBackgroundColor(Color.parseColor(theme.getTheme_color()));
    }

    @Override
    public int getItemCount() {
        return action == null ? 0 : action.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView cardThemeName;
        public TextView motto;
        public CardView card;
        public ViewHolder(View itemView) {
            super(itemView);
            cardThemeName = (TextView) itemView.findViewById(R.id.info_text);
            card=(CardView)itemView.findViewById(R.id.card_view);
            motto=(TextView)itemView.findViewById(R.id.motto);
        }

    }

}
