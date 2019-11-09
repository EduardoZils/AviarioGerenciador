package fag.edu.com.gerenciadordefichadeaviario.Util.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import fag.edu.com.gerenciadordefichadeaviario.R;
import fag.edu.com.gerenciadordefichadeaviario.models.Lote;

public class SelecaoAdapter extends BaseAdapter {
    LayoutInflater myInflater;
    List<Lote> loteList;

    public SelecaoAdapter(Context context, List<Lote> loteList) {
        this.loteList = loteList;
        myInflater = LayoutInflater.from(context); //Responsavel por inflar o layout
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Lote lote = loteList.get(position);
        view = myInflater.inflate(R.layout.support_simple_spinner_dropdown_item, null);
        ((TextView) view.findViewById(R.id.tv_nome)).setText(String.valueOf(lote.getCdLote()));
        ((TextView) view.findViewById(R.id.tv_cap)).setText(String.valueOf(lote.getQtAves()));
        ((TextView) view.findViewById(R.id.tv_dt_chegada)).setText(String.valueOf(lote.getDtChegada()));
        ((TextView) view.findViewById(R.id.tv_dt_entrega)).setText(String.valueOf(lote.getDtEntrega()));
        ((CheckBox) view.findViewById(R.id.cb_ativo)).setChecked(lote.isBlAtivo());

        return view;
    }
}
