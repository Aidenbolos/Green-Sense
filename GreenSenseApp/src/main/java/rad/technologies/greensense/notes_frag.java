package rad.technologies.greensense;
//R.A.D. Technologies
//Ryan McAdie, Aiden Waadallah, Daniel Bujold

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class notes_frag extends Fragment {
    static ArrayList<String> notes = new ArrayList<>();
    static ArrayAdapter arrayAdapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_notes_frag, null);

        SharedPreferences sharedPreferences = Objects.requireNonNull(getActivity()).getApplicationContext().getSharedPreferences("rad.technologies.greensense", Context.MODE_PRIVATE);
        ListView listView = root.findViewById(R.id.listView);
        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("notes", null);

        if (set == null) {
            notes.add("Add Note");
        }else {
            notes = new ArrayList(set);
        }
        notes.add("Add Note");

        arrayAdapter = new ArrayAdapter (getActivity(),android.R.layout.simple_list_item_1, notes);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(getActivity().getBaseContext(), NoteEditorActivity.class);
            intent.putExtra("noteId", i);
            startActivity(intent);
        });

        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent it=new Intent(getActivity(), NoteEditorActivity.class);
            startActivity(it);
        });

        listView.setOnItemLongClickListener((parent, view, i, l) -> {

            final int itemToDelete = i;

            new AlertDialog.Builder(getActivity())
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle(getString(R.string.confirm))
                    .setMessage(getString(R.string.delNote))
                    .setPositiveButton(getString(R.string.yes), (dialogInterface, i1) -> {

                        notes.remove(itemToDelete);
                        arrayAdapter.notifyDataSetChanged();

                        SharedPreferences sharedPreferences1 = getActivity().getApplicationContext().getSharedPreferences("rad.technologies.greensense", Context.MODE_PRIVATE);

                        HashSet<String> set1 =new HashSet(notes_frag.notes);

                        sharedPreferences1.edit().putStringSet("notes", set1).apply();

                    })
                    .setNegativeButton(getString(R.string.no), null)
                    .show();

            return true;
        });
        return root;
    }
}